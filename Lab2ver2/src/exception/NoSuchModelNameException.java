package exception;

public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException(String brand, String name) {
        super(String.format("[%s | %s]: Модели с названием \"%2$s\" не существует.", brand, name));
    }
}
