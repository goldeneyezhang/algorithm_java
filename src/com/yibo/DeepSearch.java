package com.yibo;

public class DeepSearch {
    private static boolean find = false;
    public static void search(int[] start, int[] target, final int depth) {
        if (start != null && target != null && start.length == target.length) {
            find = false;
            EightDigit eightDigit = new EightDigit();
            eightDigit.setNumbers(start);
            for (int i = 0; i < start.length; i++) {
                if (start[i] == 0) {
                    eightDigit.setZeroIndex(i);
                    break;
                }
            }
            deepMove(eightDigit, target, depth, 0);
        }
    }

    /**
     * 移动递归
     *
     * @param eightDigit
     * @param target
     * @param depth
     * @return
     */
    private static Boolean deepMove(EightDigit eightDigit, int[] target, final int depth, int lastMove) {
        if (!find) {
            int[] moves = new int[]{3, -3, -1, 1};
            for (int m : moves) {
                if (m != lastMove * -1 && !find) {
                    int index = eightDigit.getZeroIndex();
                    //可以移动
                    if (index + m >= 0 && index + m < target.length) {
                        EightDigit newEight = moveZero(eightDigit, m);
                        System.out.println("actualDepth = " + newEight.getDepth() + ",m=" + m);
                        //print(newEight.getNumbers());
                        if (isTarget(newEight.getNumbers(), target)) {
                            print(newEight.getNumbers());
                            EightDigit eightPrint = newEight;
                            while (eightPrint.getParent() != null) {
                                print(eightPrint.getParent().getNumbers());
                                eightPrint = eightPrint.getParent();
                            }
                            find = true;
                            return true;
                        } else if (newEight.getDepth() >= depth) {
                            return false;
                        }
                        deepMove(newEight, target, depth, m);
                    }

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
        EightDigit newParent = new EightDigit();
        newParent.setZeroIndex(parent.getZeroIndex());
        newParent.setNumbers(parent.getNumbers());
        newParent.setParent(parent.getParent());
        newParent.setDepth(parent.getDepth());
        newEightDigit.setParent(newParent);
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

    private static Boolean isTarget(int[] numberes, int[] target) {
        for (int i = 0; i < numberes.length; i++) {
            if (numberes[i] != target[i]) {
                return false;
            }
        }
        return true;
    }
}
