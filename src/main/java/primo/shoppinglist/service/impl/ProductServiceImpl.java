package primo.shoppinglist.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import primo.shoppinglist.data.entity.ProductEntity;
import primo.shoppinglist.data.entity.enumeration.CategoryEnum;
import primo.shoppinglist.data.service.ProductServiceModel;
import primo.shoppinglist.data.view.ProductViewModel;
import primo.shoppinglist.repository.ProductRepository;
import primo.shoppinglist.service.CategoryService;
import primo.shoppinglist.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public BigDecimal getTotalSum() {
        BigDecimal value = productRepository.findTotalPriceOfAllProducts();
        return value != null ? value : BigDecimal.ZERO;
    }

    @Override
    public List<ProductViewModel> getProductsByCategory(CategoryEnum category) {
        return productRepository.findAllByCategory(categoryService.getCategoryByName(category))
                .stream()
                .map(p -> mapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buyById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public void buyAllProducts() {
        productRepository.deleteAll();
    }
}
