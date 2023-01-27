package Lab15_BinPacking;

import java.util.ArrayList;

class Disk implements Comparable<Disk> {
    static final int MAX_CAPACITY = 1_000_000;
    static int nextID = 0;

    private int size;
    private final int id;
    private final ArrayList<Integer> files;

    Disk() {
        this.size = 0;
        this.id = nextID;
        this.files = new ArrayList<>();
        nextID++;
    }

    int getRemainingCapacity() {
        return MAX_CAPACITY - size;
    }

    void addFile(int size) {
        files.add(size);
        this.size += size;
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
}
