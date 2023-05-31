package costtracker.application.out;

import java.util.HashMap;

public interface ImportFile {
	HashMap<String, String> getNextEntry();
	boolean hasNextEntry();
}
