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
Generic **CRUD** operations for the management of rooms, bathrooms, facilities and others.
There will be more specific API's built along the way of developing this application and they will be added here.

## Data Model:
The relational database is built using **[PostgreSQL]**.   
*Database Diagram*:    

![Database Diagram](https://www.planttext.com/api/plantuml/svg/hLRBRjim4BphAxOfKDX0WpG5KWI2OEJKIw-z2vIuiHQcI6D9lrBdtqizB5csPDbfTJ3iNGrdPZaa_6ATOBVALH1yalXA6i69HE5aEmNKZjmEjX15CFdz5JOPEPp2RDQykcJA1U_Y1UuAG9JCejGem-7dvFdvnwF_-SMtPj53MPt1Ddf8MlIhOQIvRj0rL9JF6JIm2z9BmI87vT5kjqGmx39pr62pqZ8rh97XyLiGL5Q4p4bFuKy0qE4a6QyiyZG4qWxd7XD5FtzvU_wzfR1sOrY6i1QSPe8dtv-UfcNdo3F95XoyrtI9S1aRazj-oinuqbxEd9WDspZrBboxhk_Op6oGGqYCKIXqKIfOt2hfr2ozuRbWYKwGikUDJNg2rejEOTmfoWyPrQiyAUmSEVHBzHbtwxvg5PJhZOuQIHNvi0_u6Cm8vfwkXoqp6kE3nzEka9BHVaJagqX9aIEyUTurSXThaTyIa759HgaYcVzhScDyfSK3sU0abKc58wFFE_t-FMtgoun2zQf8QEw5D28OruIRspVXojcK5t9VwYNQb6dPxsnqT8nloEjoBlyglJejm-9ZAPoVP_5PRhQM9vLHiSLm3YgBxuFxDhHCKDxvS7ZUz_Vt-tsvb6sA8TpL-n2Il0kQQ_iO0LNYEeYoL47g-m-kXzGtJLTNLzY9hlRONqtI7CkhIUfCdyYwID8FkKpIHh15T2PI8QhI1Sn5OIy-SFuFpLy0)

![GuestHouse](https://user-images.githubusercontent.com/101935675/226185466-4733cdb5-f083-415d-a8f7-b59e65a620a3.png)


[PostgreSQL]: <http://postgresql.org>
