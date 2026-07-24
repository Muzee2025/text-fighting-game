package com.itmuzee.domain;

import java.util.ArrayList;


//我方英雄的子类构造
public class HeroCharacters extends Characters {

    public ArrayList<String> skillList;
    public ArrayList<Consumable> inventory;


    //蓝条属性
    public int MP;
    public int maxMP;

    public HeroCharacters(String name, int HP, int attack, int defense, int MP) {
        super(name, HP, attack, defense);
        this.MP = MP;
        this.maxMP = MP;
        skillList = new ArrayList<>();
        inventory = new ArrayList<>();
    }

    public HeroCharacters() {
        super();
        skillList = new ArrayList<>();
        inventory = new ArrayList<>();
    }

    //添加一个行为，用于遍历技能
    public String showSkill() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < skillList.size(); i++) {
            //添加技能到字符串中
            sb.append(skillList.get(i));
            //如果不是最后一个元素的话
            if (i != skillList.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    //3.1收到蓝量的减少
    public void useMP(int amount) {
        MP -= amount;
        if (MP < 0) {
            MP = 0;
        }
    }


    public void restoreMP(int amount) {
        MP += amount;
        if (MP > maxMP) {
            MP = maxMP;
        }
    }

    public void addConsumable(Consumable consumable) {
        inventory.add(consumable);
    }

    public void useConsumable(String name) {
        for (int i = 0; i < inventory.size(); i++) {
            //遍历背包里面所有的道具
            System.out.println("你背包里面目前");
            Consumable c = inventory.get(i);
            if (c.getName().equals(name)) {
                //恢复相对应的血量
                heal(c.getHealAmount());
                inventory.remove(c);
                System.out.println("使用了" + name + "道具，恢复了" + c.getHealAmount() + "的生命值");
                return;
            }

        }
        System.out.println("你没有这个道具");
    }

    //展示背包
    public void showConsumables() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inventory.size(); i++) {
            sb.append(inventory.get(i));
            if (i != inventory.size() - 1) {
                sb.append(", ");
            }
        }
        System.out.println(sb.toString());
    }

    //重写show方法
    public String show() {

        return name + "[当前的生命值为：" + HP + "攻击力：" + attack + "防御力：" + defense + "蓝条：" + MP + "]";
    }
}
