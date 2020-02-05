package com.yibo;

import java.util.ArrayList;
import java.util.List;

/**
 * 广度搜索
 */
public class WidthSearch {
    public static void search(int[] start, int[] target) {
        if (start != null && target != null && start.length == target.length) {
            List<EightDigit> eightDigitList = new ArrayList<>();
            EightDigit eightDigit = EightDigitUtil.iniEightDigit(start);
        }
    }


    /**
     * 移动
     *
     * @param eightDigit
     * @param target
     * @return
     */
    private static Boolean widthMove(EightDigit eightDigit, int[] target, List<EightDigit> eightDigitList) {
        int[] moves = new int[]{3, -3, -1, 1};
        for (int m : moves) {
            if (m != eightDigit.getMove() * -1) {
                int index = eightDigit.getZeroIndex();
                //可以移动
                if (index + m >= 0 && index + m < target.length) {
                    EightDigit newEight = EightDigitUtil.moveZero(eightDigit, m);
                    if (EightDigitUtil.isTarget(newEight.getNumbers(), target)) {
                        return true;
                    } else {
                        eightDigitList.add(newEight);
                        return false;
                    }
                }
            }
        }
        return false;
    }
}