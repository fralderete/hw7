package blockbuster;

public class FreeRentalCoupon extends CouponTypeDecorator {
    public FreeRentalCoupon(Coupon newCoupon, double totalPrice, double minimumRentalPrice) {
        super(newCoupon, totalPrice, minimumRentalPrice);

    }

    @Override
    public void writeData() {
        _totalPrice = totalPrice;
    }

    @Override
    public String getCouponType() {
        return ProgramProperties.FREE_RENTAL_COUPON;
    }

    public double couponModifiedPrice(double frequentRenterPoints) {
        if (frequentRenterPoints >= freeRentalThreshold) {
            // System.out.println("You have qualified for a free movie! You saved: $" + _minimumRentalPrice);
            _totalPrice = _totalPrice - _minimumRentalPrice;

        }

        return _totalPrice;

    }

}
