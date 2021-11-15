package worker;

import vehicle.Vehicle;

public class PriceThread extends Thread{
    private Vehicle vehicle;

    public PriceThread(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        double[] prices = vehicle.getModelsPrices();
        for (double price : prices) {
            System.out.println(price);
        }
    }
}
