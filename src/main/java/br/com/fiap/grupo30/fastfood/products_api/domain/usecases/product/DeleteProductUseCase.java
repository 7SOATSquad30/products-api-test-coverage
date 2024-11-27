package br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product;

import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;

public class DeleteProductUseCase {

    public void execute(ProductGateway productGateway, Long id) {
        productGateway.delete(id);
    }
}
