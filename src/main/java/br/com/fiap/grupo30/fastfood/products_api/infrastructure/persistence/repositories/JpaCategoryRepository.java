package br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.repositories;

import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.entities.CategoryEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query("SELECT obj FROM CategoryEntity obj " + "WHERE obj.name = :category")
    Optional<CategoryEntity> findCategory(String category);
}
