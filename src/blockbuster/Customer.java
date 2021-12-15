package blockbuster;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private String _name;
    private int _age;
    private double _totalCharge;
    int rentalPriceListSize;
    double minimumRentalPrice;

    List<Double> rentalPrices = new ArrayList<>();
    List<Double> purchasePrices = new ArrayList<>();

    private List<ProductTransaction> rentalProducts = new ArrayList<>();
    private List<ProductTransaction> purchaseProducts = new ArrayList<>();

    // added to include age, previous method didn't accept an additional parameter
    public void setName (String name) {
        _name = name;
    }

    public void setAge (int age) {
        _age = age;
    }

    public void addRental(ProductTransaction rental) {
        rentalProducts.add(rental);
    }

    public void addPurchase(ProductTransaction purchase) {
        purchaseProducts.add(purchase);
    }

    public String getName() {
        return _name;
    }

    public int getAge() { return _age; }

    public String htmlRentalReceipt(FRPDoubler frpDoubler) {
        // boolean _multipleGenre = frpDoubler.getMultipleGenre();
        // boolean _newMovie = frpDoubler.getNewMovie();

        String result = "\n<table><h1>Transaction record for <em>" + getName() + "</em></h1>\n";

        // following two for loops gather price information for rentals/purchases of products
        for (ProductTransaction rentalProduct : rentalProducts) {
            result += "\t<tr><td> Your rental price for " + rentalProduct.getProduct().getTitle() + "</td><td>" + ": $"
                    + rentalProduct.getProduct().getPrice() + "0</td></tr>\n";

            rentalPrices.add(rentalProduct.getRentalPrice());

        }
        
        for (ProductTransaction purchaseProduct : purchaseProducts) {
            result += "\t<tr><td> Your purchase price for " + purchaseProduct.getProduct().getTitle() + "</td><td>" + ": $"
                    + purchaseProduct.getProduct().getPrice() + "0</td></tr>\n";

            purchasePrices.add(purchaseProduct.getProduct().getPrice());

        }

        minimumRentalPrice = rentalPrices.get(0);
        rentalPriceListSize = rentalPrices.size();

        // determining the least expensive rental for free coupon
        for (int i = 1; i < rentalPriceListSize; i++) {
            if (rentalPrices.get(i) < minimumRentalPrice) {
                minimumRentalPrice = rentalPrices.get(i);
            }
        }

        result += "\n<p>Amount owed for rentals is <em>$" + getRentalCharge() + "</em></p>\n";
        result += "<p>You earned <em>" + getTotalFrequentRenterPoints()
                + "</em> frequent renter points</p>\n";
        result += "<p>Amount owed for purchases is <em>$" + getPurchaseCharge() + "</em></p>\n";

        setTotalCharge(getRentalCharge() + getPurchaseCharge());

        result += "\n<p>Total amount before coupons is <em>$" + _totalCharge + "</em></p>\n";

        // coupons applied after total collected

        // percent off amount can be changed in program properties
        Coupon percentOffCoupon = new PercentOffCoupon(null, _totalCharge, minimumRentalPrice);
        setTotalCharge(percentOffCoupon.couponModifiedPrice(frpDoubler.getDoubleFRP()));
        result += "<p>Your total after " + percentOffCoupon.getCouponType() + " is: <em>$" + _totalCharge + "</em></p>\n";

        // ten dollars off if you have more than 50 dollars of merchandise. Coupon threshold and amount discounted
        // can be changed in program properties
        Coupon xDollarsOffCoupon = new XDollarsOffCoupon(null, _totalCharge, minimumRentalPrice);
        setTotalCharge(xDollarsOffCoupon.couponModifiedPrice(frpDoubler.getDoubleFRP()) );
        result += "<p>Your total after " + xDollarsOffCoupon.getCouponType() + " is: <em>$" + _totalCharge + "</em></p>\n";

        // lowest priced rental is free if frequent renter points > 10. threshold can be changed in program properties
        Coupon freeRentalCoupon = new FreeRentalCoupon(null, _totalCharge, minimumRentalPrice);
        setTotalCharge(freeRentalCoupon.couponModifiedPrice(frpDoubler.getDoubleFRP()));
        result += "<p>Your total after " + freeRentalCoupon.getCouponType() + " is: <em>$" + _totalCharge + "</em></p></table>\n";

        return result;
    }

    public double getRentalCharge() {
        double total = 0;
        for (ProductTransaction rental : rentalProducts)
            total += rental.getRentalPrice();

        return total;
    }

    public double getPurchaseCharge() {
        double total = 0;
        for (ProductTransaction purchase : purchaseProducts)
            total += purchase.getProduct().getPrice();
        return total;
    }


    public void setTotalCharge(double totalCharge) {
        _totalCharge = totalCharge;

    }

    public  double getTotalFrequentRenterPoints() {
        double total = 0;
        for (ProductTransaction rental: rentalProducts)
            total += rental.getFrequentRenterPoints();
        return total;
    }
}