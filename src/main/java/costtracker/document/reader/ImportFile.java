package costtracker.document.reader;

import java.util.HashMap;

public interface ImportFile {
	HashMap<String, String> getNextEntry();
	boolean hasNextEntry();
}
