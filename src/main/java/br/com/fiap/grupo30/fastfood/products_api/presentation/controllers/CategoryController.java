package br.com.fiap.grupo30.fastfood.products_api.presentation.controllers;

import br.com.fiap.grupo30.fastfood.products_api.domain.usecases.category.ListAllCategoriesInMenuUseCase;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.CategoryGateway;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.CategoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
@Tag(name = "Categories Controller", description = "RESTful API for managing categories.")
public class CategoryController {

    private final ListAllCategoriesInMenuUseCase listAllCategoriesInMenuUseCase;
    private final CategoryGateway categoryGateway;

    @Autowired
    public CategoryController(
            ListAllCategoriesInMenuUseCase listAllCategoriesInMenuUseCase,
            CategoryGateway categoryGateway) {
        this.listAllCategoriesInMenuUseCase = listAllCategoriesInMenuUseCase;
        this.categoryGateway = categoryGateway;
    }

    @GetMapping
    @Operation(
            summary = "Get all categories",
            description = "Retrieve a list of all registered categories")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> categories = listAllCategoriesInMenuUseCase.execute(categoryGateway);
        return ResponseEntity.ok(categories);
    }
}
