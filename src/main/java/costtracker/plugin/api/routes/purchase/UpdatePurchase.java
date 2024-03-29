package costtracker.plugin.api.routes.purchase;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

import costtracker.application.handlers.PurchaseHandler;
import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.domain.businessobjects.Purchase;
import costtracker.plugin.api.enums.httpCodes;
import costtracker.plugin.api.enums.httpHeader;
import costtracker.plugin.api.helper.HandlerHelperFunctions;
import costtracker.plugin.api.interfaces.PutHandler;

import org.json.JSONObject;

import static costtracker.domain.businessobjects.Category.fromJSONToCategory;
import static costtracker.domain.businessobjects.Company.fromJSONToCompany;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class UpdatePurchase implements PutHandler {
	private static final Charset CHARSET = StandardCharsets.UTF_8;
	private static final int NO_RESPONSE_LENGTH = -1;

	@Override
	public void addPutHandler(HttpServer server, String path) {
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

				if (httpHeader.METHOD_PUT.headerData.equals(requestMethod)) {
					if (HandlerHelperFunctions.checkURI(handler.getRequestURI(), path)) {
						PurchaseHandler purchaseHandler = new PurchaseHandler();
						boolean returnState = purchaseHandler.update(
								Purchase.PurchaseBuilder.withValues(purchaseName, purchaseDate, purchasePrice).withCategory(purchaseCategory).withCompany(purchaseCompany).withDescription(purchaseDescription).withId(purchaseID).build());
						final String responseBody = new JSONObject().put("state", returnState).toString();

						// Set Headers and send Response to client
						headers.set(httpHeader.HEADER_CONTENT_TYPE.headerData,
								String.format("application/json; charset=%s", CHARSET));
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
