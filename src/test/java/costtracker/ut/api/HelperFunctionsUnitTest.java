package costtracker.ut.api;

import costtracker.api.helperFunctions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelperFunctionsUnitTest {
    
    @Test
    public void checkRightURITest() throws URISyntaxException {
        final URI uriUnderTest = new URI("/func1");
        final String uriToCheck = "/func1";

        assertTrue(helperFunctions.checkURI(uriUnderTest, uriToCheck));
    }

    @Test
    public void checkFalseURITest() throws URISyntaxException {
        final URI uriUnderTest = new URI("/func1");
        final String uriToCheck = "/func2";

        assertFalse(helperFunctions.checkURI(uriUnderTest, uriToCheck));
    }
}
