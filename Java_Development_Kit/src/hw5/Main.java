package hw5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {
    /*
    1. Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
    2. Вилки лежат на столе между каждой парой ближайших философов.
    3. Каждый философ может либо есть, либо размышлять.
    4. Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
    5. Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
    Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
     */

    private static int countPhilosofers = 5;
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(countPhilosofers);
        List<Philosopher> philosophers = new ArrayList<>();
        for (int i = 0; i < countPhilosofers; i++) {
            philosophers.add(new Philosopher(String.format("Философ %s", i + 1), countDownLatch));
        }
        for (int i = 0, j = 1; i < countPhilosofers; i++, j++) {
            if (j == countPhilosofers) {
                j = 0;
            }
            philosophers.get(i).setNeighbor(philosophers.get(j));
        }
        philosophers.stream().forEach(Philosopher::start);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Задача выполнена. Все философы поели необходимое кол-во раз.");
    }
}
