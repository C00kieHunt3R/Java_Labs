package tool;

import vehicle.Vehicle;

import java.io.*;

public class VehicleHandler {

    //region old methods
    public static double getAverage(Vehicle vehicle) {
        double sum = 0;
        for (double price : vehicle.getModelsPrices()) {
            sum += price;
        }
        return sum/vehicle.getModelsPrices().length;
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

        int length = vehicle.getCountOfModels();
        writeStream(stream, length);

        for (int i = 0; i < length; i++) {

            data = vehicle.getModelsNames()[i].getBytes();

            writeStream(stream, data.length);
            writeStream(stream, data);
            writeStream(stream, vehicle.getModelsPrices()[i]);
        }
    }

    public static Vehicle inputVehicle(Vehicle vehicle, InputStream in) throws IOException {

        DataInputStream stream = new DataInputStream(in);
        int length = stream.readInt();
        byte[] data = readArrayFromStream(stream, length);


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

    private static byte[] readArrayFromStream(DataInputStream stream, int length) throws IOException {
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++) {
            data[i] = stream.readByte();
        }
        return data;
    }


}
