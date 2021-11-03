import exception.*;
import test.VehicleTest;
import tool.*;
import vehicle.*;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, ClassNotFoundException, CloneNotSupportedException {

        //Task1(args);
        //Task2();
        //Task3();
        //Task4();
        //Task5();
        //Task6();
        Task7();
    }



    private static void Task1(String[] args) {
        try {
            Class vehicle = Class.forName(args[0]);
            Constructor constructor = vehicle.getConstructor(String.class, int.class);
            Method setModelPrice = vehicle.getMethod(args[1], String.class, double.class);
            Method toString = vehicle.getMethod("toString");
            Object car = constructor.newInstance(args[2], Integer.parseInt(args[3]));
            String name = args[4];
            double price = Double.parseDouble(args[5]);

            System.out.println("Default car: ");
            System.out.println(toString.invoke(car));
            System.out.println("Changed car: ");
            setModelPrice.invoke(car, name, price);
            System.out.println(toString.invoke(car));


        } catch (ClassNotFoundException |
                NoSuchMethodException |
                InstantiationException |
                IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void Task2() {
        Vehicle car = new Car("FERRARI", 5);
        Vehicle reflectedCar = VehicleHandler.createInstance("FERRARI MK.2", 5, car);
        VehicleTest.test(car);
        VehicleTest.test(reflectedCar);
    }

    private static void Task3() {
        Scooter scooter = new Scooter("I couldn't think of a brand", 5);
        VehicleTest.test(scooter);
    }

    private static void Task4() {
        Quadbike quadbike = new Quadbike("Green Camel", 5);
        VehicleTest.test(quadbike);
    }

    private static void Task5() {
        Moped moped = new Moped("IZH", 5);
        VehicleTest.test(moped);
    }

    private static void Task6() {

        Moped moped = new Moped("IZH", 5);
        Scooter scooter = new Scooter("Green Camel", 5);
        Quadbike quadbike = new Quadbike("QD", 5);
        Motorcycle motorcycle = new Motorcycle("YAMAHA", 5);
        Car car = new Car("BMW", 5);

        double average = VehicleHandler.getCollectionAverage(moped, scooter, quadbike, motorcycle, car);

        System.out.println(average);
    }

    private static void Task7() {
        VehicleHandler.writeVehicle(VehicleHandler.readVehicle(new InputStreamReader(System.in)));
    }

    //region 4th lab methods
    private static void testCar() throws DuplicateModelNameException, NoSuchModelNameException {

        Car bmw = new Car("BMW");
        bmw.addModel("X5", 9595959.5);
        bmw.addModel("X7 Drive", 1000000.9);

        /* Проверка toString() */
        System.out.println(bmw);

        /* Проверка equals() */
        Car equalBMW = new Car("BMW");
        equalBMW.addModel("X5", 9595959.5);
        equalBMW.addModel("X7 Drive", 1000000.9);

        Car notEqualBMW1 = new Car("BMW");
        notEqualBMW1.addModel("X5", 9595959.5);

        Car notEqualBMW2 = new Car("BMW");
        notEqualBMW2.addModel("X5", 100);
        notEqualBMW2.addModel("X7 Drive", 1000000.9);


        System.out.println("Absolutely equal car: " + bmw.equals(equalBMW));
        System.out.println("Not equal car (not enough models): " + bmw.equals(notEqualBMW1));
        System.out.println("Not equal car (invalid price): " + bmw.equals(notEqualBMW2));

        /* Проверка hashCode() */
        Car car = (Car)bmw.clone();
        car.setModelName("X5", "X555");
        System.out.println(bmw);
        System.out.println(car);
        System.out.println("Primary car hash-code: " + bmw.hashCode());
        System.out.println("New car hash-code: " + car.hashCode());
        System.out.println("Is primary car equal new car: " + bmw.equals(car));

    }

    private static void testMotorcycle() throws DuplicateModelNameException, NoSuchModelNameException, CloneNotSupportedException {

        Motorcycle yamaha = new Motorcycle("YAMAHA");
        yamaha.addModel("THR5", 9595959.5);
        yamaha.addModel("YAM100", 1000000.9);

        /* Проверка toString() */
        System.out.println(yamaha);

        /* Проверка equals() */
        Motorcycle equalYAMAHA = new Motorcycle("YAMAHA");
        equalYAMAHA.addModel("THR5", 9595959.5);
        equalYAMAHA.addModel("YAM100", 1000000.9);

        Motorcycle notEqualYAMAHA1 = new Motorcycle("YAMAHA");
        notEqualYAMAHA1.addModel("THR5", 9595959.5);

        Motorcycle notEqualYAMAHA2 = new Motorcycle("YAMAHA");
        notEqualYAMAHA2.addModel("THR5", 100);
        notEqualYAMAHA2.addModel("YAM100", 1000000.9);


        System.out.println("Absolutely equal motorcycle: " + yamaha.equals(equalYAMAHA));
        System.out.println("Not equal motorcycle (not enough models): " + yamaha.equals(notEqualYAMAHA1));
        System.out.println("Not equal motorcycle (invalid price): " + yamaha.equals(notEqualYAMAHA2));

        /* Проверка hashCode() */
        Motorcycle motorcycle = (Motorcycle)yamaha.clone();
        motorcycle.setModelName("THR5", "WOW123456");
        System.out.println();
        System.out.println(yamaha);
        System.out.println(motorcycle);
        System.out.println("Primary motorcycle hash-code: " + yamaha.hashCode());
        System.out.println("New motorcycle hash-code: " + motorcycle.hashCode());
        System.out.println("Is primary moto equal new moto: " + yamaha.equals(motorcycle));
    }
    //endregion

    //region 3rd lab methods
    private static void byteStreamCreateFile(Vehicle vehicle, String fileName) throws IOException {
        FileOutputStream out = new FileOutputStream(fileName);
        VehicleHandler.outputVehicle(vehicle, out);
        out.close();
    }

    private static Vehicle byteStreamReadFile(String fileName, VehicleHandler.Type type) throws IOException, DuplicateModelNameException {
        FileInputStream in = new FileInputStream(fileName);
        Vehicle vehicle = VehicleHandler.inputVehicle(in, type);
        in.close();
        return vehicle;
    }

    private static void objSerializeWrite(Vehicle vehicle, String fileName) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(vehicle);
        out.close();
    }

    private static Vehicle objSerializeRead(String fileName, VehicleHandler.Type type) throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(in);
        Vehicle vehicle;
        if (type == VehicleHandler.Type.CAR) {
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

    private static Vehicle charStreamReadFile(String fileName, VehicleHandler.Type type) throws IOException, DuplicateModelNameException {
        FileReader in = new FileReader(fileName);
        Vehicle vehicle = VehicleHandler.readVehicle(in, type);
        in.close();
        return vehicle;
    }

    private static void charStreamWriteManually(InputStreamReader in, OutputStreamWriter out, PrintWriter writer, VehicleHandler.Type type) throws DuplicateModelNameException, IOException, NoSuchModelNameException {

        Vehicle vehicle = VehicleHandler.readVehicle(in, type);
        VehicleHandler.writeVehicle(vehicle, out);
        System.out.println("hello");
    }

    //endregion

}
