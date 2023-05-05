# Guest House Booking System
This is a website for a Guest House built in Java using Spring Boot for backend and Angular for frontend.
## Description:
This website is dedicated to users who want to spend a holiday or just a weekend in the beautiful area of Bucovina. It offers two houses for anyone who wants to come and visit this place and are in need of a place to sleep, relax, play some games or just enjoy the beatiful view. The two guest houses are in the same location, but independent from weach other.
The site offers the user ***photos*** for every room in each house, together with the available date for a ***reservation***. Everyone can leave a ***review*** and also see all reviews left from others, together with the ***ratings***.
This website wants to be as ***easy to use*** as possible.

## Functionalities:
The user can select which house he wants from the 2 available, and then the proper page will be displayed for him. Each house will have specific details, different disponibility for a reservation, basically they work almost completely independent (except that they can have some common facilities).    
The ***user*** can also:
- view the photos for the selected house
- give a review for a house
- make a reservation by selecting an available period
- delete a reservation and introducing a message
- follow a specific house and receiving eventual news from it    
    
The ***admin*** can do the following operations:
- he must log-in first as an admin (entering in a special link and introducing the special username and password)
- add/delete a house
- add/delete a room
- add/delete a bathroom
- add/delete a facility

Two different users will be able to access the site, and they are **the admin**, and **the normal user**, which will be later divided into **follower** (for users who wants to be notified when the house will be free for a reservation) or **client** (for users who make a reservation - they are tagged as a "client" in the data base).

## API Requirements:
Every endpoint starts with "localhost:8080" and continues with the second part of the address. This application contains generic **CRUD** operations for the management of:     
### Houses   
- "/api/v1/houses" -> this address is responsible for house management. These endpoints are for create, read, update and delete a house from the database     
    "/api/v1/houses/id" -> this is for update and delete, where *id* is the *id* of the house that needs to be changed
### Facilities 
- "/api/v1/facilities" -> this address is responsible for the management of facilities. It consists of create, read, update and delete    
    "/api/v1/facilities/id" -> specific for update and delete
### Reservations 
- "/api/v1/reservations" -> address used to create, read, update and delete reservations from the database.    
    "/api/v1/reservations/id" -> specific for update and delete
### Reviews 
- "/api/v1/reviews" -> address used to controll the reviews added by some users. It consists of create, delete
    "/api/v1/reviews/id" -> specific for update and delete
### User 
- "/api/v1/users" -> the users are manipulated at this endpoint. They can be only created, deleted, or read, without updating.
### Client 
- "/api/v1/clients" -> every client which makes a reservation is added in the database through this endpoint. For the moment, a client can only be created or read, but they will get more options soon
### Admin 
- "/api/v1/admins" -> this is the endpoint for the admins. An admin can be created, deleted or read, nothing more.     

## Design Pattern
- **Observer Pattern**:     
This design pattern was used for notifying every admin registered in database in case of a change in *reservations* table. The **ReservationService** implements the interface called **ReservationObservable** which has only one method, used for notifying all admins. On the other hand, **Admin** implements the interface called **AdminObserver**, which has also one method called **update**. This way, every time a client creates a new reservation, deletes a reservation or just updates an existing one, every admin from the database will be notified via an email, specifying what happend through a specific message (if someone created a new reservation, if someone deleted one or an existing reservation was modified).    

## Data Model:
The relational database is built using **[PostgreSQL]**.   
*Database Diagram*:    

![public](https://user-images.githubusercontent.com/101935675/229435197-f7a9506f-7972-40cd-aef2-259080b5b5b6.png)


[PostgreSQL]: <http://postgresql.org>
