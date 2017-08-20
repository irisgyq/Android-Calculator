package com.example.irisgyq.calculator;

public class Node {
    private double value;
    private String type;
    private Node left;
    private Node right;

    public Node(double value, String type) {
        this.value = value;
        this.type = type;
        this.left = null;
        this.right = null;
    }

    public double getValue() {
        return value;
    }

    public String getType() { return type;}

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}