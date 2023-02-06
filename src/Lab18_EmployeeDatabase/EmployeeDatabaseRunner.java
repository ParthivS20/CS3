package Lab18_EmployeeDatabase;

public class EmployeeDatabaseRunner {
    public static void main(String[] args) {
        EmployeeDatabase db = new EmployeeDatabase();
        db.method = EmployeeDatabase.ProbeMethod.QUADRATIC;

        db.put(23761, new Employee("test1"));
        db.put(53761, new Employee("test2"));
        db.put(73761, new Employee("test3"));
        System.out.println(db);

        System.out.println(db.get(23761));
    }
}
