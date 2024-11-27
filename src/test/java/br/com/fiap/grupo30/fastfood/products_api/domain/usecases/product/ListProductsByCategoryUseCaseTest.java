package br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;
import br.com.fiap.grupo30.fastfood.products_api.utils.ProductHelper;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ListProductsByCategoryUseCaseTest {

    private ListProductsByCategoryUseCase listProductsByCategoryUseCase;

    @Mock private ProductGateway productGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        listProductsByCategoryUseCase = new ListProductsByCategoryUseCase();
    }

    @Test
    void shouldReturnListOfProductAsDTOs() {
        // Arrange
        Long categoryId = 1L;
        Product product1 = ProductHelper.createDefaultProductWithIdAndCategory(1L, categoryId);
        Product product2 = ProductHelper.createDefaultProductWithIdAndCategory(2L, categoryId);
        List<Product> products = List.of(product1, product2);

        when(productGateway.findProductsByCategoryId(categoryId)).thenReturn(products);

        // Act
        List<ProductDTO> result = listProductsByCategoryUseCase.execute(productGateway, categoryId);

        // Assert
        assertThat(result).hasSize(2);
    }

    @Test
    void shouldReturnFirstProductWithCorrectName() {
        // Arrange
        Long categoryId = 1L;
        Product product1 = ProductHelper.createDefaultProductWithIdAndCategory(1L, categoryId);
        Product product2 = ProductHelper.createDefaultProductWithIdAndCategory(2L, categoryId);
        List<Product> products = List.of(product1, product2);

        when(productGateway.findProductsByCategoryId(categoryId)).thenReturn(products);

        // Act
        List<ProductDTO> result = listProductsByCategoryUseCase.execute(productGateway, categoryId);

        // Assert
        assertThat(result.get(0).getName()).isEqualTo(product1.getName());
    }

    @Test
    void shouldReturnSecondProductWithCorrectName() {
        // Arrange
        Long categoryId = 1L;
        Product product1 = ProductHelper.createDefaultProductWithIdAndCategory(1L, categoryId);
        Product product2 = ProductHelper.createDefaultProductWithIdAndCategory(2L, categoryId);
        List<Product> products = List.of(product1, product2);

        when(productGateway.findProductsByCategoryId(categoryId)).thenReturn(products);

        // Act
        List<ProductDTO> result = listProductsByCategoryUseCase.execute(productGateway, categoryId);

        // Assert
        assertThat(result.get(1).getName()).isEqualTo(product2.getName());
    }

    @Test
    void shouldReturnEmptyListWhenNoProductsExist() {
        // Arrange
        Long categoryId = 1L;
        when(productGateway.findProductsByCategoryId(categoryId))
                .thenReturn(Collections.emptyList());

        // Act
        List<ProductDTO> result = listProductsByCategoryUseCase.execute(productGateway, categoryId);

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    void shouldCallFindProductsByCategoryIdOnProductGateway() {
        // Arrange
        Long categoryId = 1L;

        // Act
        listProductsByCategoryUseCase.execute(productGateway, categoryId);

        // Assert
        verify(productGateway).findProductsByCategoryId(categoryId);
    }
}
