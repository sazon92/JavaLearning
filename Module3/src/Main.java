public class Main {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        String filename = "my_data.txt"; // Имя файла
        String dataToWrite = "Привет, мир!\nЭто тестовые данные для записи в файл.\nTest";

        // --- Запись в файл ---
        try {
            System.out.println("Попытка записи данных в файл '" + filename + "'...");
            fileManager.writeToFile(filename, dataToWrite);
            System.out.println("Данные успешно записаны.");
        } catch (FileOperationException e) {
            System.err.println("Произошла ошибка при записи:");
            System.err.println("Сообщение: " + e.getMessage());
            // Можно также вывести исходную причину ошибки, если она есть
            if (e.getCause() != null) {
                System.err.println("Причина: " + e.getCause().getMessage());
            }
        }

        System.out.println("\n-----------------------------\n");

        // --- Чтение из файла ---
        try {
            System.out.println("Попытка чтения данных из файла '" + filename + "'...");
            String dataRead = fileManager.readFromFile(filename);
            System.out.println("Данные успешно прочитаны:");
            System.out.println("----------------------------");
            System.out.println(dataRead);
            System.out.println("----------------------------");

        } catch (FileOperationException e) {
            System.err.println("Произошла ошибка при чтении:");
            System.err.println("Сообщение: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Причина: " + e.getCause().getMessage());
            }
        }

        // --- Попытка чтения несуществующего файла для демонстрации ошибки ---
        System.out.println("\n-----------------------------\n");
        String nonExistentFile = "non_existent_file.txt";
        try {
            System.out.println("Попытка чтения данных из несуществующего файла '" + nonExistentFile + "'...");
            String dataRead = fileManager.readFromFile(nonExistentFile);
            System.out.println("Прочитанные данные (не должно отобразиться): " + dataRead);
        } catch (FileOperationException e) {
            System.err.println("Ожидаемая ошибка при чтении несуществующего файла:");
            System.err.println("Сообщение: " + e.getMessage());
            // Показываем тип и сообщение исходной ошибки
            if (e.getCause() != null) {
                System.err.println("Причина: " + e.getCause().getClass().getName() + ": " + e.getCause().getMessage());
            }
        }
    }
}