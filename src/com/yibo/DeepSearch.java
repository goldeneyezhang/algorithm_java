package com.yibo;

/**
 * 深度搜索
 */
public class DeepSearch {

    public static void search(int[] start, int[] target, final int depth) {
        if (start != null && target != null && start.length == target.length) {
            EightDigit eightDigit = EightDigitUtil.iniEightDigit(start);
            deepMove(eightDigit, target, depth);
        }
    }

    private static EightDigit iniEightDigit(int[] start) {
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

    /**
     * 移动递归
     *
     * @param eightDigit
     * @param target
     * @param depth
     * @return
     */
    private static Boolean deepMove(EightDigit eightDigit, int[] target, final int depth) {
            int[] moves = new int[]{3, -3, -1, 1};
            for (int m : moves) {
                if (m != eightDigit.getMove() * -1) {
                    int index = eightDigit.getZeroIndex();
                    //可以移动
                    if (EightDigitUtil.canMove(index, m, target.length)) {
                        EightDigit newEight = EightDigitUtil.moveZero(eightDigit, m);
                        //System.out.println("actualDepth = " + newEight.getDepth() + ",m=" + m);
                        //print(newEight.getNumbers());
                        if (EightDigitUtil.isTarget(newEight.getNumbers(), target)) {
                            EightDigitUtil.printRoute(newEight);
                            return true;
                        } else if (newEight.getDepth() >= depth) {
                            return false;
                        }
                        if (deepMove(newEight, target, depth)) {
                            return true;
                        }
                    }
            }
        }
        return false;
    }


}
