package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private List<E> elements;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
    }

    @Override
    public E peek() {
        ensureNotEmpty();
        return getAt(0);
    }

    @Override
    public E poll() {
        ensureNotEmpty();
        E returnValue = getAt(0);
        Collections.swap(this.elements, 0, this.size() - 1);
        this.elements.remove(this.size() - 1);
        this.heapifyDown(0);

        return returnValue;
    }

    private void heapifyUp(int index) {
        while (index > 0 && isLess(parentIndex(index), index)) {
            Collections.swap(this.elements, parentIndex(index), index);
            index = parentIndex(index);
        }
    }

    private boolean isLess(int first, int second) {
        return getAt(first).compareTo(getAt(second)) < 0;
    }

    private E getAt(int index) {
        return this.elements.get(index);
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private void ensureNotEmpty() {
        if (this.size() == 0) {
            throw new IllegalStateException();
        }
    }

    private void heapifyDown(int index) {
        while (getLeftChildIndex(index) < this.size() && isLess(index, getLeftChildIndex(index))){
            int child = getLeftChildIndex(index);
            int rightChildIndex = getRightChildIndex(index);
            if (rightChildIndex < this.size() && isLess(child, rightChildIndex)){
                child = rightChildIndex;
            }

            Collections.swap(this.elements, child, index);
            index = child;
        }
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }


}
