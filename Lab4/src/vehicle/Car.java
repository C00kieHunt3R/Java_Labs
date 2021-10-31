package vehicle;

import exception.*;

import java.io.Serializable;
import java.util.Arrays;

public class Car implements Vehicle, Serializable, Cloneable {

    private String brand;
    private int countOfModels;
    private Model[] models;

    public Car(String brand) {
        this.brand = brand;
        models = new Model[0];

    }

    public Car(String brand, int countOfModels) {
        this.brand = brand;
        this.countOfModels = countOfModels;
        models = new Model[countOfModels];
        for (int i = 0; i < countOfModels; i++) {
            models[i] = new Model("car" + i, 1000000.0);
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCountOfModels() {
        return countOfModels;
    }


    public String[] getModelsNames() {
        String[] names = new String[countOfModels];
        for (int i = 0; i < countOfModels; i++) {
            names[i] = models[i].getName();
        }
        return names;
    }

    public double[] getModelsPrices() {
        double[] prices = new double[countOfModels];
        for (int i = 0; i < countOfModels; i++) {
            prices[i] = models[i].getPrice();
        }
        return prices;
    }

    public void setModelName(String prevName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        checkForDuplicateModelName(newName);
        getModel(prevName).setName(newName);
    }

    public void setModelPrice(String name, double price) throws NoSuchModelNameException {
        checkForCorrectPrice(name, price);
        getModel(name).setPrice(price);
    }

    public double getModelPrice(String name) throws NoSuchModelNameException {
        return getModel(name).getPrice();
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {

        checkForCorrectPrice(name, price);
        checkForDuplicateModelName(name);
        models = Arrays.copyOf(models, countOfModels + 1);
        models[countOfModels] = new Model(name, price);
        countOfModels++;
    }

    public void deleteModel(String name) throws NoSuchModelNameException {

        checkForExistingModelName(name);
        int index = getIndexOfModel(name);
        System.arraycopy(models, index + 1, models, index, models.length - index - 1);
        models = Arrays.copyOf(models, models.length - 1);
        countOfModels--;
    }

    private Model getModel(String name) throws NoSuchModelNameException {

        checkForExistingModelName(name);

        return models[getIndexOfModel(name)];
    }

    private int getIndexOfModel(String name) {
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
    public int hashCode() {

        int prime = 31;
        int result = 1;

        result = result * prime + brand.hashCode();
        result = result * prime + Arrays.hashCode(getModelsNames());
        result = result * prime + Arrays.hashCode(getModelsPrices());

        return result;
    }

    @Override
    public boolean equals(Object obj) {


        if (this == obj) {
            return true;

        } else if (obj instanceof Car) {

            Car car = (Car) obj;

            if (!car.getBrand().equals(this.brand)) {
                return false;

            } else if (car.getCountOfModels() != this.countOfModels) {
                return false;

            } else for (int i = 0; i < models.length; i++) {

                if (!car.models[i].getName().equals(this.models[i].name) ||
                        car.models[i].getPrice() != this.models[i].getPrice()) {

                    return false;
                }
            }
            return true;

        } else return false;

    }

    @Override
    public Object clone() {

        Car car = null;

        try {

            car = (Car) super.clone();
            car.models = this.models.clone();
            for (int i = 0; i < countOfModels; i++) {
                car.models[i] = (Model) models[i].clone();
            }
        } catch (CloneNotSupportedException e) {

            e.printStackTrace();
        }
        return car;
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


    private class Model implements Serializable, Cloneable {
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

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

}
