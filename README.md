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



[PostgreSQL]: <http://postgresql.org>
