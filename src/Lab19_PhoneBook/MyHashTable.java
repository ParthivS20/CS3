package Lab19_PhoneBook;

public class MyHashTable<K, V> {
    private Entry<K, V>[] entries;
    private int size;

    MyHashTable() {
        this(500000);
    }

    MyHashTable(int size) {
        entries = new Entry[size];
        this.size = 0;
    }

    public V put(K key, V value) {
        int hash = hash(key);

        Entry<K, V> node = entries[hash];
        if (node == null) {
            entries[hash] = new Entry<>(key, value);
            size++;
            return null;
        }

        while (node != null) {
            if (node.key.equals(key)) {
                V temp = node.value;
                node.value = value;
                return temp;
            }

            node = node.next;
        }

        Entry<K, V> temp = entries[hash];
        entries[hash] = new Entry<>(key, value);
        entries[hash].next = temp;
        size++;

        return temp == null ? null : temp.value;
    }

    public V get(K key) {
        int hash = hash(key);

        Entry<K, V> node = entries[hash];
        while (node != null) {
            if (node.key.equals(key)) return node.value;
            node = node.next;
        }

        return null;
    }

    public int size() {
        return size;
    }

    public V remove(K key) {
        int hash = hash(key);

        Entry<K, V> node = entries[hash];
        Entry<K, V> prevNode = null;

        while (node != null) {
            if (node.key.equals(key)) {
                if (prevNode == null) {
                    entries[hash] = node.next;
                } else {
                    prevNode.next = node.next;
                }

                size--;
                return node.value;
            }

            prevNode = node;
            node = node.next;
        }

        return null;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % entries.length;
    }
}
