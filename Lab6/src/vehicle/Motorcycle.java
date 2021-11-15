package vehicle;

import exception.*;

import java.io.Serializable;
import java.util.Arrays;

public class Motorcycle implements Vehicle, Cloneable {

    private String brand;
    private int countOfModels;
    private Model head = new Model();

    {
        head.prev = head;
        head.next = head;
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

    public Motorcycle(String brand) {
        this.brand = brand;


    }

    public Motorcycle(String brand, int countOfModels) {
        this.brand = brand;
        this.countOfModels = countOfModels;
        for (int i = 0; i < countOfModels; i++) {
            addModelToList(new Model("moto" + i, 1500.0));
        }
    }

    public String[] getModelsNames() {
        String[] names = new String[countOfModels];
        Model model = head.next;
        int index = 0;
        while (!model.equals(head)) {
            names[index] = model.getName();
            model = model.next;
            index++;
        }
        return names;
    }

    public double[] getModelsPrices() {
        double[] prices = new double[countOfModels];
        Model model = head.next;
        int index = 0;
        while (!model.equals(head)) {
            prices[index] = model.getPrice();
            model = model.next;
            index++;
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
        checkForDuplicateModelName(name);
        checkForCorrectPrice(name, price);
        addModelToList(new Model(name, price));
        countOfModels++;
    }

    public void deleteModel(String name) throws NoSuchModelNameException {
        removeModelFromList(getModel(name));
        countOfModels--;
    }

    private void addModelToList(Model model) {
        model.next = head;
        model.prev = head.prev;
        head.prev.next = model;
        head.prev = model;
    }

    private void removeModelFromList(Model model) {
        model.prev.next = model.next;
        model.next.prev = model.prev;
    }

    private Model getModel(String name) throws NoSuchModelNameException {
        checkForExistingModelName(name);
        Model model = head.next;
        while (!model.name.equals(name)) {
            model = model.next;
        }
        return model;
    }

    private void checkForExistingModelName(String name) throws NoSuchModelNameException {
        if (!Arrays.asList(getModelsNames()).contains(name)) {
            throw new NoSuchModelNameException(this.brand, name);
        }
    }

    private void checkForDuplicateModelName(String name) throws DuplicateModelNameException {
        Model model = head.next;
        while (!model.equals(head)) {
            if (model.getName().equals(name)) {
                throw new DuplicateModelNameException(this.brand, name);
            }
            model = model.next;
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


        } else if (obj instanceof Motorcycle) {

            Motorcycle motorcycle = (Motorcycle) obj;

            String[] thisModels = this.getModelsNames();
            double[] thisPrices = this.getModelsPrices();
            String[] objModels = motorcycle.getModelsNames();
            double[] objPrices = motorcycle.getModelsPrices();

            if (!motorcycle.getBrand().equals(this.brand)) {
                return false;

            } else if (motorcycle.getCountOfModels() != this.countOfModels) {
                return false;

            } else for (int i = 0; i < countOfModels; i++) {

                if (!objModels[i].equals(thisModels[i]) ||
                        objPrices[i] != thisPrices[i]) {

                    return false;
                }
            }
            return true;

        } else return false;

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Motorcycle moto = (Motorcycle) super.clone();
        moto.head = new Model();
        moto.head.prev = moto.head;
        moto.head.next = moto.head;
        Model model = this.head.next;
        while (model != head) {
            Model clone = (Model) model.clone();
            clone.next = moto.head;
            clone.prev = moto.head.prev;
            moto.head.prev.next = clone;
            moto.head.prev = clone;
            model = model.next;
        }
        return moto;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Brand: ").append(brand).append("\n");
        Model model = head.next;

        while (!model.equals(head)) {

            stringBuilder.append("\t").append(model.getName()).append(" | ").append(model.getPrice()).append("\n");
            model = model.next;
        }

        return stringBuilder.toString();
    }


    private class Model implements Serializable, Cloneable {

        private String name;
        private double price;
        private Model prev;
        private Model next;

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

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
