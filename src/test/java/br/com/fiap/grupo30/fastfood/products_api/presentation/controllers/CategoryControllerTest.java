package br.com.fiap.grupo30.fastfood.products_api.presentation.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.fiap.grupo30.fastfood.products_api.domain.usecases.category.ListAllCategoriesInMenuUseCase;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.CategoryGateway;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.CategoryDTO;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock private ListAllCategoriesInMenuUseCase listAllCategoriesInMenuUseCase;
    @Mock private CategoryGateway categoryGateway;

    @InjectMocks private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void findAllShouldReturnListOfCategories() throws Exception {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO("Drinks");
        when(listAllCategoriesInMenuUseCase.execute(categoryGateway))
                .thenReturn(List.of(categoryDTO));

        // Act & Assert
        mockMvc.perform(get("/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Drinks"));

        verify(listAllCategoriesInMenuUseCase, times(1)).execute(categoryGateway);
    }
}
