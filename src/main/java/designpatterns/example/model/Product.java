package designpatterns.example.model;
import java.time.LocalDate;

public class Product implements Sellable {

    private final int id;
    private final String name;
    private final double price;
    private final Category category;
    private final int rating;
    private final LocalDate createdDate;

    private Product(int id, String name, double price, Category category,
                    int rating, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.rating = rating;
        this.createdDate = createdDate;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() { return price; }


    public Category getCategory() { return category; }
    public int getRating() { return rating; }
    public LocalDate getCreatedDate() { return createdDate; }

    //Builder
    public static class Builder {
        private int id;
        private String name;
        private double price;
        private Category category;
        private int rating;
        private LocalDate createdDate;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
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
            if (id < 1) {
                throw new IllegalStateException("Id must be 1 or higher");
            }
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalStateException("Product name can't be empty");
            }
            if (rating < 0 || rating > 10) {
                throw new IllegalStateException("Rating must be between 0 and 10");
            }

            if (createdDate == null) {
                createdDate = LocalDate.now();
            }

            return new Product(id, name, price, category, rating, createdDate);
        }
    }
}
