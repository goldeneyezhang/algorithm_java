package com.yibo;

public class Main2 {
    public static void main(String[] args) {
        double target1 = 0.01;
        double target2 = 0.09;
        double outo1 = 0.7513650695523157;
        double outo2 = 0.7729284653214625;
        double w5 = 0.4;
        double w6 = 0.45;
        double w7 = 0.5;
        double w8 = 0.55;
        double outH1 = 0.5932699921071872;
        double outH2 = 0.596884378259767;
        System.out.println(w5 - 0.5 * error(target1, outo1, outH1));
        System.out.println(w6 - 0.5 * error(target1, outo1, outH2));
        System.out.println(w7 - 0.5 * error(target2, outo2, outH1));
        System.out.println(w8 - 0.5 * error(target2, outo2, outH2));
    }

    private static double error(double target, double output, double outH) {
        return -(target - output) * output * (1.0 - output) * outH;
    }
}
