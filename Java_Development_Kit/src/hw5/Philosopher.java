package hw5;

import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread{
    private Action actionPhilosopher;
    private volatile ForkStatus statusFork;
    private int countEatToStop;
    private int countEat;
    private Philosopher neighbor;
    CountDownLatch countDownLatch;

    public Philosopher(String name, CountDownLatch countDownLatch) {
        super(name);
        this.actionPhilosopher = Action.THINK;
        this.statusFork = ForkStatus.FREE;
        this.countEatToStop = 3;
        this.countEat = 0;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.printf("Я %s, а мой сосед - %s\n", getName(), neighbor.getName());
        while (countEatToStop > countEat) {
            synchronized (this) {
//                System.out.printf("Я, %s, моя вилка - %s. Сосед - %s, его вилка - %s. Они равны - %s.\n",
//                        this.getName(), this.statusFork, neighbor.getName(),
//                        neighbor.getStatusFork(), this.statusFork == neighbor.getStatusFork());
                synchronized (neighbor) {
                    if (actionPhilosopher == Action.THINK && statusFork == ForkStatus.FREE &&
                            neighbor.getStatusFork() == ForkStatus.FREE) {
                        this.statusFork = ForkStatus.BUSY;
                        neighbor.setStatusFork(ForkStatus.BUSY);
                        this.actionPhilosopher = Action.EAT;
                        System.out.printf("Я, %s, сейчас ем в %s раз. Мне очень вкусно.\n", this.getName(), ++countEat);
                        try {
                            Thread.sleep(1_000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

            if (actionPhilosopher == Action.EAT) {
                synchronized (neighbor) {
                    System.out.printf("Я, %s, сейчас буду думать. Не только пузо надо набивать.\n", this.getName());
                    synchronized (this) {
                        this.statusFork = ForkStatus.FREE;
                    }
                    neighbor.setStatusFork(ForkStatus.FREE);
                    this.actionPhilosopher = Action.THINK;

                }
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            
        }
        countDownLatch.countDown();
    }

    public void setNeighbor(Philosopher neighbor) {
        this.neighbor = neighbor;
    }

    public ForkStatus getStatusFork() {
        return statusFork;
    }

    public void setStatusFork(ForkStatus statusFork) {
        this.statusFork = statusFork;
    }

    private enum Action {
        EAT, THINK;
    }
    private enum ForkStatus {
        BUSY, FREE;
    }
}
