/**
 * Класс для демонстрации DeadLock и LiveLock
 */
public class Main {
    static int delayTime = 5000;
    public static void main(String[] args) {
        System.out.println("=== Демонстрация типовых проблем многопоточности ===");

        System.out.println("\n1. Запуск DeadLock примера:");
        DeadlockDemo.showDeadlock();
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
        }

        System.out.println("\n2. Запуск LiveLock примера:");
        LivelockDemo.showLivelock();
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
        }

        System.out.println("\n3. Поочередный вывод чисел:");
        AlternatingOutputDemo.showAlternatingOutput();
    }
}