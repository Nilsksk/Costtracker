package costtracker.api.routes.purchase;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import costtracker.api.enums.httpCodes;
import costtracker.api.enums.httpHeader;
import costtracker.api.helper.HandlerHelperFunctions;
import costtracker.api.interfaces.PostHandler;
import costtracker.buisnesslogic.PurchaseHandler;
import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;

import static costtracker.businessobjects.Category.fromJSONToCategory;
import static costtracker.businessobjects.Company.fromJSONToCompany;

public class CreatePurchase implements PostHandler {
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final int NO_RESPONSE_LENGTH = -1;

    @Override
    public void addPostHandler(HttpServer server, String path) {
        server.createContext(path, handler -> {
            try (handler) {
                final Headers headers = handler.getResponseHeaders();
                final String requestMethod = handler.getRequestMethod();

                JSONObject bodyData = HandlerHelperFunctions.getRequestBodyAsJson(handler.getRequestBody());
                int purchaseID = Integer.parseInt(bodyData.getString("id"));
                String purchaseName = bodyData.getString("name");
                String purchaseDescription = bodyData.getString("description");
                LocalDate purchaseDate = LocalDate.parse(bodyData.getString("date"));
                double purchasePrice = Double.parseDouble(bodyData.getString("price"));
                Company purchaseCompany = fromJSONToCompany(bodyData);
                Category purchaseCategory = fromJSONToCategory(bodyData);

                if (httpHeader.METHOD_POST.headerData.equals(requestMethod)) {
                    if (HandlerHelperFunctions.checkURI(handler.getRequestURI(), path)) {
                        try {
                            PurchaseHandler purchaseHandler = new PurchaseHandler();
                            boolean returnState = purchaseHandler.create(
                                    new Purchase(purchaseID, purchaseName, purchaseDescription, purchaseDate, purchasePrice, purchaseCompany, purchaseCategory));
                            final String responseBody = new JSONObject().put("state", returnState).toString();

                            // Set Headers and send Response to client
                            headers.set(httpHeader.HEADER_CONTENT_TYPE.headerData, String.format("application/json; charset=%s", CHARSET));
                            final byte[] responseBodyByte = responseBody.getBytes(CHARSET);
                            handler.sendResponseHeaders(httpCodes.STATUS_CREATED.code, responseBodyByte.length);
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
