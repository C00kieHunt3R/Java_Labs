package vehicle;

import exception.DuplicateModelNameException;
import exception.ModelPriceOutOfBoundsException;
import exception.NoSuchModelNameException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Scooter implements Vehicle {

    private String brand;
    private HashMap<String, Double> models;


    public Scooter(String brand) {
        this.brand = brand;
        this.models = new HashMap<>();
    }
    public Scooter(String brand, int size) {
        this.brand = brand;
        this.models = new HashMap<>();
        for (int i = 0; i < size; i++) {
            models.put("scooter" + i, 1500.0);
        }
    }

    public String getBrand() {
        return this.brand;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String[] getModelsNames() {
        String[] names = new String[this.models.size()];
        int i = 0;
        for (String key : this.models.keySet()) {
            names[i] = key;
            i++;
        }
        return names;
    }


    public double[] getModelsPrices() {
        double[] prices = new double[models.size()];
        int i = 0;
        for (Map.Entry<String, Double> price : models.entrySet()) {
            prices[i] = price.getValue();
            i++;
        }
        return prices;
    }


    public void setModelName(String prevName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        checkForExistingModelName(prevName);
        checkForDuplicateModelName(newName);
        double price = models.get(prevName);
        models.remove(prevName);
        models.put(newName, price);
    }


    public void setModelPrice(String name, double price) throws NoSuchModelNameException {
        checkForExistingModelName(name);
        checkForCorrectPrice(name, price);
        models.replace(name, price);
    }


    public double getModelPrice(String name) throws NoSuchModelNameException {
        checkForExistingModelName(name);
        return models.get(name);

    }


    public void addModel(String name, double price) throws DuplicateModelNameException {
        checkForDuplicateModelName(name);
        checkForCorrectPrice(name, price);
        models.put(name, price);
    }


    public void deleteModel(String name) throws NoSuchModelNameException {
        checkForExistingModelName(name);
        models.remove(name);
    }


    public int getCountOfModels() {
        return models.size();
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Brand: ").append(brand).append("\n");

        for (Map.Entry<String, Double> model : models.entrySet()) {

            stringBuilder.append("\t").append(model.getKey()).append(" | ").append(model.getValue()).append("\n");
        }

        return stringBuilder.toString();
    }

    private void checkForExistingModelName(String name) throws NoSuchModelNameException {

        if (!models.containsKey(name)) {

            throw new NoSuchModelNameException(this.brand, name);
        }
    }

    private void checkForDuplicateModelName(String name) throws DuplicateModelNameException {

        if (models.containsKey(name)) {

            throw new DuplicateModelNameException(this.brand, name);
        }
    }

    private void checkForCorrectPrice(String name, double price) {

        if (price < 0) {

            throw new ModelPriceOutOfBoundsException(this.brand, name, price);
        }
    }
}
