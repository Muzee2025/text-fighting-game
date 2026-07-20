package com.itmuzee.domain;


//敌方英雄的子类构造
public class EnemyCharacters extends Characters {
    public String skill;
    public boolean defending;

    public EnemyCharacters(String name, int HP,  int attack, int defense, String skill) {
        super(name, HP, attack, defense);
        this.skill = skill;
        this.defending = false;
    }

    public EnemyCharacters() {
    }

    public void takeDemage(int demage) {

        int max = demage;
        //判断地方是否拥有buff的能力
        if (defending) {
            max = demage / 2 > 1 ? demage / 2 : 1;
            defending = false;

        }
        //继承父类的takeDamage的方法
        super.takeDemage(max);

    }

}
