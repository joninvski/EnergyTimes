package com.pifactorial.energytimes.domain;

import java.util.HashSet;
import java.util.Set;

import android.text.format.Time;
import android.util.Log;

public class Plan {
    private String _name;
    private Set<Schedule> _scheduleSet;

    public Plan(String name) {
        this._name = name;
        _scheduleSet = new HashSet<Schedule>();
    }

    public Plan(String name, Set<Schedule> scheduleSet) {
        this._name = name;
        _scheduleSet = scheduleSet;
    }

    public synchronized void addSchedule(Schedule s){
        _scheduleSet.add(s);
    }

    public Schedule checkTime(Time t) throws DayWithoutPlanException {
         Log.d("EnergyTimes", String.format("Checking time: %d", _scheduleSet.size()));

         for(Schedule s : _scheduleSet){
             if(s.matches(t))
                return s;
        }

         Log.d("EnergyTimes", "It did not match");
         throw new DayWithoutPlanException(this.toString());
    }

    public String toString() {
        return _name;
    }
}
