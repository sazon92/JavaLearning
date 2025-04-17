public class Main {
    public static void main(String[] args) {
        CustomHashSet<String> set = new CustomHashSet<>();

        System.out.println("=== Тестирование CustomHashSet ===");

        // Проверка добавления элементов
        System.out.println("Добавление элементов:");
        System.out.println("Добавлен 'apple': " + set.add("apple"));  // true
        System.out.println("Добавлен 'banana': " + set.add("banana")); // true
        System.out.println("Добавлен снова 'apple': " + set.add("apple")); // false (дубликат)
        System.out.println("Добавлен null: " + set.add(null)); // true
        System.out.println("Добавлен снова null: " + set.add(null)); // false (дубликат)
        System.out.println("Текущий размер: " + set.size()); // 3 (apple, banana, null)
        System.out.println();

        // Проверка contains
        System.out.println("Проверка наличия элементов:");
        System.out.println("Содержит 'apple': " + set.contains("apple")); // true
        System.out.println("Содержит 'orange': " + set.contains("orange")); // false
        System.out.println("Содержит null: " + set.contains(null)); // true
        System.out.println();

        // Проверка remove
        System.out.println("Удаление элементов:");
        System.out.println("Удалён 'banana': " + set.remove("banana")); // true
        System.out.println("Удалён 'banana' снова: " + set.remove("banana")); // false (уже удалён)
        System.out.println("Удалён null: " + set.remove(null)); // true
        System.out.println("Текущий размер: " + set.size()); // 1 (остался только 'apple')
        System.out.println();

        // Проверка isEmpty
        System.out.println("Пустой ли набор: " + set.isEmpty()); // false
        System.out.println("Удалён 'apple': " + set.remove("apple")); // true
        System.out.println("Набор пуст: " + set.isEmpty()); // true

        // Создаем тестовый список
        CustomLinkedList<String> list = new CustomLinkedList<>();

        System.out.println("=== Тестирование CustomLinkedList ===");

        // Тест 1: Добавление элементов и проверка размера
        System.out.println("\n1. Тест добавления элементов:");
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println("Список после добавления A, B, C: " + listToString(list));
        System.out.println("Размер списка: " + list.size() + " (ожидается: 3)");

        // Тест 2: Получение элементов по индексу
        System.out.println("\n2. Тест получения элементов:");
        System.out.println("Элемент с индексом 0: " + list.get(0) + " (ожидается: A)");
        System.out.println("Элемент с индексом 1: " + list.get(1) + " (ожидается: B)");
        System.out.println("Элемент с индексом 2: " + list.get(2) + " (ожидается: C)");

        // Тест 3: Удаление элементов
        System.out.println("\n3. Тест удаления элементов:");
        list.remove(1); // Удаляем элемент "B"
        System.out.println("Список после удаления элемента с индексом 1: " + listToString(list));
        System.out.println("Размер списка после удаления: " + list.size() + " (ожидается: 2)");
        System.out.println("Элемент с индексом 1 теперь: " + list.get(1) + " (ожидается: C)");

        // Тест 4: Проверка граничных условий
        System.out.println("\n4. Тест граничных условий:");
        try {
            System.out.println("Попытка получить элемент с индексом 5:");
            list.get(5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        // Тест 5: Добавление другого списка
        System.out.println("\n5. Тест добавления другого списка:");
        CustomLinkedList<String> anotherList = new CustomLinkedList<>();
        anotherList.add("D");
        anotherList.add("E");
        list.addAll(anotherList);
        System.out.println("Список после добавления anotherList: " + listToString(list));
        System.out.println("Размер списка: " + list.size() + " (ожидается: 4)");

        // Тест 6: Удаление первого и последнего элементов
        System.out.println("\n6. Тест удаления первого и последнего:");
        list.remove(0); // Удаляем первый
        System.out.println("После удаления первого элемента: " + listToString(list));
        list.remove(list.size() - 1); // Удаляем последний
        System.out.println("После удаления последнего элемента: " + listToString(list));
        System.out.println("Размер списка: " + list.size() + " (ожидается: 2)");
    }

    // Вспомогательный метод для вывода списка
    private static <T> String listToString(CustomLinkedList<T> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}