package de.htwberlin.casino.banking.model.user;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static final int MAX_NAME_LENGTH = 50;
    private final Random random = new Random();

    // Helper: make some valid random name
    private String randomValidName() {
        int length = 1 + random.nextInt(MAX_NAME_LENGTH);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(26)));
        }
        return sb.toString();
    }

    @RepeatedTest(100)
    void constructor_withValidNames_storesNames() {
        String firstName = randomValidName();
        String lastName = randomValidName();

        User user = new User(firstName, lastName);

        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
    }

}
