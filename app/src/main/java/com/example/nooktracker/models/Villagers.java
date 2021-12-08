package com.example.nooktracker.models;

public class Villagers {
    public String userId;

    public Villager villager1;
    public Villager villager2;
    public Villager villager3;
    public Villager villager4;
    public Villager villager5;
    public Villager villager6;
    public Villager villager7;
    public Villager villager8;

    public Villagers(){}

    public Villagers(String userId){

        villager1 = new Villager("Raymond", "64", "1/10", "Smug", "Cat", "https://acnhapi.com/v1/icons/villagers/64");
        villager2 = new Villager("Daisy", "118", "16/11", "Normal", "Dog", "https://acnhapi.com/v1/icons/villagers/118");
        villager3 = new Villager("Caroline", "362", "15/7", "Normal", "Squirrel", "https://acnhapi.com/v1/icons/villagers/362");
        villager4 = new Villager("Marshal", "372", "29/9", "Smug", "Squirrel", "https://acnhapi.com/v1/icons/villagers/372");
        villager5 = new Villager("Lobo", "382", "5/11", "Cranky", "Wolf", "https://acnhapi.com/v1/icons/villagers/382");
        villager6 = new Villager("Bob", "42", "1/1", "Lazy", "Cat", "https://acnhapi.com/v1/icons/villagers/42");
        villager7 = new Villager("Ankha", "61", "29/9", "Snooty", "Cat", "https://acnhapi.com/v1/icons/villagers/61");
        villager8 = new Villager("Sherb", "180", "18/1", "Lazy", "Goat", "https://acnhapi.com/v1/icons/villagers/180");

    }

    public String getUserId(){
        return userId;
    }

    public Villager getVillager1() {
        return villager1;
    }

    public Villager getVillager2() {
        return villager2;
    }

    public Villager getVillager3() {
        return villager3;
    }

    public Villager getVillager4() {
        return villager4;
    }

    public Villager getVillager5() {
        return villager5;
    }

    public Villager getVillager6() {
        return villager6;
    }

    public Villager getVillager7() {
        return villager7;
    }

    public Villager getVillager8() {
        return villager8;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setVillager1(Villager villager1) {
        this.villager1 = villager1;
    }

    public void setVillager2(Villager villager2) {
        this.villager2 = villager2;
    }

    public void setVillager3(Villager villager3) {
        this.villager3 = villager3;
    }

    public void setVillager4(Villager villager4) {
        this.villager4 = villager4;
    }

    public void setVillager5(Villager villager5) {
        this.villager5 = villager5;
    }

    public void setVillager6(Villager villager6) {
        this.villager6 = villager6;
    }

    public void setVillager7(Villager villager7) {
        this.villager7 = villager7;
    }

    public void setVillager8(Villager villager8) {
        this.villager8 = villager8;
    }

}
