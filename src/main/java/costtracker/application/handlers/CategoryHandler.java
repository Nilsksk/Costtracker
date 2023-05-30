package costtracker.application.handlers;

import costtracker.adapter.repositoryadapters.CategoryRepositoryAdapterImp;
import costtracker.application.handlers.interfaces.DatabaseHandler;
import costtracker.application.handlers.interfaces.StateHandler;
import costtracker.application.interfaces.CategoryRepositoryAdapter;
import costtracker.domain.businessobjects.Category;

import java.util.List;

public class CategoryHandler implements DatabaseHandler<Category>, StateHandler<Category> {

	private CategoryRepositoryAdapter categoryRepositoryAdapter = new CategoryRepositoryAdapterImp();
	
	public CategoryHandler() {
		
	}
	
	@Override
	public Category getById(int id){
		return categoryRepositoryAdapter.get(id);
	}

	@Override
	public List<Category> getAll() {
		return categoryRepositoryAdapter.getAll();
	}

	@Override
	public boolean deleteById(int id) {
		return categoryRepositoryAdapter.delete(id);
	}

	@Override
	public boolean update(Category category) {
		return categoryRepositoryAdapter.update(category);
	}

	@Override
	public boolean create(Category category) {
		return categoryRepositoryAdapter.insert(category);
	}

	@Override
	public boolean enable(int id) {
		return categoryRepositoryAdapter.enable(id);
	}

	@Override
	public boolean disable(int id) {
		return categoryRepositoryAdapter.disable(id);
	}

	@Override
	public List<Category> getEnabled()  {
		return categoryRepositoryAdapter.getEnabled();
	}

	@Override
	public List<Category> getDisabled() {
		return categoryRepositoryAdapter.getDisabled();
	}
}
