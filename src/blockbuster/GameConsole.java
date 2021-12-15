package blockbuster;

import javax.swing.*;

public class GameConsole extends ProductFacade {
    private String _title;
    private double _price;

    public GameConsole(String title, double price, String genre, int releaseDate) {
        super(null, title, price, releaseDate);
        _title = title;
    }

    @Override
    public double getPrice() {
        switch (_title) {
            case "PS4" :    _price = ProgramProperties.ps4Price;
                            break;

            case "Xbox" :   _price = ProgramProperties.xboxPrice;
                            break;

            case "Switch" : _price = ProgramProperties.switchPrice;
                            break;

            default :       System.out.println("Invalid console type. Try again.");
                            _price = ProgramProperties.zero;
                            break;
        }

        return _price;

    }

}
