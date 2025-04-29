/**
 * Класс для демонстрации поочередного вывода чисел
 */
class AlternatingOutputDemo {
    private static final Object lock = new Object();
    private static volatile boolean isOnesTurn = true;

    public static void showAlternatingOutput() {
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!isOnesTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    System.out.print("1 ");
                    isOnesTurn = false;
                    lock.notifyAll();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (isOnesTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    System.out.print("2 ");
                    isOnesTurn = true;
                    lock.notifyAll();
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}
