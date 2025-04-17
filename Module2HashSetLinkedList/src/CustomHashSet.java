import java.util.HashSet;
/**
 *  Простая реализация HashSet через односвязные списки.
 *
 * @param <T> тип элементов в наборе
 */
public class CustomHashSet<T> {

    /* Узел связанного списка, хранящий элемент множества. */
    private static class Node<T> {
        T key;            // Значение элемента
        Node<T> next;     // Ссылка на следующий элемент в цепочке

        Node(T key) {
            this.key = key;
        }
    }

    private final Node<T>[] buckets;  // Массив цепочек (таблица)
    private final int capacity;  // Начальный размер таблицы
    private int size = 0;       // Количество элементов во множестве

    /* Конструктор с заданным размером таблицы. */
    @SuppressWarnings("unchecked")
    public CustomHashSet(int capacity) {
        this.capacity = capacity;
        buckets = new Node[capacity];
    }

    /* Конструктор по умолчанию. Создаёт хеш-таблицу на 16 ячеек. */
    @SuppressWarnings("unchecked")
    public CustomHashSet() {
        capacity = 16;
        buckets = new Node[capacity];
    }

    /**
     *  Вычисляет индекс в таблице для заданного ключа.
     *
     * @param key элемент
     * @return индекс корзины в массиве
     */
    private int getIndex(T key) {
        return (key == null) ? 0 : Math.abs(key.hashCode() % capacity);
    }

    /**
     *  Добавляет элемент в множество.
     *
     * @param key элемент для добавления
     * @return true, если элемент был добавлен; false, если он уже присутствует
     */
    public boolean add(T key) {
        int index = getIndex(key);
        Node<T> current = buckets[index];

        // Проверка, есть ли уже такой элемент
        while (current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                return false; // Дубликат
            }
            current = current.next;
        }

        // Вставка в начало цепочки
        Node<T> newNode = new Node<>(key);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
        return true;
    }

    /**
     *  Проверяет наличие элемента в множестве.
     *
     * @param key элемент для проверки
     * @return true, если элемент найден; false — если отсутствует
     */
    public boolean contains(T key) {
        int index = getIndex(key);
        Node<T> current = buckets[index];
        while (current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     *  Удаляет элемент из множества.
     *
     * @param key элемент для удаления
     * @return true, если элемент был найден и удалён; false — если не найден
     */
    public boolean remove(T key) {
        int index = getIndex(key);
        Node<T> current = buckets[index];
        Node<T> prev = null;

        while (current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                if (prev == null) {
                    buckets[index] = current.next; // Удаляем первый узел
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    /**
     *  Возвращает количество элементов в множестве.
     *
     * @return текущий размер множества
     */
    public int size() {
        return size;
    }

    /**
     *  Проверяет, пусто ли множество.
     *
     * @return true, если множество не содержит элементов; иначе false
     */
    public boolean isEmpty() {
        return size == 0;
    }
}