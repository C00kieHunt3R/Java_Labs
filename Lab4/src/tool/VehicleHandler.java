package tool;

import exception.*;
import vehicle.Car;
import vehicle.Motorcycle;
import vehicle.Vehicle;

import java.io.*;

public class VehicleHandler {

    //region old methods
    public static double getAverage(Vehicle vehicle) {
        double sum = 0;
        for (double price : vehicle.getModelsPrices()) {
            sum += price;
        }
        return sum / vehicle.getModelsPrices().length;
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
    //endregion

    public static void outputVehicle(Vehicle vehicle, OutputStream out) throws IOException {

        DataOutputStream stream = new DataOutputStream(out);

        byte[] data = vehicle.getBrand().getBytes();

        writeStream(stream, data.length);
        writeStream(stream, data);

        int countOfModels = vehicle.getCountOfModels();
        writeStream(stream, countOfModels);

        for (int i = 0; i < countOfModels; i++) {

            data = vehicle.getModelsNames()[i].getBytes();

            writeStream(stream, data.length);
            writeStream(stream, data);
            writeStream(stream, vehicle.getModelsPrices()[i]);
        }


    }

    public static Vehicle inputVehicle(InputStream in, Type type) throws IOException, DuplicateModelNameException {

        DataInputStream stream = new DataInputStream(in);
        int countOfModels = stream.readInt();
        byte[] data = readStreamData(stream, countOfModels);
        String brand = new String(data);
        countOfModels = stream.readInt();
        Vehicle vehicle;

        if (type == Type.CAR) {

            vehicle = new Car(brand);
            String name;
            double price;
            int length;

            for (int i = 0; i < countOfModels; i++) {
                length = stream.readInt();
                data = readStreamData(stream, length);

                name = new String(data);
                price = stream.readDouble();
                vehicle.addModel(name, price);
            }

        } else {

            vehicle = new Motorcycle(brand);
            String name;
            double price;
            int length;

            for (int i = 0; i < countOfModels; i++) {
                length = stream.readInt();
                data = readStreamData(stream, length);

                name = new String(data);
                price = stream.readDouble();
                vehicle.addModel(name, price);
            }
        }
        stream.close();
        return vehicle;
    }



    public static void writeVehicle(Vehicle vehicle, Writer out) {

        PrintWriter stream = new PrintWriter(out);

        int len = vehicle.getCountOfModels();
        stream.println(vehicle.getBrand());
        stream.println(len);

        for (int i = 0; i < len; i++) {
            stream.println(vehicle.getModelsNames()[i]);
            stream.println(vehicle.getModelsPrices()[i]);
        }
        stream.flush();
    }

    public static Vehicle readVehicle(Reader in, Type type) throws IOException, DuplicateModelNameException {

        BufferedReader stream = new BufferedReader(in);
        String mark = stream.readLine();
        if (type == Type.CAR) {

            double price;
            String name;
            int countOfModels = Integer.parseInt(stream.readLine());
            Vehicle vehicle = new Car(mark);

            for (int i = 0; i < countOfModels; i++) {
                name = stream.readLine();
                price = Double.parseDouble(stream.readLine());
                vehicle.addModel(name, price);
            }
            stream.close();
            return vehicle;
        } else {

            double price;
            int countOfModels = Integer.parseInt(stream.readLine());
            Vehicle vehicle = new Motorcycle(mark);
            String name;

            for (int i = 0; i < countOfModels; i++) {
                name = stream.readLine();
                price = Double.parseDouble(stream.readLine());
                vehicle.addModel(name, price);
            }
            stream.close();
            return vehicle;
        }
    }


    private static void writeStream(DataOutputStream stream, byte[] data) throws IOException {
        for (int i = 0; i < data.length; i++) {
            stream.writeByte(data[i]);
        }
    }

    private static void writeStream(DataOutputStream stream, int n) throws IOException {
        stream.writeInt(n);
    }

    private static void writeStream(DataOutputStream stream, double n) throws IOException {
        stream.writeDouble(n);
    }

    private static byte[] readStreamData(DataInputStream stream, int length) throws IOException {
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++) {
            data[i] = stream.readByte();
        }
        return data;
    }

    public enum Type {
        CAR,
        MOTORCYCLE
    }

}
