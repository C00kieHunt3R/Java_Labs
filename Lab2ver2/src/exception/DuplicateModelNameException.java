package exception;

public class DuplicateModelNameException extends Throwable {
    public DuplicateModelNameException() {
        super("Дублирование имён недопустимо. ");
    }
}
