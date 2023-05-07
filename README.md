# Guest House Booking System
This is a website for a Guest House built in Java using Spring Boot for backend and Angular for frontend.
## Description:
This website is dedicated to users who want to spend a holiday or just a weekend in the beautiful area of Bucovina. It offers two houses for anyone who wants to come and visit this place and are in need of a place to sleep, relax, play some games or just enjoy the beatiful view. The two guest houses are in the same location, but independent from weach other.
The site offers the user ***photos*** for every room in each house, together with the available date for a ***reservation***. Everyone can leave a ***review*** (as a *user*) and also see all reviews left from others (other *users*), together with the ***ratings***.
This website wants to be as ***easy to use*** as possible.

## Functionalities:
The user can select which house he wants from the 2 available, and then the proper page will be displayed for him. An admin which want's to modify the site (add a new room, or change some details), must login with some special credentials. Each house will have specific details, different disponibility for a reservation, basically they work almost completely independent (except that they can have some common facilities).    
The ***user*** can also:
- view the photos for the selected house
- give a review for a house
- give stars for a house
- make a reservation by selecting an available period
- delete a reservation and introducing a message   
    
The ***admin*** can do the following operations:
- he must log-in first as an admin (entering in a special link and introducing the special username and password)
- add/delete a house
- add/delete a bedroom
- add/delete a bathroom
- add/delete a facility
- see all reviews
- see all stars
- see all reservations

Two different users will be able to access the site, and they are **the admin**, and **the normal user**, which will be later divided into **client** (for users who make a reservation - they are tagged as a "client" in the data base).

## API Requirements:
Every endpoint starts with "localhost:8080" and continues with the second part of the address. This application contains generic **CRUD** operations for the management of:     
### Houses  
| ENDPOINT | ROLE |
| ------ | ------ |
| "/api/v1/houses" | this address is responsible for house management. This endpoint is for ***create***, and ***read*** a house from the database. |
| "/api/v1/houses/id" | this is for ***update*** and ***delete***, where *id* is the *id* of the house that needs to be changed/deleted.|

### Bedrooms  
| ENDPOINT | ROLE |
| ------ | ------ |
| "/api/v1/bedrooms" | this address is responsible for bedroom management. This endpoint is for ***create***. It can also ***read all*** bedrooms from the database.|
| "/api/v1/bedrooms/id" | this is for ***update*** and ***delete***, where *id* is the *id* of the bedroom that needs to be changed/deleted. This endpoint can also be used for ***reading*** only the bedrooms from a specific house, specified with the *id* from the end of the url.|

### Bathrooms  
| ENDPOINT | ROLE |
| ------ | ------ |
| "/api/v1/bathrooms" | this address is responsible for bathroom management. This endpoint is for ***create***, and ***read*** a bathroom from the database. It can also ***read all*** bathrooms from the database.|
| "/api/v1/bathrooms/id" | this is for ***update*** and ***delete***, where *id* is the *id* of the bathroom that needs to be changed/deleted. This endpoint can also be used for ***reading*** only the bathrooms from a specific house, specified with the *id* from the end of the url.|

### Facilities
| ENDPOINT | ROLE |
| ------ | ------ |
| "/api/v1/facilities" | this address is responsible for the management of facilities. At this endpoint, the request can ***create*** or ***read*** from the database. |
| "/api/v1/facilities/id" | specific for ***update*** and ***delete*** a facility from the database.|

### Reservations 
| ENDPOINT | ROLE |
| ------ | ------ |
| "/api/v1/reservations" | address used to ***create*** and ***read*** reservations from the database. |
| "/api/v1/reservations/id" | specific for ***update*** and ***delete*** a reservation from the database.|

### Reviews 
| ENDPOINT | ROLE |
| ------ | ------ |
| "/api/v1/reviews" | address used to controll the reviews added by some users. This endpoint can also ***read*** all the reviews from the database. |
| "/api/v1/reviews/id" | specific for ***update*** and ***delete*** a review from the database. The review modified is specified using the *id* at the end.|

### User 
| ENDPOINT | ROLE |
| ------ | ------ |
| "/api/v1/users" | the users are manipulated at this endpoint. They can be only ***created*** or ***read***, without updating. |
| "/api/v1/users/id" | specific for ***deleting*** a user from the database.|

### Client 
| ENDPOINT | ROLE |
| ------ | ------ |
| "/api/v1/clients" | every client which makes a reservation is added in the database through this endpoint. With this endpoint, a client can only be ***created*** or ***read***.|
| "/api/v1/clients/id" | specific for ***update*** and ***delete*** a client from the database.|

### Admin 
| ENDPOINT | ROLE |
| ------ | ------ |
| "/api/v1/admins" | An admin can be ***created*** using this endpoint. This endpoint can also be used for ***reading*** all admins from the database. |
| "/api/v1/admins/id" | An admin can be ***deleted*** or ***updated*** using this endpoint. The admin modified is specified using the *id* from the end of the url.|

## Design Patterns
- **Observer Pattern**:     
This design pattern was used for notifying every admin registered in database in case of a change in *reservations* table. The **ReservationService** implements the interface called **ReservationObservable** which has only one method ("*notifyAdmins(changedReservation, oldReservation, notificationType"*), used for notifying all admins. On the other hand, **Admin** implements the interface called **AdminObserver**, which has also one method called **update**. This way, every time a client creates a new reservation, deletes a reservation or just updates an existing one, every admin from the database will be notified via an email, specifying what happend through a specific message (if someone created a new reservation, if someone deleted one or an existing reservation was modified).    

## Data Model:
The relational database is built using **[PostgreSQL]**.   
*Database Diagram*:    

![UML](https://user-images.githubusercontent.com/101935675/236671893-7ae285ef-8d36-4848-94cf-d842076c13e3.png)


[PostgreSQL]: <http://postgresql.org>
