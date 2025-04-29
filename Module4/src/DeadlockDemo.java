/**
 * Класс для демонстрации DeadLock (взаимной блокировки)
 */
class DeadlockDemo {
    static int delayTime = 2000;
    static int workTime = 100;
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    /**
     * Метод демонстрации взаимной блокировки двух потоков
     */
    public static void showDeadlock() {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Поток 1: Захватил lock1");
                try {
                    Thread.sleep(workTime); // Имитация работы
                } catch (InterruptedException e) {
                }

                System.out.println("Поток 1: Ожидает lock2...");
                synchronized (lock2) {
                    System.out.println("Поток 1: Захватил оба lock'а (эта строка никогда не выполнится)");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Поток 2: Захватил lock2");
                try {
                    Thread.sleep(workTime); // Имитация работы
                } catch (InterruptedException e) {
                }

                System.out.println("Поток 2: Ожидает lock1...");
                synchronized (lock1) {
                    System.out.println("Поток 2: Захватил оба lock'а (эта строка никогда не выполнится)");
                }
            }
        });

        thread1.start();
        thread2.start();

        // Даем потокам время заблокироваться
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
        }

        System.out.println("\nПрограмма не завершилась из-за DeadLock (взаимной блокировки потоков)");
    }
}