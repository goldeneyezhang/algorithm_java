package com.yibo;

import java.util.Stack;

public class EightDigitUtil {
    public static EightDigit iniEightDigit(int[] start) {
        EightDigit eightDigit = new EightDigit();
        eightDigit.setNumbers(start);
        eightDigit.setMove(0);
        for (int i = 0; i < start.length; i++) {
            if (start[i] == 0) {
                eightDigit.setZeroIndex(i);
                break;
            }
        }
        return eightDigit;
    }

    public static Boolean isTarget(int[] numberes, int[] target) {
        for (int i = 0; i < numberes.length; i++) {
            if (numberes[i] != target[i]) {
                return false;
            }
        }
        return true;
    }

    public static void print(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]);
            if (i % 3 == 2) {
                System.out.println();
            } else {
                System.out.print(",");
            }
        }
        System.out.println();
    }

    /**
     * 移动0
     *
     * @param parent
     * @param move
     * @return
     */
    public static EightDigit moveZero(EightDigit parent, int move) {
        int[] newNumbers = parent.getNumbers().clone();
        EightDigit newEightDigit = new EightDigit();
        EightDigit newParent = cloneEightDigit(parent);
        newEightDigit.setParent(newParent);
        newEightDigit.setMove(move);
        int index = parent.getZeroIndex();
        if (newNumbers[index] == 0 && index + move >= 0 && index + move < newNumbers.length) {
            int number = newNumbers[index + move];
            newNumbers[index + move] = newNumbers[index];
            newNumbers[index] = number;
            newEightDigit.setNumbers(newNumbers);
            newEightDigit.setZeroIndex(index + move);
            newEightDigit.setDepth(parent.getDepth() + 1);
        } else {
            newEightDigit.setNumbers(newNumbers);
            newEightDigit.setDepth(parent.getDepth());
        }
        return newEightDigit;
    }

    private static EightDigit cloneEightDigit(EightDigit parent) {
        EightDigit newParent = new EightDigit();
        newParent.setZeroIndex(parent.getZeroIndex());
        newParent.setNumbers(parent.getNumbers());
        newParent.setParent(parent.getParent());
        newParent.setDepth(parent.getDepth());
        newParent.setMove(parent.getMove());
        return newParent;
    }

    public static void printRoute(EightDigit newEight) {
        Stack<int[]> stack = new Stack<>();
        stack.push(newEight.getNumbers());
        EightDigit eightPrint = newEight;
        while (eightPrint.getParent() != null) {
            stack.push(eightPrint.getParent().getNumbers());
            eightPrint = eightPrint.getParent();
        }
        while (!stack.empty()) {
            print(stack.pop());
        }
    }

    /**
     * 是否能移动
     *
     * @param index
     * @param m
     * @param size
     * @return
     */
    public static boolean canMove(int index, int m, int size) {
        return !((index % 3 == 0 && m == -1) || (index % 3 == 2 && m == 1) || (index < 3 && m == -3) || (index > 5 && m == 3)) && (index + m >= 0 && index + m < size);
    }
}
