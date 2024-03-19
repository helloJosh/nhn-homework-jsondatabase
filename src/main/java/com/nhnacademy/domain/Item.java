package com.nhnacademy.domain;

public class Item {
    private int id;
    private String model;
    //0~10000
    private int energy;
    //0 ~100
    private int attack;
    //0~50
    private int defense;
    //0~100
    private int movingSpeed;
    //0~100
    private int attackSpeed;
    public Item(){}
    
    public Item(int id,String model, int energy, int attack, int defense, int movingSpeed, int attackSpeed) {
        this.id = id;
        this.model = model;
        this.energy = energy;
        this.attack = attack;
        this.defense = defense;
        this.movingSpeed = movingSpeed;
        this.attackSpeed = attackSpeed;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getMoveingSpeed() {
        return movingSpeed;
    }
    public void setMoveingSpeed(int moveingSpeed) {
        this.movingSpeed = moveingSpeed;
    }
    public int getAttackSpeed() {
        return attackSpeed;
    }
    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", model=" + model + ", energy=" + energy + ", attack=" + attack + ", defense="
                + defense + ", movingSpeed=" + movingSpeed + ", attackSpeed=" + attackSpeed + "]";
    }
    
}
