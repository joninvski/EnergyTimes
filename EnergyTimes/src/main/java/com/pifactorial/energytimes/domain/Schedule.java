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

    public PricePlan getPrice() {
        return _price;
    }

    public Hours getHours() {
		return _hours;
	}


    public void addTypeDay(TypeDay t){
        _validOnDaySet.add(t);
    }

    public boolean matches(Time t) {

        // Start time
        Time s = new Time();
        s.set(0, _hours.getStartMinute(), _hours.getStartHour(), _start.day, _start.month, t.year);

        // End time
        Time e = new Time();
        e.set(0, _hours.getEndMinute(), _hours.getEndHour(), _end.day, _end.month, t.year);

        if(t.after(s) && t.before(e) && TypeDay.MatchTypeDay(t, _validOnDaySet)){
            if(_hours.matches(t.hour, t.minute)){
                return true;
            }
        }

        return false;
    }

    public String toString(){
        String daysString = "";
        for(TypeDay d : _validOnDaySet)
            daysString = daysString.concat(d.toString());

        return String.format("Start:\t%s\tEnd:\t%s\n %s\n hours: %s\nDays: %s", _start.toString(),
                _end.toString(), _price.toString(), _hours.toString(), daysString);
    }


    public static Schedule[] getWinterSchedule(int startH,  int startM, int endH, int endM, Set<TypeDay> typeDays, PricePlan price) {
        return getWinterSchedule(new Hours(startH, startM, endH, endM), typeDays, price);
    }

    public static Schedule[] getWinterSchedule(Hours hours, Set<TypeDay> typeDays, PricePlan price) {

        MyDate firstDayYear = new MyDate(1, MyDate.Month.JAN);
        MyDate lastDayWinter = new MyDate(29, MyDate.Month.MAR);

        MyDate firstDayWinterSecondPart = new MyDate(26, MyDate.Month.OCT);
        MyDate lastDayYear = new MyDate(31, MyDate.Month.DEC);

        Schedule winterPart1 = new Schedule(firstDayYear, lastDayWinter, hours, typeDays, price);
        Schedule winterPart2 = new Schedule( firstDayWinterSecondPart, lastDayYear, hours, typeDays, price);

        return new Schedule[] {winterPart1, winterPart2};
    }

    public static Schedule[] getSummerSchedule(int startH,  int startM, int endH, int endM, Set<TypeDay> typeDays, PricePlan price) {
        return getSummerSchedule(new Hours(startH, startM, endH, endM), typeDays, price);
    }

    public static Schedule[] getSummerSchedule(Hours hours, Set<TypeDay> typeDays, PricePlan price) {

        MyDate firstDaySummer = new MyDate(30, MyDate.Month.MAR);
        MyDate lastDaySummer = new MyDate(25, MyDate.Month.OCT);

        Schedule summer = new Schedule(firstDaySummer, lastDaySummer, hours, typeDays, price);
        return new Schedule[] {summer};
    }

    public static Schedule[] getAllYear(int startH, int startM, int endH, int endM, Set<TypeDay> typeDays, PricePlan price) {
        return getAllYear(new Hours(startH, startM, endH, endM), typeDays, price);
    }

    public static Schedule[] getAllYear(Hours hours, Set<TypeDay> typeDays, PricePlan price) {

        MyDate firstDay = new MyDate(1, MyDate.Month.JAN);
        MyDate lastDay = new MyDate(31, MyDate.Month.DEC);

        Schedule allYear = new Schedule(firstDay, lastDay, hours, typeDays, price);
        return new Schedule[] {allYear};
    }
}
