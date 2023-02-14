package Lab15_BinPacking;

import java.util.ArrayList;

class Disk implements Comparable<Disk> {
    private static final int MAX_CAPACITY = 1_000_000;
    private static int nextID = 0;

    private int size;
    private final int id;
    private final ArrayList<Integer> files;

    Disk() {
        this.size = 0;
        this.id = nextID;
        this.files = new ArrayList<>();
        nextID++;
    }

    Disk(int fileSize) {
        this();
        addFile(fileSize);
    }

    int getRemainingCapacity() {
        return MAX_CAPACITY - size;
    }

    static double MB_TO_GB(int mb) {
        return (double) mb / 1_000_000;
    }

    @Override
    public int compareTo(Disk o) {
        return this.size - o.size;
    }

    @Override
    public String toString() {
        String s = id + "\t" + getRemainingCapacity() + ":\t";
        for(int x : files) {
            s += x + " ";
        }
        return s.substring(0, s.length() - 1);
    }

    static void resetDiskID() {
        nextID = 0;
    }

    void addFile(int size) {
        if (size > getRemainingCapacity()) throw new IllegalArgumentException("File size too large");

        files.add(size);
        this.size += size;
    }
}
