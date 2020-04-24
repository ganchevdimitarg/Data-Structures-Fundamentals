package implementations;

import interfaces.AbstractTree;

import java.util.*;
import java.util.stream.Collectors;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key) {
        this.key = key;
        this.children = new ArrayList<>();
    }

    public Tree() {
        this.children = new ArrayList<>();
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder builder = new StringBuilder();

        traverseTreeWithRecurrence(builder, 0, this);

        return builder.toString().trim();
    }

    @Override
    public List<E> getLeafKeys() {

        return traverseAllLeafBFS()
                .stream()
                .filter(t -> t.children.isEmpty())
                .map(Tree::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<E> getMiddleKeys() {
        List<Tree<E>> allNodes = new ArrayList<>();
        this.traverseTreeWithRecurrence(allNodes, this);
        return allNodes
                .stream()
                .filter(t -> t.parent != null && t.children.size() > 0)
                .map(Tree::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
//        List<Tree<E>> trees = this.traverseAllLeafBFS();
//
//        int maxPath = 0;
//
//        Tree<E> deepestLeftNode = null;
//
//        for (Tree<E> tree : trees) {
//            if (tree.isLeaf()) {
//                int currentPath = getStepsFromLeafToRoot(tree);
//                if (currentPath > maxPath){
//                    maxPath = currentPath;
//                    deepestLeftNode = tree;
//                }
//            }
//        }
//        return null;

        List<Tree<E>> deepestLeftNode = new ArrayList<>();
        int[] maxPath = new int[1];
        int max = 0;

        deepestLeftNode.add(new Tree<>());

        findDeepestNodeDFS(deepestLeftNode, maxPath, max, this);

        return deepestLeftNode.get(0);
    }

    @Override
    public List<E> getLongestPath() {
        Deque<E> path = new ArrayDeque<>();
        List<E> longestPath = new ArrayList<>();
        this.findLongestPathWithDFS(this, longestPath, path);
        Collections.reverse(longestPath);

        return longestPath;
    }

    private void findLongestPathWithDFS(Tree<E> tree, List<E> longestPath, Deque<E> path) {
        path.push(tree.key);

        for (Tree<E> child : tree.children) {
            this.findLongestPathWithDFS(child, longestPath, path);
        }

        if (path.size() > longestPath.size()) {
            longestPath.clear();
            longestPath.addAll(path);
        }

        path.pop();
    }


    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        Deque<E> currentPath = new ArrayDeque<>();
        List<List<E>> paths = new ArrayList<>();
        List<E> path = new ArrayList<>();
        int currentSum = 0;
        this.findAllPaths(this, paths, currentPath, currentSum, sum, path);

        return paths;
    }

    private void findAllPaths(Tree<E> tree, List<List<E>> paths, Deque<E> currentPath, int currentSum, int givenSum, List<E> path) {
        currentPath.push(tree.key);
        currentSum += (int) tree.key;
        for (Tree<E> child : tree.children) {
            this.findAllPaths(child, paths, currentPath, currentSum, givenSum, path);
        }

        if (currentSum == givenSum) {
            path.addAll(currentPath);
            Collections.reverse(path);
            List<E> token = new ArrayList<>(path);
            path.clear();
            paths.add(token);
        }

        currentPath.pop();
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> subtreeTree = new ArrayList<>();
        int currentSum = 0;

        for (Tree<E> child : this.children) {
            currentSum += (int) child.key;
            if (child.children.size() != 0) {
                for (Tree<E> c : child.children) {
                    currentSum += (int) c.key;
                }
            }
            if (currentSum == sum) {
                subtreeTree.add(child);
            }

            currentSum = 0;
        }

        return subtreeTree;
    }

    private String getPadding(int size) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private void traverseTreeWithRecurrence(StringBuilder builder, int indent, Tree<E> tree) {
        builder
                .append(this.getPadding(indent))
                .append(tree.getKey())
                .append(System.lineSeparator());

        for (Tree<E> child : tree.children) {
            traverseTreeWithRecurrence(builder, indent + 2, child);
        }
    }

    public String traverseWithBFS() {
        StringBuilder builder = new StringBuilder();

        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        int ident = 0;

        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();

            builder
                    .append(getPadding(ident))
                    .append(tree.getKey())
                    .append(System.lineSeparator());

            for (Tree<E> child : tree.children) {
                queue.offer(child);
            }
        }

        return builder.toString().trim();
    }

    public List<Tree<E>> traverseAllLeafBFS() {
        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        List<Tree<E>> allNodes = new ArrayList<>();

        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();

            allNodes.add(tree);

            for (Tree<E> child : tree.children) {
                queue.offer(child);
            }
        }

        return allNodes;
    }

    public void traverseTreeWithRecurrence(List<Tree<E>> collection, Tree<E> tree) {
        collection.add(tree);
        for (Tree<E> child : tree.children) {
            traverseTreeWithRecurrence(collection, child);
        }
    }

    private boolean isLeaf() {
        return this.parent != null && this.children.size() == 0;
    }

    private int getStepsFromLeafToRoot(Tree<E> tree) {
        int counter = 0;
        Tree<E> current = tree;

        while (current.parent != null) {
            counter++;
            current = current.parent;
        }

        return counter;
    }

    private void findDeepestNodeDFS(List<Tree<E>> deepestLeftNode, int[] maxPath, int max, Tree<E> tree) {
        if (max > maxPath[0]) {
            maxPath[0] = max;
            deepestLeftNode.set(0, tree);
        }
        for (Tree<E> child : tree.children) {
            findDeepestNodeDFS(deepestLeftNode, maxPath, max + 1, child);
        }
    }
}



