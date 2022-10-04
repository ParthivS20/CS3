package Lab08_LinkedListQueue;

import java.util.Iterator;

class MyLinkedList<T> implements Iterable<T> {
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
        for(T v : vals) {
            add(v);
        }
    }

    T get(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();

        ListNode node = head;
        for(int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.val;
    }

    void add(T newVal) {
        if(size == 0) {
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
            ListNode node = head;
            for(int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            ListNode temp = node.next;
            node.next = new ListNode(newVal);
            node.next.next = temp;
        }
        size++;
    }

    void set(T newVal, int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();

        ListNode node = head;
        for(int i = 0; i < index; i++) {
            node = node.next;
        }
        node.val = newVal;
    }

    T remove(int index) {
        if(isEmpty() || index < 0 || index >= size) throw new IndexOutOfBoundsException();

        size--;

        if(index == 0) {
            T val = head.val;
            head = head.next;
            return val;
        }

        ListNode node = head;
        for(int i = 0; i < index - 1; i++) {
            node = node.next;
        }

        T val = node.next.val;
        if(node.next.equals(tail)) {
            tail = node;
            node.next = null;
        }
        else {
            node.next = node.next.next;
        }

        return val;
    }

    boolean contains(T target) {
        return indexOf(target) >= 0;
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

    private int sizeRecursive(ListNode current) {
        return current == null ? 0 : 1 + sizeRecursive(current.next);
    }

    boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        ListNode node = head;
        String out = "";

        for(int i = 0; i < size; i++) {
            out += node + (i < size - 1 ? ", " : "");
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
        }

        @Override
        public String toString() {
            return "" + this.val;
        }
    }
}
