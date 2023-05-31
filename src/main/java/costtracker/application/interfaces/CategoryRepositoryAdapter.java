package costtracker.application.interfaces;

import java.util.List;

import costtracker.domain.businessobjects.Category;

public interface CategoryRepositoryAdapter {
	
	public Category get(int id);

	public boolean update(Category category);

	public boolean insert(Category category);

	public boolean delete(int id);

	public List<Category> getAll();

	public boolean enable(int id);

	public boolean disable(int id);

	public List<Category> getEnabled();

	public List<Category> getDisabled();
}
