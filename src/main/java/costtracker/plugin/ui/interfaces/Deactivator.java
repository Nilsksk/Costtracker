package costtracker.plugin.ui.interfaces;

import java.sql.SQLException;

public interface Deactivator {

	void deactivate() throws SQLException;
}
