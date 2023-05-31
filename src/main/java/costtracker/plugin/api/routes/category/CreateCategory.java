package costtracker.plugin.api.routes.category;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

import costtracker.application.handlers.CategoryHandler;
import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.plugin.api.enums.httpCodes;
import costtracker.plugin.api.enums.httpHeader;
import costtracker.plugin.api.helper.HandlerHelperFunctions;
import costtracker.plugin.api.interfaces.PostHandler;

import org.json.JSONObject;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CreateCategory implements PostHandler {
	private static final Charset CHARSET = StandardCharsets.UTF_8;
	private static final int NO_RESPONSE_LENGTH = -1;

	@Override
	public void addPostHandler(HttpServer server, String path) {
		server.createContext(path, handler -> {
			try (handler) {
				final Headers headers = handler.getResponseHeaders();
				final String requestMethod = handler.getRequestMethod();

				JSONObject bodyData = HandlerHelperFunctions.getRequestBodyAsJson(handler.getRequestBody());
				int categoryId = Integer.parseInt(bodyData.getString("id"));
				String categoryName = bodyData.getString("name");

				if (httpHeader.METHOD_POST.headerData.equals(requestMethod)) {
					if (HandlerHelperFunctions.checkURI(handler.getRequestURI(), path)) {
						CategoryHandler categoryHandler = new CategoryHandler();
						boolean returnState = categoryHandler.create(Category.CategoryBuilder.withName(categoryName).withId(categoryId).build());
						final String responseBody = new JSONObject().put("state", returnState).toString();

						// Set Headers and send Response to client
						headers.set(httpHeader.HEADER_CONTENT_TYPE.headerData,
								String.format("application/json; charset=%s", CHARSET));
						final byte[] responseBodyByte = responseBody.getBytes(CHARSET);
						handler.sendResponseHeaders(httpCodes.STATUS_CREATED.code, responseBodyByte.length);
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
