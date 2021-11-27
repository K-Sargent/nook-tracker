package com.example.nooktracker.models;

public class User {
    public String email;
    public String userId;
    public String islandName;
    public String characterName;

    public User(){}

    public User(String email, String userId, String islandName, String characterName){
        this.email = email;
        this.userId = userId;
        this.islandName = islandName;
        this.characterName = characterName;
    }

    public String getEmail(){return email;}

    public String getUserId(){ return userId; }

    public String getIslandName(){ return islandName; }

    public String getCharacterName(){ return characterName; }

    public void setEmail(String email){ this.email = email; }

    public void setUserId(String userId){ this.userId = userId; }

    public void setIslandName(String islandName){ this.islandName = islandName; }

    public void setCharacterName(String characterName){ this.characterName = characterName; }
}

