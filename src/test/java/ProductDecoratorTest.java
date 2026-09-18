import designpatterns.example.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductDecoratorTest {

    @Test
    @DisplayName("Rabatten läggs till utan att förändra orginal-produkten")
    void discountDecorator_reducesPrice_unchangedOriginalProduct() {
        Product movie = new Product.Builder()
                .id(1)
                .name("The Best Movie")
                .price(1000.0)
                .category(Category.MOVIE)
                .rating(8)
                .build();

        Sellable discountedMovie = new DiscountDecorator(movie, 20);

        assertThat(movie.getPrice()).isEqualTo(1000.0);
        assertThat(discountedMovie.getPrice()).isEqualTo(800.0);
    }

    @Test
    @DisplayName("Id och namn hämtas oförändrade från orginal-produkten")
    void discountDecorator_delegatesIdAndName_unchanged() {
        Product show = new Product.Builder()
                .id(2)
                .name("Great Series")
                .price(1500.0)
                .category(Category.SERIES)
                .rating(9)
                .build();

        Sellable discountedShow = new DiscountDecorator(show, 10);

        assertThat(discountedShow.getId()).isEqualTo(show.getId());
        assertThat(discountedShow.getName()).isEqualTo(show.getName());
    }

    @Test
    @DisplayName("0% rabatt ger orignalpriset")
    void discountDecorator_withZeroPercent_leavesPriceUnchanged() {
        Product item = new Product.Builder()
                .id(3)
                .name("Full Price Movie")
                .price(50.0)
                .category(Category.MOVIE)
                .rating(5)
                .build();

        Sellable noDiscount = new DiscountDecorator(item, 0);

        assertThat(noDiscount.getPrice()).isEqualTo(50.0);
    }

    @Test
    @DisplayName("100% rabatt ger priset 0.0")
    void discountDecorator_withHundredPercent_makesPriceZero() {
        Product item = new Product.Builder()
                .id(4)
                .name("Free Movie")
                .price(200.0)
                .category(Category.MOVIE)
                .rating(5)
                .build();

        Sellable fullDiscount = new DiscountDecorator(item, 100);

        assertThat(fullDiscount.getPrice()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Produkt använder Sellable")
    void product_directlyImplementsSellable() {

        Sellable sellable = new Product.Builder()
                .id(6)
                .name("Plain Movie")
                .price(49.99)
                .category(Category.MOVIE)
                .rating(7)
                .build();

        assertThat(sellable.getId()).isEqualTo(6);
        assertThat(sellable.getName()).isEqualTo("Plain Movie");
        assertThat(sellable.getPrice()).isEqualTo(49.99);
    }

    @Test
    @DisplayName("getPrice() ger orginalpriset när decoratorn inte är overrided")
    void getPrice_delegatesToDecoratedProduct_whenNotOverridden() {
        Product movie = new Product.Builder()
                .id(101)
                .name("Delegated Movie")
                .price(25.0)
                .category(Category.MOVIE)
                .rating(6)
                .build();

        Sellable decorator = new ProductDecorator(movie) { };

        assertThat(decorator.getPrice()).isEqualTo(movie.getPrice());
        assertThat(decorator.getPrice()).isEqualTo(25.0);
    }
}
