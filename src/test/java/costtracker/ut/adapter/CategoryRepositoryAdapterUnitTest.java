package costtracker.ut.adapter;

import costtracker.adapter.persistence.UnitOfWork;
import costtracker.adapter.repositoryadapters.CategoryRepositoryAdapterImp;
import costtracker.application.interfaces.CategoryRepositoryAdapter;
import costtracker.domain.businessobjects.Category;
import costtracker.domain.dependencyinjection.DependencyContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryRepositoryAdapterUnitTest {
    @Test
    void testCategoryAdapter() {
        DependencyContainer container = DependencyContainer.getInstance();
        container.registerScoped(UnitOfWork.class, UnitOfWorkMock.class);
        //var uowMock = container.getDependency(UnitOfWork.class);

        CategoryRepositoryAdapter categoryRepositoryAdapter = new CategoryRepositoryAdapterImp();
        Category category = categoryRepositoryAdapter.get(2);

        assertEquals("Essen", category.getName());
    }
}
