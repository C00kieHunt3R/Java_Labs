import exception.*;
import tool.*;
import vehicle.*;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, ClassNotFoundException {

        Vehicle moto1 = new Motorcycle("YAMAHA", 3);
        moto1.addModel("YAM101", 2500000);
        moto1.addModel("WST555", 5000000);
        System.out.println("Initial data:");
        System.out.println(moto1);

        /*БАЙТОВЫЙ ПОТОК*/
        System.out.println("\n---------------\n[БАЙТОВЫЙ ПОТОК / ЗАПИСЬ В ФАЙЛ]\n--------------");
        System.out.println(" ВВЕДИТЕ НАЗВАНИЕ ФАЙЛА: ");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();

        //ЗАПИСЬ
        FileOutputStream fileOut1 = new FileOutputStream(filename);
        VehicleHandler.outputVehicle(moto1, fileOut1);
        fileOut1.close();

        //ЧТЕНИЕ
        System.out.println("\n---------------\n[БАЙТОВЫЙ ПОТОК / ЧТЕНИЕ ИЗ ФАЙЛА]\n--------------");
        System.out.println(" ВВЕДИТЕ НАЗВАНИЕ ФАЙЛА: ");
        filename = scanner.nextLine();
        FileInputStream fileIn1 = new FileInputStream(filename);
        Vehicle takenMoto = VehicleHandler.inputVehicleMoto(fileIn1);
        fileIn1.close();

        System.out.println("----------------------------");
        System.out.println(takenMoto.getBrand());
        VehicleHandler.printModelsNames(takenMoto);
        VehicleHandler.printModelsPrices(takenMoto);

        //********************************************************************************************//

        //ПРОВЕРКА СЕРИАЛИЗАЦИИ
        System.out.println("\n---------------\n[БАЙТОВЫЙ ПОТОК / ПРОВЕРКА СЕРИАЛИЗАЦИИ / ЗАПИСЬ В ФАЙЛ]\n--------------");
        System.out.println(" ВВЕДИТЕ НАЗВАНИЕ ФАЙЛА: ");
        filename = scanner.nextLine();
        ObjectOutputStream fileOut2 = new ObjectOutputStream(new FileOutputStream(filename));
        fileOut2.writeObject(moto1);
        fileOut2.close();

        System.out.println("\n---------------\n[БАЙТОВЫЙ ПОТОК / ПРОВЕРКА СЕРИАЛИЗАЦИИ / ЧТЕНИЕ ИЗ ФАЙЛА]\n--------------");
        System.out.println(" ВВЕДИТЕ НАЗВАНИЕ ФАЙЛА: ");
        filename = scanner.nextLine();
        FileInputStream fileIn2 = new FileInputStream(filename);
        ObjectInputStream objI = new ObjectInputStream(fileIn2);
        Vehicle autoAfter1 = (Motorcycle) objI.readObject();
        fileIn2.close();

        System.out.println("----------------------------");
        System.out.println(autoAfter1.getBrand());
        VehicleHandler.printModelsNames(autoAfter1);
        VehicleHandler.printModelsPrices(autoAfter1);

        //********************************************************************************************//

        /*СИМВОЛЬНЫЙ ПОТОК АВТОМАТИЧЕСКИ*/
        //ЗАПИСЬ
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        System.out.println("\n---------------\n[СИМВОЛЬНЫЙ ПОТОК / ЗАПИСЬ В ФАЙЛ]\n--------------");
        System.out.println(" ВВЕДИТЕ НАЗВАНИЕ ФАЙЛА: ");
        filename = scanner.nextLine();
        FileWriter fileOut3 = new FileWriter(filename);
        VehicleHandler.writeVehicle(moto1, fileOut3);
        fileOut3.close();

        //ЧТЕНИЕ
        System.out.println("\n---------------\n[СИМВОЛЬНЫЙ ПОТОК / ЧТЕНИЕ ИЗ ФАЙЛА]\n--------------");
        System.out.println(" ВВЕДИТЕ НАЗВАНИЕ ФАЙЛА: ");
        filename = scanner.nextLine();
        FileReader fileIn3 = new FileReader(filename);
        Vehicle autoAfter2 = VehicleHandler.readVehicleMoto(fileIn3);
        fileIn3.close();

        System.out.println("----------------------------");
        System.out.println(autoAfter2.getBrand());
        VehicleHandler.printModelsNames(autoAfter2);
        VehicleHandler.printModelsPrices(autoAfter2);

        //********************************************************************************************//

        /*СИМВОЛЬНЫЙ ПОТОК ВРУЧНУЮ*/
        //ЗАПИСЬ
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        System.out.println("\n---------------\n[СИМВОЛЬНЫЙ ПОТОК / ЗАПИСЬ БЕЗ ФАЙЛА (средством System.in)]\n--------------");
        System.out.println(" ВВЕДИТЕ ВСЁ, ЧТО НЕОБХОДИМО(сначала МАРКА, потом КОЛ-ВО моделей, потом МОДЕЛЬ и ЦЕНА): ");
        Vehicle autoAfter3 = VehicleHandler.readVehicleMoto(new InputStreamReader(System.in));
        VehicleHandler.writeVehicle(autoAfter3, new OutputStreamWriter(System.out));

    }

    private static void byteStreamCreateFile(Vehicle vehicle, String fileName) throws IOException {
        FileOutputStream out = new FileOutputStream(fileName);
        VehicleHandler.outputVehicle(vehicle, out);
        out.close();
    }

    private static Vehicle byteStreamReadFile(String fileName) throws IOException, DuplicateModelNameException {
        FileInputStream in = new FileInputStream(fileName);
        Vehicle vehicle = VehicleHandler.inputVehicleMoto(in);
        in.close();
        return vehicle;
    }

    private static void objSerializeWrite(Vehicle vehicle, String fileName) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(vehicle);
        out.close();
    }

    private static Vehicle objSerializeRead(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(in);
        Vehicle vehicle = (Motorcycle) ois.readObject();
        in.close();
        return vehicle;
    }

    private static void charStreamCreateFile(Vehicle vehicle, String fileName) throws IOException {
        FileWriter out = new FileWriter(fileName);
        VehicleHandler.writeVehicle(vehicle, out);
        out.close();
    }

    private static Vehicle charStreamReadFile(String fileName) throws IOException, DuplicateModelNameException, NoSuchModelNameException {
        FileReader in = new FileReader(fileName);
        Vehicle vehicle = VehicleHandler.readVehicleMoto(in);
        in.close();

        return vehicle;
    }

    private static void charStreamWriteManually() throws DuplicateModelNameException, IOException, NoSuchModelNameException {
        Vehicle vehicle = VehicleHandler.readVehicleMoto(new InputStreamReader(System.in));
        VehicleHandler.writeVehicle(vehicle, new OutputStreamWriter(System.out));
    }

}
