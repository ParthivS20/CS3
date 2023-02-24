package Lab18_EmployeeDatabase;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeDatabaseRunner {
    private static final double[] loadFactors = {0.10, 0.50, 0.80, 0.90, 0.99};

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter outputFile = new PrintWriter(new PackageFile("database report.txt", EmployeeDatabaseRunner.class));

        Scanner dataFile = new Scanner(new PackageFile("data/Large Data Set.txt", EmployeeDatabaseRunner.class));
        Scanner successfulFile = new Scanner(new PackageFile("data/Successful Search Records.txt", EmployeeDatabaseRunner.class));
        Scanner unsuccessfulFile = new Scanner(new PackageFile("data/Unsuccessful Search Records.txt", EmployeeDatabaseRunner.class));

        ArrayList<String> largeDataSet = new ArrayList<>();
        while (dataFile.hasNextLine()) largeDataSet.add(dataFile.nextLine());

        ArrayList<String> successfulDataSet = new ArrayList<>();
        while (successfulFile.hasNextLine()) successfulDataSet.add(successfulFile.nextLine());

        ArrayList<String> unsuccessfulDataSet = new ArrayList<>();
        while (unsuccessfulFile.hasNextLine()) unsuccessfulDataSet.add(unsuccessfulFile.nextLine());

        outputFile.println("Data Set Size: " + unsuccessfulDataSet.size());
        outputFile.println("Hash Method: Modular Hashing");
        outputFile.println();

        for (EmployeeDatabase.ProbeMethod method : EmployeeDatabase.ProbeMethod.values()) {
            for (double loadFactor : loadFactors) {
                outputFile.println("Probe Method: " + method + " (\u03B1 = " + loadFactor + ")");

                EmployeeDatabase db = new EmployeeDatabase((int) (largeDataSet.size() / loadFactor));
                db.method = method;

                outputFile.println("  \u2022 Table Size: " + db.getLength());
                outputFile.println();

                long buildTimer = 0;
                long successfulSearchTimer = 0;
                long unsuccessfulSearchTimer = 0;

                buildTimer = System.currentTimeMillis();
                for (String e : largeDataSet) {
                    String[] x = e.split(" ", 2);
                    db.put(Integer.parseInt(x[0]), new Employee(x[1]));
                }
                buildTimer = System.currentTimeMillis() - buildTimer;

                outputFile.println("  \u2022 Build Timer: " + buildTimer + "ms");
                outputFile.println("  \u2022 Build Collisions: " + db.getBuildCollisions());
                outputFile.println();

                db.resetSearchQueries();
                successfulSearchTimer = System.currentTimeMillis();
                for (String e : successfulDataSet) {
                    String[] x = e.split(" ", 2);
                    db.get(Integer.parseInt(x[0]));
                }
                successfulSearchTimer = System.currentTimeMillis() - successfulSearchTimer;

                outputFile.println("  \u2022 Successful Search Timer: " + successfulSearchTimer + "ms");
                outputFile.println("  \u2022 Average Successful Search Queries: " + db.getAvgQueries());
                outputFile.println();

                db.resetSearchQueries();
                unsuccessfulSearchTimer = System.currentTimeMillis();
                for (String e : unsuccessfulDataSet) {
                    String[] x = e.split(" ", 2);
                    db.get(Integer.parseInt(x[0]));
                }
                unsuccessfulSearchTimer = System.currentTimeMillis() - unsuccessfulSearchTimer;

                outputFile.println("  \u2022 Unsuccessful Search Timer: " + unsuccessfulSearchTimer + "ms");
                outputFile.println("  \u2022 Average Unsuccessful Search Queries: " + db.getAvgQueries());
                outputFile.println();
            }
        }

        outputFile.close();
    }
}
