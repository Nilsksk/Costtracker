package costtracker.ut.api;

import costtracker.api.handlerHelperFunctions;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class HelperFunctionsUnitTest {
    
    @Test
    public void checkRightURITest() throws URISyntaxException {
        final URI uriUnderTest = new URI("/func1");
        final String uriToCheck = "/func1";

        assertTrue(handlerHelperFunctions.checkURI(uriUnderTest, uriToCheck));
    }

    @Test
    public void checkFalseURITest() throws URISyntaxException {
        final URI uriUnderTest = new URI("/func1");
        final String uriToCheck = "/func2";

        assertFalse(handlerHelperFunctions.checkURI(uriUnderTest, uriToCheck));
    }

    @Test
    public void getRequestBodyAsJsonTest() throws IOException {
        final String stringUnderTest = "{\"id\":\"1\"}";
        final JSONObject jsonObjectUnderTest = new JSONObject(stringUnderTest);
        InputStream inputStreamUnderTest = IOUtils.toInputStream("{\"id\":\"1\"}", "UTF-8");

        assertEquals(handlerHelperFunctions.getRequestBodyAsJson(inputStreamUnderTest).toString(), jsonObjectUnderTest.toString());
    }
}
