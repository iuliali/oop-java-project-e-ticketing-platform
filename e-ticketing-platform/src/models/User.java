package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private LocalDateTime birthDate;
    private String firstName;
    private String lastName;
    private List<TicketEvent> boughtTickets;

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
        this.userName = userName;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.boughtTickets = new ArrayList<>();
    }
}
