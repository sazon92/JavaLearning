import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Класс для управления операциями записи и чтения файлов.
 * Использует FileOperationException для обработки ошибок.
 */
public class FileManager {

    /**
     * Записывает указанные данные в файл.
     * Если файл существует, он будет перезаписан.
     *
     * @param filePath Путь к файлу.
     * @param data     Строка данных для записи.
     * @throws FileOperationException Если возникает ошибка при записи файла (например, нет прав доступа, диск полон).
     */
    public void writeToFile(String filePath, String data) throws FileOperationException {
        // Используем try-with-resources для автоматического закрытия BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8))) {
            writer.write(data);
            writer.newLine(); // Добавим новую строку для наглядности, если нужно писать несколько раз
        } catch (IOException e) {
            // Оборачиваем стандартное IOException в наше пользовательское исключение
            String errorMessage = String.format("Ошибка при записи в файл '%s'. Причина: %s", filePath, e.getMessage());
            throw new FileOperationException(errorMessage, e);
        }
    }

    /**
     * Читает все содержимое файла и возвращает его в виде строки.
     *
     * @param filePath Путь к файлу.
     * @return Содержимое файла в виде строки.
     * @throws FileOperationException Если возникает ошибка при чтении файла (например, файл не найден, нет прав доступа).
     */
    public String readFromFile(String filePath) throws FileOperationException {
        StringBuilder content = new StringBuilder();
        // Используем try-with-resources для автоматического закрытия BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator()); // Добавляем прочитанную строку и системный разделитель строк
            }
        } catch (IOException e) {
            // Оборачиваем стандартное IOException в наше пользовательское исключение
            String errorMessage = String.format("Ошибка при чтении из файла '%s'. Причина: %s", filePath, e.getMessage());
            throw new FileOperationException(errorMessage, e);
        }

        // Удаляем последний разделитель строк, если он был добавлен
        if (!content.isEmpty()) {
            content.setLength(content.length() - System.lineSeparator().length());
        }

        return content.toString();
    }
}
