package primo.shoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import primo.shoppinglist.data.entity.CategoryEntity;
import primo.shoppinglist.data.entity.ProductEntity;
import primo.shoppinglist.data.entity.enumeration.CategoryEnum;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    @Query("SELECT SUM(p.price) FROM ProductEntity p")
    BigDecimal findTotalPriceOfAllProducts();
    List<ProductEntity> findAllByCategory(CategoryEntity category);
}
