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

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] buckets;
    private int size = 0;
    private final float loadFactor;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new Entry[DEFAULT_CAPACITY];
        this.loadFactor = DEFAULT_LOAD_FACTOR;
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
        if (size >= buckets.length * loadFactor) {
            resize();
        }

        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        size++;
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
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }

            prev = current;
            current = current.next;
        }

        return null;
    }

    /**
     * Перехэширует все элементы в массив нового размера.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldBuckets = buckets;
        buckets = new Entry[oldBuckets.length * 2];
        size = 0;

        for (Entry<K, V> bucket : oldBuckets) {
            while (bucket != null) {
                put(bucket.key, bucket.value);
                bucket = bucket.next;
            }
        }
    }


}