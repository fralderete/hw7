package blockbuster;

public class XDollarsOffCoupon extends CouponTypeDecorator {

    public XDollarsOffCoupon(Coupon newCoupon, double totalPrice, double minimumRentalPrice) {
        super(newCoupon, totalPrice, minimumRentalPrice);
    }

    @Override
    public void writeData() {
        _totalPrice = totalPrice;
    }

    @Override
    public String getCouponType() {
        return ProgramProperties.X_USD_OFF_COUPON;
    }

    public double couponModifiedPrice(double frequentRenterPoints) {
        if ( _totalPrice > xDollarOffThreshold) {
            // System.out.println("You qualified for ten dollars off of your purchase.");
            // the dollar amount discounted can be changed here
            _totalPrice = _totalPrice - ProgramProperties.X_DOLLARS;

        }

        return _totalPrice;

    }

}
