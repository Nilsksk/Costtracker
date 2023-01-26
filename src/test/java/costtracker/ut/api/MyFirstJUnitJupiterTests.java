package costtracker.ut.api;

import costtracker.api.Api;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyFirstJUnitJupiterTests {

    @Test
    void testMyTest() {
        Assertions.assertEquals(new Api().Test(), "Test");
    }
}

