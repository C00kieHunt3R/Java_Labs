package vehicle;

import exception.*;

public class Motorcycle implements Vehicle {

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

    public void setCountOfModels(int countOfModels) {
        this.countOfModels = countOfModels;
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

    public Double[] getModelsPrices() {
        Double[] prices = new Double[countOfModels];
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
        checkForDuplicateName(newName);
        getModel(prevName).setName(newName);
    }

    public void setModelPrice(String name, double price) throws NoSuchModelNameException {
        getModel(name).setPrice(price);
    }

    public double getModelPrice(String name) throws NoSuchModelNameException {
        return getModel(name).getPrice();
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {
        checkForDuplicateName(name);
        addModelToList(new Model(name, price));
    }

    public void deleteModel(String name) throws NoSuchModelNameException {
        removeModelFromList(getModel(name));
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
        Model model = head.next;
        if (countOfModels != 0) {
            while (!model.name.equals(name)) {
                model = model.next;
                if (model.equals(head))
                    throw new NoSuchModelNameException();
            }
        } else throw new NoSuchModelNameException();
        return model;
    }

    private void checkForDuplicateName(String name) throws DuplicateModelNameException {
        Model model = head.next;
        while (!model.equals(head)) {
            if (model.getName().equals(name)) throw new DuplicateModelNameException();
            model = model.next;
        }
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


    private class Model {

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

        public Model() {}

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }
}
