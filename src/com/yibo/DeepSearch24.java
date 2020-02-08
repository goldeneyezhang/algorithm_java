package com.yibo;

import java.util.ArrayList;
import java.util.List;

/**
 * 24点深度搜索
 */
public class DeepSearch24 {
    private static final int depth = 3;

    /**
     * 搜索
     *
     * @param numbers
     * @param target
     */
    public static void search(int[] numbers, int target) {
        move(numbers, new ArrayList<String>(), target);

    }


    /**
     * 全排列
     *
     * @param numbers
     * @param symbolList
     * @return
     */
    private static List<TwentyFour> getPerums(int[] numbers, List<String> symbolList) {
        List<int[]> arrays = new ArrayList<>();
        perum(numbers, 0, numbers.length - 1, arrays);
        List<TwentyFour> twList = new ArrayList<>();
        for (int[] number : arrays) {
            TwentyFour twentyFour = new TwentyFour();
            twentyFour.setNumbers(number);
            twentyFour.setSymbols(new ArrayList<>(symbolList));
            twList.add(twentyFour);
        }
        return twList;
    }

    /**
     * 移动
     *
     * @param numbers
     * @param symbolList
     * @param target
     * @return
     */
    private static Boolean move(int[] numbers, List<String> symbolList, int target) {
        String[] symbol = new String[]{"+", "-", "*", "/"};
        if (numbers.length >= 2) {
            List<TwentyFour> twentyFours = getPerums(numbers, symbolList);
            for (TwentyFour twentyFour : twentyFours) {
                for (String s : symbol) {
                    TwentyFour newTF = new TwentyFour();
                    newTF.setSymbols(new ArrayList<>(twentyFour.getSymbols()));
                    int[] newNumbers = new int[twentyFour.getNumbers().length - 1];
                    newNumbers[0] = compute(twentyFour.getNumbers()[0], twentyFour.getNumbers()[1], s);
                    newTF.getSymbols().add(String.format("%d%s%d=%d", twentyFour.getNumbers()[0], s, twentyFour.getNumbers()[1], newNumbers[0]));
                    if (newNumbers[0] == target && twentyFour.getNumbers().length == 2) {
                        for (String sy : newTF.getSymbols()) {
                            System.out.println(sy);
                        }
                        System.out.println("------------------");
                        return true;
                    }
                    for (int i = 1; i < newNumbers.length; i++) {
                        newNumbers[i] = twentyFour.getNumbers()[i + 1];
                    }
                    newTF.setNumbers(newNumbers);
                    if (newTF.getDepth() > depth) {
                        return false;
                    } else {
                        if (move(newTF.getNumbers(), newTF.getSymbols(), target)) {
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    /**
     * 计算
     *
     * @param number1
     * @param number2
     * @param symbol
     * @return
     */
    private static int compute(int number1, int number2, String symbol) {
        int answer = number1;
        if (symbol == "+") {
            answer += number2;
        } else if (symbol == "-") {
            answer -= number2;
        } else if (symbol == "*") {
            answer *= number2;
        } else if (symbol == "/") {
            if (answer % number2 == 0) {
                answer /= number2;
            } else {
                answer = 10000;
            }
        }
        return answer;
    }

    private static void swap(int[] arr, int i, int j) {
        // 交换函数
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 对数组arr进行全排列
    public static void perum(int[] arr, int p, int q, List<int[]> arrays) {
        // for循环将数组中所有的元素和第一个元素进行交换。然后进行全排列。
        // 递归结束条件
        if (p == q) {
            //  一次递归结束。将整个数组打印出来
            //printArray(arr, q + 1);
            arrays.add(arr.clone());
        }
        for (int i = p; i <= q; i++) {
            swap(arr, i, p);
            // 把剩下的元素都做全排列。
            perum(arr, p + 1, q, arrays);
            // 然后再交换回去，数组还原，保证下一次不会有重复交换。
            swap(arr, i, p);
        }
    }

    private static void printArray(int[] arr, int n) {
        // 打印数组
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}
