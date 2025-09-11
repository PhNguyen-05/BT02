package vn.iotstar.services.impl;

import java.util.List;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao dao = new CategoryDaoImpl();

    @Override
    public List<CategoryModel> findAllByUserId(String userUsername) {  // Sửa: String thay int
        return dao.findAllByUserId(userUsername);
    }

    @Override
    public CategoryModel findById(int cateId) {
        return dao.findById(cateId);
    }

    @Override
    public void insert(CategoryModel category) {
        dao.insert(category);
    }

    @Override
    public void update(CategoryModel category) {
        dao.update(category);
    }

    @Override
    public void delete(int cateId) {
        dao.delete(cateId);
    }
}