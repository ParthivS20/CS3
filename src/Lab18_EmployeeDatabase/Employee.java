package Lab18_EmployeeDatabase;

public class Employee {
    String name;

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
