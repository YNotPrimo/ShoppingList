package primo.shoppinglist.service;

import primo.shoppinglist.data.entity.CategoryEntity;
import primo.shoppinglist.data.entity.enumeration.CategoryEnum;

public interface CategoryService {
    void seedCategories();

    CategoryEntity getCategoryByName(CategoryEnum category);
}
