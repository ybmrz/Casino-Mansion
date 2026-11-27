package de.htwberlin.casino.banking.model.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    public static final int MAX_NAME_LENGTH = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    protected User() {
        // untuk JPA
    }

    public User(String firstName, String lastName) {
        this.firstName = validateName(firstName);
        this.lastName = validateName(lastName);
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Long getId() {
        return id;
    }

    private static String validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name must not be longer than " + MAX_NAME_LENGTH);
        }
        return name;
    }

    public void setFirstName(String firstName) {
        this.firstName = validateName(firstName);
    }
    public void setLastName(String lastName) {
        this.lastName = validateName(lastName);
    }

}