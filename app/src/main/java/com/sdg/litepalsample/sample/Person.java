package com.sdg.litepalsample.sample;

import org.litepal.crud.LitePalSupport;

public class Person extends LitePalSupport {

    private String name;
    private String desc;
    private int age;
    private int cla;
    private String superText;//超大文本好调试

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCla() {
        return cla;
    }

    public void setCla(int cla) {
        this.cla = cla;
    }

    public String getSuperText() {
        return superText;
    }

    public void setSuperText(String superText) {
        this.superText = superText;
    }
}
