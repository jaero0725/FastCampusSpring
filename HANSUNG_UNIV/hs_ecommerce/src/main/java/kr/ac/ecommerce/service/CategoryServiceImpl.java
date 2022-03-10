package kr.ac.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.ecommerce.dao.CategoryDao;
import kr.ac.ecommerce.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryRepository;

	@Override
	public Category createCategory(String name) {

		Category category = new Category();
		category.setName(name);

		int id = categoryRepository.addCategory(category);
		category.setId(id);

		return category;
	}

}
