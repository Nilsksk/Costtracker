import costtracker.api.Controller;
import costtracker.ui.Dialogue;
import costtracker.ui.History;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    // Start of my Application

    public static void main(String[] args) throws IOException, SQLException {
        Controller controller = new Controller("localhost", 8080, 1);
        controller.startServer();
        controller.addHandler();
        
		Dialogue dialogue = new Dialogue();
        dialogue.talk();
    }
}
