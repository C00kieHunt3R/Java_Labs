package vehicle;

import exception.DuplicateModelNameException;
import exception.ModelPriceOutOfBoundsException;
import exception.NoSuchModelNameException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Quadbike implements Vehicle {

    private String brand;
    private ArrayList<Model> models;

    public Quadbike(String brand) {
        this.brand = brand;
        models = new ArrayList<>();
    }

    public Quadbike(String brand, int size) {

        this.brand = brand;
        models = new ArrayList<>();

        for (int i = 0; i < size; i++) {

            Model model = new Model("quadbike" + i, 9876.5);
            models.add(model);
        }
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String[] getModelsNames() {

        String[] modelsNames = new String[models.size()];

        for(int i = 0; i < modelsNames.length; i++) {

            modelsNames[i] = models.get(i).getName();
        }
        return modelsNames;
    }

    public double[] getModelsPrices() {

        double[] modelsPrices = new double[models.size()];

        for(int i = 0; i < modelsPrices.length; i++) {

            modelsPrices[i] = models.get(i).getPrice();
        }
        return modelsPrices;
    }

    public void setModelName(String prevName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {

        checkForExistingModelName(prevName);
        checkForDuplicateModelName(newName);

        models.get(getModelIndex(prevName)).setName(newName);
    }

    public void setModelPrice(String name, double price) throws NoSuchModelNameException {

        checkForExistingModelName(name);

        models.get(getModelIndex(name)).setPrice(price);
    }

    public double getModelPrice(String name) throws NoSuchModelNameException {

        checkForExistingModelName(name);

        return models.get(getModelIndex(name)).getPrice();
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {

        checkForDuplicateModelName(name);
        checkForCorrectPrice(name, price);

        models.add(new Model(name, price));

    }

    public void deleteModel(String name) throws NoSuchModelNameException {

        checkForExistingModelName(name);

        models.remove(getModelIndex(name));
    }

    public int getCountOfModels() {
        return models.size();
    }

    private int getModelIndex(String name) {
        return Arrays.asList(getModelsNames()).indexOf(name);
    }

    private void checkForExistingModelName(String name) throws NoSuchModelNameException {

        if (!Arrays.asList(getModelsNames()).contains(name)) {

            throw new NoSuchModelNameException(this.brand, name);
        }
    }

    private void checkForDuplicateModelName(String name) throws DuplicateModelNameException {

        if (Arrays.asList(getModelsNames()).contains(name)) {

            throw new DuplicateModelNameException(this.brand, name);
        }
    }

    private void checkForCorrectPrice(String name, double price) {

        if (price < 0) {

            throw new ModelPriceOutOfBoundsException(this.brand, name, price);
        }
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Brand: ").append(brand).append("\n");

        for (Model model : models) {

            stringBuilder.append("\t").append(model.getName()).append(" | ").append(model.getPrice()).append("\n");
        }

        return stringBuilder.toString();
    }

    private class Model {
        private String name;
        private double price;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Model() {
        }

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }
}
