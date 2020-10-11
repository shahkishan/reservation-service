package com.restaurant.tablebookingservice.service.impl;

import com.restaurant.tablebookingservice.dto.*;
import com.restaurant.tablebookingservice.dto.exceptions.ReservationException;
import com.restaurant.tablebookingservice.entity.Reservation;
import com.restaurant.tablebookingservice.entity.Slot;
import com.restaurant.tablebookingservice.entity.Table;
import com.restaurant.tablebookingservice.repo.ReservationsRepo;
import com.restaurant.tablebookingservice.repo.SlotsRepo;
import com.restaurant.tablebookingservice.repo.TablesRepo;
import com.restaurant.tablebookingservice.service.Mapper;
import com.restaurant.tablebookingservice.service.SlotsCache;
import com.restaurant.tablebookingservice.service.TablesCache;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationsRepo reservationsRepo;
    @Mock
    private SlotsRepo slotsRepo;
    @Mock
    private TablesRepo tablesRepo;

    @InjectMocks
    private SlotsCache slotsCache;

    @InjectMocks private TablesCache tablesCache;

    @InjectMocks private Mapper mapper;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(slotsCache, "slotsRepo", slotsRepo);
        ReflectionTestUtils.setField(tablesCache, "tablesRepo", tablesRepo);
        ReflectionTestUtils.setField(mapper, "tablesCache", tablesCache);
        ReflectionTestUtils.setField(mapper, "slotsCache", slotsCache);
        ReflectionTestUtils.setField(reservationService, "mapper", mapper);


        when(slotsRepo.findAll())
                .thenReturn(List.of(new Slot(1, "11AM-1PM"),
                        new Slot(2, "1PM-3PM"),
                        new Slot(3, "3M-5PM")));

        when(tablesRepo.findAll())
                .thenReturn(List.of(new Table(1, "table1"), new Table(2, "table2")));

        when(reservationsRepo.findAllByReservationDate(any()))
                .thenReturn(List.of(
                        new Reservation("1",
                            "Davis",
                            "123456",
                            LocalDate.parse("2020-10-10"),
                            new Slot(3, "3PM-5PM"),
                            new Table(2, "table2")),
                        new Reservation("1",
                                "Scott",
                                "12345678",
                                LocalDate.parse("2020-10-10"),
                                new Slot(2, "1PM-3PM"),
                                new Table(1, "table1"))));
    }

    @Test
    void getAvailableSlots() {
        List<AvailableSlotsResponse> availableSlots = reservationService.getAvailableSlots(LocalDate.parse("2020-10-10"));
        boolean hasExistingSlot = availableSlots.stream().anyMatch(s -> (3 == s.getSlotId() && 2 == s.getTableId())
                || (2 == s.getSlotId() && 1 == s.getTableId()));

        assertFalse(hasExistingSlot);
    }

    @Test
    void getActiveBookings() {
        List<ActiveReservationsResponse> activeBookings = reservationService.getActiveBookings(LocalDate.parse("2020-10-10"));
        assertEquals(LocalDate.parse("2020-10-10"), activeBookings.get(0).getReservationDate());
        assertAll(() -> assertEquals(LocalDate.parse("2020-10-10"), activeBookings.get(0).getReservationDate()),
                () -> assertEquals(LocalDate.parse("2020-10-10"), activeBookings.get(1).getReservationDate()),
                () -> assertEquals("Davis", activeBookings.get(0).getName()),
                () -> assertEquals("Scott", activeBookings.get(1).getName()),
                () -> assertEquals("table2", activeBookings.get(0).getTableName()),
                () -> assertEquals("table1", activeBookings.get(1).getTableName()),
                () -> assertEquals("3PM-5PM", activeBookings.get(0).getReservationTime()),
                () -> assertEquals("1PM-3PM", activeBookings.get(1).getReservationTime()));
    }

    @Test
    void getActiveReservation() {
        when(reservationsRepo.findById(anyString()))
                .thenReturn(Optional.of(new Reservation("1",
                        "Davis",
                        "123456",
                        LocalDate.parse("2020-10-10"),
                        new Slot(3, "3PM-5PM"),
                        new Table(2, "table2"))));

        ActiveReservationsResponse activeReservation = reservationService.getActiveReservation("1");
        assertAll(() -> assertEquals("Davis", activeReservation.getName()),
                () -> assertEquals(LocalDate.parse("2020-10-10"), activeReservation.getReservationDate()),
                () -> assertEquals("3PM-5PM", activeReservation.getReservationTime()),
                () -> assertEquals("table2", activeReservation.getTableName()));
    }

    @Test
    void getActiveReservation_notFound() {
        when(reservationsRepo.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(ReservationException.class, () -> reservationService.getActiveReservation("1"));
    }

    @Test
    void createReservation() {
        Reservation r = new Reservation();
        r.setId("123");
        r.setName("John");
        when(reservationsRepo.save(any()))
                .thenReturn(r);

        ReservationResponse reservation = reservationService.createReservation(new CreateReservationRequest("John",
                "1234",
                LocalDate.parse("2020-10-10"),
                "11AM-1PM",
                "table1"));
        verify(reservationsRepo, times(1)).save(any());
        assertEquals("BOOKED", reservation.getStatus());
    }

    @Test
    void createReservation_conflict() {

        when(reservationsRepo.save(any()))
                .thenThrow(new ConstraintViolationException("Error", new SQLException("constraint violation"), null));

        ReservationResponse reservation = reservationService.createReservation(new CreateReservationRequest("John",
                "1234",
                LocalDate.parse("2020-10-10"),
                "11AM-1PM",
                "table1"));
        verify(reservationsRepo, times(1)).save(any());
        assertEquals("UNAVAILABLE", reservation.getStatus());
    }

    @Test
    void updateReservation() {
        Reservation r = new Reservation();
        r.setId("123");
        r.setName("John");
        when(reservationsRepo.save(any()))
                .thenReturn(r);

        ReservationResponse reservation = reservationService.updateReservation(new UpdateReservationRequest("123",
                "John",
                "1234",
                LocalDate.parse("2020-10-10"),
                "11AM-1PM",
                "table1"));
        verify(reservationsRepo, times(1)).save(any());
        assertEquals("BOOKED", reservation.getStatus());
    }

    @Test
    void updateReservation_conflict() {

        when(reservationsRepo.save(any()))
                .thenThrow(new ConstraintViolationException("Error", new SQLException("constraint violation"), null));

        ReservationResponse reservation = reservationService.updateReservation(new UpdateReservationRequest("123",
                "John",
                "1234",
                LocalDate.parse("2020-10-10"),
                "11AM-1PM",
                "table1"));

        verify(reservationsRepo, times(1)).save(any());
        assertEquals("UNAVAILABLE", reservation.getStatus());
    }

    @Test
    void deleteReservation_notFound() {
        doThrow(new EmptyResultDataAccessException(1)).when(reservationsRepo).deleteById(anyString());
        ReservationResponse reservationResponse = reservationService.deleteReservation("123");
        verify(reservationsRepo, times(1)).deleteById(anyString());
        assertEquals("0",reservationResponse.getId());
        assertEquals("NO_RECORD_FOUND",reservationResponse.getStatus());
    }

    @Test
    void deleteReservation_success() {
        doNothing().when(reservationsRepo).deleteById(anyString());
        ReservationResponse reservationResponse = reservationService.deleteReservation("123");
        verify(reservationsRepo, times(1)).deleteById(anyString());
        assertEquals("0",reservationResponse.getId());
        assertEquals("UNRESERVED",reservationResponse.getStatus());
    }


}