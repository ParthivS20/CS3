package Lab08_LinkedListQueue;

public class MyQueue<T> implements QueueADT<T> {
    private MyLinkedList<T> queue;

    MyQueue() {
        queue = new MyLinkedList<>();
    }

    @SafeVarargs
    MyQueue(T... vals) {
        queue = new MyLinkedList<>(vals);
    }

    @Override
    public void offer(T item) {
        queue.add(item);
    }

    @Override
    public T poll() {
        return queue.remove(0);
    }

    @Override
    public T peek() {
        return queue.get(0);
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void clear() {
        queue = new MyLinkedList<>();
    }
}
