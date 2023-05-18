package primo.shoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import primo.shoppinglist.data.entity.CategoryEntity;
import primo.shoppinglist.data.entity.enumeration.CategoryEnum;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    CategoryEntity findByCategory(CategoryEnum category);
}
