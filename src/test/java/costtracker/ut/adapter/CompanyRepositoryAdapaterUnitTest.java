package costtracker.ut.adapter;

import costtracker.adapter.persistence.UnitOfWork;
import costtracker.adapter.repositoryadapters.CompanyRepositoryAdapterImp;
import costtracker.application.interfaces.CompanyRepositoryAdapter;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.dependencyinjection.DependencyContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyRepositoryAdapaterUnitTest {
    @Test
    void testCompanyAdapter() {
        DependencyContainer container = DependencyContainer.getInstance();
        container.registerScoped(UnitOfWork.class, UnitOfWorkMock.class);
        var uowMock = container.getDependency(UnitOfWork.class);

        CompanyRepositoryAdapter companyRepositoryAdapter = new CompanyRepositoryAdapterImp();
        Company company = companyRepositoryAdapter.get(3);

        assertEquals("FitX", company.getName());
        assertEquals("Remscheid", company.getLocation());
    }
}
