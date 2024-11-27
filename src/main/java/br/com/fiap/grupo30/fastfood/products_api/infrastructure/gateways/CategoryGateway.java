package br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Category;
import br.com.fiap.grupo30.fastfood.products_api.domain.repositories.CategoryRepository;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.entities.CategoryEntity;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.repositories.JpaCategoryRepository;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.exceptions.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryGateway implements CategoryRepository {

    public final JpaCategoryRepository jpaCategoryRepository;

    @Autowired
    public CategoryGateway(JpaCategoryRepository jpaCategoryRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return jpaCategoryRepository.findAll().stream()
                .map(CategoryEntity::toDomainEntity)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Category findOne(String category) {
        return jpaCategoryRepository
                .findCategory(category)
                .map(CategoryEntity::toDomainEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }
}
