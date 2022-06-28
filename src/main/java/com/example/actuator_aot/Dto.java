package com.example.actuator_aot;

public class Dto {
    private final String firstName;

    private final String lastName;

    public Dto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
