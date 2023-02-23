package costtracker.api.interfaces;

import com.sun.net.httpserver.HttpServer;

public interface PostHandler {
    void addPostHandler(HttpServer server, String path);

}
