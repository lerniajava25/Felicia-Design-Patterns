package designpatterns.builder;

import java.time.LocalDate;

public class Product {

    private final String id;
    private final String name;
    private final Category category;
    private final int rating;
    private final LocalDate createdDate;

    private Product(String id, String name, Category category,
                    int rating, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.createdDate = createdDate;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public int getRating() { return rating; }
    public LocalDate getCreatedDate() { return createdDate; }

    public static class Builder {
        private String id;
        private String name;
        private Category category;
        private int rating;
        private LocalDate createdDate;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder createdDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Product build() {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalStateException("Product name can't be empty");
            }
            if (rating < 0 || rating > 10) {
                throw new IllegalStateException("Rating must be between 0 and 10");
            }

            if (createdDate == null) {
                createdDate = LocalDate.now();
            }

            return new Product(id, name, category, rating, createdDate);
        }
    }
}