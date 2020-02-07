package com.yibo;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //八数码问题
        int[] start = new int[]{2, 0, 3, 1, 8, 4, 7, 6, 5};
        int[] target = new int[]{1, 2, 3, 8, 0, 4, 7, 6, 5};
        int[] start2 = new int[]{2, 8, 3, 1, 6, 4, 7, 0, 5};
        int[] start3 = new int[]{2, 1, 6, 4, 0, 8, 7, 5, 3};
        System.out.println("deep");
        DeepSearch.search(start, target, 4);
        System.out.println("width");
        WidthSearch.search(start3, target);
        System.out.println("astar");
        AStar.search(start, target);
        System.out.println("astar");
        AStar.search(start3, target);
        System.out.println("24");
        int[] numbers1 = new int[]{12, 12, 12, 12};
        int[] numbers2 = new int[]{3, 9, 1, 2};
        DeepSearch24.search(numbers1, 24);
        DeepSearch24.search(numbers2, 24);
    }
}
