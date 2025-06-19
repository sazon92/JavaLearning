package Adapter;

/**
 * Адаптер от старого принтера к новому интерфейсу.
 */
public class PrinterAdapter implements Printer {
    private final OldPrinter oldPrinter;

    public PrinterAdapter(OldPrinter oldPrinter) {
        this.oldPrinter = oldPrinter;
    }

    @Override
    public void print(String message) {
        oldPrinter.printText(message);
    }
}
