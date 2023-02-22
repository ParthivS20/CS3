package Lab18_EmployeeDatabase;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeDatabaseRunner {
    public static void main(String[] args) throws FileNotFoundException {
        double[] loadFactors = {0.1, 0.5, 0.8, 0.9, 0.99};

        long buildTimer = 0;
        long successfulSearchTimer = 0;
        long unsuccessfulSearchTimer = 0;

        Scanner file = new Scanner(new PackageFile("data/Large Data Set.txt", EmployeeDatabaseRunner.class));
        ArrayList<String> input = new ArrayList<>();

        while (file.hasNextLine()) input.add(file.nextLine());

        EmployeeDatabase db = new EmployeeDatabase(input.size());
        db.method = EmployeeDatabase.ProbeMethod.QUADRATIC;

        System.out.println("Database Length: " + db.getLength());
        System.out.println();

        buildTimer = System.currentTimeMillis();
        for (String e : input) {
            String[] x = e.split(" ", 2);
            db.put(Integer.parseInt(x[0]), new Employee(x[1]));
        }
        buildTimer = System.currentTimeMillis() - buildTimer;

        System.out.println("Build Timer: " + buildTimer + "ms");
        System.out.println("Build Collisions: " + db.getBuildCollisions());
        System.out.println("Database Size: " + db.getSize());
        System.out.println();

        file = new Scanner(new PackageFile("data/Successful Search Records.txt", EmployeeDatabaseRunner.class));
        input.clear();
        while (file.hasNextLine()) input.add(file.nextLine());

        db.resetSearchQueries();
        successfulSearchTimer = System.currentTimeMillis();
        for (String e : input) {
            String[] x = e.split(" ", 2);
            db.get(Integer.parseInt(x[0]));
        }
        successfulSearchTimer = System.currentTimeMillis() - successfulSearchTimer;

        System.out.println("Successful Search Timer: " + successfulSearchTimer + "ms");
        System.out.println("Average Successful Search Queries: " + db.getAvgQueries());
        System.out.println();


        file = new Scanner(new PackageFile("data/Unsuccessful Search Records.txt", EmployeeDatabaseRunner.class));
        input.clear();
        while (file.hasNextLine()) input.add(file.nextLine());

        db.resetSearchQueries();
        unsuccessfulSearchTimer = System.currentTimeMillis();
        for (String e : input) {
            String[] x = e.split(" ", 2);
            db.get(Integer.parseInt(x[0]));
        }
        unsuccessfulSearchTimer = System.currentTimeMillis() - unsuccessfulSearchTimer;

        System.out.println("Successful Search Timer: " + unsuccessfulSearchTimer + "ms");
        System.out.println("Average Successful Search Queries: " + db.getAvgQueries());
    }
}
