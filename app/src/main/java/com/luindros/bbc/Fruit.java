package com.luindros.bbc;

/**
 * Created by luigmus on 29/10/17.
 */

//Class Fruit.
public class Fruit {

    private String type;
    private int price;
    private int weight;

    public Fruit() {
    }

    //Constructor
    public Fruit(String type, int price, int weight) {
        this.type = type;
        this.price = price;
        this.weight = weight;
    }
    //Setter and getter for the access to the attributes
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
