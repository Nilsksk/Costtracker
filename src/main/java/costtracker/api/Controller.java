package costtracker.api;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;



public class Controller {
    private static String HOSTNAME;
    private static int PORT;
    private static int BACKLOG;
    private static HttpServer server;

    // Constructor (
    public Controller(String HOSTNAME, int PORT, int BACKLOG) throws IOException {
        Controller.HOSTNAME = HOSTNAME;
        Controller.PORT = PORT;
        Controller.BACKLOG = BACKLOG;
        Controller.server = HttpServer.create(new InetSocketAddress(HOSTNAME, PORT), BACKLOG);

        functionHandler handler = new functionHandler();
        handler.handleGet(server, "/func1");
        handler.handleGet(server, "/func2");
    }

    // Start Server
    public void startServer() {
        server.start();
    }

    public static String getHOSTNAME() {
        return HOSTNAME;
    }

    public static void setHOSTNAME(String HOSTNAME) {
        Controller.HOSTNAME = HOSTNAME;
    }

    public static int getPORT() {
        return PORT;
    }

    public static void setPORT(int PORT) {
        Controller.PORT = PORT;
    }

    public static int getBACKLOG() {
        return BACKLOG;
    }

    public static void setBACKLOG(int BACKLOG) {
        Controller.BACKLOG = BACKLOG;
    }

    public HttpServer getServer() {
        return server;
    }
}
