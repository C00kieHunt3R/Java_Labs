import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;
import vehicle.Car;
import vehicle.Motorcycle;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException {
        Car kia = new Car("KIA", 5);
        kia.addModel("Ceed", 12345.6);
        kia.addModel("K5", 7777.7);
        kia.addModel("Cerato", 9100.5);
        for (String s : kia.getModelsNames()) {
            System.out.println(s);
        }

        Motorcycle suzuki = new Motorcycle("SUZUKI", 5);
        suzuki.addModel("GSX750", 1500.0);
        System.out.println(kia);
        System.out.println(suzuki);
    }
}
