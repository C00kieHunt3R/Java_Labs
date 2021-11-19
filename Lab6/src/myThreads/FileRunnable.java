package myThreads;

import vehicle.Car;
import vehicle.Vehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class FileRunnable implements Runnable {

    private Vehicle vehicle;
    private String fname;
    private ArrayBlockingQueue<Vehicle> queue;

    public FileRunnable(String fname, ArrayBlockingQueue<Vehicle> queue) {

        this.fname = fname;
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            BufferedReader in = new BufferedReader(new FileReader(fname));
            String brand = in.readLine();
            vehicle = new Car(brand);
            queue.put(vehicle);
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }
}
