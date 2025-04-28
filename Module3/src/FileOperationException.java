import java.io.IOException;

/**
 * Пользовательское исключение для ошибок файловых операций.
 * Оборачивает стандартные IOException для предоставления
 * более специфичного контекста ошибки.
 */
public class FileOperationException extends IOException {

    /**
     * Конструктор с сообщением об ошибке.
     * @param message Описание ошибки.
     */
    public FileOperationException(String message) {
        super(message);
    }

    /**
     * Конструктор с сообщением об ошибке и исходной причиной.
     * @param message Описание ошибки.
     * @param cause Исходное исключение.
     */
    public FileOperationException(String message, Throwable cause) {
        super(message,cause);
    }
}
