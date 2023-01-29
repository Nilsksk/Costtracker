package costtracker.api;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class functionHandler {
    private static final String HEADER_ALLOW = "Allow";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final int STATUS_OK = 200;
    private static final int STATUS_METHOD_NOT_ALLOWED = 405;
    private static final int NO_RESPONSE_LENGTH = -1;
    private static final String METHOD_GET = "GET";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String ALLOWED_METHODS = METHOD_GET + "," + METHOD_OPTIONS;
    private JSONObject jsonObject;

    public void handleGet(HttpServer server, String path) {


       server.createContext(path, he -> {
            try (he){
                final Headers headers = he.getResponseHeaders();
                final String requestMethod = he.getRequestMethod().toUpperCase();
                switch (requestMethod) {
                    case METHOD_GET -> {
                        if (he.getRequestURI().toString().equals("/func1")){
                            jsonObject = new JSONObject().put("number", 1)
                                    .put("object", new JSONObject().put("string", "Hello"))
                                    .put("boolean", true)
                                    .put("array", new JSONArray()
                                            .put(47.11).put("Hello again func1"));
                        } else {
                            jsonObject = new JSONObject().put("number", 1)
                                    .put("object", new JSONObject().put("string", "Hello"))
                                    .put("boolean", true)
                                    .put("array", new JSONArray()
                                            .put(47.11).put("Hello again other func"));
                        }
                        // do something with the request parameters
                        final String responseBody = jsonObject.toString();
                        headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
                        final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
                        he.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
                        he.getResponseBody().write(rawResponseBody);
                    }
                    case METHOD_OPTIONS -> {
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        he.sendResponseHeaders(STATUS_OK, NO_RESPONSE_LENGTH);
                    }
                    default -> {
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        he.sendResponseHeaders(STATUS_METHOD_NOT_ALLOWED, NO_RESPONSE_LENGTH);
                    }
                }
            }
        });
    }
}