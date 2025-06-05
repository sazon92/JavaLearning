/**
 * Основной метод для тестирования реализации.
 */
public static void main(String[] args) {
    MyHashMap<String, Integer> map = new MyHashMap<>();
    for (int i = 1; i <= 30; i++) {
        map.put("ключ" + i, i);
    }

    System.out.println("Значение по ключу 'ключ10': " + map.get("ключ10"));
    map.remove("ключ10");
    System.out.println("После удаления: " + map.get("ключ10")); // должно быть null

    map.put("ключ10", 999);
    System.out.println("После повторного добавления: " + map.get("ключ10"));
}