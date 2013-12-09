package com.pifactorial.energytimes.domain;

import java.util.HashSet;
import java.util.Set;

public class Company {
    private String _name;
    private Set<Plan> _planSet;

    public Company(String name) {
        this._name = name;
        _planSet= new HashSet<Plan>();
    }

    public Company(String name, Set<Plan> planSet) {
        this._name = name;
        _planSet= planSet;
    }

    public synchronized void addPlan(Plan p){
        _planSet.add(p);
    }

    public Set<Plan> getPlanSet(){
        return _planSet;
    }
}
