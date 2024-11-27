package br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product;

import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;

public class GetProductUseCase {

    public ProductDTO execute(ProductGateway productGateway, Long id) {
        return productGateway.findById(id).toDTO();
    }
}
