package com.itmuzee.ui;

import com.itmuzee.domain.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Login {
    public void start() {

        ArrayList<User> list = new ArrayList<>();


        //使用ctrl + alt  + T 包裹循环语句
        while (true) {
            //UI的启动页面
            System.out.println("项目的注册页面打开了~");

            System.out.println("┌──────────────────────────────────────────────┐");
            System.out.println("        🎮 欢迎来到文字格斗游戏 🎮        ");
            System.out.println("└──────────────────────────────────────────────┘");
            System.out.println("请选择操作：1登录  2注册  3退出");

            Scanner sc = new Scanner(System.in);
            String choose = sc.next();

            switch (choose) {
                case "1":
                    login(list);
                    break;
                case "2":
                    register(list);
                    break;
                case "3":
                    System.out.println("用户选择了退出操作");
                    System.exit(0);
                    break;
                default:
                    System.out.println("没有这个操作，拜拜~");
                    break;
            }
        }


    }

    //登陆操作
    public void login(ArrayList<User> list) {
        System.out.println("用户选择了登陆界面");
        //用户登陆的操作：
        //接收到用户键盘录入的信息，判断是否有这个用户
        //存在，判断这个账户的账号是否被禁用
        //没有被禁用，判断验证码是否正确
        //最后验证密码是否正确

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.next();

        //判断是否存在这个用户
        if (!contine(list, username)) {
            System.out.println("当前用户不存在，请返回注册~");
            return;
        }

        //判断这个用户的状态是否正常
        //需要通过用户名获取这个用户的索引
        int index = findIndex(list, username);
        User u = list.get(index);
        if (!u.isStatus()) {
            System.out.println("当前用户账号被禁用，请联系管理员~");
            return;
        }

        //让用户输入密码和验证码
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入密码：");
            String password = sc.next();

            String rightPassword = u.getPassword();
            while (true) {

                String rightCode = getCode();
                System.out.println("正确的验证码为：" + rightCode); // 输出正确的验证码

                System.out.println("请输入验证码：");
                String code = sc.next();


                if (!code.equalsIgnoreCase(rightCode)) {
                    System.out.println("验证码输入错误，请重新输入~");
                    break;
                } else {
                    System.out.println("验证码输入正确~");
                    break;
                }
            }
            if (password.equals(rightPassword)) {
                System.out.println("密码输入正确,游戏启动！~");
                //密码成功输入启动游戏
                FightingGame fg = new FightingGame();
                fg.gameStart(u.getUsername());
                break;
            } else {
                System.out.println("密码输入错误，请重新输入~");
                if (i == 2) {
                    System.out.println("密码输入错误次数过多，账号被禁用，请联系管理员~");
                    u.setStatus(false);
                    return;
                } else {
                    System.out.println("密码输入错误，你还剩下" + (2 - i) + "次机会");
                }

            }


        }


    }

    //获取用户索引
    public int findIndex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(i);
            if (u.getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    //注册操作
    public void register(ArrayList<User> list) {
        System.out.println("用户选择了注册界面");

        //注册的整个流程
        //创建User（）对象  添加用户名 添加密码  最后放到集合中
//        具体的操作步骤：
//        1、创建一个User(空参)类
        User u = new User();
//        2、键盘录入用户名
        //判断输入的用户名是否符合要求
        //u.setusername();
        /*
        * 2. 用户名username:

        · 用户名唯一

        · 只能由字母、数字组成,不能是纯数字*/

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入用户名：");
            String username = sc.next();


            //判断用户名的长度是否符合要求
            //长度必须在3~ 16位
            if (!cheaklen(3, 16, username)) {
                System.out.println("用户名的长度必须是3到16位的，请重新输入~");
                continue;
            }


            //· 只能由字母、数字组成,不能是纯数字
            if (!cheakUsername(username)) {
                System.out.println("您输入的用户名必须只能由字母、数字组成,不能是纯数字组成~");
                continue;
            }

            //判断用户名唯一
            if (contine(list, username)) {
                System.out.println("当前用户名已经存在，请重新输入~");
                continue;
            }


            //如果成功运行到这里，说明代码符合要求，将username添加到User中
            u.setUsername(username);
            break;
        }


        //3.键盘录入密码
        while (true) {
            //判断输入的密码是否符合要求
            //u.setpassword()
            // 长度3~8位
            //只能是字母加数字的组合,不能有其他字母
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入密码：");
            String password1 = sc.next();
            System.out.println("请再次确认输入密码：");
            String password2 = sc.next();

            if (!password1.equals(password2)) {
                System.out.println("两次输入的密码不一致，请重新输入~");
                continue;
            }

            if (!cheakpassword(password1)) {
                System.out.println("您输入的密码必须是3~8位的字母加数字的组合,不能有其他字母~");
                continue;

            }

            if (!cheaklen(3, 8, password1)) {
                System.out.println("密码的长度必须是3到8位的，请重新输入~");
                continue;
            }


            u.setPassword(password1);


            break;


        }


        //把user对象添加到集合中
        list.add(u);

        System.out.println("用户注册成功！");

    }

    //判断用户名长度是否符合要求的方法
    public Boolean cheaklen(int minLen, int maxLen, String username) {
        return username.length() >= minLen && username.length() <= maxLen;
    }

    //对于字符串的个数进行统计，抽象方法
    public int[] countstr(String userinfo) {
        //字母>0
        int chatcount = 0;
        //数字>=0
        int numcount = 0;
        //其他==0
        int othercount = 0;

        for (int i = 0; i < userinfo.length(); i++) {
            char c = userinfo.charAt(i);
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                chatcount++;
            } else if (c >= '0' && c <= '9') {
                numcount++;
            } else {
                othercount++;
            }
        }

        return new int[]{chatcount, numcount, othercount};
    }


    //· 只能由字母、数字组成,不能是纯数字
    //对用户名的进行逐个拆解分析

    public boolean cheakUsername(String username) {
        int[] count = countstr(username);

        return count[0] > 0 && count[1] >= 0 && count[2] == 0;

    }

    //对密码的判断 是不是由数字＋字母组成的
    //检查密码是否正确
    //密码的长度 3-8位
    public boolean cheakpassword(String password) {
        int[] count = countstr(password);
        return count[0] > 0 && count[1] > 0 && count[2] == 0;
    }

    //判断用户名唯一
    //username 在集合中是否包含
    //包含 不唯一
    //不包含  唯一
    public boolean contine(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(i);
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


    //获取验证码
    public static String getCode() {

        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char) ('A' + i));
            list.add((char) ('a' + i));
        }
        //System.out.println(list);

        //2.从集合中抽取随机字母
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            //获取集合中的索引
            int index = r.nextInt(list.size());
            Character c = list.get(index);
            sb.append(c);
        }
        //生成一个随机的数字0-9
        sb.append(r.nextInt(10));

        //System.out.println(sb);

        //生成数字的位置是任意的
        char[] arr = sb.toString().toCharArray();
        //把最大索引上面的数字跟最后一个索引的数字进行交换
        int i = r.nextInt(arr.length);

        char temp = arr[i];
        arr[i] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;

        String code = new String(arr);

        return code;
    }


}
