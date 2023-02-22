package costtracker.api;

import com.sun.net.httpserver.HttpServer;

public interface IdeleteHandler {

    void addDeleteHandler(HttpServer server, String path);

}
