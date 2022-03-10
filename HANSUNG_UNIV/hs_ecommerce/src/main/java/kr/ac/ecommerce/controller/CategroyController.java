package kr.ac.ecommerce.controller;



import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.ecommerce.dto.CategoryDto;
import kr.ac.ecommerce.entity.Category;
import kr.ac.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/api/categories")
@RequiredArgsConstructor
public class CategroyController {

	private CategoryService categoryService;
	
	//Create -POST
	public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDto request){
		
		Category category = categoryService.createCategory(request.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(category);
        
	}
	//Read   -GET 
	
	//Read   -GET
	
	//Update -PUT
	
	//DELTE  -DELETE
	
}
