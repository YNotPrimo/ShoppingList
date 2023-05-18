package primo.shoppinglist.data.entity;

import jakarta.persistence.*;
import primo.shoppinglist.data.entity.enumeration.CategoryEnum;

@Entity
@Table(name = "Category")
public class CategoryEntity extends BaseEntity {
    private CategoryEnum category;
    private String description;

    public CategoryEntity() {
    }

    public CategoryEntity(CategoryEnum category, String desc) {
        this.category = category;
        this.description = desc;
    }

    @Enumerated(EnumType.STRING)
    public CategoryEnum getCategory() {
        return category;
    }

    public CategoryEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
