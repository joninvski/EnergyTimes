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

    public boolean equals(PricePlan other, boolean biHour){

        if(biHour == false) {
            return this.equalsName(other.getPricePlan());
        }

        else {
            if (this.isVazio()) {
                return other.isVazio();
            }

            else if (other.isVazio()){
                return false;
            }

            // If we get here we are certain that both this and other are cheia or vazio
            else return true;
        }
    }
}
