package blockbuster;

abstract class ProductFacade implements Product {
    private String _title;
    private double _price;
    public int _releaseDate;

    protected Product _tempProduct;

    public ProductFacade(Product newProduct, String title, double price, int releaseDate) {
        _tempProduct = newProduct;
        _price = price;
        _title = title;
        _releaseDate = releaseDate;
    }

    public double getPrice() { return _price; }

    public String getTitle() { return _title; }

    public int getReleaseDate() { return _releaseDate; }

    public String getGenre() {return "";}
}
