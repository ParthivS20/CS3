package Lab16_MinHeap;

public class MinHeap<T extends Comparable<T>> {
    public static final int DEFAULT_CAPACITY = 8;

    private T[] heap;
    private int size;

    public MinHeap() {
        heap = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    public MinHeap(T... vals) {
        size = vals.length;
        int newHeapSize = size + 1;

        heap = (T[]) new Comparable[newHeapSize];

        System.arraycopy(vals, 0, heap, 1, vals.length);
        for (int i = getParentIndex(size); i >= 1; i--) siftDown(i);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    T peekMinimum() {
        return heap[1];
    }

    public int getLeftChildIndex(int index) {
        return index * 2;
    }

    public int getRightChildIndex(int index) {
        return index * 2 + 1;
    }

    public int getParentIndex(int index) {
        return index / 2;
    }

    private void doubleCapacity() {
        T[] temp = (T[]) new Comparable[heap.length * 2];

        if (heap.length - 1 >= 0) System.arraycopy(heap, 1, temp, 1, heap.length - 1);
        heap = temp;
    }

    public void insert(T num) {
        if(size >= heap.length - 1) doubleCapacity();

        size++;
        heap[size] = num;
        bubbleUp(size);
    }

    private void bubbleUp(int index) {
        if(heap[getParentIndex(index)] == null) return;

        if(heap[index].compareTo(heap[getParentIndex(index)]) < 0) {
            T temp = heap[getParentIndex(index)];
            heap[getParentIndex(index)] = heap[index];
            heap[index] = temp;

            bubbleUp(getParentIndex(index));
        }
    }

    public T popMinimum() {
        T temp1 = peekMinimum();
        T temp2 = heap[size];

        heap[size] = null;
        size--;
        heap[1] = temp2;

        siftDown(1);
        return temp1;
    }

    private void siftDown(int index) {
        if(heap[getLeftChildIndex(index)] == null && heap[getRightChildIndex(index)] == null) return;

        if(heap[getLeftChildIndex(index)] == null && heap[getRightChildIndex(index)] != null) {
            if(heap[index].compareTo(heap[getRightChildIndex(index)]) > 0) {
                T temp = heap[getRightChildIndex(index)];
                heap[getRightChildIndex(index)] = heap[index];
                heap[index] = temp;
            }

            return;
        }

        if(heap[getLeftChildIndex(index)] != null && heap[getRightChildIndex(index)] == null) {
            if(heap[index].compareTo(heap[getLeftChildIndex(index)]) > 0) {
                T temp = heap[getLeftChildIndex(index)];
                heap[getLeftChildIndex(index)] = heap[index];
                heap[index] = temp;
            }

            return;
        }

        int i = heap[getLeftChildIndex(index)].compareTo(heap[getRightChildIndex(index)]) < 0 ? getLeftChildIndex(index) : getRightChildIndex(index);
        T temp = heap[i];
        heap[i] = heap[index];
        heap[index] = temp;

        siftDown(i);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= getSize(); i++)
            output += heap[i] + ", ";
        return output.substring(0, output.lastIndexOf(","));
    }

    public void display() {
        int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
        String dots = "...............................";
        System.out.println(dots + dots);
        while (j <= this.getSize()) {
            if (column == 0)
                for (int k = 0; k < nBlanks; k++)
                    System.out.print(' ');
            System.out.print((heap[j] == null) ? "" : heap[j]);
            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(' ');
            j++;
        }
        System.out.println("\n" + dots + dots);
    }
}
