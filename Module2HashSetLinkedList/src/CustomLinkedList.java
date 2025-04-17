/**
 * Простая реализация односвязного списка.
 *
 * @param <T> тип элементов в списке
 */
public class CustomLinkedList<T> {
    /**
     * Внутренний класс, представляющий узел списка.
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    /**
     * Добавляет элемент в конец списка.
     *
     * @param value элемент, который нужно добавить
     */
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент на указанной позиции
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public T get(int index) {
        checkBounds(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.data;
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс удаляемого элемента
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public void remove(int index) {
        checkBounds(index);
        if (index == 0) {
            head = head.next;
            if (head == null) tail = null;
        } else {
            Node<T> prev = head;
            for (int i = 0; i < index - 1; i++) prev = prev.next;
            prev.next = prev.next.next;
            if (index == size - 1) tail = prev;
        }
        size--;
    }

    /**
     * Добавляет в конец текущего списка все элементы из другого списка.
     *
     * @param other другой список, элементы которого будут добавлены
     */
    public void addAll(CustomLinkedList<T> other) {
        for (int i = 0; i < other.size(); i++) {
            add(other.get(i));
        }
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return количество элементов
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, входит ли индекс в допустимые границы.
     *
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс недопустим
     */
    private void checkBounds(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за границы (размер: " + size + ")");
    }
}
