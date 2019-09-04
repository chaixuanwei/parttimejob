package com.example.myapplication.me.bean;

public class CheckBean {
    private String name;
    private boolean isSelected;

    public CheckBean(String pName, boolean pIsSelected) {
        name = pName;
        isSelected = pIsSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean pSelected) {
        isSelected = pSelected;
    }
}
