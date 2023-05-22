package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static Integer idGenerator = 0;
    private Integer id;
    private String userName;
    private LocalDateTime birthDate;
    private String firstName;
    private String lastName;
    private List<TicketEvent> boughtTickets;

    public User(Integer id) {
        this.id = id;
    }

    public static void setIdGenerator(Integer generator) {
        idGenerator =  generator;
    }

    public static Integer getIdGenerator() {
        return idGenerator;
    }

    @Override
    public String toString() {
        return "User {\n" +
                "\tusername: " + userName + ',' +'\n' +
                "\tbirthDate: " + birthDate.toLocalDate() +  ',' +'\n' +
                "\tfirstName: " + firstName + ',' +'\n' +
                "\tlastName: " + lastName+ ',' +'\n' +
                "\tnumber of tickets bought:" + boughtTickets.size() + ',' +'\n' +
                '}';
    }

    public void addTicket(TicketEvent ticket) {
        boughtTickets.add(ticket);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<TicketEvent> getBoughtTickets() {
        return boughtTickets;
    }

    public void setBoughtTickets(List<TicketEvent> boughtTickets) {
        this.boughtTickets = boughtTickets;
    }

    public User(String userName, LocalDateTime birthDate, String firstName, String lastName) {
        this.id = ++idGenerator;
        this.userName = userName;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.boughtTickets = new ArrayList<>();
    }


    public User(Integer id, String userName, LocalDateTime birthDate, String firstName, String lastName) {
        this.id = id;
        this.userName = userName;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.boughtTickets = new ArrayList<>();
    }
}
