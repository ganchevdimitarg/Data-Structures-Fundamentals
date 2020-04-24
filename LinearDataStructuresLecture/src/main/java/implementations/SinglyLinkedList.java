package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> nextElement = new Node<>(element);

        nextElement.next = this.head;
        this.head = nextElement;

        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newElement = new Node<>(element);

        if (this.isEmpty()) {
            this.head = newElement;
        } else {
            Node<E> current = this.head;
            while (current.next != null) {
                current = current.next;
            }

            current.next = newElement;
        }

        this.size++;
    }

    @Override
    public E removeFirst() {
        ensureNotEmpty();
        E tmp = this.head.element;

        this.head = this.head.next;

        this.size--;
        return tmp;
    }

    @Override
    public E removeLast() {
        ensureNotEmpty();
        if (this.size == 1) {
            E value = this.head.element;
            this.head = null;

            return value;
        }

        Node<E> preLast = this.head;
        Node<E> toRemove = this.head.next;

        while (toRemove.next != null){
            preLast = toRemove;
            toRemove = toRemove.next;
        }
        preLast.next = null;

        this.size--;
        return toRemove.element;
    }

    @Override
    public E getFirst() {
        ensureNotEmpty();
        return this.head.element;
    }

    @Override
    public E getLast() {
        ensureNotEmpty();
        Node<E> current = this.head;

        while (current.next !=null){
            current = current.next;
        }

        return current.element;
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
                E value = current.element;

                current = current.next;

                return value;
            }
        };
    }

    private void ensureNotEmpty() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
    }
}
