package com.yibo;

public class EightDigit {
    private int[] numbers;
    private EightDigit parent;
    private int zeroIndex;


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

}
