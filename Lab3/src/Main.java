import exception.*;
import tool.*;
import vehicle.*;

import java.io.*;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, ClassNotFoundException {

        //testCar();
        testMotorcycle();
    }

    private static void testCar() throws DuplicateModelNameException, IOException, ClassNotFoundException, NoSuchModelNameException {

        InputStreamReader in = new InputStreamReader(System.in);
        OutputStreamWriter out = new OutputStreamWriter(System.out);
        PrintWriter writer = new PrintWriter(out);

        Vehicle car1 = new Car("BMW", 5);
        car1.addModel("X5", 123123.1);
        car1.addModel("M240", 150000.5);

        System.out.println("-----object car1 (created)-----");
        System.out.println(car1);

        /* Байтовый поточек. */

        /* Запись в файл. */
        byteStreamCreateFile(car1, "carByteFile");

        /* Чтение из файла. */
        Vehicle car2 = byteStreamReadFile("carByteFile", Type.CAR);
        System.out.println("Object car2 (from Byte file)");
        System.out.println(car2);

        /* Сериализация. */

        /* Запись в файл. */
        objSerializeWrite(car1, "carSerializedFile");

        /* Чтение из файла */
        car2 = objSerializeRead("carSerializedFile", Type.CAR);
        System.out.println("Object car2 (from Serialize file)");
        System.out.println(car2);

        /* Символьный поток. */

        /* Запись в файл. */
        charStreamCreateFile(car1, "carCharFile");

        /* Чтение из файла. */
        car2 = charStreamReadFile("carCharFile", Type.CAR);
        System.out.println("Object car2 (from Char file)");
        System.out.println(car2);

        /* Символьный поток вручную. */

        /* Запись */
        System.out.println("Enter all required vehicle data:");
        charStreamWriteManually(in, out, writer, Type.CAR);
    }


    private static void testMotorcycle() throws DuplicateModelNameException, IOException, ClassNotFoundException, NoSuchModelNameException {

        InputStreamReader in = new InputStreamReader(System.in);
        OutputStreamWriter out = new OutputStreamWriter(System.out);
        PrintWriter writer = new PrintWriter(out);

        Vehicle moto1 = new Motorcycle("YAMAHA", 5);
        moto1.addModel("YAM123", 123123.1);
        moto1.addModel("MEOW", 150000.5);

        System.out.println("-----object moto1 (created)-----");
        System.out.println(moto1);

        /* Байтовый поточек. */

        /* Запись в файл. */
        byteStreamCreateFile(moto1, "motoByteFile");

        /* Чтение из файла. */
        Vehicle moto2 = byteStreamReadFile("motoByteFile", Type.MOTORCYCLE);
        System.out.println("Object Moto2 (from Byte file)");
        System.out.println(moto2);

        /* Сериализация. */

        /* Запись в файл. */
        objSerializeWrite(moto1, "motoSerializedFile");

        /* Чтение из файла */
        moto2 = objSerializeRead("motoSerializedFile", Type.MOTORCYCLE);
        System.out.println("Object Moto2 (from Serialize file)");
        System.out.println(moto2);

        /* Символьный поток. */

        /* Запись в файл. */
        charStreamCreateFile(moto1, "motoCharFile");

        /* Чтение из файла. */
        moto2 = charStreamReadFile("motoCharFile", Type.MOTORCYCLE);
        System.out.println("Object Moto2 (from Char file)");
        System.out.println(moto2);

        /* Символьный поток вручную. */

        /* Запись */
        System.out.println("Enter all required vehicle data:");
        charStreamWriteManually(in, out, writer, Type.MOTORCYCLE);
        in.close();
        writer.close();
    }

    private static void byteStreamCreateFile(Vehicle vehicle, String fileName) throws IOException {
        FileOutputStream out = new FileOutputStream(fileName);
        VehicleHandler.outputVehicle(vehicle, out);
        out.close();
    }

    private static Vehicle byteStreamReadFile(String fileName, Type type) throws IOException, DuplicateModelNameException {
        FileInputStream in = new FileInputStream(fileName);
        Vehicle vehicle;
        if (type == Type.CAR) {
            vehicle = VehicleHandler.inputVehicleCar(in);
        } else {
            vehicle = VehicleHandler.inputVehicleMoto(in);
        }

        in.close();
        return vehicle;
    }

    private static void objSerializeWrite(Vehicle vehicle, String fileName) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(vehicle);
        out.close();
    }

    private static Vehicle objSerializeRead(String fileName, Type type) throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(in);
        Vehicle vehicle;
        if (type == Type.CAR) {
            vehicle = (Car) ois.readObject();
        } else {
            vehicle = (Motorcycle) ois.readObject();
        }

        ois.close();
        return vehicle;
    }

    private static void charStreamCreateFile(Vehicle vehicle, String fileName) throws IOException {
        FileWriter out = new FileWriter(fileName);
        VehicleHandler.writeVehicle(vehicle, out);
        out.close();
    }

    private static Vehicle charStreamReadFile(String fileName, Type type) throws IOException, DuplicateModelNameException {
        FileReader in = new FileReader(fileName);
        Vehicle vehicle;
        if (type == Type.CAR) {
            vehicle = VehicleHandler.readVehicleCar(in);
        } else {
            vehicle = VehicleHandler.readVehicleMoto(in);
        }

        in.close();
        return vehicle;
    }

    private static void charStreamWriteManually(InputStreamReader in, OutputStreamWriter out, PrintWriter writer, Type type) throws DuplicateModelNameException, IOException, NoSuchModelNameException {

        Vehicle vehicle;
        if (type == Type.CAR) {
            vehicle = VehicleHandler.readVehicleCar(in);
        } else {
            vehicle = VehicleHandler.readVehicleMoto(in);
        }
        VehicleHandler.writeVehicle(vehicle, out);
        System.out.println("hello");
    }

    private enum Type {
        CAR,
        MOTORCYCLE
    }

}
