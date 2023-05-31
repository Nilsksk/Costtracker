package costtracker.plugin.api.routes.company;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import costtracker.application.handlers.CompanyHandler;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.plugin.api.enums.httpCodes;
import costtracker.plugin.api.enums.httpHeader;
import costtracker.plugin.api.helper.HandlerHelperFunctions;
import costtracker.plugin.api.interfaces.PutHandler;

import org.json.JSONObject;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class UpdateCompany implements PutHandler {
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final int NO_RESPONSE_LENGTH = -1;

    @Override
    public void addPutHandler(HttpServer server, String path) {
        server.createContext(path, handler -> {
            try (handler) {
                final Headers headers = handler.getResponseHeaders();
                final String requestMethod = handler.getRequestMethod();

                JSONObject bodyData = HandlerHelperFunctions.getRequestBodyAsJson(handler.getRequestBody());
                int companyId = Integer.parseInt(bodyData.getString("id"));
                String companyName = bodyData.getString("name");
                String companyLocation = bodyData.getString("location");

                if (httpHeader.METHOD_PUT.headerData.equals(requestMethod)) {
                    if (HandlerHelperFunctions.checkURI(handler.getRequestURI(), path)) {
                            CompanyHandler companyHandler = new CompanyHandler();
                            boolean returnState = companyHandler.update(Company.CompanyBuilder.withName(companyName).withLocation(companyLocation).withId(companyId).build());
                            final String responseBody = new JSONObject().put("state", returnState).toString();

                            // Set Headers and send Response to client
                            headers.set(httpHeader.HEADER_CONTENT_TYPE.headerData, String.format("application/json; charset=%s", CHARSET));
                            final byte[] responseBodyByte = responseBody.getBytes(CHARSET);
                            handler.sendResponseHeaders(httpCodes.STATUS_OK.code, responseBodyByte.length);
                            handler.getResponseBody().write(responseBodyByte);
                    } else {
                        handler.sendResponseHeaders(httpCodes.STATUS_BAD_REQUEST.code, NO_RESPONSE_LENGTH);
                    }
                } else {
                    headers.set(httpHeader.HEADER_ALLOW.headerData, httpHeader.ALLOWED_METHODS.headerData);
                    handler.sendResponseHeaders(httpCodes.STATUS_METHOD_NOT_ALLOWED.code, NO_RESPONSE_LENGTH);
                }
            } catch (IncorrectEntryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }
}
