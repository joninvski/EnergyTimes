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
        try {
            Period start = searchPeriodTriHour(t);
            Period end = searchEndPeriod(start, biHour);
            Period merged = Period.getMergedPeriod(start, end);
            return merged;
        }

        catch (DayWithoutPlanException e) {
            Log.w(Constants.LOG, "No plan was found for the selected time: " + t.toString());
            throw e;
        }
    }

    public Period searchEndPeriod(Period p, boolean biHour) {

        Time followingInstant = p.getInstantAfterThisPeriod();

        try {
            Period end = searchPeriodTriHour(followingInstant);

            // Now let's check if the period is for a different price
            if (p.getPrice().equals(end.getPrice(), biHour)) {
                //if it is the same we have to look for the next time instant
                return searchEndPeriod(end, biHour);
            }

            // If the price plan has changed, this period is the end
            return end;
        }

        catch (DayWithoutPlanException e) {
            // If the following period was not found it ends in the period passed as argument
            return p;
        }
    }

    public Period searchPeriodTriHour(Time t) throws DayWithoutPlanException {
        for(Period p : _periodSet) {
            if(p.matches(t)) {
                return p;
            }
        }

        throw new DayWithoutPlanException(this.toString());
    }

    public String toString() {
        return _name;
    }
}
