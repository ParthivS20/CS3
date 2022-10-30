package Lab08_LinkedListQueue;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {
    private ListNode head, tail;
    private int size;

    MyLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    MyLinkedList(T val) {
        this();
        add(val);
    }

    @SafeVarargs
    MyLinkedList(T... vals) {
        this();
        for(T v : vals) {
            add(v);
        }
    }

    T get(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();

        return getNode(index).val;
    }

    void add(T newVal) {
        if(isEmpty()) {
            head = new ListNode(newVal);
            tail = head;
        }
        else {
            tail.next = new ListNode(newVal);
            tail = tail.next;
        }
        size++;
    }

    void add(T newVal, int index) {
        if(index < 0 || index > size) throw new IndexOutOfBoundsException();

        if(index == size) {
            add(newVal);
            return;
        }

        if(index == 0) {
            ListNode temp = head;
            head = new ListNode(newVal);
            head.next = temp;
        }
        else {
            ListNode node = getNode(index - 1);
            ListNode temp = node.next;
            node.next = new ListNode(newVal);
            node.next.next = temp;
        }
        size++;
    }

    void set(T newVal, int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();

        getNode(index).val = newVal;
    }

    T remove(int index) {
        if(isEmpty() || index < 0 || index >= size) throw new IndexOutOfBoundsException();

        size--;

        if(index == 0) {
            T val = head.val;
            head = head.next;
            return val;
        }

        ListNode node = getNode(index - 1);
        T val = node.next.val;
        if(node.next == tail) {
            tail = node;
        }
        node.next = node.next.next;

        return val;
    }

    boolean contains(T target) {
        return indexOf(target) != -1;
    }

    int indexOf(T target) {
        ListNode node = head;
        for(int i = 0; i < size; i++) {
            if(node.val.equals(target)) return i;
            node = node.next;
        }
        return -1;
    }

    int size() {
        return size;
    }

    int sizeRecursive() {
        return sizeRecursive(head);
    }
    private int sizeRecursive(ListNode current) {
        return current == null ? 0 : sizeRecursive(current.next) + 1;
    }

    boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        ListNode node = head;
        String out = "";

        while(node != null) {
            out += node + (node.next == null ? "" : ", ");
            node = node.next;
        }

        return "[" + out + "]";
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            ListNode node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                T val = node.val;
                node = node.next;
                return val;
            }

            @Override
            public void remove(){
                throw new UnsupportedOperationException("Remove not implemented.");
            }
        };
    }

    private class ListNode {
        T val;
        ListNode next;

        ListNode(T val) {
            this.val = val;
            this.next = null;
        }

        @Override
        public String toString() {
            return "" + this.val;
        }
    }

    private ListNode getNode(int index) {
        ListNode node = head;
        for(int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
}
