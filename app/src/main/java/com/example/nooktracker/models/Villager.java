package com.example.nooktracker.models;

public class Villager {
    private String name;
    private String id;
    private String birthday;
    private String personality;
    private String species;
    private String imageUrl;

    public Villager(){}

    public Villager(String name, String id, String birthday, String personality, String species, String imageUrl) {
        this.name = name;
        this.id = id;
        this.birthday = birthday;
        this.personality = personality;
        this.species = species;
        this.imageUrl = imageUrl;
    }
    public String getName() {
        return this.name;
    }
    public String getId() {
        return this.id;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public String getPersonality() {
        return personality;
    }
    public String getSpecies() {
        return species;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
