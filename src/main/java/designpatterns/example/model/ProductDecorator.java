package designpatterns.example.model;

public abstract class ProductDecorator implements Sellable {

    protected Sellable decoratedProduct;

    protected ProductDecorator (Sellable decoratedProduct) {
        this.decoratedProduct = decoratedProduct;
    }

    @Override
    public int getId() {
        return decoratedProduct.getId();
    }

    @Override
    public String getName() {
        return decoratedProduct.getName();
    }

    @Override
    public double getPrice() {
        return decoratedProduct.getPrice();
    }

}
