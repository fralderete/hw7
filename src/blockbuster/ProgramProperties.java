package blockbuster;

import java.util.ArrayList;
import java.util.List;

public class ProgramProperties {
    static List<Movie> movieRentalSelection = new ArrayList<>();
    static List<Movie> moviePurchaseSelection = new ArrayList<>();
    static List<VideoGame> gameRentalSelection = new ArrayList<>();
    static List<VideoGame> gamePurchaseSelection = new ArrayList<>();
    static List<GameConsole> consoleSelection = new ArrayList<>();
    static List<Book> bookRentalSelection = new ArrayList<>();
    static List<Book> bookPurchaseSelection = new ArrayList<>();

    static final int TWO_DAYS_RENTED = 2;
    static final int ONE_DAY_RENTED = 1;
    static final int THREE_DAYS_RENTED = 3;
    static final int FOUR_DAYS_RENTED = 4;
    static final int ONE_MONTH_RENTED = 30;

    static final int TWO_WEEKS = 2;
    static final int TWO_YEARS = 104;
    static final int ANCIENT = 999;
    static final int THREE_WEEKS = 3;
    static final int ONE_WEEK = 1;

    static final double movieRentalPrice = 4.99;
    static final double moviePurchasePrice = 24.99;
    static final double bookRentalPrice = 2.99;
    static final double bookPurchasePrice = 14.99;
    static final double videoGameRentalPrice = 5.99;
    static final double videoGamePurchasePrice = 59.99;
    static final double ps4Price = 349.99;
    static final double xboxPrice = 299.99;
    static final double switchPrice = 249.99;

    static final double zero = 0.0;

    static final String CHILDRENS_GENRE = "CHILDRENS";
    static final String THRILLER_GENRE = "THRILLER";
    static final String FPS_GENRE = "FIRST PERSON SHOOTER";
    static final String RPG_GENRE = "ROLE PLAYING GAME";
    static final String DYSTOPIAN_GENRE = "DYSTOPIAN";
    static final String SELF_HELP_GENRE = "SELF HELP";

    static final double X_DOLLARS = 10.00;
    static final double X_OFF_THRESHOLD = 50.00;
    static final int FRP_THRESHOLD = 10;


    static final double X_PERCENT = .1;
    static final String X_PERCENT_OFF = "10% off coupon";
    static final String HALF_OFF_COUPON = "50% off coupon";
    static final String X_USD_OFF_COUPON = "10 dollars off coupon";
    static final String FREE_RENTAL_COUPON = "free rental coupon";

}
