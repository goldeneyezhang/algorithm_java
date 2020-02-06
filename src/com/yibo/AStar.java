package com.yibo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A*算法 启发式搜索
 */
public class AStar {
    public static void search(int[] start, int[] target) {
        List<EightDigit> opens = new ArrayList<>();
        List<EightDigit> closed = new ArrayList<>();
        EightDigit eightDigit = EightDigitUtil.iniEightDigit(start);
        eightDigit.setgValue(0);
        eightDigit.sethValue(getHValue(start, target));
        opens.add(eightDigit);
        handleOpen(opens, closed, target);
    }

    private static void handleOpen(List<EightDigit> opens, List<EightDigit> closed, int[] target) {
        while (opens.size() > 0) {
            opens = opens.stream().sorted(Comparator.comparing(EightDigit::getfValue)).collect(Collectors.toList());
            EightDigit first = opens.get(0);
            //EightDigitUtil.print(first.getNumbers());
            //System.out.println(first.getfValue());
            opens.remove(0);
            closed.add(first);
            if (EightDigitUtil.isTarget(first.getNumbers(), target)) {
                EightDigitUtil.printRoute(first);
                break;
            } else {
                widthMove(first, opens, closed, target);
            }
        }
    }

    private static void widthMove(EightDigit eightDigit, List<EightDigit> opens, List<EightDigit> closed, int[] target) {
        int[] moves = new int[]{3, -3, -1, 1};
        for (int m : moves) {
            if (m != eightDigit.getMove() * -1) {
                int index = eightDigit.getZeroIndex();
                //可以移动
                if (index + m >= 0 && index + m < target.length) {
                    EightDigit newEight = EightDigitUtil.moveZero(eightDigit, m);
                    newEight.setgValue(eightDigit.getgValue() + 1);
                    newEight.sethValue(getHValue(newEight.getNumbers(), target));

                    boolean inOpen = false;
                    int openIndex = -1;
                    boolean inClose = false;
                    for (EightDigit close : closed) {
                        //新找到的和close中的数值一样，耗散较小，则加入open，否则什么都不干
                        if (EightDigitUtil.isTarget(newEight.getNumbers(), close.getNumbers())) {
                            inClose = true;
                            if (newEight.getfValue() < close.getfValue()) {
                                opens.add(newEight);

                            }
                            break;
                        }
                    }

                    for (int i = 0; i < opens.size(); i++) {
                        //新找到的和open中的数值一样，耗散较小，则加入open，旧的不要
                        if (EightDigitUtil.isTarget(newEight.getNumbers(), opens.get(i).getNumbers())) {
                            inOpen = true;
                            if (newEight.getfValue() < opens.get(i).getfValue()) {
                                opens.add(eightDigit);
                                openIndex = i;
                            }
                            break;
                        }
                    }
                    if (openIndex > -1) {
                        opens.remove(openIndex);
                    }


                    //不在open，也不在close，则加入open
                    if (!inOpen && !inClose) {
                        opens.add(newEight);
                    }
                }
            }
        }

    }

    /**
     * 不在位的数目
     *
     * @param numbers
     * @param target
     * @return
     */
    private static int getHValue(int[] numbers, int[] target) {
        int h = 0;
        for (int i = 0; i < numbers.length; i++) {
            h += numbers[i] != target[i] && numbers[i] != 0 ? 1 : 0;
        }
        return h;
    }
}
