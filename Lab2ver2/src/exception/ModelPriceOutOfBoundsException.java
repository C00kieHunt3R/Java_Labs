package exception;

public class ModelPriceOutOfBoundsException extends RuntimeException{
    public ModelPriceOutOfBoundsException(double price) {
        super(String.format("Указанная цена \"%.f\" является недопустимой (цена не может быть отрицательной).", price ));
    }
}
