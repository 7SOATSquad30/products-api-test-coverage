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

class CreateProductUseCaseTest {

    @Mock private ProductGateway productGateway;

    @Mock private CategoryGateway categoryGateway;

    @InjectMocks private CreateProductUseCase createProductUseCase;

    private static final String CATEGORY_NAME = "Snacks";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnProductDTOWithCorrectName() {
        // Arrange
        Category category = ProductHelper.createDefaultCategory();
        Product product = ProductHelper.createDefaultProduct();
        ProductDTO productDTO = ProductHelper.createDefaultProductDTO();

        when(categoryGateway.findOne(CATEGORY_NAME)).thenReturn(category);
        when(productGateway.save(any(Product.class))).thenReturn(product);

        // Act
        ProductDTO result =
                createProductUseCase.execute(productGateway, categoryGateway, productDTO);

        // Assert
        assertThat(result.getName()).isEqualTo(productDTO.getName());
    }

    @Test
    void shouldCallFindOneOnCategoryGateway() {
        // Arrange
        Category category = ProductHelper.createDefaultCategory();
        Product product = ProductHelper.createDefaultProduct();
        ProductDTO productDTO = ProductHelper.createDefaultProductDTO();

        when(categoryGateway.findOne(CATEGORY_NAME)).thenReturn(category);
        when(productGateway.save(any(Product.class))).thenReturn(product);

        // Act
        createProductUseCase.execute(productGateway, categoryGateway, productDTO);

        // Verify
        verify(categoryGateway).findOne(CATEGORY_NAME);
    }

    @Test
    void shouldCallSaveOnProductGateway() {
        // Arrange
        Category category = ProductHelper.createDefaultCategory();
        Product product = ProductHelper.createDefaultProduct();
        ProductDTO productDTO = ProductHelper.createDefaultProductDTO();

        when(categoryGateway.findOne(CATEGORY_NAME)).thenReturn(category);
        when(productGateway.save(any(Product.class))).thenReturn(product);

        // Act
        createProductUseCase.execute(productGateway, categoryGateway, productDTO);

        // Verify
        verify(productGateway).save(product);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound() {
        // Arrange
        ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithNonExistentCategory();
        when(categoryGateway.findOne("Unknown")).thenReturn(null);

        // Act & Assert
        assertThatThrownBy(
                        () ->
                                createProductUseCase.execute(
                                        productGateway, categoryGateway, productDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Category not found");
    }

    @Test
    void shouldNotCallProductGatewayWhenCategoryNotFound() {
        // Arrange
        ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithNonExistentCategory();
        when(categoryGateway.findOne("Unknown")).thenReturn(null);

        // Act
        try {
            createProductUseCase.execute(productGateway, categoryGateway, productDTO);
        } catch (IllegalArgumentException ignored) {
            // Expected exception, ignore
        }

        // Verify
        verifyNoInteractions(productGateway);
    }

    @Test
    void shouldThrowExceptionWhenProductNotSaved() {
        // Arrange
        Category category = ProductHelper.createDefaultCategory();
        ProductDTO productDTO = ProductHelper.createDefaultProductDTO();

        when(categoryGateway.findOne(CATEGORY_NAME)).thenReturn(category);
        when(productGateway.save(any(Product.class))).thenReturn(null);

        // Act & Assert
        assertThatThrownBy(
                        () ->
                                createProductUseCase.execute(
                                        productGateway, categoryGateway, productDTO))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Product could not be saved");
    }
}
