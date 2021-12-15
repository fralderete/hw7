package blockbuster;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class blockbusterRunner {

    public static void main(String[] args) {

        FRPDoubler frpDoubler = new FRPDoubler(false, false);
        Customer customer = new Customer();
        customer.setName("Floop Kartsenflogger");
        customer.setAge(21);

        List<ProductTransaction> rentalProducts = new ArrayList<>();
        List<ProductTransaction> purchaseProducts = new ArrayList<>();

        // grab any items added to the transaction about to occur
        populateRentalProductSelection();
        populatePurchaseProductSelection();

        // rentals
        rentalProducts.add(new ProductTransaction(ProgramProperties.movieRentalSelection.get(0), ProgramProperties.TWO_DAYS_RENTED));
        rentalProducts.add(new ProductTransaction(ProgramProperties.movieRentalSelection.get(1), ProgramProperties.THREE_DAYS_RENTED));
        rentalProducts.add(new ProductTransaction(ProgramProperties.bookRentalSelection.get(0), ProgramProperties.ONE_DAY_RENTED));
        rentalProducts.add(new ProductTransaction(ProgramProperties.gameRentalSelection.get(0), ProgramProperties.ONE_MONTH_RENTED));

        // purchases
        purchaseProducts.add(new ProductTransaction(ProgramProperties.gamePurchaseSelection.get(0), 0));
        purchaseProducts.add(new ProductTransaction(ProgramProperties.moviePurchaseSelection.get(0), 0));
        purchaseProducts.add(new ProductTransaction(ProgramProperties.bookPurchaseSelection.get(0), 0));
        purchaseProducts.add(new ProductTransaction(ProgramProperties.consoleSelection.get(0), 0));

        setRentalProductStrategies(rentalProducts);

        // send rentals and purchases to the final processing
        addCustomerRentals(customer, rentalProducts);
        addCustomerPurchases(customer, purchaseProducts);

        // some processing to see if they qualify for double points
        List<String> uniqueGenre = rentalProducts.stream().map(ProductTransaction::getGenre).distinct().collect(Collectors.toCollection(ArrayList::new));
        List<Integer> daysRented = rentalProducts.stream().map(ProductTransaction::getDaysRented).collect(Collectors.toList());

        setFRPDoubler(customer, uniqueGenre, daysRented, frpDoubler);

        setFRPStrategies(rentalProducts);

        System.out.println(customer.htmlRentalReceipt(frpDoubler));

    }

    // add any rentals here
    public static void populateRentalProductSelection() {
        ProgramProperties.movieRentalSelection.add(new Movie("Jurassic Park", ProgramProperties.movieRentalPrice, ProgramProperties.THRILLER_GENRE, ProgramProperties.TWO_YEARS));
        ProgramProperties.movieRentalSelection.add(new Movie("Parasite", ProgramProperties.movieRentalPrice, ProgramProperties.THRILLER_GENRE, ProgramProperties.ONE_WEEK));
        ProgramProperties.bookRentalSelection.add(new Book("The Manly Art of Knitting", ProgramProperties.bookRentalPrice, ProgramProperties.SELF_HELP_GENRE, ProgramProperties.TWO_WEEKS));
        ProgramProperties.gameRentalSelection.add(new VideoGame("Skyrim 2", ProgramProperties.videoGameRentalPrice, ProgramProperties.RPG_GENRE, ProgramProperties.TWO_YEARS));
    }

    // add any purchases here
    public static void populatePurchaseProductSelection() {
        ProgramProperties.gamePurchaseSelection.add(new VideoGame("Halo Infinite", ProgramProperties.videoGamePurchasePrice, ProgramProperties.FPS_GENRE, ProgramProperties.TWO_WEEKS));
        ProgramProperties.moviePurchaseSelection.add(new Movie("Empire Strikes Back", ProgramProperties.moviePurchasePrice, ProgramProperties.CHILDRENS_GENRE, ProgramProperties.THREE_WEEKS));
        ProgramProperties.bookPurchaseSelection.add(new Book("1984", ProgramProperties.bookPurchasePrice, ProgramProperties.DYSTOPIAN_GENRE, ProgramProperties.ANCIENT));

        // special case, I made consoles purchase only, price is set at it's own class
        ProgramProperties.consoleSelection.add(new GameConsole("Xbox", 0, "", 0));
    }

    public static void setRentalProductStrategies(List<ProductTransaction> products) {
        for(ProductTransaction r : products)
            if (r.getProduct().getReleaseDate() < ProgramProperties.TWO_WEEKS) r.setPriceStrategy(new NewPriceStrategy());
            else if (r.getProduct().getGenre().equalsIgnoreCase(ProgramProperties.CHILDRENS_GENRE))
                r.setPriceStrategy(new ChildrensPriceStrategy());
            else r.setPriceStrategy(new RegularPriceStrategy());
    }

    public static void setFRPStrategies(List<ProductTransaction> rentals) {
        for(ProductTransaction r : rentals) {
            if (r.getProduct().getReleaseDate() < ProgramProperties.TWO_WEEKS) {
                r.setFrequentRenterPointsStrategy(new BonusFrequentRenterPointsStrategy());
            } else {
                r.setFrequentRenterPointsStrategy(new FrequentRenterPointsStrategy());
            }
        }
    }

    public static void setFRPDoubler(Customer customer, List<String> genres, List<Integer> daysRented, FRPDoubler frpDoubler) {
        int numberOfGenres = 0;
        int customerAge = customer.getAge();
        boolean doubleForAgeNew = false;
        boolean doubleForMultipleGenre = false;

        for(String g : genres) numberOfGenres++;

        // if customer rents any new movies while being 18-22 double points
        if(customerAge <= 22 && customerAge >= 18) for (Integer d : daysRented)
            if (d < ProgramProperties.TWO_WEEKS) { doubleForAgeNew = true; }

        // indicate double renter points
        if (numberOfGenres > 1) doubleForMultipleGenre = true;

        frpDoubler.setNewMovie(doubleForAgeNew);
        frpDoubler.setMultipleGenre(doubleForMultipleGenre);

    }

    public static void addCustomerRentals(Customer customer, List<ProductTransaction> rentals) {
        for(ProductTransaction r : rentals) customer.addRental(r);
    }

    public static void addCustomerPurchases(Customer customer, List<ProductTransaction> purchases) {
        for(ProductTransaction p : purchases) customer.addPurchase(p);
    }

}
