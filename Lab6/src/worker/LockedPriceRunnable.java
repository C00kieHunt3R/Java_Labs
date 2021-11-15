package worker;

import vehicle.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public class LockedPriceRunnable implements Runnable {

    private Vehicle vehicle;
    private ReentrantLock lock;


    public LockedPriceRunnable(Vehicle vehicle, ReentrantLock lock) {

        this.vehicle = vehicle;
        this.lock = lock;
    }

    @Override
    public void run() {

        try {

            double[] prices = vehicle.getModelsPrices();
            lock.lock();

            for (int i = 0; i < vehicle.getCountOfModels(); i++) {

                System.out.println(prices[i]);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }
    }
}