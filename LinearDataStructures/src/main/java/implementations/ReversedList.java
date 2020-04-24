package implementations;

import java.util.Iterator;

public class ReversedList<E>{
    private Object[] elements;
    private int head;
    private int tail;
    private int size;

    public ReversedList() {
        this.elements = new Object[2];
        this.head = this.tail = this.size = 0;
    }


    public void add(Object element) {
        if (this.size == this.elements.length) {
            this.elements = grow();
        }
        if (this.size == 0) {
            this.elements[this.head] = element;
            this.tail = this.head;
        } else {
            this.elements[++this.head] = element;
        }
        this.size++;
    }


    public int size() {
        return this.size;
    }


    public int capacity() {
        return this.elements.length;
    }


    public E get(int index) {
        ensureIndex(index);
        int indexElement = this.size - index - 1;
        return this.getIndex(indexElement);
    }


    public E removeAt(int index) {
        ensureIndex(index);
        int indexElement = this.size - index - 1;
        E element = this.getIndex(indexElement);
        Object[] newElements = new Object[this.size - 1];
        removeElement(indexElement, newElements);
        this.elements = newElements;
        this.size--;
        return element;
    }


    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return head >= tail;
            }

            @Override
            public Object next() {
                return getIndex(head--);
            }
        };
    }

    private Object[] grow() {
        int newCapacity = this.elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        for (int i = 0; i < this.elements.length; i++) {
            newElements[i] = this.elements[i];
        }
        return newElements;
    }

    @SuppressWarnings("unchecked")
    private E getIndex(int index) {
        return (E) this.elements[index];
    }

    private void ensureIndex(int index) {
        if (index < this.tail || index > this.head) {
            throw new IndexOutOfBoundsException("No such index");
        }
    }

    private void removeElement(int index, Object[] newElements) {
        for (int i = 0; i <= newElements.length - 1 ; i++) {
            if (i == index){
                continue;
            } else {
                newElements[i] = this.elements[i];
            }
        }
    }
}
