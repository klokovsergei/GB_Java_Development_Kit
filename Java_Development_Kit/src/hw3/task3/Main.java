package hw3.task3;

public class Main {

    /*
    3. Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
    Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары,
    а также переопределение метода toString(), возвращающее строковое представление пары.
     */

    public static void main(String[] args) {
        System.out.println(new Pair<Integer, String>(5, "пять"));
        System.out.println(new Pair<Integer, Double>(5, 5.0));
    }

}
