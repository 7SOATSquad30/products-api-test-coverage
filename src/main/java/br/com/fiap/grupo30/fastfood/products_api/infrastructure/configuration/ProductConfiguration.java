package br.com.fiap.grupo30.fastfood.products_api.infrastructure.configuration;

import br.com.fiap.grupo30.fastfood.products_api.domain.repositories.ProductRepository;
import br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product.*;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.repositories.JpaProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration {

    @Bean
    public ProductRepository productRepository(JpaProductRepository jpaProductRepository) {
        return new ProductGateway(jpaProductRepository);
    }

    @Bean
    public ListProductsByCategoryUseCase listProductsByCategoryUseCase() {
        return new ListProductsByCategoryUseCase();
    }

    @Bean
    public GetProductUseCase getProductUseCase() {
        return new GetProductUseCase();
    }

    @Bean
    public CreateProductUseCase createProductUseCase() {
        return new CreateProductUseCase();
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase() {
        return new UpdateProductUseCase();
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase() {
        return new DeleteProductUseCase();
    }
}
