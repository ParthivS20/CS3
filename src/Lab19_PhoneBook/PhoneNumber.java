package Lab19_PhoneBook;

public class PhoneNumber {
    String number;

    PhoneNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number;
    }
}
