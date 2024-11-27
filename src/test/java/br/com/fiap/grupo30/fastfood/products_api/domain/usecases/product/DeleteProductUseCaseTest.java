package br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product;

import static org.mockito.Mockito.*;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.utils.ProductHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DeleteProductUseCaseTest {

    @Mock private ProductGateway productGateway;

    @InjectMocks private DeleteProductUseCase deleteProductUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteProductWhenIdIsValid() {
        // Arrange
        Long validId = 1L;
        Product product = ProductHelper.createDefaultProductWithId(1L);
        when(productGateway.findById(validId)).thenReturn(product);

        // Act
        deleteProductUseCase.execute(productGateway, validId);

        // Assert
        verify(productGateway).delete(validId);
    }

    @Test
    void shouldCallDeleteOnProductGateway() {
        // Arrange
        Long productId = 1L;

        // Act
        deleteProductUseCase.execute(productGateway, productId);

        // Assert
        verify(productGateway, times(1)).delete(productId);
    }
}
