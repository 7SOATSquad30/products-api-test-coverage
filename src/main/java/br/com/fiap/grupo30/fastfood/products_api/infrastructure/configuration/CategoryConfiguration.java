package br.com.fiap.grupo30.fastfood.products_api.infrastructure.configuration;

import br.com.fiap.grupo30.fastfood.products_api.domain.repositories.CategoryRepository;
import br.com.fiap.grupo30.fastfood.products_api.domain.usecases.category.ListAllCategoriesInMenuUseCase;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.CategoryGateway;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.repositories.JpaCategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfiguration {

    @Bean
    public CategoryRepository categoryRepository(JpaCategoryRepository jpaCategoryRepository) {
        return new CategoryGateway(jpaCategoryRepository);
    }

    @Bean
    public ListAllCategoriesInMenuUseCase listAllCategoriesInMenuUseCase() {
        return new ListAllCategoriesInMenuUseCase();
    }
}
