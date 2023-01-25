package Lab15_BinPacking;

import java.io.FileNotFoundException;
import java.util.*;
import Utils.MyFile;

public class WorstFitDecreasing {
    int totalSize;
    ArrayList<Integer> files;
    Queue<Disk> disks;

    WorstFitDecreasing(String fileName){
        fileName = "data/" + fileName;
        files = new ArrayList<>();
        disks = new PriorityQueue<>();
        disks.offer(new Disk());

        Scanner file = null;
        try {
            file = new Scanner(new MyFile(fileName, getClass()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(file.hasNextInt()) files.add(file.nextInt());
        files.sort(Comparator.reverseOrder());
    }

    void solve() {
        Disk.nextID = 0;
        for(int x : files) {
            totalSize += x;

            Disk d;
            if(!disks.isEmpty() && disks.peek().getRemainingCapacity() >= x) {
                d = disks.poll();
            } else {
                d = new Disk();
            }

            d.addFile(x);
            disks.offer(d);
        }

        System.out.println("Total size = " + ((double) totalSize / Disk.MAX_CAPACITY) + " GB");
        System.out.println("Disks req'd = " + disks.size());

        if(disks.size() < 100) {
            while(!disks.isEmpty()) {
                System.out.println(disks.poll());
            }
        }
    }
}
