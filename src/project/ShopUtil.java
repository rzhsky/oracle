package com.oracle.corejava.project;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author rzh
 * time 2018-09-28 0:44
 */
public class ShopUtil {

    public static int getGoodsNum(String[][] arr) {
        int i;
        for (i = 0; i < arr.length; i++) {
            String temp = null;
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != null) {
                    temp = arr[i][j];
                    break;
                }
            }
            if (temp == null) {
                break;
            }
        }
        return i;
    }

    public static String nextLine() {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            try {
                String str = scanner.nextLine();
                if (str.trim().equals("")){
                    System.err.println("你的输入不能为空，请重新输入！");
                    continue;
                }
                return str;
            } catch (InputMismatchException e) {
                System.err.println("您输入的内容不正确，请重新输入");
            }
        }
    }

    public static int nextInt() {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("您输入的内容不正确，请重新输入");
            }
        }
    }

    public static int randomInt(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public static String randomDate() {
        Random random = new Random();
        int year = 2018;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(30) + 1;

        return year + "-" + month + "-" + day;
    }

    public static String randomGoodsName() {
        String[] names = new String[]{"方便面", "火腿肠", "饮料", "蔬菜", "水果", "鲜奶制品"};
        Random random = new Random();
        String name = names[random.nextInt(names.length)] + random.nextInt(99) + 1;

        return name;
    }
}
