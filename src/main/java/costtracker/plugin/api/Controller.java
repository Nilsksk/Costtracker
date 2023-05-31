package costtracker.plugin.api;

import com.sun.net.httpserver.HttpServer;

import costtracker.plugin.api.routes.category.*;
import costtracker.plugin.api.routes.company.*;
import costtracker.plugin.api.routes.purchase.*;

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
        // Category Routes
        CreateCategory createCategory = new CreateCategory();
        createCategory.addPostHandler(server, "/category/createCategory");

        GetCategories getCategories = new GetCategories();
        getCategories.addGetHandler(server, "/category/getCategories");

        GetCategoryById getCategoryById = new GetCategoryById();
        getCategoryById.addGetHandler(server, "/category/getCategoryById");

        GetEnabledCategories getEnabledCategories = new GetEnabledCategories();
        getEnabledCategories.addGetHandler(server, "/category/getEnabledCategories");

        GetDisabledCategories getDisabledCategories = new GetDisabledCategories();
        getDisabledCategories.addGetHandler(server, "/category/getDisabledCategories");

        UpdateCategory updateCategory = new UpdateCategory();
        updateCategory.addPutHandler(server, "/category/updateCategory");

        DeleteCategoryById deleteCategoryById = new DeleteCategoryById();
        deleteCategoryById.addDeleteHandler(server, "/category/deleteCategoryById");

        DisableCategory disableCategory = new DisableCategory();
        disableCategory.addPutHandler(server, "/category/disableCategory");

        EnableCategory enableCategory = new EnableCategory();
        enableCategory.addPutHandler(server, "/category/enableCategory");

        // Company Routes
        CreateCompany createCompany = new CreateCompany();
        createCompany.addPostHandler(server, "/company/createCompany");

        GetCompanies getCompanies = new GetCompanies();
        getCompanies.addGetHandler(server, "/company/getCompanies");

        GetCompanyById getCompanyById = new GetCompanyById();
        getCompanyById.addGetHandler(server, "/company/getCompanyById");

        GetEnabledCompanies getEnabledCompanies = new GetEnabledCompanies();
        getEnabledCompanies.addGetHandler(server, "/company/getEnabledCompanies");

        GetDisabledCompanies getDisabledCompanies = new GetDisabledCompanies();
        getDisabledCompanies.addGetHandler(server,"/company/getDisabledCompanies");

        UpdateCompany updateCompany = new UpdateCompany();
        updateCompany.addPutHandler(server, "/company/updateCompany");

        DeleteCompanyById deleteCompanyById = new DeleteCompanyById();
        deleteCompanyById.addDeleteHandler(server, "/company/deleteCompanyById");

        DisableCompany disableCompany = new DisableCompany();
        disableCompany.addPutHandler(server, "/company/disableCompany");

        EnableCompany enableCompany = new EnableCompany();
        enableCompany.addPutHandler(server, "/company/enableCompany");

        // Purchase Routes
        CreatePurchase createPurchase = new CreatePurchase();
        createPurchase.addPostHandler(server, "/purchase/createPurchase");

        GetPurchaseById getPurchaseById = new GetPurchaseById();
        getPurchaseById.addGetHandler(server, "/purchase/getPurchaseById");

        GetPurchaseByWeek getPurchaseByWeek = new GetPurchaseByWeek();
        getPurchaseByWeek.addGetHandler(server, "/purchase/getPurchaseByWeek");

        GetPurchaseByMonth getPurchaseByMonth = new GetPurchaseByMonth();
        getPurchaseByMonth.addGetHandler(server, "/purchase/getPurchaseByMonth");

        GetPurchaseByYear getPurchaseByYear = new GetPurchaseByYear();
        getPurchaseByYear.addGetHandler(server, "/purchase/getPurchaseByYear");

        GetPurchaseByTimespan getPurchaseByTimespan = new GetPurchaseByTimespan();
        getPurchaseByTimespan.addGetHandler(server, "/purchase/getPurchaseByTimespan");

        GetPurchaseByCategoryByTimespan getPurchaseByCategoryByTimespan = new GetPurchaseByCategoryByTimespan();
        getPurchaseByCategoryByTimespan.addGetHandler(server, "/purchase/getPurchaseByCategoryByTimespan");

        GetPurchaseByCompanyByTimespan getPurchaseByCompanyByTimespan = new GetPurchaseByCompanyByTimespan();
        getPurchaseByCompanyByTimespan.addGetHandler(server, "/purchase/getPurchaseByCompanyByTimespan");

        UpdatePurchase updatePurchase = new UpdatePurchase();
        updatePurchase.addPutHandler(server, "/purchase/updatePurchase");

        DeletePurchaseById deletePurchaseById = new DeletePurchaseById();
        deletePurchaseById.addDeleteHandler(server, "/purchase/deletePurchaseById");
    }

    // Start Server
    public void startServer() {
        server.start();
        System.out.println("-------------------------------------------------");
        System.out.printf("HTTP Server started at: %s with Port: %s%n", this.getHOSTNAME(), this.getPORT());
        System.out.println("-------------------------------------------------");
    }

    public void stopServer(){
        server.stop(0);
        System.out.println("\n-------------------");
        System.out.println("HTTP Server stopped");
        System.out.println("-------------------");
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
