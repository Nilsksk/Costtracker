package costtracker.api;

import com.sun.net.httpserver.HttpServer;

public interface IpostHandler {
    void addPostHandler(HttpServer server, String path);

}
