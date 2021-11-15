package worker;

import vehicle.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public class LockedNameRunnable implements Runnable {

    private Vehicle vehicle;
    private ReentrantLock lock;


    public LockedNameRunnable(Vehicle vehicle, ReentrantLock lock) {

        this.vehicle = vehicle;
        this.lock = lock;
    }

    @Override
    public void run() {

        try {

            String[] names = vehicle.getModelsNames();
            lock.lock();

            for (int i = 0; i < vehicle.getCountOfModels(); i++) {

                System.out.println(names[i]);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }
    }
}
