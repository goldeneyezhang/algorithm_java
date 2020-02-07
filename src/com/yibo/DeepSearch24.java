package com.yibo;

import java.util.ArrayList;
import java.util.List;

/**
 * 24点深度搜索
 */
public class DeepSearch24 {
    /**
     * 搜索
     *
     * @param numbers
     * @param target
     */
    public static void search(int[] numbers, int target) {
        List<int[]> arrays = new ArrayList<>();
        perum(numbers, 0, numbers.length - 1, arrays);
        for (int[] number : arrays) {
            TwentyFour twentyFour = new TwentyFour();
            twentyFour.setNumbers(number);
            List<String> symbols = new ArrayList<>();
            twentyFour.setSymbols(symbols);
            TwentyFour answer = move(twentyFour, target);
            if (null != answer) {
                System.out.println("done");
                printAnswer(answer);
                break;
            }
        }
    }

    private static void printAnswer(TwentyFour answer) {
        for (int i = 0; i < answer.getNumbers().length; i++) {
            System.out.print(answer.getNumbers()[i]);
            if (i < answer.getSymbols().size()) {
                System.out.print(answer.getSymbols().get(i));
            }
        }
        System.out.println();
    }

    /**
     * 移动
     *
     * @param twentyFour
     * @param target
     * @return
     */
    private static TwentyFour move(TwentyFour twentyFour, int target) {
        String[] symbol = new String[]{"+", "-", "*", "/"};
        for (String s : symbol) {
            TwentyFour newTF = new TwentyFour();
            newTF.setNumbers(twentyFour.getNumbers().clone());
            newTF.setSymbols(new ArrayList<>(twentyFour.getSymbols()));
            newTF.getSymbols().add(s);
            if (compute(newTF, target)) {
                return newTF;
            } else if (newTF.getDepth() > newTF.getNumbers().length - 1) {
                return null;
            } else {
                TwentyFour result = move(newTF, target);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 计算
     *
     * @param twentyFour
     * @param target
     * @return
     */
    private static boolean compute(TwentyFour twentyFour, int target) {
        if (twentyFour.getDepth() != twentyFour.getNumbers().length - 1) {
            return false;
        } else {
            int[] numbers = twentyFour.getNumbers();
            int answer = numbers[0];
            for (int i = 0; i < twentyFour.getSymbols().size(); i++) {
                String symbol = twentyFour.getSymbols().get(i);
                if (symbol == "+") {
                    answer += numbers[i + 1];
                } else if (symbol == "-") {
                    answer -= numbers[i + 1];
                } else if (symbol == "*") {
                    answer *= numbers[i + 1];
                } else if (symbol == "/") {
                    answer /= numbers[i + 1] * 1.0;
                }
            }
            //System.out.println(answer);
            if (answer == target) {
                return true;
            }
        }
        return false;
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
