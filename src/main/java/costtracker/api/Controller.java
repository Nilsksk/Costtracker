package costtracker.api;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Controller {
    private static String HOSTNAME;
    private static int PORT;
    private static int BACKLOG;
    private static HttpServer server;

    // Constructor
    public Controller(String HOSTNAME, int PORT, int BACKLOG) throws IOException {
        Controller.HOSTNAME = HOSTNAME;
        Controller.PORT = PORT;
        Controller.BACKLOG = BACKLOG;
        Controller.server = HttpServer.create(new InetSocketAddress(HOSTNAME, PORT), BACKLOG);
    }

    // Add Handler
    public void addHandler() {
        // Add function handler to Server
        functionHandler handler = new functionHandler();
        handler.handleFunc1(server, "/func1");
        handler.handleFunc2(server, "/func2");
    }

    // Start Server
    public void startServer() {
        server.start();
        System.out.println("-------------------------------------------------");
        System.out.printf("HTTP Server started at: %s with Port: %s%n", this.getHOSTNAME(), this.getPORT());
        System.out.println("-------------------------------------------------");
    }

    public String getHOSTNAME() {
        return HOSTNAME;
    }

    public void setHOSTNAME(String HOSTNAME) {
        Controller.HOSTNAME = HOSTNAME;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        Controller.PORT = PORT;
    }

    public int getBACKLOG() {
        return BACKLOG;
    }

    public void setBACKLOG(int BACKLOG) {
        Controller.BACKLOG = BACKLOG;
    }

    public HttpServer getServer() {
        return server;
    }
}
