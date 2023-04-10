## PROIECT PAO TALPALARIU IULIA-GEORGIANA 234
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

### Actiuni care pot fi facute asupra sistemului (dupa instantiere servicii in ordinea mentionata mai sus) :
 #### UserService:
 - *registerNewUser* -> creeaza un nou user si il adauga in repository
 - *getUserByUserName* -> returneaza un obiect de tip user din user repository dupa username, daca nu exista -> arunca eroare
 - *buyTicket* -> cumpara un bilet 

 #### TicketService:
 - *addTicket* -> adauga un ticket in repository
 - *getAvailableTicket* -> returneaza un bilet la evenimentul dorit, la categoria dorita, poate arunca eroare daca toate biletele sunt vandute

 #### LocationService:
  - *addLocation* -> adauga locatia in repository
  - *getLocations*

 #### EventService:
  - *addEvent* -> se adauga in repository Eventul dupa ce se valideaza in prealabil ca nu sunt mai multe bilete puse spre vanzare decat locuri in locatia evenimentului
  - *getEventsSorted* -> returneaza lista evenimentelor inregistrate in repository in ordinea datei si orei de incepere si la egaliatte alfabetic
  - *addDayEventsToMultipleDayEvent* -> primeste ca prim argument evenimentul de tip MultiDayEvent si -restul parametrilor- un numar variabil de evenimente de tip SingleDayEvent


