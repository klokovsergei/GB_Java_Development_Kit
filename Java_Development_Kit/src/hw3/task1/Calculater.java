package hw3.task1;

public class Calculater {

    public static <T extends Number> Double sum(T first, T second) {
        return first.doubleValue() + second.doubleValue();
    }
    public static <T extends Number> Double multiply(T first, T second) {
        return first.doubleValue() * second.doubleValue();
    }
    public static <T extends Number> Double divide(T first, T second) {
        if (second.intValue() == 0)
            throw new RuntimeException("Деление на 0 запрещено");
        return first.doubleValue() / second.doubleValue();
    }
    public static <T extends Number> Double subtract(T first, T second) {
        return first.doubleValue() + second.doubleValue();
    }


}
