import costtracker.api.Controller;

import java.io.IOException;

public class Main {
    // Start of my Application

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller("localhost", 8080, 1);
        controller.startServer();
        controller.addHandler();
    }
}
