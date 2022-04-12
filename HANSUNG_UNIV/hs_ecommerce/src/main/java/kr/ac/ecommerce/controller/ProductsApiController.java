package kr.ac.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import kr.ac.ecommerce.dto.ProductDto;
import kr.ac.ecommerce.entity.Product;
import kr.ac.ecommerce.service.CategoryService;
import kr.ac.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/products")
@RequiredArgsConstructor
public class ProductsApiController {

    private final ProductService productService;

    // CREATE - POST | create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto request) {
        Product product = productService.createProduct(request);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    // READ  - GET   | get a product
    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) throws NotFoundException {
        Product product = productService.getProductById(id);

        // null Exception
        if (product == null) {
            throw new NotFoundException("not found id");
        }

        return ResponseEntity.ok(product);
    }

    // READ  - GET   | get all products
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

}
