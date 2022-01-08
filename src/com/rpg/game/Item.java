package com.rpg.game;


public class Item {

    private String name;
    private Ability ability;
    private int value;
    private int price;
    private int stock;

    public Item(String name, Ability ability, int value, int price, int stock) {
        this.name = name;
        this.ability = ability;
        this.value = value;
        this.price = price;
        this.stock = stock;
    }
    public String getName() {
        return name;
    }
    public Ability getAbility() {
        return ability;
    }
    public int getValue() {
        return value;
    }
    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void minusStock() {
        this.stock--;
    }

}
