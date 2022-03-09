package kr.ac.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.ecommerce.dao.ProductDao;
import kr.ac.ecommerce.dto.ProductDto;
import kr.ac.ecommerce.entity.Product;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductDao productDao;

	@Override
	public Product createProduct(ProductDto request) {
		Product product = new Product();
		product.setName(request.getName());
		product.setDescription(request.getDiscription());
		
		int productId = productDao.createProduct(product);
		product.setId(productId);

		return product;
	}

	@Override
	public Product getProductById(int id) {
		Product product = productDao.getProductById(id);
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = productDao.getAllProducts();
		return products;
	}
}
