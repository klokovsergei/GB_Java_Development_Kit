package hw3.task1;

public class Main {

    /*
    1. Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
    sum(), multiply(), divide(), subtract(). Параметры этих методов – два числа разного типа,
    над которыми должна быть произведена операция.
     */

    public static void main(String[] args) {
        byte b = 4;
        long l = 9289274274L;
        short s = 0;
        int i = 25048;
        float f = 352.234f;
        double d = 4134123.123131233;

        System.out.println("СЛОЖЕНИЕ:");
        System.out.println(Calculater.sum(b, l));
        System.out.println(Calculater.sum(s, d));
        System.out.println(Calculater.sum(f, b));

        System.out.println("УМНОЖЕНИЕ:");
        System.out.println(Calculater.multiply(b, l));
        System.out.println(Calculater.multiply(s, d));
        System.out.println(Calculater.multiply(f, b));

        System.out.println("ДЕЛЕНИЕ:");
        System.out.println(Calculater.divide(b, l));
        System.out.println(Calculater.divide(s, d));
        System.out.println(Calculater.divide(f, b));

        System.out.println("ВЫЧИТАНИЕ:");
        System.out.println(Calculater.subtract(b, l));
        System.out.println(Calculater.subtract(s, d));
        System.out.println(Calculater.subtract(f, b));

    }
}
