package costtracker.api;

import com.sun.net.httpserver.HttpServer;

public interface IputHandler {

    void addPutHandler(HttpServer server, String path);

}
