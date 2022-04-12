package kr.ac.ecommerce.service;

import java.util.List;

import kr.ac.ecommerce.dto.ProductDto;
import kr.ac.ecommerce.entity.Product;

public interface ProductService {

    //CREATE - POST   | create one product
    public Product createProduct(ProductDto request);

    //READ   - GET    | get one product
    public Product getProductById(int id);

    //READ 	 - GET    | get all product
    public List<Product> getAllProducts();

    //UPDATE - PUT    | update one product

    //DELETE - DELETE | delete one product

    //DELETE - DELETE | delete all product

}
