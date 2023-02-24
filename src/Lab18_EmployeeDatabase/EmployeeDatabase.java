package Lab18_EmployeeDatabase;

import java.util.ArrayList;

public class EmployeeDatabase {
    public ProbeMethod method = ProbeMethod.LINEAR;
    private Entry[] entries;
    private int buildCollisions;
    private ArrayList<Integer> searchQueries;
    private int size;

    public EmployeeDatabase(int employees) {
        entries = new Entry[nextPrime(employees)];
        size = 0;
        buildCollisions = 0;
        searchQueries = new ArrayList<>();
    }

    private int hash(int key) {
        return key % entries.length;
    }

    void put(int key, Employee value) {
        int hashedKey = hash(key);
        int i = 1;
        do {
            if (hashedKey >= 0 && (entries[hashedKey] == null || entries[hashedKey].ID == key)) {
                entries[hashedKey] = new Entry(key, value);
                size++;
                return;
            }

            buildCollisions++;
            hashedKey = (method == ProbeMethod.LINEAR ? hashedKey + i : hashedKey + (int) Math.pow(i, 2)) % entries.length;
            i++;
        } while (hashedKey < entries.length);
    }

    Employee get(int key) {
        int hashedKey = hash(key);
        int i = 1;
        do {
            if (hashedKey >= 0 && entries[hashedKey] != null && entries[hashedKey].ID == key) {
                searchQueries.add(i);
                return entries[hashedKey].employee;
            }

            hashedKey = (method == ProbeMethod.LINEAR ? hashedKey + i : hashedKey + (int) Math.pow(i, 2)) % entries.length;
            i++;
        } while (hashedKey < entries.length);

        searchQueries.add(i);
        return null;
    }

    public void resetSearchQueries() {
        searchQueries.clear();
    }

    public double getAvgQueries() {
        double sum = 0;

        for (int x : searchQueries) {
            sum += x;
        }

        return sum / searchQueries.size();
    }

    public int getBuildCollisions() {
        return buildCollisions;
    }

    public int getSize() {
        return size;
    }

    public int getLength() {
        return entries.length;
    }

    @Override
    public String toString() {
        String x = "";
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null) continue;
            x += "[" + i + "] " + entries[i] + (i < entries.length - 1 ? "\n" : "");
        }
        return x;
    }

    public enum ProbeMethod {
        LINEAR,
        QUADRATIC
    }

    private static class Entry {
        int ID;
        Employee employee;

        Entry(int ID, Employee employee) {
            this.ID = ID;
            this.employee = employee;
        }

        @Override
        public String toString() {
            return ID + ": " + employee;
        }
    }

    private boolean isPrime(int n) {
        if (n == 2 || n == 3) return true;
        if (n <= 1 || n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }

        return true;
    }

    private int nextPrime(int n) {
        if (n <= 1) return 2;

        boolean found = false;

        while (!found) {
            found = isPrime(n);
            n++;
        }

        return n - 1;
    }
}
