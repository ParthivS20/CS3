package Lab15_BinPacking;

public class BinPackingRunner {
    public static void main(String[] args) {
        //System.out.println("Worst Fit");
        //WorstFit a = new WorstFit("input20.txt");
        //a.solve();

        System.out.println("\nWorst Fit (Decreasing)");
        WorstFitDecreasing b = new WorstFitDecreasing("input20.txt");
        b.solve();
    }
}
