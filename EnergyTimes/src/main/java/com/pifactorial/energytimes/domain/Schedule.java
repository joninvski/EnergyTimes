package com.pifactorial.energytimes.domain;

import java.util.Set;

import android.text.format.Time;
import android.util.Log;

import com.pifactorial.energytimes.Constants;

public class Schedule {

    private MyDate _start;
    private MyDate _end;
    private Hours _hours;
    private Set<TypeDay> _validOnDaySet;
    private PricePlan _price;

    public Schedule(MyDate start, MyDate end, Hours hours, Set<TypeDay> typeDays, PricePlan price){
        _start = start;
        _end = end;
        _hours = hours;
        _validOnDaySet = typeDays;
        _price = price;
    }

    public PricePlan get_price() {
        return _price;
    }

    public void addTypeDay(TypeDay t){
        _validOnDaySet.add(t);
    }

    public boolean matches(Time t) {
        Log.d(Constants.LOG, String.format("Day:\t%d\t%d", t.monthDay, t.month));
        Log.d(Constants.LOG, String.format("Start\t%s\t\tEnd\t%s", _start.toString(), _end.toString()));

        // Start time
        Time s = new Time();
        s.set(0, _hours.getStartMinute(), _hours.getStartHour(), _start.day, _start.month, t.year);

        // End time
        Time e = new Time();
        e.set(0, _hours.getEndMinute(), _hours.getEndHour(), _end.day, _end.month, t.year);

        if(t.after(s) && t.before(e) && TypeDay.MatchTypeDay(t, _validOnDaySet)){
            if(_hours.matches(t.hour, t.minute)){
                Log.d("EnergyTimes", "Matched");
                return true;
            }
        }

        Log.d("EnergyTimes", "Not matched");
        return false;
    }

    public String toString(){
        String daysString = "";
        for(TypeDay d : _validOnDaySet)
            daysString = daysString.concat(d.toString());

        return String.format("Start:\t%s\tEnd:\t%s\n %s\n hours: %s\nDays: %s", _start.toString(), _end.toString(), _price.toString(), _hours.toString(), daysString);
    }

    public static Schedule[] getWinterSchedule(Hours hours, Set<TypeDay> typeDays, PricePlan price) {

        MyDate firstDayYear = new MyDate(1, MyDate.Month.JAN);
        MyDate lastDayWinter = new MyDate(27, MyDate.Month.FEV);

        MyDate firstDayWinterSecondPart = new MyDate(1, MyDate.Month.NOV);
        MyDate lastDayYear = new MyDate(31, MyDate.Month.DEC);

        Schedule winterPart1 = new Schedule(firstDayYear, lastDayWinter, hours, typeDays, price);
        Schedule winterPart2 = new Schedule( firstDayWinterSecondPart, lastDayYear, hours, typeDays, price);

        return new Schedule[] {winterPart1, winterPart2};
    }

    public static Schedule[] getSummerSchedule(Hours hours, Set<TypeDay> typeDays, PricePlan price) {

        MyDate firstDaySummer = new MyDate(1, MyDate.Month.MAR);
        MyDate lastDaySummer = new MyDate(31, MyDate.Month.OCT);

        Schedule summer = new Schedule(firstDaySummer, lastDaySummer, hours, typeDays, price);
        return new Schedule[] {summer};
    }

    public static Schedule[] getAllYear(Hours hours, Set<TypeDay> typeDays, PricePlan price) {

        MyDate firstDay = new MyDate(1, MyDate.Month.JAN);
        MyDate lastDay = new MyDate(31, MyDate.Month.DEC);

        Schedule allYear = new Schedule(firstDay, lastDay, hours, typeDays, price);
        return new Schedule[] {allYear};
    }
}
