package br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.repositories;

import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.entities.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(
            "SELECT obj FROM ProductEntity obj "
                    + "WHERE (:categoryId IS NULL OR obj.category.id = :categoryId)")
    List<ProductEntity> findProductsByCategoryId(Long categoryId);
}
