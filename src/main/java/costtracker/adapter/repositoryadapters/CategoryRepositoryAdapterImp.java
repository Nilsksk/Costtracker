package costtracker.adapter.repositoryadapters;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import costtracker.adapter.entities.CategoryEntity;
import costtracker.adapter.mappers.EntityMapper;
import costtracker.adapter.persistence.UnitOfWork;
import costtracker.application.interfaces.CategoryRepositoryAdapter;
import costtracker.domain.businessobjects.Category;
import costtracker.plugin.db.unitofwork.UnitOfWorkImp;

public class CategoryRepositoryAdapterImp implements CategoryRepositoryAdapter {
	private UnitOfWork uow = new UnitOfWorkImp();

	public CategoryRepositoryAdapterImp() {
	
	}

	@Override
	public Category get(int id) {
		Category category = null;
		try {
			CategoryEntity categoryEntity = uow.getCategoryRepository().get(id);
			category = EntityMapper.toBo(categoryEntity);
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return category;
	}

	@Override
	public boolean update(Category category) {
		CategoryEntity categoryEntity = EntityMapper.toEntity(category);
		boolean ret = false;
		try {
			ret = uow.getCategoryRepository().update(categoryEntity);
			if(ret)
				uow.save();
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return ret;
	}

	@Override
	public boolean insert(Category category) {
		CategoryEntity categoryEntity = EntityMapper.toEntity(category);
		boolean ret = false;
		try {
			ret = uow.getCategoryRepository().insert(categoryEntity);
			if(ret)
				uow.save();
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return ret;
	}

	@Override
	public boolean delete(int id) {
		boolean ret = false;
		try {
			ret = uow.getCategoryRepository().delete(id);
			if(ret)
				uow.save();
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return ret;
	}

	@Override
	public List<Category> getAll() {
		List<Category> categories = new ArrayList<>();
		try {
			List<CategoryEntity> entities = uow.getCategoryRepository().getAll();
			for (CategoryEntity categoryEntity : entities) {
				Category Category = EntityMapper.toBo(categoryEntity);
				categories.add(Category);
			}
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return categories;
	}

	@Override
	public boolean enable(int id) {
		boolean ret = false;
		try {
			ret = uow.getCategoryRepository().enable(id);
			if(ret)
				uow.save();
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return ret;
	}

	@Override
	public boolean disable(int id) {
		boolean ret = false;
		try {
			ret = uow.getCategoryRepository().disable(id);
			if(ret)
				uow.save();
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return ret;
	}

	@Override
	public List<Category> getEnabled() {
		List<Category> companies = new ArrayList<>();
		try {
			List<CategoryEntity> entities = uow.getCategoryRepository().getEnabled();
			for (CategoryEntity categoryEntity : entities) {
				Category category = EntityMapper.toBo(categoryEntity);
				companies.add(category);
			}
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return companies;
	}

	@Override
	public List<Category> getDisabled() {
		List<Category> categories = new ArrayList<>();
		try {
			List<CategoryEntity> entities = uow.getCategoryRepository().getDisabled();
			for (CategoryEntity categoryEntity : entities) {
				Category category = EntityMapper.toBo(categoryEntity);
				categories.add(category);
			}
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return categories;
	}
}
