package exception;

public class ModelPriceOutOfBoundsException extends RuntimeException{
    public ModelPriceOutOfBoundsException() {
        super("Цена на модель не может быть меньше 0.");
    }
}
