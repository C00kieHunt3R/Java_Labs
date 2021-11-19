package myThreads;

public class PriceRunnable implements Runnable {

    private VehicleSynchronizer vehicle;


    public PriceRunnable(VehicleSynchronizer vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        try {
            while (vehicle.canPrintPrice()) {
                vehicle.printPrice();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
