package br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;
import br.com.fiap.grupo30.fastfood.products_api.utils.ProductHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GetProductUseCaseTest {

    @Mock private ProductGateway productGateway;

    @InjectMocks private GetProductUseCase getProductUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCorrectProductName() {
        // Arrange
        Long validId = 1L;
        Product product = ProductHelper.createDefaultProductWithId(validId);
        when(productGateway.findById(validId)).thenReturn(product);

        // Act
        ProductDTO result = getProductUseCase.execute(productGateway, validId);

        // Assert
        assertThat(result.getName()).isEqualTo(product.getName());
    }

    @Test
    void shouldCallFindByIdOnProductGateway() {
        // Arrange
        Long productId = 1L;
        Product product = ProductHelper.createDefaultProductWithId(productId);
        when(productGateway.findById(productId)).thenReturn(product);

        // Act
        getProductUseCase.execute(productGateway, productId);

        // Assert
        verify(productGateway).findById(productId);
    }
}
