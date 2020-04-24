package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    public Queue() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> toInsert = new Node<>(element);
        if (this.isEmpty()){
            this.head = toInsert;
        } else {
            Node<E> current = this.head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = toInsert;
        }
        this.size++;
    }

    @Override
    public E poll() {
        ensureNotEmpty();
        Node<E> current = this.head;
        this.head = current.next;

        this.size--;

        return current.element;
    }

    @Override
    public E peek() {
        ensureNotEmpty();

        return this.head.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E tmp = current.element;

                current = current.next;

                return tmp;
            }
        };
    }

    private void ensureNotEmpty() {
        if (isEmpty()){
            throw new IllegalStateException("Not such elements");
        }
    }
}
