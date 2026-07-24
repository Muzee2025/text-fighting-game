package com.itmuzee.domain;

public class Consumable {
    private String name;
    private int healAmount;

    public Consumable(String name, int healAmount) {
        this.name = name;
        this.healAmount = healAmount;
    }

    public Consumable() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public String toString() {
        return "使用了" + name + "道具，恢复了" + healAmount + "的生命值";
    }
}
