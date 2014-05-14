package com.pifactorial.energytimes.domain;

import java.util.HashSet;
import java.util.Set;
import org.joda.time.LocalTime;
import org.joda.time.LocalDate;

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

    public synchronized void addPeriod(Period s) {
        _periodSet.add(s);
    }

    public synchronized void addPeriod(Period[] sArray) {
        for (Period s : sArray)
            addPeriod(s);
    }

    public Period searchPeriod(LocalTime hour, LocalDate date, boolean biHour) throws DayWithoutPlanException {
        try {
            Period start = searchPeriodTriHour(hour, date);
            Period end = getNextPeriodDifferentPrice(start, date, biHour);
            Period merged = Period.getMergedPeriod(start, end);
            return merged;
        }

        catch (DayWithoutPlanException e) {
            throw e;
        }
    }

    public Period getNextPeriodDifferentPrice(Period p, LocalDate date, boolean biHour) {

        LocalTime followingInstant = p.getMinuteAfterThisPeriod();

        try {
            Period end = searchPeriodTriHour(followingInstant, date);

            // Now let's check if the period is for a different price
            if (p.getPrice().equals(end.getPrice(), biHour)) {
                //if it is the same we have to look for the next time instant
                return getNextPeriodDifferentPrice(end, date, biHour);
            }

            // If the price plan has changed, this period is the end
            return p;
        }

        catch (DayWithoutPlanException e) {
            // If the following period was not found it ends in the period passed as argument
            return p;
        }
    }

    public Period searchPeriodTriHour(LocalTime hour, LocalDate date) throws DayWithoutPlanException {
        for(Period p : _periodSet) {
            if(p.matches(hour, date)) {
                return p;
            }
        }

        throw new DayWithoutPlanException(this.toString());
    }

    public String toString() {
        return _name;
    }
}
