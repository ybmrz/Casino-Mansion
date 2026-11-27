package de.htwberlin.casino.banking.model.user;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final Random random = new Random();

    // Helper: make some valid random name
    private String randomValidName() {
        int length = 1 + random.nextInt(User.MAX_NAME_LENGTH);
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

    @Test
    void constructor_withNullFirstName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new User(null, randomValidName()));
    }

    @Test
    void constructor_withEmptyFirstName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new User("", randomValidName()));
    }

    @Test
    void constructor_withEmptyLastName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new User(randomValidName(), ""));
    }

    @Test
    void constructor_withBlankFirstName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new User("   ", randomValidName()));
    }

    @Test
    void constructor_withNullLastName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new User(randomValidName(), null));
    }

    @Test
    void constructor_withBlankLastName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new User(randomValidName(), "   "));
    }

    @Test
    void constructor_withNameOfMaxLength_succeeds() {
        String firstName = "a".repeat(User.MAX_NAME_LENGTH);
        String lastName = "b".repeat(User.MAX_NAME_LENGTH);

        assertDoesNotThrow(() -> new User(firstName, lastName));
    }

    @Test
    void constructor_withTooLongFirstName_throwsException() {
        String firstName = "a".repeat(User.MAX_NAME_LENGTH + 1);

        assertThrows(IllegalArgumentException.class, () -> new User(firstName, randomValidName()));
    }

    @Test
    void constructor_withTooLongLastName_throwsException() {
        String lastName = "b".repeat(User.MAX_NAME_LENGTH + 1);

        assertThrows(IllegalArgumentException.class, () -> new User(randomValidName(), lastName));
    }

    @Test
    void setFirstName_withNull_throwsAndKeepsOldValue() {
        User user = new User(randomValidName(), randomValidName());
        String oldFirstName = user.getFirstName();

        assertThrows(IllegalArgumentException.class, () -> user.setFirstName(null));
        assertEquals(oldFirstName, user.getFirstName());
    }

    @Test
    void setFirstName_withValidName_updatesName() {
        User user = new User(randomValidName(), randomValidName());
        String newFirstName = randomValidName();

        user.setFirstName(newFirstName);
        assertEquals(newFirstName, user.getFirstName());
    }

    @Test
    void setLastName_withNull_throwsAndKeepsOldValue() {
        User user = new User(randomValidName(), randomValidName());
        String oldLastName = user.getLastName();

        assertThrows(IllegalArgumentException.class, () -> user.setLastName(null));
        assertEquals(oldLastName, user.getLastName());
    }

    @Test
    void setLastName_withValidName_updatesName() {
        User user = new User(randomValidName(), randomValidName());
        String newLastName = randomValidName();

        user.setLastName(newLastName);
        assertEquals(newLastName, user.getLastName());
    }

}
