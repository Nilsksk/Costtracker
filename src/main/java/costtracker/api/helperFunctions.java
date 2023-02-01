package costtracker.api;

import java.net.URI;

public class helperFunctions {
    public static boolean checkURI(URI uri, String uriToCheck) {
        return uri.toString().equals(uriToCheck);
    }
}
