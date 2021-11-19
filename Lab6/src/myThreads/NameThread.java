package myThreads;

import vehicle.Vehicle;

public class NameThread extends Thread {
    private Vehicle vehicle;

    public NameThread(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void run() {
        String[] names = vehicle.getModelsNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
