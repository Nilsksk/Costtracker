package costtracker.buisnesslogic;

import costtracker.buisnesslogic.interfaces.DatabaseHandler;
import costtracker.buisnesslogic.interfaces.StateHandler;
import costtracker.businessobjects.Category;
import costtracker.db.entities.CategoryEntity;
import costtracker.db.unitofwork.UnitOfWork;
import costtracker.db.unitofwork.UnitOfWorkImp;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryHandler implements DatabaseHandler<Category>, StateHandler<Category> {

	@Override
	public Category getById(int id) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {
			CategoryEntity categoryEntity = uow.getCategoryRepository().get(id);
			return Category.fromEntity(categoryEntity);
		}
	}

	@Override
	public List<Category> getAll() throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {
			List<CategoryEntity> categoryEntityList = uow.getCategoryRepository().getAll();
			return categoryEntityList.stream().map(Category::fromEntity).collect(Collectors.toList());
		}
	}

	@Override
	public boolean deleteById(int id) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {
			boolean state = uow.getCategoryRepository().delete(id);
			if (state) {
				uow.Save();
			}
			return state;
		}
	}

	@Override
	public boolean update(Category category) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {
			boolean state = uow.getCategoryRepository().update(category.toEntity());
			if (state) {
				uow.Save();
			}
			return state;
		}
	}

	@Override
	public boolean create(Category category) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {
			boolean state = uow.getCategoryRepository().insert(category.toEntity());

			if (state) {
				uow.Save();
			}

			return state;
		}
	}

	@Override
	public boolean enable(int id) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {
			boolean state = uow.getCategoryRepository().enable(id);

			if (state) {
				uow.Save();
			}

			return state;
		}
	}

	@Override
	public boolean disable(int id) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {
			boolean state = uow.getCategoryRepository().disable(id);

			if (state) {
				uow.Save();
			}

			return state;
		}
	}

	@Override
	public List<Category> getEnabled() throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {
			List<CategoryEntity> categoryEntityList = uow.getCategoryRepository().getEnabled();

			return categoryEntityList.stream().map(Category::fromEntity).collect(Collectors.toList());
		}
	}

	@Override
	public List<Category> getDisabled() throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {
			List<CategoryEntity> categoryEntityList = uow.getCategoryRepository().getDisabled();

			return categoryEntityList.stream().map(Category::fromEntity).collect(Collectors.toList());
		}
	}
}
