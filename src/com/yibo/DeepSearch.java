package com.yibo;

public class DeepSearch {
    public static void search(int[] start, int[] target, final int depth) {
        if (start != null && target != null && start.length == target.length) {
            int actualDepth = 0;
            EightDigit eightDigit = new EightDigit();
            eightDigit.setNumbers(start);
            for (int i = 0; i < start.length; i++) {
                if (start[i] == 0) {
                    eightDigit.setZeroIndex(i);
                    break;
                }
            }
            deepMove(eightDigit, target, depth, actualDepth, 0);
        }
    }

    /**
     * 移动递归
     *
     * @param eightDigit
     * @param target
     * @param depth
     * @param actualDepth
     * @return
     */
    private static Boolean deepMove(EightDigit eightDigit, int[] target, final int depth, int actualDepth, int lastMove) {
        int[] moves = new int[]{3, -3, -1, 1};
        for (int m : moves) {
            if (m != lastMove * -1) {
                int index = eightDigit.getZeroIndex();
                //可以移动
                if (index + m >= 0 && index + m < target.length && m != lastMove * -1) {
                    EightDigit newEight = moveZero(eightDigit, m);
                    actualDepth++;
                    System.out.println("actualDepth = " + actualDepth + ",m=" + m);
                    print(newEight.getNumbers());
                    if (isTarget(newEight.getNumbers(), target)) {
                        print(newEight.getNumbers());
                        return true;
                    } else if (actualDepth >= depth) {
                        return false;
                    }
                    deepMove(newEight, target, depth, actualDepth, m);
                }

            }
        }
        return false;
    }

    private static void print(int[] numbers) {
        for (int i : numbers) {
            System.out.print(i + ",");
        }
        System.out.println();
    }

    private static EightDigit moveZero(EightDigit parent, int move) {
        int[] newNumbers = parent.getNumbers().clone();
        EightDigit newEightDigit = new EightDigit();
        newEightDigit.setParent(parent);
        int index = parent.getZeroIndex();
        if (newNumbers[index] == 0 && index + move >= 0 && index + move < newNumbers.length) {
            int number = newNumbers[index + move];
            newNumbers[index + move] = newNumbers[index];
            newNumbers[index] = number;
            newEightDigit.setNumbers(newNumbers);
            newEightDigit.setZeroIndex(index + move);
        } else {
            newEightDigit.setNumbers(newNumbers);
        }
        return newEightDigit;
    }

    private static Boolean isTarget(int[] numberes, int[] target) {
        for (int i = 0; i < numberes.length; i++) {
            if (numberes[i] != target[i]) {
                return false;
            }
        }
        return true;
    }
}
