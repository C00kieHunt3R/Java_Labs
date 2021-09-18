package tool;

import vehicle.Vehicle;

public class VehicleHandler {

    public static double getAverage(Vehicle vehicle) {
        double sum = 0;
        for (double price : vehicle.getModelsPrices()) {
            sum += price;
        }
        return sum/vehicle.getModelsPrices().length;
    }

    public static void printModelsNames(Vehicle vehicle) {
        for (String name : vehicle.getModelsNames()) {
            System.out.println(name);
        }
    }

    public static void printModelsPrices(Vehicle vehicle) {
        for (double price : vehicle.getModelsPrices()) {
            System.out.println(price);
        }
    }
}
