package Program;

import java.sql.SQLException;

import costtracker.ui.Dialogue;

public class ProgramStart {

	public static void main(String[] args) throws SQLException {
		
		Dialogue dialogue = new Dialogue();
		dialogue.talk();
	}
}
