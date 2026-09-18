package designpatterns.example.model;

public class DiscountDecorator extends ProductDecorator {

    private double discountPercentage;

    public DiscountDecorator(Sellable decoratedProduct, double discountPercentage) {
        super(decoratedProduct);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getPrice() {
        double originalPrice = decoratedProduct.getPrice();
        return originalPrice - (originalPrice * discountPercentage / 100.0);
    }
}