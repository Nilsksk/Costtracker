package costtracker.api.routes.company;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import costtracker.api.enums.httpCodes;
import costtracker.api.enums.httpHeader;
import costtracker.api.helper.HandlerHelperFunctions;
import costtracker.api.interfaces.GetHandler;
import costtracker.buisnesslogic.CompanyHandler;
import costtracker.businessobjects.Company;
import org.json.JSONArray;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class GetDisabledCompanies implements GetHandler {
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final int NO_RESPONSE_LENGTH = -1;

    @Override
    public void addGetHandler(HttpServer server, String path) {
        server.createContext(path, handler -> {
            try (handler) {
                final Headers headers = handler.getResponseHeaders();
                final String requestMethod = handler.getRequestMethod();

                if (httpHeader.METHOD_GET.headerData.equals(requestMethod)) {
                    if (HandlerHelperFunctions.checkURI(handler.getRequestURI(), path)) {
                        try {
                            CompanyHandler companyHandler = new CompanyHandler();
                            List<Company> companyList = companyHandler.getDisabled();
                            String responseBody = new JSONArray(companyList).toString();

                            // Set Headers and send Response to client
                            headers.set(httpHeader.HEADER_CONTENT_TYPE.headerData, String.format("application/json; charset=%s", CHARSET));
                            final byte[] responseBodyByte = responseBody.getBytes(CHARSET);
                            handler.sendResponseHeaders(httpCodes.STATUS_OK.code, responseBodyByte.length);
                            handler.getResponseBody().write(responseBodyByte);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
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
