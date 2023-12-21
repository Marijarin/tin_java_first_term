package edu.hw_9.task1;

public class Answer {
    final double answer;
    final CTask operation;

    public Answer(double answer, CTask operation) {
        this.answer = answer;
        this.operation = operation;
    }

    @Override public String toString() {
        return operation.name() + " result is " + answer;
    }

    public double getAnswer() {
        return this.answer;
    }
}
