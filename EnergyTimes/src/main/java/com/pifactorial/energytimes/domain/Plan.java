package com.pifactorial.energytimes.domain;

import java.util.HashSet;
import java.util.Set;

import android.text.format.Time;
import android.util.Log;

import com.pifactorial.energytimes.Constants;

public class Plan {
    private String _name;
    private Set<Schedule> _scheduleSet;

    public Plan(String name) {
        this(name, new HashSet<Schedule>());
    }

    public Plan(String name, Set<Schedule> scheduleSet) {
        this._name = name;
        _scheduleSet = scheduleSet;
    }

    public String getName() {
        return _name;
    }

    public synchronized void addSchedule(Schedule s){
        _scheduleSet.add(s);
    }

    public synchronized void addSchedule(Schedule[] sArray){
        for (Schedule s : sArray)
        	addSchedule(s);
    }
    
    public Schedule checkTime(Time t) throws DayWithoutPlanException {
         Log.d(Constants.LOG, String.format("Checking time: %d", _scheduleSet.size()));

         for(Schedule s : _scheduleSet){
             if(s.matches(t))
                return s;
        }

         Log.d(Constants.LOG, "It did not match");
         throw new DayWithoutPlanException(this.toString());
    }

    public String toString() {
        return _name;
    }
}
