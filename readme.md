# Reservation Management Service

Spring boot application that helps us view, create, modify, and delete, and view available slots. The following technologies and frameworks have been used

  - Java
  - Spring Boot
  - Spring Data JPA
  - H2 Database
  - Junit5
  - Maven
  

# Setup

  - Open terminal and clone the repository
  - cd to the project root
  - Run the following command: ``` mvn clean install ```
  - Run the project using any ide like intellij, sts or eclipse or use the following command: ``` mvn boot run```.
  - The project runs on the port 8080 by default.

# API documentation:
  - **GET /v1/availableSlots/{date} - Retrieves all available slots for a given date**
   Response
    ``` json
    [
        {
            "tableName": "table1",
            "availableDate": "2016-09-30",
            "availableTime": "5PM-7PM"
        },
        {
            "tableName": "table1",
            "availableDate": "2016-09-30",
            "availableTime": "3PM-5PM"
        },
        {
            "tableName": "table2",
            "availableDate": "2016-09-29",
            "availableTime": "3PM-5PM"
        }
    ]
    ```
    
- **GET /v1/reservations{date} - Retrieves all reservations for a given date.**
 Response
    ``` json
    [
        {
            "id":"04000000",
            "tableName": "table1",
            "reservationDate": "2016-09-30","reservationTime": "5PM-7PM",
            "name": "Ram",
            "contact": "040000"
        },
        {
            "id":"04000001",
            "tableName": "table2",
            "reservationDate": "2016-09-30",
            "reservationTime": "3PM-5PM",
            "name": "Anna",
            "contact": "040001"
        }
    ]
    ```
    
- **GET /v1/reservations/{id} -Retrieves a specific reservation based on ID**
  Response:
    ``` json
    {
        "id": "04000000",
        "name": "Ram Manohar",
        "contact": "04000000",
        "reservationDate": " 2016-09-30 ",
        "reservationTime": "5PM-7PM",
        "tableName": "table1"
    }
    ```
 - **POST /v1/reservations - Creates a new reservation**
    Request: 
     ``` json
   {
        "name": "Ram Manohar",
        "contact": "04000000",
        "reservationDate": " 2016-09-30 ",
        "reservationTime": "5PM-7PM",
        "tableName": "table1"
    }
     ```
     
     Response - Success: 
     ``` json 
    {
        "id": "04000000",
        "status" : "BOOKED"
    }
     ```
     
     Response - Unavailable: 
     ``` json 
    {
        "id": "0",
        "status" : "UNAVAILABLE"
    }
     ```
     
- **PUT /v1/reservations/{id} - Updates an existing reservation.**
    Request{
    ``` json
    {
        "id": "04000000",
        "name": "Ram Manohar",
        "contact": "04000000",
        "reservationDate": " 2016-09-30 ",
        "reservationTime": "5PM-7PM",
        "tableName": "table2"
    }
    ```
    Response - Successful
    ``` json
    {
        "id": "04000000",
        "status" : "BOOKED"
    }
    ```
    Response - Unavailable
    ``` json
    {
        "id": "0",
        "status" : "UNAVAILABLE"
    }
    ```

- **DELETE /v1/reservations/{id} - Removes a specific reservation based on ID**

    Response - Successful
    ``` json
    {
        "id": "0","status" : "UNRESERVED"
    }
    ```
