package br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Category;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.entities.CategoryEntity;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.repositories.JpaCategoryRepository;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CategoryGatewayTest {

    @Mock private JpaCategoryRepository jpaCategoryRepository;

    @InjectMocks private CategoryGateway categoryGateway;

    private static final Long CATEGORY_ID = 1L;
    private static final String CATEGORY_NAME = "Drinks";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Testes para findAll
    @Test
    void findAllShouldReturnListWithCorrectSize() {
        // Arrange
        CategoryEntity entity = new CategoryEntity(CATEGORY_ID, CATEGORY_NAME);
        when(jpaCategoryRepository.findAll()).thenReturn(List.of(entity));

        // Act
        List<Category> categories = categoryGateway.findAll();

        // Assert
        assertThat(categories).hasSize(1);
    }

    @Test
    void findAllShouldVerifyRepositoryCall() {
        // Arrange
        CategoryEntity entity = new CategoryEntity(CATEGORY_ID, CATEGORY_NAME);
        when(jpaCategoryRepository.findAll()).thenReturn(List.of(entity));

        // Act
        categoryGateway.findAll();

        // Verify
        verify(jpaCategoryRepository, times(1)).findAll();
    }

    @Test
    void findAllShouldReturnCategoryWithCorrectId() {
        // Arrange
        CategoryEntity entity = new CategoryEntity(CATEGORY_ID, CATEGORY_NAME);
        when(jpaCategoryRepository.findAll()).thenReturn(List.of(entity));

        // Act
        List<Category> categories = categoryGateway.findAll();

        // Assert
        assertThat(categories.get(0).getId()).isEqualTo(CATEGORY_ID);
    }

    @Test
    void findAllShouldReturnCategoryWithCorrectName() {
        // Arrange
        CategoryEntity entity = new CategoryEntity(CATEGORY_ID, CATEGORY_NAME);
        when(jpaCategoryRepository.findAll()).thenReturn(List.of(entity));

        // Act
        List<Category> categories = categoryGateway.findAll();

        // Assert
        assertThat(categories.get(0).getName()).isEqualTo(CATEGORY_NAME);
    }

    // Testes para findOne
    @Test
    void findOneShouldReturnCategoryWithCorrectId() {
        // Arrange
        CategoryEntity entity = new CategoryEntity(CATEGORY_ID, CATEGORY_NAME);
        when(jpaCategoryRepository.findCategory(CATEGORY_NAME)).thenReturn(Optional.of(entity));

        // Act
        Category category = categoryGateway.findOne(CATEGORY_NAME);

        // Assert
        assertThat(category.getId()).isEqualTo(CATEGORY_ID);
    }

    @Test
    void findOneShouldReturnCategoryWithCorrectName() {
        // Arrange
        CategoryEntity entity = new CategoryEntity(CATEGORY_ID, CATEGORY_NAME);
        when(jpaCategoryRepository.findCategory(CATEGORY_NAME)).thenReturn(Optional.of(entity));

        // Act
        Category category = categoryGateway.findOne(CATEGORY_NAME);

        // Assert
        assertThat(category.getName()).isEqualTo(CATEGORY_NAME);
    }

    @Test
    void findOneShouldVerifyRepositoryCall() {
        // Arrange
        CategoryEntity entity = new CategoryEntity(CATEGORY_ID, CATEGORY_NAME);
        when(jpaCategoryRepository.findCategory(CATEGORY_NAME)).thenReturn(Optional.of(entity));

        // Act
        categoryGateway.findOne(CATEGORY_NAME);

        // Verify
        verify(jpaCategoryRepository, times(1)).findCategory(CATEGORY_NAME);
    }

    @Test
    void findOneShouldThrowExceptionIfNotFound() {
        // Arrange
        when(jpaCategoryRepository.findCategory(CATEGORY_NAME)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> categoryGateway.findOne(CATEGORY_NAME))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Category not found");
    }

    @Test
    void findOneShouldVerifyRepositoryCallOnException() {
        // Arrange
        when(jpaCategoryRepository.findCategory(CATEGORY_NAME)).thenReturn(Optional.empty());

        // Act & Assert
        try {
            categoryGateway.findOne(CATEGORY_NAME);
        } catch (ResourceNotFoundException ignored) {
            // Ignored: check is done separately
        }

        // Verify
        verify(jpaCategoryRepository, times(1)).findCategory(CATEGORY_NAME);
    }
}
