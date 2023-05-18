package primo.shoppinglist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import primo.shoppinglist.data.entity.CategoryEntity;
import primo.shoppinglist.data.entity.enumeration.CategoryEnum;
import primo.shoppinglist.repository.CategoryRepository;
import primo.shoppinglist.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() {
        if (categoryRepository.count() != 0) {
            return;
        }
        Arrays.stream(CategoryEnum.values()).forEach(
                category -> {
                    CategoryEntity ent = new CategoryEntity(category,
                            "A description for the " + category.name());

                    categoryRepository.save(ent);
                }
        );

    }

    @Override
    public CategoryEntity getCategoryByName(CategoryEnum category) {
        return categoryRepository.findByCategory(category);
    }
}
