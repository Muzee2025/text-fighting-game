package com.itmuzee.ui;

import com.itmuzee.domain.EnemyCharacters;
import com.itmuzee.domain.HeroCharacters;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FightingGame {

    //启动游戏
    public void gameStart(String username) {

        // 1. 显示游戏的标题
        System.out.println("┌─────────────────────────────────────────────────────────┐");
        System.out.println("    🎮 " + username + "欢迎来到文字格斗游戏 🎮    ");
        System.out.println("└─────────────────────────────────────────────────────────┘");

        HeroCharacters player = createPlayerCharacter(username);
        System.out.println("角色创建成功！！！");
        System.out.println("初始属性为：" + player.show());
        System.out.println("拥有的技能：" + player.showSkill());

        // 创建敌人列表，添加敌人对象
        ArrayList<EnemyCharacters> enemyList = new ArrayList<>();
        enemyList.add(new EnemyCharacters("初级战士", 80, 15, 10, "猛击"));
        enemyList.add(new EnemyCharacters("敏捷刺客", 60, 20, 5, "快速攻击"));
        enemyList.add(new EnemyCharacters("重装坦克", 120, 10, 20, "防御姿态"));
        enemyList.add(new EnemyCharacters("神秘法师", 70, 25, 8, "火球术"));

        //进入循环准备战斗
        int win = 0;
        int count = 1;//当前是跟第几个敌人战斗

        while (player.isAlive()) {
            if (win != 0) {
                for (int i = 0; i < enemyList.size(); i++) {
                    EnemyCharacters c = enemyList.get(i);
                    c.maxHP += 10;
                    c.HP = c.maxHP;
                    c.attack += 3;
                    c.defense += 2;
                    c.defending = false;

                }
            }
            //随机选择敌人
            Random r = new Random();
            int index = r.nextInt(enemyList.size());
            EnemyCharacters enemy = enemyList.get(index);
            System.out.println(enemy.show());

            //战斗开始：
            System.out.println("===============================================");

            System.out.println("第" + count + "场战斗开始！ 对手：" + enemy.name);

            int round = 1;
            //回合游戏开始进行中
            while (player.isAlive()) {
                //显示双方的状态
                System.out.println("-----------------------------------------------");
                System.out.println("第" + round + "回合开始！");

                //打印敌我双方的血条
                System.out.println(getHealthBar(player.name, player.HP, player.maxHP));
                System.out.println(getHealthBar(enemy.name, enemy.HP, enemy.maxHP));

                playerTurn(player, enemy);

                //判断敌人是否还有血量
                if (!enemy.isAlive()) {
                    System.out.println("恭喜你，击败了" + enemy.name + "！");
                    win++;
                    break;
                }


                enemyTurn(enemy, player);

                //判断玩家是否击败
                if (!player.isAlive()) {
                    System.out.println("你被" + enemy.name + "击败了！");
                    System.out.println("游戏结束！");
                    break;
                }

                //如果没有被玩家击败
                round++;


            }


            //如果这轮玩家游戏结束之后，玩家的状态应该会得到恢复
            if (player.isAlive()) {
                int healHP = r.nextInt(21) + 20;
                //恢复玩家的血量
                player.heal(healHP);
                System.out.println("战斗结束！你恢复了" + healHP + "点血量！");
                System.out.println("当前胜场：" + win);
                System.out.println("=============================================");
            }



            //每胜利三场，任务的属性就要增加
            if (player.isAlive() && win % 3 == 0 && win > 0) {
                System.out.println("恭喜你，获得了属性的提升！");
                player.attack += 5;
                player.defense += 3;

                player.maxHP += 30;
                System.out.println("最大生命值提升30！当前最大生命值为：" + player.maxHP);
                System.out.println("攻击提升5！当前攻击为：" + player.attack);
                System.out.println("防御提升3！当前防御为：" + player.defense);


            }
            //询问玩家是否要继续
            if (player.isAlive()) {
                System.out.println("是否要继续游戏？ y/n");
                Scanner sc = new Scanner(System.in);
                String choice = sc.next();
                if ("y".equalsIgnoreCase(choice)) {
                    System.out.println("继续游戏...");
                    count++;
                    continue;
                } else if ("n".equalsIgnoreCase(choice)) {
                    System.out.println("游戏结束！");
                    break;
                } else {
                    System.out.println("输入错误，默认继续游戏！");
                    continue;
                }
            }


        }


        //游戏结算
        System.out.println("========================================");
        System.out.println("游戏结束！感谢游玩！");
        System.out.println("你的最终胜场为："+win);
        System.out.println("感谢游玩文字版格斗游戏！");
        System.exit(0);

    }

    private void enemyTurn(EnemyCharacters enemy, HeroCharacters player) {

        String attack = "普通攻击";
        Random r = new Random();
        int num = r.nextInt(10);
        if (num < 5) {
            attack = enemy.skill;
        }

        switch (attack) {
            case "普通攻击":
                System.out.println(enemy.name + "使用了普通攻击！");
                int damage = calculateDamage(enemy.attack, player.defense);
                System.out.println(enemy.name + "对" + player.name + "造成了" + damage + "点伤害！");
                player.takeDemage(damage);
                break;
            case "猛击":
                System.out.println(enemy.name + "使用了猛击！");
                int strongDamage = calculateDamage((int) (enemy.attack * 1.5), player.defense);
                System.out.println(enemy.name + "对" + player.name + "造成了" + strongDamage + "点伤害！");
                player.takeDemage(strongDamage);
                break;
            case "快速攻击":
                System.out.println(enemy.name + "使用了快速攻击！");
                int quickDamage = 0;
                for (int i = 0; i < 2; i++) {
                    int temp = calculateDamage((int) (enemy.attack / 2), player.defense);
                    quickDamage += temp;
                }
                System.out.println(enemy.name + "对" + player.name + "造成了" + quickDamage + "点伤害！");
                player.takeDemage(quickDamage);
                break;
            case "防御姿态":
                System.out.println(enemy.name + "使用了防御姿态！");
                enemy.defending = true;
                System.out.println(enemy.name + "摆出了防御姿态，开启他的buff");
                break;
            case "火球术":
                System.out.println(enemy.name + "使用了火球术！");
                int fireDamage = calculateDamage((int) (enemy.attack * 1.8), player.defense);
                System.out.println(enemy.name + "对" + player.name + "造成了" + fireDamage + "点伤害！");
                player.takeDemage(fireDamage);
                break;
        }
    }

    //回合攻击
    public void playerTurn(HeroCharacters player, EnemyCharacters enemy) {
        System.out.println("=====  你的回合  ====");
        System.out.println("请选择攻击方式：");
        System.out.println("1. 普通攻击");
        System.out.println("2. 强力一击");
        System.out.println("3. 生命汲取");
        Scanner sc = new Scanner(System.in);
        String choice = sc.next();
        switch (choice) {
            default:
                System.out.println("无效的选择，默认进行普通攻击");
            case "1":
                System.out.println("你选择了：1 普通攻击！");
                int damage = calculateDamage(player.attack, enemy.defense);
                System.out.println("你对" + enemy.name + "造成了" + damage + "点伤害！");
                //扣血操作
                enemy.takeDemage(damage);
                break;
            case "2":
                System.out.println("你选择了：2 强力一击！");
                if (player.HP < 10) {
                    System.out.println("你的生命值不足10，无法使用强力一击！");
                } else {
                    int strongDamage = calculateDamage((int) (player.attack * 1.8), enemy.defense);
                    System.out.println("你对" + enemy.name + "造成了" + strongDamage + "点伤害！");
                    enemy.takeDemage(strongDamage);
                    player.takeDemage(10);
                }
                break;
            case "3":
                System.out.println("你选择了：3 生命汲取！");
                if (player.HP < 10) {
                    System.out.println("你的生命值不足10，无法使用生命汲取！");
                } else {
                    player.takeDemage(10);
                    Random r = new Random();
                    int healHP = r.nextInt(21);
                    player.heal(healHP);
                    System.out.println("消耗了10点生命值,你恢复了" + healHP + "点生命值！");
                }
                break;
        }
    }

    //计算普通攻击的伤害
    //伤害 = 攻击力 - 对方的防御力 ；若伤害小于0 则最小伤害为1；
    public int calculateDamage(int attack, int defense) {
        int damage = attack - defense;
        if (damage <= 0) {
            damage = 1;
        }
        return damage;
    }


    //显示血条
    public String getHealthBar(String name, int HP, int maxHP) {
        int barLength = 20;
        int bar = (int) ((HP * 1.0 / maxHP) * barLength);
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":[");

        for (int i = 0; i < barLength; i++) {

            if (i < bar) {
                sb.append("█");
            } else {
                sb.append(" ");
            }
        }
        sb.append("]").append(HP).append("/").append(maxHP).append("HP");
        return sb.toString();
    }


    public HeroCharacters createPlayerCharacter(String username) {

        System.out.println("请创建你的角色");
        System.out.println("您的角色名为：" + username);

        int point = 20;
        System.out.println("请分配属性点（共20点）：");
        String[] attributes = {"生命值", "攻击力", "防御力"};
        int[] values = new int[3];

        for (int i = 0; i < attributes.length; i++) {
            System.out.println((i + 1) + "、" + attributes[i]);

            System.out.println("分配点数到" + attributes[i] + "(剩余点数：" + point + "):");
            //用户键盘录入
            Scanner sc = new Scanner(System.in);

            int value = sc.nextInt();

            //对边界条件进行判断
            if (value < 0) {
                System.out.println("请输入大于0的数字,当前的值归为0");
                value = 0;
            }

            if (value > point) {
                System.out.println("请输入小于等于" + point + "的数字，目前的输入按照最大值处理");
                value = point;
            }

            point -= value;
            values[i] = value;


        }

        //现在有了属性，我需要创建一个完整的对象

        HeroCharacters player = new HeroCharacters(
                username,
                100 + values[0] * 10,//生命
                10 + values[1] * 2,//攻击
                0 + values[2] * 1//防御
        );

        //还有英雄的技能需要说明
        player.skillList.add("普通攻击");
        player.skillList.add("强力一击");
        player.skillList.add("生命汲取");

        return player;

    }

}
