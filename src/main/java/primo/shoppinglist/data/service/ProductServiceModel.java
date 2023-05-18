package primo.shoppinglist.data.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import primo.shoppinglist.data.binding.ProductAddBindingModel;
import primo.shoppinglist.data.entity.enumeration.CategoryEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductServiceModel {
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime neededBefore;
    private CategoryEnum category;

    public ProductServiceModel() {
    }
    @Length(min = 3, max = 20, message = "The name must be between 3 and 20 characters")
    public String getName() {
        return name;
    }

    public ProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    @Length(min = 5, message = "The description must be at least 5 characters")
    public String getDescription() {
        return description;
    }

    public ProductServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }
    @DecimalMin(value = "0", message = "The price must be a positive number")
    public BigDecimal getPrice() {
        return price;
    }

    public ProductServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be before the present day")
    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public ProductServiceModel setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    @NotNull(message = "Invalid category")
    public CategoryEnum getCategory() {
        return category;
    }

    public ProductServiceModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
