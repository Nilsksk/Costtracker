package test.ui;

import costtracker.api.Api;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyFirstJUnitJupiterTest {

    @Test
    public void testMyTest() {
        assertEquals(new Api().Test(), "Test");
    }
}

