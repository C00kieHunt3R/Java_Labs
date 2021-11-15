package worker;

import vehicle.Vehicle;

public class BrandRunnable implements Runnable {

    private Vehicle vehicle;

    public BrandRunnable(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        System.out.println(vehicle.getBrand());
    }
}
