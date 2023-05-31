package costtracker.ut.dependencyinjection;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import costtracker.adapter.repositoryadapters.PurchaseRepositoryAdapterImp;
import costtracker.application.interfaces.PurchaseRepositoryAdapter;
import costtracker.domain.dependencyinjection.DependencyContainer;

public class DependencyFactoryUnitTest {
	@Test
	void testRegisterScoped() {
		DependencyContainer container = DependencyContainer.getInstance();
		container.registerScoped(PurchaseRepositoryAdapter.class, PurchaseRepositoryAdapterImp.class);
		var o = container.getDependency(PurchaseRepositoryAdapter.class);
		
		assertInstanceOf(PurchaseRepositoryAdapter.class, o);
	}
}
