package costtracker.ut.api;

import org.junit.jupiter.api.Test;

import costtracker.plugin.api.Controller;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerUnitTest {

    @Test
    public void controllerInitializeTest() throws IOException {
        final Controller controller = new Controller("localhost", 3000, 1);

        assertEquals(controller.getPORT(), 3000);
        assertEquals(controller.getHOSTNAME(), "localhost");
        assertEquals(controller.getBACKLOG(), 1);
    }
}
