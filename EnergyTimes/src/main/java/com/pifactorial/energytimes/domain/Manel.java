package com.pifactorial.energytimes.domain;

import java.util.HashSet;
import java.util.Set;

import android.text.format.Time;

public class Manel {

    private String _name;
    private Set<Plan> _planSet;

    public String getName() {
        return _name;
    }

    public Manel(String name) {
        _name = name;
        _planSet= new HashSet<Plan>();
    }

    public Manel(String name, Set<Plan> planSet) {
        this._name = name;
        _planSet= planSet;
    }

    /**
     *
     */
    public Manel() {
    }

    public synchronized void addPlan(Plan p){
        _planSet.add(p);
    }

    public void addPlan(Plan[] plans) {
        for (Plan p : plans)
            addPlan(p);
    }

    public Set<Plan> getPlanSet(){
        return _planSet;
    }

    public String toString(){
        String r = this._name;
        for (Plan p : _planSet)
            r = r.concat(String.format("\t %s", p.toString()));
        return r;
    }

    public Schedule checkCurrentSchedule(Time time, String planName) throws DayWithoutPlanException, PlanNotFoundException {
        for(Plan p : _planSet){
            if(p.getName().equals(planName))
                return p.checkTime(time);
        }
        throw new PlanNotFoundException(String.format("Plan %s was not found", planName));
    }
}
