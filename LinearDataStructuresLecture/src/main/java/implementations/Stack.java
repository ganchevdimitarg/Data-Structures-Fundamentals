package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {
    private Node<E> top;
    private int size;

    private static class Node<E>{
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.next = this.top;
        this.top = newNode;
        this.size++;
    }

    @Override
    public E pop() {
        ensureNorEmpty();

        Node<E> tmp = this.top;
        this.top = tmp.next;
        this.size--;

        return tmp.element;
    }

    @Override
    public E peek() {
        ensureNorEmpty();

        return this.top.element;
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
            private Node<E> tmp = top;
            @Override
            public boolean hasNext() {
                return this.tmp.next != null;
            }

            @Override
            public E next() {
                E value = this.tmp.element;

                this.tmp = this.tmp.next;

                return value;
            }
        };
    }

    private void ensureNorEmpty() {
        if (this.size == 0){
            throw new IllegalStateException("No such elements");
        }
    }
}
