package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_SIZE = 4;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[INITIAL_SIZE];
        this.size = 0;
    }

    @Override
    public boolean add(E element) {
        if (this.size == this.elements.length) {
            resize();
        }

        this.elements[this.size++] = element;

        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (!isValidIndex(index)) {
            return false;
        }

        shiftRight(index);

        this.elements[index] = element;
        this.size++;

        return true;
    }

    @Override
    public E get(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Not such index");
        }
        return (E) this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Not such index");
        }

        E el = this.get(index);
        this.elements[index] = element;

        return el;
    }

    @Override
    public E remove(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Not such index");
        }

        E existing = this.get(index);

        shiftLeft(index);
        this.size--;

        shrink();

        return existing;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        return this.indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private void resize() {

        Object[] tmp = new Object[this.elements.length * 2];

        for (int i = 0; i < this.elements.length; i++) {
            tmp[i] = this.elements[i];
        }

        this.elements = tmp;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < this.size;
    }

    private void shiftRight(int index) {
        for (int i = this.size - 1; i >= index; i--) {
            this.elements[i + 1] = this.elements[i];
        }
    }

    private void shiftLeft(int index) {
        for (int i = index; i < this.size; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }

    private void shrink() {
        if (this.size > this.elements.length / 3) {
            return;
        }
        this.elements = Arrays.copyOf(this.elements, this.elements.length / 2);
    }
}
