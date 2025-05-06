package com.pamytniki.model;

public class Client {
    private String clientId;
    private String lastName;
    private String firstName;
    private String middleName;
    private String email;
    private long phoneNumber;

    public Client(String clientId, String lastName, String firstName,
                  String middleName, String email, long phoneNumber) {
        this.clientId = clientId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Геттеры
    public String getClientId() { return clientId; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getEmail() { return email; }
    public long getPhoneNumber() { return phoneNumber; }

    // Сеттеры
    public void setClientId(String clientId) { this.clientId = clientId; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(long phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + middleName;
    }
}
