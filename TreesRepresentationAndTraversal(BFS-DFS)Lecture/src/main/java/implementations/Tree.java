package implementations;

import interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {
    private E value;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E value, Tree<E>... subtrees) {
        this.value = value;
        this.parent = null;
        this.children = new ArrayList<>();

        for (Tree<E> subtree : subtrees) {
            this.children.add(subtree);
            subtree.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();
        Deque<Tree<E>> queue = new ArrayDeque<>();
        if (this.value == null) {return result;}

        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();
            result.add(current.value);
            for (Tree<E> child : current.children) {
                queue.offer(child);
            }
        }

        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();

//      recursion
        this.doDfs(this, result);

//        without recursion
//        Deque<Tree<E>> toTraverse = new ArrayDeque<>();
//        toTraverse.push(this);
//
//        while (!toTraverse.isEmpty()){
//            Tree<E> current = toTraverse.pop();
//
//            for (Tree<E> node : toTraverse) {
//                toTraverse.push(node);
//            }
//
//            result.add(current.value);
//        }

        return result;
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> search = find(parentKey);

        if (search == null) {
            throw new IllegalArgumentException();
        }

        search.children.add(child);
        child.parent = search;
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> toRemove = find(nodeKey);

        if (toRemove == null) {
            throw new IllegalArgumentException();
        }

        for (Tree<E> child : toRemove.children) {
            child.parent = null;
        }
        toRemove.children.clear();

        Tree<E> parent = toRemove.parent;

        if (parent != null) {
            parent.children.remove(toRemove);
        }

        toRemove.value = null;
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstNode = find(firstKey);
        Tree<E> secondNode = find(secondKey);

        if (firstNode == null || secondNode == null){
            throw new IllegalArgumentException();
        }

        Tree<E> parentFirst = firstNode.parent;
        Tree<E> parentSecond = secondNode.parent;

        if (parentFirst == null){
            swapRoot(secondNode);
            return;
        } else if (parentSecond == null){
            swapRoot(firstNode);
            return;
        }

        firstNode.parent = parentSecond;
        secondNode.parent = parentFirst;

        int firstIndex = parentFirst.children.indexOf(firstNode);
        int secondIndex = parentSecond.children.indexOf(secondNode);

        parentFirst.children.set(firstIndex, secondNode);
        parentSecond.children.set(secondIndex, firstNode);
    }

    private void doDfs(Tree<E> node, List<E> result) {
        for (Tree<E> child : node.children) {
            this.doDfs(child, result);
        }

        result.add(node.value);
    }

    private Tree<E> find(E parentKey) {
        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();

            if (current.value.equals(parentKey)) {
                return current;
            }

            for (Tree<E> child : current.children) {
                queue.offer(child);
            }
        }

        return null;
    }

    private void swapRoot(Tree<E> node){
        this.value = node.value;
        this.parent = null;
        this.children = node.children;
        node.parent = null;
    }
}



