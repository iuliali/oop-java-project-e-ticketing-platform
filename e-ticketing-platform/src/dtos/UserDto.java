package dtos;

import java.time.LocalDateTime;

public class UserDto {
    private String userName;
    private LocalDateTime birthDate;
    private String firstName;
    private String lastName;

    public UserDto(String userName, LocalDateTime birthDate, String firstName, String lastName) {
        this.userName = userName;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
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
}
