package exception;

public class DuplicateModelNameException extends Throwable {
    public DuplicateModelNameException(String brand, String name) {
        super(String.format("[%s | %s]: Модель с именем \"%2$s\" уже существует.", brand, name));
    }
}
