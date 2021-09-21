package exception;

public class DuplicateModelNameException extends Throwable {
    public DuplicateModelNameException(String name) {
        super(String.format("Модель с именем \"%s\" уже существует.", name));
    }
}
