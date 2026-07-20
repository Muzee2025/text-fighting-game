package com.itmuzee.domain;

import java.util.ArrayList;


//我方英雄的子类构造
public class HeroCharacters extends Characters {

    public ArrayList<String> skillList;

    public HeroCharacters(String name, int HP, int attack, int defense) {
        super(name, HP, attack, defense);
        skillList = new ArrayList<>();
    }

    public HeroCharacters() {
        super();
        skillList = new ArrayList<>();
    }

    //添加一个行为，用于遍历技能
    public String showSkill(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < skillList.size(); i++) {
            //添加技能到字符串中
            sb.append(skillList.get(i));
            //如果不是最后一个元素的话
            if(i != skillList.size() - 1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
