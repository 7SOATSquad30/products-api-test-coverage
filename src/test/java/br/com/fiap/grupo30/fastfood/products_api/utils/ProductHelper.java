package br.com.fiap.grupo30.fastfood.products_api.utils;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Category;
import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;

public class ProductHelper {

    private static final String DEFAULT_NAME = "Burger";
    private static final String DEFAULT_DESCRIPTION = "Delicious burger";
    private static final double DEFAULT_PRICE = 12.99;
    private static final String DEFAULT_IMAGE_URL = "http://example.com/burger.png";
    private static final Long DEFAULT_CATEGORY_ID = 1L;
    private static final String DEFAULT_CATEGORY_NAME = "Snacks";

    /**
     * Creates a default Category instance.
     */
    public static Category createDefaultCategory() {
        return new Category(DEFAULT_CATEGORY_ID, DEFAULT_CATEGORY_NAME);
    }

    /**
     * Creates a Category with specified ID and name.
     */
    public static Category createCategory(Long id, String name) {
        return new Category(id, name);
    }

    /**
     * Creates a default Product instance.
     */
    public static Product createDefaultProduct() {
        return createProduct(
                null,
                DEFAULT_NAME,
                DEFAULT_DESCRIPTION,
                DEFAULT_PRICE,
                DEFAULT_IMAGE_URL,
                createDefaultCategory());
    }

    /**
     * Creates a Product with specified parameters.
     */
    public static Product createProduct(
            Long id,
            String name,
            String description,
            double price,
            String imgUrl,
            Category category) {
        Product product = Product.create(name, description, price, imgUrl, category);
        product.setId(id);
        return product;
    }

    /**
     * Creates a Product with a specific ID.
     */
    public static Product createDefaultProductWithId(Long id) {
        return createProduct(
                id,
                DEFAULT_NAME,
                DEFAULT_DESCRIPTION,
                DEFAULT_PRICE,
                DEFAULT_IMAGE_URL,
                createDefaultCategory());
    }

    /**
     * Creates a Product with a specific ID and Category ID.
     */
    public static Product createDefaultProductWithIdAndCategory(Long id, Long categoryId) {
        return createProduct(
                id,
                DEFAULT_NAME,
                DEFAULT_DESCRIPTION,
                DEFAULT_PRICE,
                DEFAULT_IMAGE_URL,
                createCategory(categoryId, DEFAULT_CATEGORY_NAME));
    }

    /**
     * Converts a default Product to a DTO.
     */
    public static ProductDTO createDefaultProductDTO() {
        return createDefaultProduct().toDTO();
    }

    /**
     * Creates a default ProductDTO with a specified ID.
     */
    public static ProductDTO createDefaultProductDTOWithId(Long id) {
        return createDefaultProductWithId(id).toDTO();
    }

    /**
     * Creates a ProductDTO with a non-existent category.
     */
    public static ProductDTO createDefaultProductDTOWithNonExistentCategory() {
        return createProduct(
                        null,
                        DEFAULT_NAME,
                        DEFAULT_DESCRIPTION,
                        DEFAULT_PRICE,
                        DEFAULT_IMAGE_URL,
                        createCategory(null, "Unknown"))
                .toDTO();
    }
}
