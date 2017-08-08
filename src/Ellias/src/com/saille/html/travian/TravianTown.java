package com.saille.html.travian;

import java.util.Date;

public class TravianTown {
    private int race;
    private int id;
    private String name;
    private String owner;
    private int x;
    private int y;
    private int wood;
    private int clay;
    private int iron;
    private int food;
    private int woodProduct;
    private int clayProduct;
    private int ironProduct;
    private int foodProduct;
    private int woodSize;
    private int claySize;
    private int ironSize;
    private int foodSize;
    public int[] levels;
    public String[] builds;
    private String constructing;
    private Date endTime;
    private int merchants;
    private int maxMerchants;

    public TravianTown() {
        this.levels = new int[40];
        this.builds = new String[40];
    }

    public int getRace() {
        return this.race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWood() {
        return this.wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getClay() {
        return this.clay;
    }

    public void setClay(int clay) {
        this.clay = clay;
    }

    public int getIron() {
        return this.iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getFood() {
        return this.food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getWoodProduct() {
        return this.woodProduct;
    }

    public void setWoodProduct(int woodProduct) {
        this.woodProduct = woodProduct;
    }

    public int getClayProduct() {
        return this.clayProduct;
    }

    public void setClayProduct(int clayProduct) {
        this.clayProduct = clayProduct;
    }

    public int getIronProduct() {
        return this.ironProduct;
    }

    public void setIronProduct(int ironProduct) {
        this.ironProduct = ironProduct;
    }

    public int getFoodProduct() {
        return this.foodProduct;
    }

    public void setFoodProduct(int foodProduct) {
        this.foodProduct = foodProduct;
    }

    public int getWoodSize() {
        return this.woodSize;
    }

    public void setWoodSize(int woodSize) {
        this.woodSize = woodSize;
    }

    public int getClaySize() {
        return this.claySize;
    }

    public void setClaySize(int claySize) {
        this.claySize = claySize;
    }

    public int getIronSize() {
        return this.ironSize;
    }

    public void setIronSize(int ironSize) {
        this.ironSize = ironSize;
    }

    public int getFoodSize() {
        return this.foodSize;
    }

    public void setFoodSize(int foodSize) {
        this.foodSize = foodSize;
    }

    public int[] getLevels() {
        return this.levels;
    }

    public void setLevels(int[] levels) {
        this.levels = levels;
    }

    public String[] getBuilds() {
        return this.builds;
    }

    public void setBuilds(String[] builds) {
        this.builds = builds;
    }

    public String getConstructing() {
        return this.constructing;
    }

    public void setConstructing(String constructing) {
        this.constructing = constructing;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getMerchants() {
        return this.merchants;
    }

    public void setMerchants(int merchants) {
        this.merchants = merchants;
    }

    public int getMaxMerchants() {
        return this.maxMerchants;
    }

    public void setMaxMerchants(int maxMerchants) {
        this.maxMerchants = maxMerchants;
    }
}