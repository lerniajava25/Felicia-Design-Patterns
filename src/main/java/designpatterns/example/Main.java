package designpatterns.example;

import designpatterns.example.model.Category;
import designpatterns.example.model.DiscountDecorator;
import designpatterns.example.model.Product;
import designpatterns.example.model.Sellable;


import static java.lang.Double.valueOf;

public class Main {
    public static void main()
    {
        Product.Builder builder = new Product.Builder();
        builder.id(42);
        builder.name("The Best Movie");
        builder.price(valueOf(100.0)); builder.category(Category.MOVIE);
        builder.rating(8);
        Product movie = builder
                .build();

        Sellable discountedMovie = new DiscountDecorator(movie, 25);

        IO.println("Before discount: " + movie.getPrice());
        IO.println("After discount: " + discountedMovie.getPrice());
    }
}
