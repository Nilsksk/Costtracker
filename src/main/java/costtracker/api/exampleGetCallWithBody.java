package costtracker.api;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class exampleGetCallWithBody implements IgetHandler {
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final int NO_RESPONSE_LENGTH = -1;
    // json Object (=Response Data)
    private JSONObject jsonObject;

    @Override
    public void addGetHandler(HttpServer server, String path) {
        server.createContext(path, handler -> {
            try (handler) {
                final Headers headers = handler.getResponseHeaders();
                final String requestMethod = handler.getRequestMethod();

                JSONObject getBody = handlerHelperFunctions.getRequestBodyAsJson(handler.getRequestBody());
                System.out.println(getBody.getString("id"));

                // Check if request Method and URI matches
                if (httpHeader.METHOD_GET.headerData.equals(requestMethod)) {
                    if (handlerHelperFunctions.checkURI(handler.getRequestURI(), path)) {
                        jsonObject = new JSONObject().put("number", 2)
                                .put("object", new JSONObject()
                                        .put("string", "Hello"))
                                .put("boolean", true)
                                .put("array", new JSONArray()
                                        .put(47.11)
                                        .put("Hello again func1"));

                        // Map jsonObject to response Body
                        final String responseBody = jsonObject.toString();
                        // Set Headers
                        headers.set(httpHeader.HEADER_CONTENT_TYPE.headerData, String.format("application/json; charset=%s", CHARSET));
                        final byte[] responseBodyByte = responseBody.getBytes(CHARSET);
                        // Send response to Client
                        handler.sendResponseHeaders(httpCodes.STATUS_OK.code, responseBodyByte.length);
                        handler.getResponseBody().write(responseBodyByte);
                    } else {
                        handler.sendResponseHeaders(httpCodes.STATUS_BAD_REQUEST.code, NO_RESPONSE_LENGTH);
                    }
                } else {
                    headers.set(httpHeader.HEADER_ALLOW.headerData, httpHeader.ALLOWED_METHODS.headerData);
                    handler.sendResponseHeaders(httpCodes.STATUS_METHOD_NOT_ALLOWED.code, NO_RESPONSE_LENGTH);
                }
            }
        });
    }

}
