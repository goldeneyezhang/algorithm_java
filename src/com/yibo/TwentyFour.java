package com.yibo;

import java.util.List;

public class TwentyFour {
    private int[] numbers;
    private List<String> symbols;

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }

    public int getDepth() {
        return symbols.size();
    }
}
