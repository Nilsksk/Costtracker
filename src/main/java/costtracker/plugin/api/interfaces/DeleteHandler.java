package costtracker.plugin.api.interfaces;

import com.sun.net.httpserver.HttpServer;

public interface DeleteHandler {

    void addDeleteHandler(HttpServer server, String path);

}
