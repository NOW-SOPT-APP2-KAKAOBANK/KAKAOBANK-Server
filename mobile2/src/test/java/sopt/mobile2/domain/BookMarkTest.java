package sopt.mobile2.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookMarkTest {

    @Test
    public void testBookMarkCreation() {
        Account myAccount = new Account(); // Assume Account has a no-arg constructor
        Account markedAccount = new Account(); // Assume Account has a no-arg constructor

        BookMark bookMark = BookMark.builder()
                .myAccount(myAccount)
                .markedAccount(markedAccount)
                .build();

        assertNotNull(bookMark);
        assertEquals(myAccount, bookMark.getMyAccount());
        assertEquals(markedAccount, bookMark.getMarkedAccount());
    }
}
