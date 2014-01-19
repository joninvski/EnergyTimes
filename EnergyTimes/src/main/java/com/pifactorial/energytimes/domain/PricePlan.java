package com.pifactorial.energytimes.domain;

public enum PricePlan {
    PONTA("Ponta"), CHEIAS("Cheias"), VAZIO("Vazio"), SUPER_VAZIO("Super Vazio");

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

}
