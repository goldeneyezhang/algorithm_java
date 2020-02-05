package com.yibo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 广度搜索
 */
public class WidthSearch {
    public static void search(int[] start, int[] target) {
        if (start != null && target != null && start.length == target.length) {
            List<EightDigit> eightDigitList = new ArrayList<>();
            EightDigit eightDigit = EightDigitUtil.iniEightDigit(start);
            eightDigitList.add(eightDigit);
            int depth = 0;
            while (!widthMove(eightDigitList, target, depth)) {
                depth++;
            }
        }
    }


    /**
     * 移动
     *
     * @param eightDigitList
     * @param target
     * @param depth
     * @return
     */
    private static Boolean widthMove(List<EightDigit> eightDigitList, int[] target, int depth) {
        int[] moves = new int[]{3, -3, -1, 1};
        for (EightDigit eightDigit : eightDigitList.stream().filter(x -> x.getDepth() == depth).collect(Collectors.toList())) {
            for (int m : moves) {
                if (m != eightDigit.getMove() * -1) {
                    int index = eightDigit.getZeroIndex();
                    //可以移动
                    if (index + m >= 0 && index + m < target.length) {
                        EightDigit newEight = EightDigitUtil.moveZero(eightDigit, m);
                        if (EightDigitUtil.isTarget(newEight.getNumbers(), target)) {
                            EightDigitUtil.printRoute(newEight);
                            return true;
                        } else {
                            eightDigitList.add(newEight);
                        }
                    }
                }
            }
        }
        return false;
    }
}