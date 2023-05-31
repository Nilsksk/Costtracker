import costtracker.adapter.persistence.UnitOfWork;
import costtracker.adapter.repositoryadapters.CategoryRepositoryAdapterImp;
import costtracker.adapter.repositoryadapters.CompanyRepositoryAdapterImp;
import costtracker.adapter.repositoryadapters.PurchaseRepositoryAdapterImp;
import costtracker.application.interfaces.CategoryRepositoryAdapter;
import costtracker.application.interfaces.CompanyRepositoryAdapter;
import costtracker.application.interfaces.PurchaseRepositoryAdapter;
import costtracker.domain.dependencyinjection.DependencyContainer;
import costtracker.plugin.api.Controller;
import costtracker.plugin.db.unitofwork.UnitOfWorkImp;
import costtracker.plugin.ui.Dialogue;


import java.io.IOException;
import java.sql.SQLException;

public class Main {
    // Start of my Application

    public static void main(String[] args) throws IOException, SQLException {
    	//TODO Supress? Seperate Task?
    	addDependencies();
        Controller controller = new Controller("localhost", 8080, 1);
        controller.startServer();
        controller.addHandler();
        
		Dialogue dialogue = new Dialogue();
        dialogue.talk();
        
    }

	private static void addDependencies() {
		DependencyContainer container = DependencyContainer.getInstance();
		container.registerScoped(UnitOfWork.class, UnitOfWorkImp.class);
		container.registerScoped(CompanyRepositoryAdapter.class, CompanyRepositoryAdapterImp.class);
		container.registerScoped(CategoryRepositoryAdapter.class, CategoryRepositoryAdapterImp.class);
		container.registerScoped(PurchaseRepositoryAdapter.class, PurchaseRepositoryAdapterImp.class);
		
		
	}
}
