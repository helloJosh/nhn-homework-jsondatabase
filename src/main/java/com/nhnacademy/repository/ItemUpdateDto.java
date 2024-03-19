package com.nhnacademy.repository;

public class ItemUpdateDto {
    private String model;
    private Integer energy;
    private Integer attack;
    private Integer defense;
    private Integer movingSpeed;
    private Integer attackSpeed;

    public ItemUpdateDto(String model, Integer energy, Integer attack, Integer defense, Integer movingSpeed,
            Integer attackSpeed) {
        this.model = model;
        this.energy = energy;
        this.attack = attack;
        this.defense = defense;
        this.movingSpeed = movingSpeed;
        this.attackSpeed = attackSpeed;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getMovingSpeed() {
        return movingSpeed;
    }

    public void setMovingSpeed(Integer movingSpeed) {
        this.movingSpeed = movingSpeed;
    }

    public Integer getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(Integer attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
    

    
}
