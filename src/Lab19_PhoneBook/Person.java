package Lab19_PhoneBook;

public class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Person)) return false;

        Person p = (Person) obj;
        return p.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
