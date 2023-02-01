package costtracker.api;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class functionHandler {
    // Header Constants
    private static final String HEADER_ALLOW = "Allow";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final int NO_RESPONSE_LENGTH = -1;
    // Status Codes
    private static final int STATUS_OK = 200;
    private static final int STATUS_CREATED = 200;
    private static final int STATUS_BAD_REQUEST = 400;
    private static final int STATUS_METHOD_NOT_ALLOWED = 405;
    // Supported Methods
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static final String ALLOWED_METHODS = METHOD_GET + "," + METHOD_POST;
    // json Object (=Response Data)
    private JSONObject jsonObject;

    // Handler for Function 1
    public void handleFunc1(HttpServer server, String path) {
        System.out.println("Add handler handleFunc1 to Server");
        server.createContext(path, handler -> {
            try (handler) {
                final Headers headers = handler.getResponseHeaders();
                final String requestMethod = handler.getRequestMethod();

                // Check if request Method and URI matches
                if (METHOD_GET.equals(requestMethod)) {
                    if (helperFunctions.checkURI(handler.getRequestURI(), path)) {
                        jsonObject = new JSONObject().put("number", 1)
                                .put("object", new JSONObject()
                                        .put("string", "Hello"))
                                .put("boolean", true)
                                .put("array", new JSONArray()
                                        .put(47.11)
                                        .put("Hello again func1"));

                        // Map jsonObject to response Body
                        final String responseBody = jsonObject.toString();
                        // Set Headers
                        headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
                        final byte[] responseBodyByte = responseBody.getBytes(CHARSET);
                        // Send response to Client
                        handler.sendResponseHeaders(STATUS_OK, responseBodyByte.length);
                        handler.getResponseBody().write(responseBodyByte);
                    } else {
                        handler.sendResponseHeaders(STATUS_BAD_REQUEST, NO_RESPONSE_LENGTH);
                    }
                } else {
                    headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                    handler.sendResponseHeaders(STATUS_METHOD_NOT_ALLOWED, NO_RESPONSE_LENGTH);
                }
            }
        });
    }

    // Handler for Function 2
    public void handleFunc2(HttpServer server, String path) {
        System.out.println("Add handler handleFunc2 to Server");
        server.createContext(path, handler -> {
            try (handler) {
                final Headers headers = handler.getResponseHeaders();
                final String requestMethod = handler.getRequestMethod();

                // Check if request Method and URI matches
                if (METHOD_GET.equals(requestMethod)) {
                    if (helperFunctions.checkURI(handler.getRequestURI(), path)) {
                        jsonObject = new JSONObject().put("number", 2)
                                .put("object", new JSONObject()
                                        .put("string", "Hello"))
                                .put("boolean", true)
                                .put("array", new JSONArray()
                                        .put(47.11)
                                        .put("Hello again func2"));

                        // Map jsonObject to response Body
                        final String responseBody = jsonObject.toString();
                        // Set Headers
                        headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
                        final byte[] responseBodyByte = responseBody.getBytes(CHARSET);
                        // Send response to Client
                        handler.sendResponseHeaders(STATUS_OK, responseBodyByte.length);
                        handler.getResponseBody().write(responseBodyByte);
                    } else {
                        handler.sendResponseHeaders(STATUS_BAD_REQUEST, NO_RESPONSE_LENGTH);
                    }
                } else {
                    headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                    handler.sendResponseHeaders(STATUS_METHOD_NOT_ALLOWED, NO_RESPONSE_LENGTH);
                }
            }
        });
    }
}