import designpatterns.builder.Category;
import designpatterns.builder.Product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ProductTest {

    @Test
    @DisplayName("Happy path - Alla fält tillsammans")
    void build_filledFields_returnsProductWithSameValues() {
        LocalDate created = LocalDate.of(2026, 9, 15);

        Product product = new Product.Builder()
                .id(1)
                .name("Gladiator")
                .category(Category.MOVIE)
                .rating(8)
                .createdDate(created)
                .build();

        assertThat(product.getId()).isEqualTo(1);
        assertThat(product.getName()).isEqualTo("Gladiator");
        assertThat(product.getCategory()).isEqualTo(Category.MOVIE);
        assertThat(product.getRating()).isEqualTo(8);
        assertThat(product.getCreatedDate()).isEqualTo(created);
    }

    @Test
    @DisplayName("Builder-metoder stämmer")
    void builderMethods_returnSameBuilderInstance() {
        Product.Builder builder = new Product.Builder();

        Product.Builder afterId = builder.id(6);
        Product.Builder afterName = afterId.name("Scary Movie");

        assertThat(afterId).isSameAs(builder);
        assertThat(afterName).isSameAs(builder);
    }

    @Test
    @DisplayName("Default för createdDate")
    void build_noCreatedDate_defaultsToToday() {
        Product product = new Product.Builder()
                .id(7)
                .name("New Show")
                .category(Category.SERIES)
                .rating(5)
                .build();

        assertThat(product.getCreatedDate()).isEqualTo(LocalDate.now());
    }

    @Test
    @DisplayName("Throws exception om namn är null")
    void build_withNullName_throwsIllegalStateException() {
        Product.Builder builder = new Product.Builder()
                .id(8)
                //Name = null
                .category(Category.MOVIE)
                .rating(5);

        assertThatThrownBy(builder::build)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Throws exception om namn är tomt")
    void build_withEmptyName_throwsIllegalStateException() {
        Product.Builder builder = new Product.Builder()
                .id(9)
                .name("   ")
                .category(Category.MOVIE)
                .rating(5);

        assertThatThrownBy(builder::build)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Throws exception om rating är över 10")
    void build_withRatingTooHigh_throwsIllegalStateException() {
        Product.Builder builder = new Product.Builder()
                .id(10)
                .name("The Best Movie")
                .category(Category.MOVIE)
                .rating(50);

        assertThatThrownBy(builder::build)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Throws exception om rating är under 10")
    void build_withNegativeRating_throwsIllegalStateException() {
        Product.Builder builder = new Product.Builder()
                .id(11)
                .name("The Worst Movie")
                .category(Category.MOVIE)
                .rating(-1);

        assertThatThrownBy(builder::build)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Throws exception om id är mindre än 1")
    void build_withNegativeId_throwsIllegalStateException() {
        Product.Builder builder = new Product.Builder()
                .id(-1)
                .name("Fake Movie")
                .category(Category.MOVIE)
                .rating(10);

        assertThatThrownBy(builder::build)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Självständighet mellan builders")
    void twoProductsBuiltFromDifferentBuilders_areIndependent() {
        Product productA = new Product.Builder()
                .id(20)
                .name("A Movie")
                .category(Category.MOVIE)
                .rating(4)
                .build();

        Product productB = new Product.Builder()
                .id(21)
                .name("A Series")
                .category(Category.SERIES)
                .rating(9)
                .build();

        assertThat(productA.getId()).isNotEqualTo(productB.getId());
        assertThat(productA.getName()).isNotEqualTo(productB.getName());
        assertThat(productA.getCategory()).isNotEqualTo(productB.getCategory());
        assertThat(productA.getRating()).isNotEqualTo(productB.getRating());
    }
}
