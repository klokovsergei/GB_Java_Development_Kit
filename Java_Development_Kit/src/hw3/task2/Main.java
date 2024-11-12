package hw3.task2;

public class Main {
    /*
    2. Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true,
    если они одинаковые, и false в противном случае.
    Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа.
    */

    public static <T> boolean compareArrays(T[] firstArr, T[] secondArr) {
        return firstArr.length == secondArr.length;
    }
    public static void main(String[] args) {
        String[] a1 = new String[] {"ghbdtn", "adasd"};
        int[] a2 = new int[] {1, 4, 6, 9};
        String[] a3 = new String[] {"ghbdtn", "adasd", "rqwf"};
        String[] a4 = new String[] {"ghwedbdtn", "adasd"};
//        System.out.println(compareArrays(a1, a2));
        System.out.println(compareArrays(a1, a3));
        System.out.println(compareArrays(a1, a4));
    }
}
