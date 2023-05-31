package costtracker.plugin.api.interfaces;

import com.sun.net.httpserver.HttpServer;

public interface PutHandler {

    void addPutHandler(HttpServer server, String path);

}
