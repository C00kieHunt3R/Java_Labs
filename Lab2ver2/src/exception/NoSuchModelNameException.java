package exception;

public class NoSuchModelNameException extends Throwable {
    public NoSuchModelNameException(String name) {
        super(String.format("Модели с названием \"%s\"не существует.", name));
    }
}
