package exception;

public class ModelPriceOutOfBoundsException extends RuntimeException{
    public ModelPriceOutOfBoundsException(String brand, String name, double price) {
        super(String.format("[%s | %s]: Указанная цена \"%.1f\" является недопустимой (цена не может быть отрицательной).", brand, name, price));
    }
}
