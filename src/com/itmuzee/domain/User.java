package com.itmuzee.domain;

import java.util.Random;

public class User {

    //属性 id 密码 状态 用户名
    private String id;
    private String password;
    private boolean status;
    private String username;




    public User(){
        //创建用户id
        id = createID();

        //让用户注册状态为真
        status = true;
    }

    public User( String password, String username) {
        id = createID();
        this.password = password;
        this.username = username;
        status = true;

    }

    //id用户无法自己设置的 必须是系统自动生成的 由muzee+5个随机的数字
    public String createID(){
        StringBuilder sb = new StringBuilder();

        Random r = new Random();

        for (int i = 0; i < 5; i++) {
            int randon_num = r.nextInt(10);//0-9
            sb.append(randon_num);
        }
        return "muzee" + sb.toString();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}


