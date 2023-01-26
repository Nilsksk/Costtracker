package api;

import costtracker.api.Api;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyFirstJUnitJupiterTests {

    @Test
    void testMyTest() {
        assertEquals(new Api().Test(), "Test");
    }
}

