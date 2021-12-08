package com.example.nooktracker.models;

public class giTasks {
    public String userId;

    public Boolean villagerCheckbox1;
    public Boolean villagerCheckbox2;
    public Boolean villagerCheckbox3;
    public Boolean villagerCheckbox4;
    public Boolean villagerCheckbox5;
    public Boolean villagerCheckbox6;
    public Boolean villagerCheckbox7;
    public Boolean villagerCheckbox8;

    public Boolean shoppingCheckbox1;
    public Boolean shoppingCheckbox2;

    public Boolean rocksCheckbox1;
    public Boolean rocksCheckbox2;
    public Boolean rocksCheckbox3;
    public Boolean rocksCheckbox4;
    public Boolean rocksCheckbox5;
    public Boolean rocksCheckbox6;

    public Boolean recipeCheckbox1;
    public Boolean recipeCheckbox2;

    public Boolean moneyTreeCheckbox1;

    public Boolean flowersCheckbox1;

    public Tasks(){}

    public Tasks(String userId){
        this.userId = userId;

        villagerCheckbox1 = false;
        villagerCheckbox2 = false;
        villagerCheckbox3 = false;
        villagerCheckbox4 = false;
        villagerCheckbox5 = false;
        villagerCheckbox6 = false;
        villagerCheckbox7 = false;
        villagerCheckbox8 = false;

        shoppingCheckbox1 = false;
        shoppingCheckbox2 = false;

        rocksCheckbox1 = false;
        rocksCheckbox2 = false;
        rocksCheckbox3 = false;
        rocksCheckbox4 = false;
        rocksCheckbox5 = false;
        rocksCheckbox6 = false;

        recipeCheckbox1 = false;
        recipeCheckbox2 = false;

        moneyTreeCheckbox1 = false;

        flowersCheckbox1 = false;
    }

    public String getUserId(){return userId;}

    public Boolean getVillagerCheckbox1(){return villagerCheckbox1;}

    public Boolean getVillagerCheckbox2(){return villagerCheckbox2;}

    public Boolean getVillagerCheckbox3(){return villagerCheckbox3;}

    public Boolean getVillagerCheckbox4(){return villagerCheckbox4;}

    public Boolean getVillagerCheckbox5(){return villagerCheckbox5;}

    public Boolean getVillagerCheckbox6(){return villagerCheckbox6;}

    public Boolean getVillagerCheckbox7(){return villagerCheckbox7;}

    public Boolean getVillagerCheckbox8(){return villagerCheckbox8;}

    public Boolean getShoppingCheckbox1(){return shoppingCheckbox1;}

    public Boolean getShoppingCheckbox2(){return shoppingCheckbox2;}

    public Boolean getRocksCheckbox1(){return rocksCheckbox1;}

    public Boolean getRocksCheckbox2(){return rocksCheckbox2;}

    public Boolean getRocksCheckbox3(){return rocksCheckbox3;}

    public Boolean getRocksCheckbox4(){return rocksCheckbox4;}

    public Boolean getRocksCheckbox5(){return rocksCheckbox5;}

    public Boolean getRocksCheckbox6(){return rocksCheckbox6;}

    public Boolean getRecipeCheckbox1(){return recipeCheckbox1;}

    public Boolean getRecipeCheckbox2(){return recipeCheckbox2;}

    public Boolean getMoneyTreeCheckbox1(){return moneyTreeCheckbox1;}

    public Boolean getFlowersCheckbox1(){return flowersCheckbox1;}

    //______________________________________________________________________________________________


    public void setVillagerCheckbox1(Boolean villagerCheckbox1) {
        this.villagerCheckbox1 = villagerCheckbox1;
    }

    public void setVillagerCheckbox2(Boolean villagerCheckbox2) {
        this.villagerCheckbox2 = villagerCheckbox2;
    }

    public void setVillagerCheckbox3(Boolean villagerCheckbox3) {
        this.villagerCheckbox3 = villagerCheckbox3;
    }

    public void setVillagerCheckbox4(Boolean villagerCheckbox4) {
        this.villagerCheckbox4 = villagerCheckbox4;
    }

    public void setVillagerCheckbox5(Boolean villagerCheckbox5) {
        this.villagerCheckbox5 = villagerCheckbox5;
    }

    public void setVillagerCheckbox6(Boolean villagerCheckbox6) {
        this.villagerCheckbox6 = villagerCheckbox6;
    }

    public void setVillagerCheckbox7(Boolean villagerCheckbox7) {
        this.villagerCheckbox7 = villagerCheckbox7;
    }

    public void setVillagerCheckbox8(Boolean villagerCheckbox8) {
        this.villagerCheckbox8 = villagerCheckbox8;
    }

    public void setShoppingCheckbox1(Boolean shoppingCheckbox1) {
        this.shoppingCheckbox1 = shoppingCheckbox1;
    }

    public void setShoppingCheckbox2(Boolean shoppingCheckbox2) {
        this.shoppingCheckbox2 = shoppingCheckbox2;
    }

    public void setRocksCheckbox1(Boolean rocksCheckbox1) {
        this.rocksCheckbox1 = rocksCheckbox1;
    }

    public void setRocksCheckbox2(Boolean rocksCheckbox2) {
        this.rocksCheckbox2 = rocksCheckbox2;
    }

    public void setRocksCheckbox3(Boolean rocksCheckbox3) {
        this.rocksCheckbox3 = rocksCheckbox3;
    }

    public void setRocksCheckbox4(Boolean rocksCheckbox4) {
        this.rocksCheckbox4 = rocksCheckbox4;
    }

    public void setRocksCheckbox5(Boolean rocksCheckbox5) {
        this.rocksCheckbox5 = rocksCheckbox5;
    }

    public void setRocksCheckbox6(Boolean rocksCheckbox6) {
        this.rocksCheckbox6 = rocksCheckbox6;
    }

    public void setRecipeCheckbox1(Boolean recipeCheckbox1) {
        this.recipeCheckbox1 = recipeCheckbox1;
    }

    public void setRecipeCheckbox2(Boolean recipeCheckbox2) {
        this.recipeCheckbox2 = recipeCheckbox2;
    }

    public void setMoneyTreeCheckbox1(Boolean moneyTreeCheckbox1) {
        this.moneyTreeCheckbox1 = moneyTreeCheckbox1;
    }

    public void setFlowersCheckbox1(Boolean flowersCheckbox1) {
        this.flowersCheckbox1 = flowersCheckbox1;
    }

    public void setAll(Boolean[] checkBoxes){
        this.villagerCheckbox1 = checkBoxes[0];
        this.villagerCheckbox2 = checkBoxes[1];
        this.villagerCheckbox3 = checkBoxes[2];
        this.villagerCheckbox4 = checkBoxes[3];
        this.villagerCheckbox5 = checkBoxes[4];
        this.villagerCheckbox6 = checkBoxes[5];
        this.villagerCheckbox7 = checkBoxes[6];
        this.villagerCheckbox8 = checkBoxes[7];
        this.shoppingCheckbox1 = checkBoxes[8];
        this.shoppingCheckbox2 = checkBoxes[9];
        this.rocksCheckbox1 = checkBoxes[10];
        this.rocksCheckbox2 = checkBoxes[11];
        this.rocksCheckbox3 = checkBoxes[12];
        this.rocksCheckbox4 = checkBoxes[13];
        this.rocksCheckbox5 = checkBoxes[14];
        this.rocksCheckbox6 = checkBoxes[15];
        this.recipeCheckbox1 = checkBoxes[16];
        this.recipeCheckbox2 = checkBoxes[17];
        this.moneyTreeCheckbox1 = checkBoxes[18];
        this.flowersCheckbox1 = checkBoxes[19];
    }
}
