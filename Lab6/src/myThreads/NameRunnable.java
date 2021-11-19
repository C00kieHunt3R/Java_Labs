package myThreads;

public class NameRunnable implements Runnable {

    private VehicleSynchronizer vehicle;

    public NameRunnable(VehicleSynchronizer vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        try {
            while (vehicle.canPrintName()) {
                vehicle.printName();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
