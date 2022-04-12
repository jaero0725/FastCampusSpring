package kr.ac.ecommerce.service;

import java.util.List;

import kr.ac.ecommerce.entity.Category;

public interface CategoryService {

    //CREATE - POST   | create one category
    public Category createCategory(String name);

    public List<Category> getAllCategories();

    //READ   - GET    | get one category

    //READ 	 - GET    | get all category

    //UPDATE - PUT    | update one category

    //DELETE - DELETE | delete one category

    //DELETE - DELETE | delete all category

}
