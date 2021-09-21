package vehicle;

import exception.*;

import java.util.Arrays;
import java.util.List;

public class Car implements Vehicle {

    private String brand;
    private int countOfModels;
    private Model[] models;

    public Car(String brand, int countOfModels) {
        this.brand = brand;
        this.countOfModels = countOfModels;
        models = new Model[countOfModels];
        for (int i = 0; i < countOfModels; i++) {
            models[i] = new Model("car" + i, 1000.0);
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

    public void setCountOfModels(int countOfModels) {
        this.countOfModels = countOfModels;
    }

    public String[] getModelsNames() {
        String[] names = new String[countOfModels];
        for (int i = 0; i < countOfModels; i++) {
            names[i] = models[i].getName();
        }
        return names;
    }

    public Double[] getModelsPrices() {
        Double[] prices = new Double[countOfModels];
        for (int i = 0; i < countOfModels; i++) {
            prices[i] = models[i].getPrice();
        }
        return prices;
    }

    public void setModelName(String prevName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        Model model = getModel(prevName);
        if (prevName.equals(newName)) {// ошибка на повторяющееся имя
            model.setName(newName);
        } else throw new DuplicateModelNameException();
    }

    public void setModelPrice(String name, double price) throws NoSuchModelNameException {
        if (price > 0) {
            Model model = getModel(name);
            model.setPrice(price);
        } else throw new ModelPriceOutOfBoundsException();
    }

    public double getModelPrice(String name) throws NoSuchModelNameException {
        return getModel(name).getPrice();
    }

    public void addModel(String name, double price) throws DuplicateModelNameException { //СДЕЛАТЬ ТОЖЕ САМОЕ ДЛЯ MOTORCYCLE
        if (price < 0) throw new ModelPriceOutOfBoundsException();
        if (Arrays.asList(getModelsNames()).contains(name)) throw new DuplicateModelNameException();
        models = Arrays.copyOf(models, countOfModels + 1);
        models[countOfModels] = new Model(name, price);
        countOfModels++;
    }

    public void deleteModel(String name) throws NoSuchModelNameException {
        int index = getIndexOfModel(name);
        System.arraycopy(models, index + 1, models, index, models.length - index - 1);
        models = Arrays.copyOf(models, models.length - 1);
        countOfModels--;
    }

    private Model getModel(String name) throws NoSuchModelNameException {
        for (Model model : models) {
            if (model.getName().equals(name)) {
                return model;
            }
        }
        throw new NoSuchModelNameException();
    }


    private int getIndexOfModel(String name) throws NoSuchModelNameException {
        List<String> names = Arrays.asList(getModelsNames());
        if (!names.contains(name)) {
            throw new NoSuchModelNameException();
        }
        return names.indexOf(name);
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

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

}
