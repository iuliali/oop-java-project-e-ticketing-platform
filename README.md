## PROIECT PAO TALPALARIU IULIA-GEORGIANA 234
<img src="https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExYjk1NGZmZDRlNTVlMWY5OTI3NzAxMmYxMGZhOTljNmJlMzVjNWNmYiZjdD1n/8GjxwxtZWS29xlJnUH/giphy.gif" width="400" height="300" />

### ETAPELE 2 SI 3
Etapele 2 si 3 au fost dezvoltate pe branchurile :
- ```csv-persistence``` [link](../csv-persistence) 
-  ```db-persistence``` [link](../db-persistence)


### ETAPA 1

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
 - *showSoldTicketsByEvents*
 #### LocationService:
  - *addLocation* -> adauga locatia in repository
  - *getLocations*

 #### EventService:
  - *addEvent* -> se adauga in repository Eventul dupa ce se valideaza in prealabil ca nu sunt mai multe bilete puse spre vanzare decat locuri in locatia evenimentului
  - *getEventsSorted* -> returneaza lista evenimentelor inregistrate in repository in ordinea datei si orei de incepere si la egaliatte alfabetic
  - *addDayEventsToMultipleDayEvent* -> primeste ca prim argument evenimentul de tip MultiDayEvent si -restul parametrilor- un numar variabil de evenimente de tip SingleDayEvent
___

Cele 10 actiuni pentru ETAPA 1 (11 aprilie 2023):

- *corespund cu actiunile ilustrate si in main*

1. Adauga locatii in sistem (cate una pe rand cu ajutorul addLocation din locationService
    ``` 
    locationService.addLocation(berariaH);
    locationService.addLocation(salaPalatului);
    locationService.addLocation(areneleRomane);
    ```

2. Adauga evenimente in sistem
    ```
    eventService.addEvent(concertSimfonic);
    eventService.addEvent(concertPop); /// da eroare
    eventService.addEvent(ouOfOfficeFest);
    ```
3. Adauga evenimentelor cu mai multe zile zile de eveniment
    ````    
    eventService.addDayEventsToMultipleDayEvent(ouOfOfficeFest,firstDayFest,secondDayFest);
    ````
4. Obtine lista cu toate evenimentele din sistem sortata
    ```        
    System.out.println(eventService.getEventsSorted());
    ```

5. Obtine lista cu toate locatiile din sistem
    ``` 
    System.out.println(locationService.getLocations());
    ```

6. Inregistreaza noi useri in sistem
    ```
    userService.registerNewUser("georgiana99", "Iulia",
    "Antonescu",
    LocalDateTime.of(2000,5,7, 19, 0, 0));
    //erorr for next action ->
    userService.registerNewUser("georgiana99", "Georgiana",
    "Marinescu",
    LocalDateTime.of(2005,5,7, 19, 0, 0));
    userService.registerNewUser("andrei645", "Andrei",
    "Andries",
    LocalDateTime.of(1996,7,7, 19, 0, 0));
    ```
7. Obtine lista cu toti userii din sistem
    ```
    userService.getUsers();
    ```
8. Gaseste un user dupa username in sistem
    ```
     try {
                User user1= userService.getUserByUserName("georgiana99").orElseThrow(
                        () -> new UserNameNotFoundException(USERNAME_NOT_FOUND, "georgiana99")
                );
                System.out.println(user1);
                User user2 = userService.getUserByUserName("georgiana89").orElseThrow(
                        () -> new UserNameNotFoundException(USERNAME_NOT_FOUND, "georgiana89")
                );
                System.out.println(user2);
            } catch (UserNameNotFoundException exception) {
                System.out.println(exception.getMessage());
            }
    ```
9. Achizitioneaza un bilet la un eveniment (doar pentru userii inregistrati)
    ```
     TicketEvent ticket = userService.buyTicket("georgiana99",
                    concertSimfonic, TicketCategory.GENERAL_ENTRANCE).orElseThrow();
            System.out.println(ticket);
            TicketEvent ticket2 = userService.buyTicket("georgiana99",
                    ouOfOfficeFest, TicketCategory.PASS).orElseThrow();
            System.out.println(ticket2);
    ```
10. Afiseaza biletele vandute grupate pe eveniment
    ```
    ticketService.showSoldTicketsByEvents();
    ```



