package com.pifactorial.energytimes.domain;

import java.util.HashSet;
import java.util.Set;

import android.text.format.Time;
import android.util.Log;

import com.pifactorial.energytimes.Constants;

public class Plan {
    private String _name;
    private Set<Period> _periodSet;

    public Plan(String name) {
        this(name, new HashSet<Period>());
    }

    public Plan(String name, Set<Period> PeriodSet) {
        this._name = name;
        _periodSet = PeriodSet;
    }

    public String getName() {
        return _name;
    }

    public synchronized void addPeriod(Period s){
        _periodSet.add(s);
    }

    public synchronized void addPeriod(Period[] sArray){
        for (Period s : sArray)
            addPeriod(s);
    }

    public Period searchPeriod(Time t, boolean biHour) throws DayWithoutPlanException {
        for(Period s : _periodSet){
            if(s.matches(t))
                return s;
        }

        Log.w(Constants.LOG, "Now plan was found for the selected Time");
        throw new DayWithoutPlanException(this.toString());
    }

    public String toString() {
        return _name;
    }
}
