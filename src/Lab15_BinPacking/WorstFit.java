package Lab15_BinPacking;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class WorstFit {
    private int totalSize;
    private ArrayList<Integer> files;
    private Queue<Disk> disks;

    WorstFit(String fileName) {
        fileName = "data/" + fileName;
        files = new ArrayList<>();
        disks = new PriorityQueue<>();

        Scanner file = null;
        try {
            file = new Scanner(new PackageFile(fileName, getClass()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert file != null;
        while (file.hasNextInt()) files.add(file.nextInt());
    }

    void solve() {
        Disk.resetDiskID();

        for (int x : files) {
            totalSize += x;

            Disk disk;
            if (!disks.isEmpty() && disks.peek().getRemainingCapacity() >= x) {
                disk = disks.poll();
            } else {
                disk = new Disk();
            }

            assert disk != null;
            disk.addFile(x);
            disks.offer(disk);
        }

        System.out.println("Total size = " + Disk.MB_TO_GB(totalSize) + " GB");
        System.out.println("Disks req'd = " + disks.size());

        if (disks.size() < 100) {
            while (!disks.isEmpty()) {
                System.out.println(disks.poll());
            }
        }
    }
}
