package vehicle;

import exception.*;

import java.io.Serializable;

public interface Vehicle extends Serializable {
    String getBrand();
    void setBrand(String brand);

    String[] getModelsNames();
    double[] getModelsPrices();

    void setModelName(String prevName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    void setModelPrice(String name, double price) throws NoSuchModelNameException;

    double getModelPrice(String name) throws NoSuchModelNameException;

    void addModel(String name, double price) throws DuplicateModelNameException;
    void deleteModel(String name) throws NoSuchModelNameException;

    int getCountOfModels();
}
