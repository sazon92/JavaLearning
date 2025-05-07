import Strategy.PaymentContext;
import Strategy.PayPalPayment;

import ChainOfResponsibility.AuthHandler;
import ChainOfResponsibility.LogHandler;
import ChainOfResponsibility.Handler;

import Builder.User;

import Proxy.Internet;
import Proxy.ProxyInternet;

import Decorator.Notifier;
import Decorator.EmailNotifier;
import Decorator.SMSNotifier;

import Adapter.Printer;
import Adapter.PrinterAdapter;
import Adapter.OldPrinter;

public class Main {
    public static void main(String[] args) {
        strategyDemo();
        chainOfResponsibilityDemo();
        builderDemo();
        proxyDemo();
        decoratorDemo();
        adapterDemo();
    }

    public static void strategyDemo() {
        System.out.println("--- Стратегия ---");
        PaymentContext context = new PaymentContext();
        context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.executePayment(150.0);
    }

    public static void chainOfResponsibilityDemo() {
        System.out.println("\n--- Цепочка обязанностей ---");
        Handler auth = new AuthHandler();
        Handler log = new LogHandler();
        auth.setNext(log);
        auth.handleRequest("auth");
        auth.handleRequest("log");
    }

    public static void builderDemo() {
        System.out.println("\n--- Билдер ---");
        User user = new User.Builder()
                .setFirstName("Иван")
                .setLastName("Иванов")
                .setAge(30)
                .build();
        System.out.println(user);
    }

    public static void proxyDemo() {
        System.out.println("\n--- Прокси ---");
        Internet internet = new ProxyInternet();
        internet.connectTo("example.com");
        internet.connectTo("banned.com");
    }

    public static void decoratorDemo() {
        System.out.println("\n--- Декоратор ---");
        Notifier notifier = new SMSNotifier(new EmailNotifier());
        notifier.send("Ваша посылка отправлена");
    }

    public static void adapterDemo() {
        System.out.println("\n--- Адаптер ---");
        Printer printer = new PrinterAdapter(new OldPrinter());
        printer.print("Документ #1");
    }
}