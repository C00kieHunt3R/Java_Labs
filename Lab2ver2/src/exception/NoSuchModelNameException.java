package exception;

public class NoSuchModelNameException extends Throwable {
    public NoSuchModelNameException() {
        super("Модели с таким названием не существует.");
    }
}
