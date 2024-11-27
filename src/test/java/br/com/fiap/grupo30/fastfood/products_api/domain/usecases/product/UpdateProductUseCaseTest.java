package br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Category;
import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.CategoryGateway;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;
import br.com.fiap.grupo30.fastfood.products_api.utils.ProductHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UpdateProductUseCaseTest {

    @Mock private ProductGateway productGateway;

    @Mock private CategoryGateway categoryGateway;

    @InjectMocks private UpdateProductUseCase updateProductUseCase;

    private static final String CATEGORY_NAME = "Snacks";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateProductName() {
        // Arrange
        Long productId = 1L;
        Category category = ProductHelper.createDefaultCategory();
        Product product = ProductHelper.createDefaultProductWithId(productId);
        ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithId(productId);

        when(categoryGateway.findOne(CATEGORY_NAME)).thenReturn(category);
        when(productGateway.findById(1L)).thenReturn(product);
        when(productGateway.save(any(Product.class))).thenReturn(product);

        // Act
        updateProductUseCase.execute(productGateway, categoryGateway, productDTO);

        // Assert
        assertThat(product.getName()).isEqualTo(productDTO.getName());
    }

    @Test
    void shouldUpdateProductDescription() {
        // Arrange
        Long productId = 1L;
        Category category = ProductHelper.createDefaultCategory();
        Product product = ProductHelper.createDefaultProductWithId(productId);
        ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithId(productId);

        when(categoryGateway.findOne(CATEGORY_NAME)).thenReturn(category);
        when(productGateway.findById(1L)).thenReturn(product);
        when(productGateway.save(any(Product.class))).thenReturn(product);

        // Act
        updateProductUseCase.execute(productGateway, categoryGateway, productDTO);

        // Assert
        assertThat(product.getDescription()).isEqualTo(productDTO.getDescription());
    }

    @Test
    void shouldUpdateProductPrice() {
        // Arrange
        Long productId = 1L;
        Category category = ProductHelper.createDefaultCategory();
        Product product = ProductHelper.createDefaultProductWithId(productId);
        ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithId(productId);

        when(categoryGateway.findOne(CATEGORY_NAME)).thenReturn(category);
        when(productGateway.findById(1L)).thenReturn(product);
        when(productGateway.save(any(Product.class))).thenReturn(product);

        // Act
        updateProductUseCase.execute(productGateway, categoryGateway, productDTO);

        // Assert
        assertThat(product.getPrice()).isEqualTo(productDTO.getPrice());
    }

    @Test
    void shouldUpdateProductImgUrl() {
        // Arrange
        Long productId = 1L;
        Category category = ProductHelper.createDefaultCategory();
        Product product = ProductHelper.createDefaultProductWithId(productId);
        ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithId(productId);

        when(categoryGateway.findOne(CATEGORY_NAME)).thenReturn(category);
        when(productGateway.findById(1L)).thenReturn(product);
        when(productGateway.save(any(Product.class))).thenReturn(product);

        // Act
        updateProductUseCase.execute(productGateway, categoryGateway, productDTO);

        // Assert
        assertThat(product.getImgUrl()).isEqualTo(productDTO.getImgUrl());
    }

    @Test
    void shouldUpdateProductCategory() {
        // Arrange
        Long productId = 1L;
        Category category = ProductHelper.createDefaultCategory();
        Product product = ProductHelper.createDefaultProductWithId(productId);
        ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithId(productId);

        when(categoryGateway.findOne(CATEGORY_NAME)).thenReturn(category);
        when(productGateway.findById(1L)).thenReturn(product);
        when(productGateway.save(any(Product.class))).thenReturn(product);

        // Act
        updateProductUseCase.execute(productGateway, categoryGateway, productDTO);

        // Assert
        assertThat(product.getCategory()).isEqualTo(category);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound() {
        // Arrange
        ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithId(1L);
        when(categoryGateway.findOne(CATEGORY_NAME)).thenReturn(null);

        // Act & Assert
        assertThatThrownBy(
                        () ->
                                updateProductUseCase.execute(
                                        productGateway, categoryGateway, productDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Category not found");
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        // Arrange
        Category category = ProductHelper.createDefaultCategory();
        ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithId(1L);

        when(categoryGateway.findOne(CATEGORY_NAME)).thenReturn(category);
        when(productGateway.findById(1L)).thenReturn(null);

        // Act & Assert
        assertThatThrownBy(
                        () ->
                                updateProductUseCase.execute(
                                        productGateway, categoryGateway, productDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product not found");
    }

    @Test
    void shouldThrowExceptionWhenProductNotSaved() {
        // Arrange
        Long productId = 1L;
        Category category = ProductHelper.createDefaultCategory();
        Product product = ProductHelper.createDefaultProductWithId(productId);
        ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithId(productId);

        when(categoryGateway.findOne(CATEGORY_NAME)).thenReturn(category);
        when(productGateway.findById(1L)).thenReturn(product);
        when(productGateway.save(any(Product.class))).thenReturn(null);

        // Act & Assert
        assertThatThrownBy(
                        () ->
                                updateProductUseCase.execute(
                                        productGateway, categoryGateway, productDTO))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Product could not be saved");
    }

    @Test
    void shouldCallSaveOnProductGateway() {
        // Arrange
        Long productId = 1L;
        Category category = ProductHelper.createDefaultCategory();
        Product product = ProductHelper.createDefaultProductWithId(productId);
        ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithId(productId);

        when(categoryGateway.findOne(CATEGORY_NAME)).thenReturn(category);
        when(productGateway.findById(1L)).thenReturn(product);
        when(productGateway.save(any(Product.class))).thenReturn(product);

        // Act
        updateProductUseCase.execute(productGateway, categoryGateway, productDTO);

        // Verify
        verify(productGateway).save(product);
    }
}
