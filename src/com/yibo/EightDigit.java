package com.yibo;

public class EightDigit {
    private int[] numbers;
    private EightDigit parent;
    private int zeroIndex;
    private int depth = 0;
    private int move = 0;

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public EightDigit getParent() {
        return parent;
    }

    public void setParent(EightDigit parent) {
        this.parent = parent;
    }

    public int getZeroIndex() {
        return zeroIndex;
    }

    public void setZeroIndex(int zeroIndex) {
        this.zeroIndex = zeroIndex;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }
}
