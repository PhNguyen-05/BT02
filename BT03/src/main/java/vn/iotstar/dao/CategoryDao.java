package vn.iotstar.dao;

import java.util.List;
import vn.iotstar.models.CategoryModel;

public interface CategoryDao {
    List<CategoryModel> findAllByUserId(String userUsername);  // Sửa: String thay int
    CategoryModel findById(int cateId);
    void insert(CategoryModel category);
    void update(CategoryModel category);
    void delete(int cateId);
}