package primo.shoppinglist.service;
import primo.shoppinglist.data.entity.enumeration.CategoryEnum;
import primo.shoppinglist.data.service.ProductServiceModel;
import primo.shoppinglist.data.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel map);

    BigDecimal getTotalSum();

    List<ProductViewModel> getProductsByCategory(CategoryEnum categoryEnum);

    void buyById(String id);

    void buyAllProducts();
}
