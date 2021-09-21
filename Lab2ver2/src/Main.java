import exception.*;
import tool.*;
import vehicle.*;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException {

        Car bmw = new Car("BMW", 5);

        bmw.addModel("M5", 9600000.7);
        bmw.addModel("Z4 Roadster", 4200000.8);

        bmw.setModelName("M5", "M7");
        bmw.setModelPrice("M7", 999999.9);

        bmw.deleteModel("M7");

        //System.out.println(VehicleHandler.getAverage(bmw));
        //VehicleHandler.printModelsNames(bmw);
        //VehicleHandler.printModelsPrices(bmw);
        //System.out.println(bmw);

        Motorcycle yamaha = new Motorcycle("YAMAHA", 3);

        yamaha.addModel("YZF", 765432.1);
        yamaha.addModel("XT1200", 1383000.8);
        yamaha.addModel("MT10", 9214121.2);

        yamaha.setModelName("YZF", "YTF");
        yamaha.setModelPrice("YTF", 857329.6);

        yamaha.deleteModel("YTF");

        //System.out.println(VehicleHandler.getAverage(yamaha));
        //VehicleHandler.printModelsNames(yamaha);
        //VehicleHandler.printModelsPrices(yamaha);
        //System.out.println(yamaha);

    }
}
