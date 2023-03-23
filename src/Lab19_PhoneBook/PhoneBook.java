package Lab19_PhoneBook;

public class PhoneBook implements IMap {
    private Entry<Person, PhoneNumber>[] entries;
    private int size;

    PhoneBook(int size) {
        entries = new Entry[size];
        this.size = 0;
    }

    @Override
    public PhoneNumber put(Person person, PhoneNumber phone) {
        int hash = hash(person);

        Entry<Person, PhoneNumber> node = entries[hash];
        if (node == null) {
            entries[hash] = new Entry<>(person, phone);
            size++;
            return null;
        }

        while (node != null) {
            if (node.key.equals(person)) {
                PhoneNumber temp = node.value;
                node.value = phone;
                return temp;
            }

            node = node.next;
        }

        Entry<Person, PhoneNumber> temp = entries[hash];
        entries[hash] = new Entry<>(person, phone);
        entries[hash].next = temp;
        size++;

        return temp == null ? null : temp.value;
    }

    @Override
    public PhoneNumber get(Person person) {
        int hash = hash(person);

        Entry<Person, PhoneNumber> node = entries[hash];
        while (node != null) {
            if (node.key.equals(person)) return node.value;
            node = node.next;
        }

        return null;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public PhoneNumber remove(Person person) {
        int hash = hash(person);

        Entry<Person, PhoneNumber> node = entries[hash];
        Entry<Person, PhoneNumber> prevNode = null;
        while (node != null) {
            if (node.key.equals(person)) {
                if (prevNode == null) {
                    entries[hash] = null;
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

    private int hash(Person person) {
        return Math.abs(person.hashCode()) % entries.length;
    }
}
