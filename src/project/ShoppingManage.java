package com.oracle.corejava.project;

/**
 * @author rzh
 * time 2018-09-27 22:16
 */
public class ShoppingManage {
    private final static String USRRNAME = "admin";
    private final static String PASSWORD = "123456";
    //商品参数：商品编号、商品名称、商品单价、进货日期、库存数量、销售数量
    private static String[][] shoppingGoods = new String[200][5];


    public static void main(String[] args) {
        login();
    }

    private static void login() {
        System.out.println("欢迎来到xx超市管理系统！");
        System.out.println("管理员登录");

        for (int i = 0; i < 3; i++) {
            pause(100); //暂停100毫秒
            System.out.print("账号：");
            String user = ShopUtil.nextLine();
            System.out.print("密码：");
            String pwd = ShopUtil.nextLine();

            if (USRRNAME.equals(user) && PASSWORD.equals(pwd)) {
                break;
            }

            if (i < 2) {
                System.err.println("账号或密码错误，请重新输入");
            } else {
                System.err.println("账号或密码错误次数达到3次，退出程序");
                System.exit(0);
            }
        }

        mainBoard();
    }

    private static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void mainBoard() {
        System.out.println("-----------------欢迎来到xx超市管理系统！-----------------");
        System.out.println("                        1.商品管理                        ");
        System.out.println("                        2.用户管理                        ");
        System.out.println("                        0.退出系统                        ");
        System.out.println("----------------------------------------------------------");

        int choose = ShopUtil.nextInt();
        while (true) {
            switch (choose) {
                case 1:
                    goodsManageMenu();
                    break;
                case 2:
                    userManage();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.err.println("你的输入有误，请重新输入");
                    choose = ShopUtil.nextInt();
            }
        }
    }

    private static void goodsManageMenu() {
        System.out.println("-----------------欢迎来到xx超市管理系统！-----------------");
        System.out.println("                        1.添加商品                        ");
        System.out.println("                        2.删除商品                        ");
        System.out.println("                        3.修改商品                        ");
        System.out.println("                        4.商品列表                        ");
        System.out.println("                        5.查询商品                        ");
        System.out.println("                        6.返回主页面                      ");
        System.out.println("                        0.退出系统                        ");
        System.out.println("----------------------------------------------------------");

        int choose = ShopUtil.nextInt();

        while (true) {
            switch (choose) {
                case 1:
                    addGoodsMenu();
                    break;
                case 2:
                    deleteGoodsMenu();
                    break;
                case 3:
                    updateGoodsMenu();
                    break;
                case 4:
                    showGoodsMenu();
                    break;
                case 5:
                    selectGoodsMenu();
                    break;
                case 6:
                    mainBoard();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.err.println("你的输入有误，请重新输入");
                    choose = ShopUtil.nextInt();
                    break;
            }
        }


    }

    private static void addGoodsMenu() {
        System.out.println("-----------------欢迎来到xx超市管理系统！-----------------");
        System.out.println("                      1.单个添加商品                      ");
        System.out.println("                      2.批量添加商品                      ");
        System.out.println("                      3.随机添加商品                      ");
        System.out.println("                      4.返回上一级                        ");
        System.out.println("----------------------------------------------------------");

        int choose = ShopUtil.nextInt();
        while (true) {
            switch (choose) {
                case 1:
                    addGoodsByOne();
                    break;
                case 2:
                    addGoodsByMore();
                    break;
                case 3:
                    addGoodsByRandom();
                    break;
                case 4:
                    goodsManageMenu();
                    break;
                default:
                    System.err.println("你的输入有误，请重新输入");
                    choose = ShopUtil.nextInt();
                    break;
            }
        }

    }

    private static void addGoodsByOne() {
        int length = ShopUtil.getGoodsNum(shoppingGoods);
        int goodsNum = length;
        System.out.print("请输入商品名称：");
        String name = ShopUtil.nextLine();
        System.out.print("请输入商品单价：");
        int price = ShopUtil.nextInt();
        System.out.print("请输入进货日期：");
        String date = ShopUtil.nextLine();
        System.out.print("请输入库存数量：");
        int invent = ShopUtil.nextInt();

        String[] temp = addGood(++goodsNum, name, price, date, invent);
        System.out.print("是否保存操作(T / F)：");
        String store = ShopUtil.nextLine();
        if ("T".equals(store) || "t".equals(store)) {
            ShoppingManage.shoppingGoods[length++] = temp;
            System.out.println("商品添加成功");
        } else if ("F".equals(store) || "f".equals(store)) {
            System.out.println("商品取消添加");
        } else {
            System.err.println("商品添加失败");
        }

        System.out.print("是否继续添加，若不添加，则返回商品界面(T / F)：");
        String choose = ShopUtil.nextLine();
        while (true) {
            if ("T".equals(choose) || "t".equals(choose)) {
                addGoodsByOne();
            } else if ("F".equals(choose) || "f".equals(choose)) {
                goodsManageMenu();
            } else {
                System.err.println("你的输入有误，请重新输入");
                choose = ShopUtil.nextLine();
            }
        }
    }

    private static void addGoodsByMore() {
        int length = ShopUtil.getGoodsNum(shoppingGoods);
        int goodNum = length;
        System.out.print("请输入添加商品的个数：");
        int num = ShopUtil.nextInt();

        String[][] temp = new String[num][6];
        int count = 0;
        for (int i = 0; i < num; i++) {
            System.out.print("请输入商品名称：");
            String name = ShopUtil.nextLine();
            System.out.print("请输入商品单价：");
            int price = ShopUtil.nextInt();
            System.out.print("请输入进货日期：");
            String date = ShopUtil.nextLine();
            System.out.print("请输入库存数量：");
            int invent = ShopUtil.nextInt();

            count++;
            temp[i] = addGood(++goodNum, name, price, date, invent);

            //输入最后一个商品时不显示
            if (i < num - 1) {
                System.out.print("是否停止添加，确定停止（T）,其他键继续：");
                String pause = ShopUtil.nextLine();
                if ("T".equals(pause) || "t".equals(pause)) {
                    break;
                }
            }
        }

        System.out.print("是否保存之前的添加操作（T/F）：");
        String store = ShopUtil.nextLine();
        if ("T".equals(store) || "t".equals(store)) {
            for (int j = 0; j < count; j++) {
                ShoppingManage.shoppingGoods[length++] = temp[j];
            }
            System.out.println("商品添加成功");
        } else if ("F".equals(store) || "f".equals(store)) {
            System.out.println("商品取消添加");
        } else {
            System.err.println("商品添加失败");
        }

        System.out.print("输入任意键返回商品界面");
        String s = ShopUtil.nextLine();
        goodsManageMenu();

    }

    private static void addGoodsByRandom() {
        int length = ShopUtil.getGoodsNum(shoppingGoods);
        System.out.print("请输入你要添加商品的个数：");
        int num = ShopUtil.nextInt();

        for (int i = length; i < num + length; i++) {
            String name = ShopUtil.randomGoodsName();
            int price = ShopUtil.randomInt(5, 20);
            String date = ShopUtil.randomDate();
            int invent = ShopUtil.randomInt(50, 500);
            shoppingGoods[i] = addGood(i + 1, name, price, date, invent);
        }

        System.out.println("商品添加成功");
        System.out.println("1.返回上一级");
        System.out.println("2.返回商品界面");
        System.out.println("3.返回主菜单");
        System.out.println("0.退出系统");

        int choose = ShopUtil.nextInt();
        while (true) {
            switch (choose) {
                case 1:
                    addGoodsMenu();
                case 2:
                    goodsManageMenu();
                case 3:
                    mainBoard();
                case 0:
                    System.exit(0);
                default:
                    System.err.println("你的输入有误，请重新输入");
                    choose = ShopUtil.nextInt();
                    break;
            }
        }
    }

    private static void deleteGoodsMenu() {
        System.out.println("-----------------欢迎来到xx超市管理系统！-----------------");
        System.out.println("                      1.商品编号删除                      ");
        System.out.println("                      2.商品名称删除                      ");
        System.out.println("                      3.返回上一级                        ");
        System.out.println("----------------------------------------------------------");

        int choose = ShopUtil.nextInt();
        while (true) {
            switch (choose) {
                case 1:
                    deleteGoodsByID();
                    break;
                case 2:
                    deleteGoodsByName();
                    break;
                case 3:
                    goodsManageMenu();
                    break;
                default:
                    System.err.println("你的输入有误，请重新输入");
                    choose = ShopUtil.nextInt();
                    break;
            }
        }
    }

    private static void deleteGoodsByID() {
        int length = ShopUtil.getGoodsNum(shoppingGoods);
        System.out.print("请输入要删除的商品编号：");
        int id = ShopUtil.nextInt();

        while (true) {
            if (id > length || id < 1) {
                System.out.println("你输入的编号不存在");
                id = ShopUtil.nextInt();
            } else {
                break;
            }
        }

        shoppingGoods[id - 1] = shoppingGoods[length - 1];
        shoppingGoods[id - 1][0] = id + "";
        shoppingGoods[length - 1] = new String[6];

        System.out.println("删除成功\n");
        System.out.print("是否继续删除，若不删除，则返回商品界面(T / F)：");
        String choose = ShopUtil.nextLine();
        while (true) {
            if ("T".equals(choose) || "t".equals(choose)) {
                deleteGoodsByID();
            } else if ("F".equals(choose) || "f".equals(choose)) {
                goodsManageMenu();
            } else {
                System.err.println("你的输入有误，请重新输出");
                choose = ShopUtil.nextLine();
            }
        }

    }

    private static void deleteGoodsByName() {
        int length = ShopUtil.getGoodsNum(shoppingGoods);
        System.out.print("请输入要删除的商品名称：");
        String name = ShopUtil.nextLine();

        int count = 0;

        for (int i = 0; i < length; i++) {
            if (name == shoppingGoods[i][1]) {
                String id = shoppingGoods[i][0];
                shoppingGoods[i] = shoppingGoods[length - 1];
                shoppingGoods[i] = new String[6];
                System.out.println("删除成功\n");
                break;
            }

            if (i == length - 1) {
                System.out.println("你输入的商品名称不存在！");
                System.out.println("删除失败\n");
            }
        }

        System.out.print("是否继续删除，若不删除，则返回商品界面(T / F)：");
        String choose = ShopUtil.nextLine();
        while (true) {
            if ("T".equals(choose) || "t".equals(choose)) {
                deleteGoodsByID();
            } else if ("F".equals(choose) || "f".equals(choose)) {
                goodsManageMenu();
            } else {
                System.err.println("你的输入有误，请重新输入");
                choose = ShopUtil.nextLine();
            }
        }
    }

    private static void updateGoodsMenu() {
        System.out.println("-----------------欢迎来到xx超市管理系统！-----------------");
        System.out.println("                      1.修改全部信息                      ");
        System.out.println("                      2.修改单个信息                      ");
        System.out.println("                      3.返回上一级                        ");
        System.out.println("                      4.退出系统                          ");
        System.out.println("----------------------------------------------------------");

        int choose = ShopUtil.nextInt();
        switch (choose) {
            case 1:
                updateAll();
            case 2:
                updateOne();
            case 3:
                goodsManageMenu();
            case 4:
                System.exit(0);
            default:
                System.err.println("你的输入有误，请重新输入");
                choose = ShopUtil.nextInt();
                break;
        }

    }

    private static void updateOne() {
        System.out.print("请输入要修改的商品的编号：");
        int id = ShopUtil.nextInt();

        while (true) {
            if (id > ShopUtil.getGoodsNum(shoppingGoods) || id < 1) {
                System.out.println("你输入的编号不存在，请重新输入");
            } else {
                break;
            }
        }

        System.out.println("输入要更新的属性：");
        System.out.println("1:商品名称，2:商品价格，3:进货日期，4:库存数量");

        int choose = ShopUtil.nextInt();

        switch (choose) {
            case 1:
                System.out.print("请输入更新后的商品名称：");
                break;
            case 2:
                System.out.print("请输入更新后的商品价格：");
                break;
            case 3:
                System.out.print("请输入更新后的进货日期：");
                break;
            case 4:
                System.out.print("请输入更新后的库存数量：");
                break;
            default:
                System.out.println("你输入的值有误，无法更新");
                break;
        }

        if (choose > 0 || choose < 5) {
            String value = ShopUtil.nextLine();
            shoppingGoods[id - 1][choose] = value;
            System.out.println("商品信息更新成功");
        }

        System.out.print("输入任意键返回商品界面");
        String s = ShopUtil.nextLine();
        goodsManageMenu();
    }

    private static void updateAll() {
        System.out.print("请输入要修改的商品的编号：");
        int id = ShopUtil.nextInt();

        while (true) {
            if (id > ShopUtil.getGoodsNum(shoppingGoods) || id < 1) {
                System.err.println("你输入的编号不存在，请重新输入");
            } else {
                break;
            }
        }

        String[] temp = new String[6];
        System.out.println("输入修改内容");
        System.out.print("商品名称：");
        String name = ShopUtil.nextLine();

        System.out.print("价格：");
        int price = ShopUtil.nextInt();

        System.out.print("进货日期：");
        String date = ShopUtil.nextLine();

        System.out.print("库存：");
        int invent = ShopUtil.nextInt();

        temp = addGood(id, name, price, date, invent);

        System.out.println("更新数据为");
        System.out.println("编号：" + temp[0] + "，名称：" + temp[1] + "，价格：" + temp[2] + "，进货日期：" +
                temp[3] + "，库存：" + temp[4]);

        System.out.println("是否保存更新！(T / F)");
        String store = ShopUtil.nextLine();
        if ("T".equals(store) || "t".equals(store)) {
            shoppingGoods[id - 1] = temp;
            System.out.println("商品信息更新成功");
        } else if ("F".equals(store) || "f".equals(store)) {
            System.out.println("商品信息取消更新");
        } else {
            System.err.println("数据更新失败");
        }

        System.out.print("输入任意键返回商品界面");
        String s = ShopUtil.nextLine();
        goodsManageMenu();
    }

    private static void showGoodsMenu() {
        System.out.println("-----------------欢迎来到xx超市管理系统！-----------------");
        System.out.println("                        1.全部查询                        ");
        System.out.println("                        2.分页查询                        ");
        System.out.println("                        3.查看已有商品                    ");
        System.out.println("                        4.返回上一级                      ");
        System.out.println("----------------------------------------------------------");
        int choose = ShopUtil.nextInt();
        while (true) {
            switch (choose) {
                case 1:
                    showGoodsAll();
                    break;
                case 2:
                    System.out.print("请输入每页显示多少条：");
                    int pageNum = ShopUtil.nextInt();
                    showGoodsByPage(pageNum, 1);
                    break;
                case 3:
                    showGoodsName();
                case 4:
                    goodsManageMenu();
                    break;
                default:
                    System.err.println("你的输入有误，请重新输入");
                    choose = ShopUtil.nextInt();
                    break;
            }
        }
    }

    private static void showGoodsByPage(int pageNum, int currentPage) {
        int length = ShopUtil.getGoodsNum(shoppingGoods);
        int totalPage = (length - 1) / pageNum + 1;

        int judge = currentPage * pageNum < length ? currentPage * pageNum : length;
        for (int i = (currentPage - 1) * pageNum; i < judge; i++) {
            System.out.println("编号：" + shoppingGoods[i][0] + "，名称：" + shoppingGoods[i][1] + "，价格：" + shoppingGoods[i][2] + "，进货日期：" +
                    shoppingGoods[i][3] + "，库存：" + shoppingGoods[i][4]);
        }

        System.out.println("当前是第" + currentPage + "页，共" + totalPage + "页，共" + length + "条记录\n");
        System.out.println("1.继续查询");
        System.out.println("2.返回商品界面");
        System.out.println("0.退出系统");

        int choose = ShopUtil.nextInt();
        while (true) {
            switch (choose) {
                case 1:
                    System.out.print("请输入显示第几页：");
                    currentPage = ShopUtil.nextInt();
                    showGoodsByPage(pageNum, currentPage);
                    break;
                case 2:
                    goodsManageMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.err.println("你的输入有误，请重新输入");
                    choose = ShopUtil.nextInt();
                    break;
            }
        }
    }

    private static void showGoodsAll() {
        int length = ShopUtil.getGoodsNum(shoppingGoods);
        for (int i = 0; i < length; i++) {
            System.out.println("编号：" + shoppingGoods[i][0] + "，名称：" + shoppingGoods[i][1] + "，价格：" + shoppingGoods[i][2] + "，进货日期：" +
                    shoppingGoods[i][3] + "，库存：" + shoppingGoods[i][4]);
        }

        System.out.println("1.返回商品界面");
        System.out.println("0.退出系统");
        int choose = ShopUtil.nextInt();
        while (true) {
            switch (choose) {
                case 1:
                    goodsManageMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.err.println("你的输入有误，请重新输入");
                    choose = ShopUtil.nextInt();
                    break;
            }
        }
    }

    private static void showGoodsName() {
        int length = ShopUtil.getGoodsNum(shoppingGoods);
        System.out.println("商品名称：");
        for (int i = 0; i < length; i++) {
            System.out.print(shoppingGoods[i][1] + " ");
            if (i % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("1.返回商品界面");
        System.out.println("0.退出系统");

        int choose = ShopUtil.nextInt();
        while (true) {
            switch (choose) {
                case 1:
                    goodsManageMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.err.println("你的输入有误，请重新输入");
                    choose = ShopUtil.nextInt();
                    break;
            }
        }
    }

    private static void selectGoodsMenu() {
        System.out.println("-----------------欢迎来到xx超市管理系统！-----------------");
        System.out.println("                       1.精确编号查找                     ");
        System.out.println("                       2.精确名称查找                     ");
        System.out.println("                       3.价格区间查找                     ");
        System.out.println("                       4.模糊名称查找                     ");
        System.out.println("                       5.返回上一级                       ");
        System.out.println("----------------------------------------------------------");


        int choose = ShopUtil.nextInt();
        while (true) {
            switch (choose) {
                case 1:
                    selectByID();
                    break;
                case 2:
                    selectByPreciseName();
                    break;
                case 3:
                    selectByPriceRange();
                    break;
                case 4:
                    selectByVagueName();
                    break;
                case 5:
                    goodsManageMenu();
                    break;
                default:
                    System.err.println("你的输入有误，请重新输入");
                    choose = ShopUtil.nextInt();
                    break;
            }
        }

    }

    private static void selectByID() {
        int length = ShopUtil.getGoodsNum(shoppingGoods);
        System.out.print("请输入要查询的编号：");
        int num = ShopUtil.nextInt();

        if (num <= length) {
            System.out.println("编号：" + shoppingGoods[num - 1][0] + "，名称：" + shoppingGoods[num - 1][1] + "，价格：" + shoppingGoods[num - 1][2] + "，进货日期：" +
                    shoppingGoods[num - 1][3] + "，库存：" + shoppingGoods[num - 1][4]);
        } else {
            System.err.println("你输入的编号有误");
        }


        System.out.print("是否继续查询，若不查询，则返回商品界面(T / F)：");
        String choose = ShopUtil.nextLine();
        while (true) {
            if ("T".equals(choose) || "t".equals(choose)) {
                selectByID();
            } else if ("F".equals(choose) || "f".equals(choose)) {
                goodsManageMenu();
            } else {
                System.err.println("你的输入有误，请重新输出");
                choose = ShopUtil.nextLine();
            }
        }

    }

    private static void selectByPriceRange() {
        int length = ShopUtil.getGoodsNum(shoppingGoods);

        int minPrice, maxPrice;
        while (true) {
            System.out.print("请输入要查询的商品最小价格：");
            minPrice = ShopUtil.nextInt();

            System.out.print("请输入要查询的商品最大价格：");
            maxPrice = ShopUtil.nextInt();

            if (minPrice > maxPrice) {
                System.err.println("你输入的价格区间有误，请重新输入");
            } else {
                break;
            }
        }

        int count = 0;
        for (int i = 0; i < length; i++) {
            int price = Integer.parseInt(shoppingGoods[i][2]);
            if (price >= minPrice && price <= maxPrice) {
                System.out.println("编号：" + shoppingGoods[i][0] + "，名称：" + shoppingGoods[i][1] + "，价格：" + shoppingGoods[i][2] + "，进货日期：" +
                        shoppingGoods[i][3] + "，库存：" + shoppingGoods[i][4]);
                count++;
            }
        }

        if (count == 0){
            System.out.println("没有满足条件的商品");
        }

        System.out.print("\n是否继续查询，若不查询，则返回商品界面(T / F)：");
        String choose = ShopUtil.nextLine();
        while (true) {
            if ("T".equals(choose) || "t".equals(choose)) {
                selectByPriceRange();
            } else if ("F".equals(choose) || "f".equals(choose)) {
                goodsManageMenu();
            } else {
                System.err.println("你的输入有误，请重新输出");
                choose = ShopUtil.nextLine();
            }
        }

    }

    private static void selectByPreciseName() {
        int length = ShopUtil.getGoodsNum(shoppingGoods);

        System.out.print("请输入要查询的商品名称：");
        String name = ShopUtil.nextLine();

        for (int i = 0; i < length; i++) {
            if (shoppingGoods[i][1] == name) {
                System.out.println("编号：" + shoppingGoods[i][0] + "，名称：" + shoppingGoods[i][1] + "，价格：" + shoppingGoods[i][2] + "，进货日期：" +
                        shoppingGoods[i][3] + "，库存：" + shoppingGoods[i][4]);
                break;
            }
            if (i == length - 1) {
                System.err.println("没有满足条件的商品");
                pause(100);
            }
        }

        System.out.print("\n是否继续查询，若不查询，则返回商品界面(T / F)：");
        String choose = ShopUtil.nextLine();
        while (true) {
            if ("T".equals(choose) || "t".equals(choose)) {
                selectByPreciseName();
            } else if ("F".equals(choose) || "f".equals(choose)) {
                goodsManageMenu();
            } else {
                System.err.println("你的输入有误，请重新输入");
                choose = ShopUtil.nextLine();
            }
        }
    }

    private static void selectByVagueName() {
        int length = ShopUtil.getGoodsNum(shoppingGoods);

        System.out.print("请输入要查询的商品名称：");
        String name = ShopUtil.nextLine();

        int count = 0; // 记录满足条件的数据
        for (int i = 0; i < length; i++) {
            if (shoppingGoods[i][1].contains(name)) {
                count++;
                System.out.println("编号：" + shoppingGoods[i][0] + "，名称：" + shoppingGoods[i][1] + "，价格：" + shoppingGoods[i][2] + "，进货日期：" +
                        shoppingGoods[i][3] + "，库存：" + shoppingGoods[i][4] + "，销量：" + shoppingGoods[i][5]);
            }
        }

        if (count == 0) {
            System.err.println("没有满足条件的商品");
            pause(100);
        }


        System.out.print("\n是否继续查询，若不查询，则返回商品界面(T / F)：");
        String choose = ShopUtil.nextLine();
        while (true) {
            if ("T".equals(choose) || "t".equals(choose)) {
                selectByPreciseName();
            } else if ("F".equals(choose) || "f".equals(choose)) {
                goodsManageMenu();
            } else {
                System.err.println("你的输入有误，请重新输入");
                choose = ShopUtil.nextLine();
            }
        }
    }

    //商品参数：商品编号、商品名称、商品单价、进货日期、库存数量
    public static String[] addGood(int id, String name, int price, String date, int invent) {
        String[] arr = new String[6];

        arr[0] = id + "";
        arr[1] = name;
        arr[2] = price + "";
        arr[3] = date;
        arr[4] = invent + "";

        return arr;
    }

    private static void userManage() {
        System.out.println("该功能尚未完成！！！");
        System.exit(0);
    }


}