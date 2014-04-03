package com.pifactorial.energytimes.domain;

import android.text.format.Time;

import android.util.Log;

import com.pifactorial.energytimes.Constants;

import java.util.Set;

import org.joda.time.MonthDay;
import org.joda.time.DateTimeConstants;


public class Period {

    private MonthDay _start;
    private MonthDay _end;
    private LocalTimeInterval _hours;
    private Set<TypeDay> _validOnDaySet;
    private PricePlan _price;

    public Period(MonthDay start, MonthDay end, LocalTimeInterval hours, Set<TypeDay> typeDays, PricePlan price) {
        _start = start;
        _end = end;
        _hours = hours;
        _validOnDaySet = typeDays;
        _price = price;
    }

    public PricePlan getPrice() {
        return _price;
    }

    public LocalTimeInterval getHours() {
        return _hours;
    }


    public void addTypeDay(TypeDay t){
        _validOnDaySet.add(t);
    }

    public boolean matches(Time t) {

        // Start time
        Time s = new Time();
        s.set(0, _hours.getStartMinute(), _hours.getStartHour(), _start.getDayOfMonth(), _start.getMonthOfYear(), t.year);

        // End time
        Time e = new Time();
        e.set(0, _hours.getEndMinute(), _hours.getEndHour(), _end.getDayOfMonth(), _end.getMonthOfYear(), t.year);

        if(t.after(s) && t.before(e) && TypeDay.MatchTypeDay(t, _validOnDaySet)){
            if(_hours.overlapsWith(t.hour, t.minute)){
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


    public static Period[] getWinterPeriod(int startH,  int startM, int endH, int endM, Set<TypeDay> typeDays, PricePlan price) {
        return getWinterPeriod(new LocalTimeInterval(startH, startM, endH, endM), typeDays, price);
    }

    public static Period[] getWinterPeriod(LocalTimeInterval hours, Set<TypeDay> typeDays, PricePlan price) {

        MonthDay firstDayYear = new MonthDay(DateTimeConstants.JANUARY, 1);
        MonthDay lastDayWinter = new MonthDay(DateTimeConstants.MARCH, 29);

        MonthDay firstDayWinterSecondPart = new MonthDay(DateTimeConstants.OCTOBER, 26);
        MonthDay lastDayYear = new MonthDay(DateTimeConstants.DECEMBER, 31);

        Period winterPart1 = new Period(firstDayYear, lastDayWinter, hours, typeDays, price);
        Period winterPart2 = new Period( firstDayWinterSecondPart, lastDayYear, hours, typeDays, price);

        return new Period[] {winterPart1, winterPart2};
    }

    public static Period[] getSummerPeriod(int startH,  int startM, int endH, int endM, Set<TypeDay> typeDays, PricePlan price) {
        return getSummerPeriod(new LocalTimeInterval(startH, startM, endH, endM), typeDays, price);
    }

    public static Period[] getSummerPeriod(LocalTimeInterval hours, Set<TypeDay> typeDays, PricePlan price) {

        MonthDay firstDaySummer = new MonthDay(DateTimeConstants.MARCH, 30);
        MonthDay lastDaySummer = new MonthDay(DateTimeConstants.OCTOBER, 25);

        Period summer = new Period(firstDaySummer, lastDaySummer, hours, typeDays, price);
        return new Period[] {summer};
    }

    public static Period[] getAllYear(int startH, int startM, int endH, int endM, Set<TypeDay> typeDays, PricePlan price) {
        return getAllYear(new LocalTimeInterval(startH, startM, endH, endM), typeDays, price);
    }

    public static Period[] getAllYear(LocalTimeInterval hours, Set<TypeDay> typeDays, PricePlan price) {

        MonthDay firstDay = new MonthDay(DateTimeConstants.JANUARY, 1);
        MonthDay lastDay = new MonthDay(DateTimeConstants.DECEMBER, 31);

        Period allYear = new Period(firstDay, lastDay, hours, typeDays, price);
        return new Period[] {allYear};
    }
}
