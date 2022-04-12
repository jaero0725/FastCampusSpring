package kr.ac.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.ecommerce.dto.CategoryDto;
import kr.ac.ecommerce.entity.Category;
import kr.ac.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/categories")
@RequiredArgsConstructor
public class CategroyController {

    private CategoryService categoryService;

    // Create -POST
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDto request) {

        Category category = categoryService.createCategory(request.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    // Read -GET
    @GetMapping
    public ResponseEntity<?> retrieveAllCategories() {
        final List<Category> categories = categoryService.getAllCategories();

        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(categories);
        //return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    // Read -GET

    // Update -PUT

    // DELTE -DELETE

}
