package br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Category;
import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.CategoryGateway;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;

public class UpdateProductUseCase {

    public ProductDTO execute(
            ProductGateway productGateway, CategoryGateway categoryGateway, ProductDTO dto) {
        Category categoryEntity = categoryGateway.findOne(dto.getCategory());
        if (categoryEntity == null) {
            throw new IllegalArgumentException("Category not found");
        }

        Product product = productGateway.findById(dto.getProductId());
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
        product.setCategory(categoryEntity);

        Product savedProduct = productGateway.save(product);
        if (savedProduct == null) {
            throw new IllegalStateException("Product could not be saved");
        }

        return savedProduct.toDTO();
    }
}
