package test.csv;

import costtracker.api.Api;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyFirstJUnitJupiterTests {

    @Test
    public void testMyTest() {
        assertEquals(new Api().Test(), "Test");
    }
}

