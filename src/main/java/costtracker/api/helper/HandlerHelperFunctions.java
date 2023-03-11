package costtracker.api.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

public class HandlerHelperFunctions {
    public static boolean checkURI(URI uri, String uriToCheck) {
        return uri.toString().equals(uriToCheck);
    }

    public static JSONObject getRequestBodyAsJson(InputStream requestBody) throws IOException {
        InputStreamReader isr = new InputStreamReader(requestBody, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);

        int b = 0;
        StringBuilder buf = new StringBuilder();
        while (true) {
            try {
                if ((b = br.read()) == -1) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            buf.append((char) b);
        }
        br.close();
        isr.close();

        return new JSONObject(buf.toString());
    }
}
