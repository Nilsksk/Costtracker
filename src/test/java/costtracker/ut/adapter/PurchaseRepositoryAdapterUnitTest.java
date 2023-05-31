package costtracker.ut.adapter;

import costtracker.adapter.persistence.UnitOfWork;
import costtracker.adapter.repositoryadapters.PurchaseRepositoryAdapterImp;
import costtracker.application.interfaces.PurchaseRepositoryAdapter;
import costtracker.domain.businessobjects.Purchase;
import costtracker.domain.dependencyinjection.DependencyContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchaseRepositoryAdapterUnitTest {
    @Test
    void testPurchaseAdapter() {
        DependencyContainer container = DependencyContainer.getInstance();
        container.registerScoped(UnitOfWork.class, UnitOfWorkMock.class);
        var uowMock = container.getDependency(UnitOfWork.class);

        PurchaseRepositoryAdapter purchaseRepositoryAdapter = new PurchaseRepositoryAdapterImp();
        Purchase purchase = purchaseRepositoryAdapter.get(1);

        assertEquals(23.5, purchase.getPrice());
        assertEquals("TestEinkauf1", purchase.getName());
        assertEquals("1/1/2023", purchase.getDateString());
        assertEquals("Trinken", purchase.getCategory().getName());
        assertEquals("Rewe", purchase.getCompany().getName());
        assertEquals("Karlsruhe", purchase.getCompany().getLocation());
    }
}
