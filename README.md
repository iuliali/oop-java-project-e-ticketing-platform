# oop-java-project-e-ticketing-platform
<img src="https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExYjk1NGZmZDRlNTVlMWY5OTI3NzAxMmYxMGZhOTljNmJlMzVjNWNmYiZjdD1n/8GjxwxtZWS29xlJnUH/giphy.gif" width="400" height="300" />

### Tipuri de obiecte: 
 - Location
 - Event (Event - abstract class, also implements Comparable interface) -> SingleDayEvent, MultiDayEvent
 - TicketEvent -> TicketSeatedEvent,Pass
 - User
 ____

### Services care trebuie instantiate (in ordinea asta):
  - LocationSevice
  - EventService 
  - TicketService 
  - UserService 

 **Repositories** :
 - se instantiaza automat la instantierea Service-ului corespunzator (ex: LocationRepository se instantiaza cand se apeleaza constructorul pt LocationService etc.)
 - ele au un vector/ map care *simuleaza* baza de date
___

### Actiuni care pot fi facute asupra sistemului (dupa instantiere servicii in ordinea metionata mai sus) :
 #### UserService:
 - *registerNewUser* -> creeaza un nou user si il adauga in repository
 - **
 - *buyTicket* -> cumpara un bilet 

 #### TicketService:

 #### LocationService:
  - *addLocation* -> adauga locatia in repository
  - 
 #### EventService:
  - *addEvent* -> se adauga in repository Eventul dupa ce se valideaza in prealabil ca nu sunt mai multe bilete puse spre vanzare decat locuri in locatia evenimentului
  - *getEventsSorted* -> returneaza lista evenimentelor inregistrate in repository in ordinea datei si orei de incepere si la egaliatte alfabetic


