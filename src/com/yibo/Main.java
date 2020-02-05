package com.yibo;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //八数码问题
        int[] start = new int[]{2, 0, 3, 1, 8, 4, 7, 6, 5};
        int[] target = new int[]{1, 2, 3, 8, 0, 4, 7, 6, 5};
        System.out.println("deep");
        DeepSearch.search(start, target, 4);
        System.out.println("width");
        WidthSearch.search(start, target);
    }
}
