/**
 * Класс для демонстрации LiveLock (активной блокировки)
 */
class LivelockDemo {
    static int delayTime = 2000;
    static int workTime = 100;
    private static final Object lock = new Object();
    private static volatile boolean isLocked = true;

    /**
     * Метод демонстрации активной блокировки потоков
     */
    public static void showLivelock() {
        Thread worker1 = new Thread(() -> {
            while (isLocked) {
                synchronized (lock) {
                    System.out.println("Рабочий 1: Пытается выполнить работу...");
                    if (!isLocked) {
                        System.out.println("Рабочий 1: Наконец-то работает!");
                        break;
                    }

                    try {
                        Thread.sleep(workTime);
                        lock.notify(); // Уведомляем другой поток
                        lock.wait();   // Освобождаем блокировку
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        });

        Thread worker2 = new Thread(() -> {
            while (isLocked) {
                synchronized (lock) {
                    System.out.println("Рабочий 2: Пытается выполнить работу...");
                    if (!isLocked) {
                        System.out.println("Рабочий 2: Наконец-то работает!");
                        break;
                    }

                    try {
                        Thread.sleep(workTime);
                        lock.notify(); // Уведомляем другой поток
                        lock.wait();   // Освобождаем блокировку
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        });

        // Поток, который разблокирует рабочих через 2 секунды
        Thread resolver = new Thread(() -> {
            try {
                Thread.sleep(delayTime);

                // Последнее уведомление для пробуждения потоков
                synchronized (lock) {
                    isLocked = false;
                    lock.notifyAll();
                }
                System.out.println("\nПрограмма не завершилась из-за LiveLock (активной блокировки потоков)");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        worker1.start();
        worker2.start();
        resolver.start();
    }
}