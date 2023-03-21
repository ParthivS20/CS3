package Lab19_PhoneBook;

public class PhoneBook implements IMap {
    private Entry<Person, PhoneNumber>[] entries;
    private int size;

    PhoneBook() {
        entries = new Entry[1];
        size = 0;
    }

    @Override
    public PhoneNumber put(Person person, PhoneNumber phone) {
        size++;
        return null;
    }

    @Override
    public PhoneNumber get(Person person) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public PhoneNumber remove(Person person) {
        return null;
    }
}
