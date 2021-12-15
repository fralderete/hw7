package blockbuster;

public interface Product {
    public String _title = "";
    public boolean _isRental = false;
    public double _price = 0.0;

    public double getPrice();

    public String getTitle();

}
