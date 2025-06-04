/**
 * Основной метод для тестирования реализации.
 */
public static void main(String[] args) {
    MyHashMap<String, Integer> map = new MyHashMap<>();

    map.put("один", 1);
    map.put("два", 2);
    map.put("три", 3);

    System.out.println("Получить 'два': " + map.get("два")); // 2

    map.put("два", 22);
    System.out.println("Обновлённое значение 'два': " + map.get("два")); // 22

    System.out.println("Удаление 'один': " + map.remove("один")); // 1
    System.out.println("Попытка получить удалённый 'один': " + map.get("один")); // null
}