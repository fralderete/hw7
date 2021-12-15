package blockbuster;

// facade
public class ProductTransaction {

    private ProductFacade _product;

    private int  _daysRented;
    private PriceStrategy priceStrategy;
    private FrequentRenterPointsStrategy frequentRenterPointsStrategy;

    public ProductTransaction(ProductFacade product, int daysRented) {
        _product = product;
        _daysRented = daysRented;
    }

    public ProductFacade getProduct() { return _product; }

    public String getGenre() { return _product.getGenre(); }

    public int getDaysRented() { return _daysRented; }

    public double getRentalPrice() {
        return priceStrategy.computeRentalPrice(_daysRented);
    }

    public double getFrequentRenterPoints() {
        return frequentRenterPointsStrategy.computeFrequentRenterPoints();
    }

    public void setFrequentRenterPointsStrategy(FrequentRenterPointsStrategy strategy) {
        this.frequentRenterPointsStrategy = strategy;
    }

    public void setPriceStrategy(PriceStrategy strategy) {
        this.priceStrategy = strategy;
    }

}