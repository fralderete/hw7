package blockbuster;

public class PercentOffCoupon extends CouponTypeDecorator {
    public PercentOffCoupon(Coupon newCoupon, double totalPrice, double minimumRentalPrice) {
        super(newCoupon, totalPrice, minimumRentalPrice);

    }

    @Override
    public void writeData() {
        _totalPrice = totalPrice;
    }

    @Override
    public String getCouponType() {
        return ProgramProperties.X_PERCENT_OFF; // placeholder

    }

    public double couponModifiedPrice(double frequentRenterPoints) {
        // System.out.println("You have applied a half off coupon to your purchase!");
        _totalPrice = _totalPrice - (_totalPrice * ProgramProperties.X_PERCENT);
        return _totalPrice;

    }

}
