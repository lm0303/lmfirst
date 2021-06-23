package com.limeng.design.behaviorMode.command;

public class CommandTest {
    public static void main(String[] args) {
        GuangDoneCuisine guangDoneCuisine = new GuangDoneCuisine(new GuangDongCook());
        JiangSuCuisine jiangSuCuisine = new JiangSuCuisine(new JiangSuCook());
        ShanDongCuisine shanDongCuisine = new ShanDongCuisine(new ShanDongCook());
        SiChuanCuisine siChuanCuisine = new SiChuanCuisine(new SiChuanCook());
        Waiter waiter = new Waiter();
        waiter.order(guangDoneCuisine);
        waiter.order(jiangSuCuisine);
        waiter.order(shanDongCuisine);
        waiter.order(siChuanCuisine);
        waiter.placeOrder();
    }
}
