package ru.appline.logic;

public class Direction {
    private String left;
    private String right;

    public Direction() {
        super();
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public Direction(String left, String right) {
        this.left=left;
        this.right=right;
    }
}
