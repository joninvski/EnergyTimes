package com.pifactorial.energytimes.domain;

public enum PricePlan {
    PONTA("Ponta"), CHEIA("Cheia"), VAZIO("Vazio"), SUPER_VAZIO("Super Vazio");

    private String name;

    private PricePlan(String name) {
        this.name = name;
    }

    public String getPricePlan() {
        return name;
    }

    public boolean equalsName(String otherName){
        return (otherName == null)? false:name.equals(otherName);
    }

    public boolean isVazio(){
        return name.equals(VAZIO.getPricePlan()) || name.equals(SUPER_VAZIO.getPricePlan());
    }

    public boolean isCheia(){
        return name.equals(CHEIA.getPricePlan());
    }

    public boolean isPonta(){
        return name.equals(PONTA.getPricePlan());
    }
}
