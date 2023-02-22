package costtracker.api;

import com.sun.net.httpserver.HttpServer;

public interface IgetHandler {

    void addGetHandler(HttpServer server, String path);

}
