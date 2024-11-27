package br.com.fiap.grupo30.fastfood.products_api.domain.repositories;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> findProductsByCategoryId(Long categoryId);

    Product findById(Long id);

    Product save(Product product);

    void delete(Long id);
}
