package edu.project_2.solvers;

public class Node<T> implements Comparable<Node<T>> {
    final T state;
    Node<T> parent;
    int cost;
    int heuristic;

    Node(T state, Node<T> parent, int cost, int heuristic) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    @Override
    public int compareTo(Node<T> other) {
        Integer mine = cost + heuristic;
        Integer theirs = other.cost + other.heuristic;
        return mine.compareTo(theirs);
    }
}
