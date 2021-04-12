package com.missouristate.penrose.friendlistex2;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public Person(int id, String firstName, String lastName, String emailAddress) {
        setID(id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmailAddress(emailAddress);
    }

    private void setID(int newId) {
        this.id = newId;
    }

    private void setFirstName(String newName) {
        this.firstName = newName;
    }

    private void setLastName(String newName) {
        this.lastName = newName;
    }

    private void setEmailAddress(String newEmailAddress) {
        this.emailAddress = newEmailAddress;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}