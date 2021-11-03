package test;

import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;
import vehicle.Vehicle;

public class VehicleTest {
    public static void test(Vehicle vehicle) {
        Vehicle vehicleTest = vehicle;
        try {
            addModelTest(vehicleTest);
            deleteModelTest(vehicleTest);
            setModelNameTest(vehicleTest);
            setModelPriceTest(vehicleTest);
        } catch (Exception e) {
            System.out.println("Test failed!");
        }
    }

    private static void addModelTest(Vehicle vehicle) throws DuplicateModelNameException {

        vehicle.addModel("modelTest", 500);
    }

    private static void deleteModelTest(Vehicle vehicle) throws NoSuchModelNameException {

        vehicle.deleteModel(vehicle.getModelsNames()[0]);
    }

    private static void setModelNameTest(Vehicle vehicle) throws DuplicateModelNameException, NoSuchModelNameException {
        vehicle.setModelName(vehicle.getModelsNames()[0], "NEW NAME");
    }

    private static void setModelPriceTest(Vehicle vehicle) throws NoSuchModelNameException {
        vehicle.setModelPrice(vehicle.getModelsNames()[0], 500);
    }
}
