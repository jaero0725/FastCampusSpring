package kr.ac.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/api/categories")
@RequiredArgsConstructor
public class CategroyController {

	private CategoryService categoryService;
	
}
