package com.itmuzee.domain;

public class Characters {
    public String name;
    public int HP;
    public int maxHP;
    public int attack;
    public int defense;


    public Characters() {

    }

    public Characters(String name, int HP, int attack, int defense) {
        this.name = name;
        this.HP = HP;
        this.maxHP = HP;
        this.attack = attack;
        this.defense = defense;
    }

    //判断他的血量是否为0
    public boolean isAlive() {
        return HP > 0;
    }

    //2、判断恢复的血量是否合规
    public void heal(int amount) {
        HP += amount;
        if (HP > maxHP) {
            HP = maxHP;
        }
    }

    //3、受到了伤害
    public void takeDemage(int demage) {
        HP -= demage;
        if (HP < 0) {
            HP = 0;
        }
    }

    //4、展示人物属性
    public String show() {

        return name + "[当前的生命值为：" + HP + "攻击力：" + attack + "防御力：" + defense + "]";
    }
}
