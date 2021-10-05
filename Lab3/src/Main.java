import exception.*;
import tool.*;
import vehicle.*;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, ClassNotFoundException {

        Vehicle currentMoto = new Motorcycle("YAMAHA", 3);
        currentMoto.addModel("YAM101", 2500000);
        currentMoto.addModel("WST555", 5000000);
        System.out.println("ИСХОДНЫЕ ДАННЫЕ:");
        System.out.println(currentMoto);

        /*БАЙТОВЫЙ ПОТОК*/
        System.out.println("\n---------------\n[БАЙТОВЫЙ ПОТОК / ЗАПИСЬ В ФАЙЛ]\n--------------");
        System.out.println(" ВВЕДИТЕ НАЗВАНИЕ ФАЙЛА: ");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();

        //ЗАПИСЬ
        FileOutputStream fileOut1 = new FileOutputStream(filename);
        VehicleHandler.outputVehicle(currentMoto, fileOut1);
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
        fileOut2.writeObject(currentMoto);
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
        VehicleHandler.writeVehicle(currentMoto, fileOut3);
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
}
