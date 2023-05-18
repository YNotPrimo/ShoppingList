package primo.shoppinglist.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import primo.shoppinglist.data.entity.ProductEntity;
import primo.shoppinglist.data.service.ProductServiceModel;
import primo.shoppinglist.repository.ProductRepository;
import primo.shoppinglist.service.CategoryService;
import primo.shoppinglist.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapper mapper;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ModelMapper mapper, CategoryService categoryService, ProductRepository productRepository) {
        this.mapper = mapper;
        this.categoryService = categoryService;
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(ProductServiceModel model) {
        ProductEntity productEntity = mapper.map(model, ProductEntity.class);
        productEntity.setCategory(categoryService.getCategoryByName(model.getCategory()));

        productRepository.saveAndFlush(productEntity);
    }
}
