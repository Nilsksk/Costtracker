package costtracker.plugin.api.interfaces;

import com.sun.net.httpserver.HttpServer;

public interface GetHandler {

    void addGetHandler(HttpServer server, String path);

}
