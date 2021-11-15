package worker;

import vehicle.Vehicle;

public class VehicleSynchronizer {
    private Vehicle vehicle;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public VehicleSynchronizer(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double printPrice() throws InterruptedException {

        double val;
        synchronized(lock) {

            double [] prices = vehicle.getModelsPrices();
            if (!canPrintPrice()) throw new InterruptedException();

            while (!set)
                lock.wait();
            val = prices[current++];
            System.out.println("Print price: " + val);
            set = false;
            lock.notifyAll();
        }
        return val;
    }

    public void printName() throws InterruptedException {

        synchronized(lock) {

            String [] names = vehicle.getModelsNames();
            if (!canPrintName()) throw new InterruptedException();
            while (set)
                lock.wait();
            System.out.println("Print model: " + names[current]);
            set = true;
            lock.notifyAll();
        }
    }

    public boolean canPrintPrice() {

        return current < vehicle.getCountOfModels();
    }

    public boolean canPrintName() {

        return (!set && current < vehicle.getCountOfModels()) || (set && current < vehicle.getCountOfModels() - 1);
    }
}
