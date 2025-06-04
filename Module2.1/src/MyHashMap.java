import java.util.Objects;

/**
 * Собственная реализация хэш-таблицы (HashMap) с методами put, get и remove.
 *
 * @param <K> Тип ключей
 * @param <V> Тип значений
 */
public class MyHashMap<K, V> {

    /**
     * Внутренний класс, представляющий пару ключ-значение.
     */
    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Entry<K, V>[] buckets;
    private final int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new Entry[DEFAULT_CAPACITY];
    }

    /**
     * Вычисляет индекс бакета по хэшу ключа.
     */
    private int getBucketIndex(K key) {
        return Math.abs(Objects.hashCode(key)) % buckets.length;
    }

    /**
     * Добавляет новую пару ключ-значение или обновляет значение, если ключ уже существует.
     *
     * @param key   Ключ
     * @param value Значение
     */
    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                current.value = value; // Обновление значения
                return;
            }
            current = current.next;
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
    }

    /**
     * Возвращает значение по заданному ключу.
     *
     * @param key Ключ
     * @return Значение или null, если ключ не найден
     */
    public V get(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    /**
     * Удаляет пару по заданному ключу.
     *
     * @param key Ключ
     * @return Значение удалённого элемента или null, если элемент не найден
     */
    public V remove(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return current.value;
            }
            prev = current;
            current = current.next;
        }

        return null;
    }
}