package blockbuster;

public class VideoGame extends ProductFacade {
    private String _title;
    private double _price;
    private int _releaseDate;
    private String _genre;

    public VideoGame(String title, double price, String genre, int releaseDate) {
        super(null, title, price, releaseDate);
        _title = title;
        _genre = genre;
        _price = price;
        _releaseDate = releaseDate;
    }

    @Override
    public double getPrice() {
        return _price;
    }

    @Override
    public String getTitle() {
        return _title;
    }

    @Override
    public int getReleaseDate() {
        return _releaseDate;
    }

    @Override
    public String getGenre() {
        return _genre;
    }

}
