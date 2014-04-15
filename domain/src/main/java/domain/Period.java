package com.pifactorial.energytimes.domain;

import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTime;


public class Period {

    private LocalDate _start;
    private LocalDate _end;
    private LocalTimeInterval _hours;
    private Set<TypeDay> _validOnDaySet;
    private PricePlan _price;

    public Period(LocalDate start, LocalDate end, LocalTimeInterval hours, Set<TypeDay> typeDays, PricePlan price) {
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

    public boolean matches(DateTime now) {
        LocalTime nowHours = new LocalTime( now.getHourOfDay(), now.getMinuteOfHour());
        LocalDate nowDayMonth = new LocalDate(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth());
        return matches(nowDayMonth, nowHours);
    }

    public boolean matches(LocalDate daysMonth, LocalTime hours) {
        if(daysMonth.isAfter(_start) && daysMonth.isBefore(_end) && TypeDay.MatchTypeDay(daysMonth, _validOnDaySet)) {
            if(_hours.overlapsWith(hours)) {
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

    protected static Period getMergedPeriod(Period start, Period end) {
        if(start.equals(end)) {
            return start;
        }

        LocalTimeInterval mergedHours = LocalTimeInterval.getMergedTimeInterval(start._hours, end._hours);
        return new Period(start._start, end._end, mergedHours, start._validOnDaySet, start._price);
    }

    protected DateTime getInstantAfterThisPeriod() {
        LocalTime nextHour = this._hours.getLocalTimeAfterEnd();

        DateTime dateTime = new DateTime();
        dateTime = dateTime.withFields(nextHour);

        return dateTime;
    }


    public static Period[] getWinterPeriod(int startH,  int startM, int endH, int endM, Set<TypeDay> typeDays, PricePlan price, int year) {
        return getWinterPeriod(new LocalTimeInterval(startH, startM, endH, endM), typeDays, price, year);
    }

    public static Period[] getWinterPeriod(LocalTimeInterval hours, Set<TypeDay> typeDays, PricePlan price, int year) {

        LocalDate firstDayYear = new LocalDate(year, DateTimeConstants.JANUARY, 1);
        LocalDate lastDayWinter = new LocalDate(year, DateTimeConstants.MARCH, 29);

        LocalDate firstDayWinterSecondPart = new LocalDate(year, DateTimeConstants.OCTOBER, 26);
        LocalDate lastDayYear = new LocalDate(year, DateTimeConstants.DECEMBER, 31);

        Period winterPart1 = new Period(firstDayYear, lastDayWinter, hours, typeDays, price);
        Period winterPart2 = new Period(firstDayWinterSecondPart, lastDayYear, hours, typeDays, price);

        return new Period[] {winterPart1, winterPart2};
    }

    public static Period[] getSummerPeriod(int startH,  int startM, int endH, int endM, Set<TypeDay> typeDays, PricePlan price, int year) {
        return getSummerPeriod(new LocalTimeInterval(startH, startM, endH, endM), typeDays, price, year);
    }

    public static Period[] getSummerPeriod(LocalTimeInterval hours, Set<TypeDay> typeDays, PricePlan price, int year) {

        LocalDate firstDaySummer = new LocalDate(year, DateTimeConstants.MARCH, 30);
        LocalDate lastDaySummer = new LocalDate(year, DateTimeConstants.OCTOBER, 25);

        Period summer = new Period(firstDaySummer, lastDaySummer, hours, typeDays, price);
        return new Period[] {summer};
    }

    public static Period[] getAllYear(int startH, int startM, int endH, int endM, Set<TypeDay> typeDays, PricePlan price, int year) {
        return getAllYear(new LocalTimeInterval(startH, startM, endH, endM), typeDays, price, year);
    }

    public static Period[] getAllYear(LocalTimeInterval hours, Set<TypeDay> typeDays, PricePlan price, int year) {

        LocalDate firstDay = new LocalDate(year, DateTimeConstants.JANUARY, 1);
        LocalDate lastDay = new LocalDate(year, DateTimeConstants.DECEMBER, 31);

        Period allYear = new Period(firstDay, lastDay, hours, typeDays, price);
        return new Period[] {allYear};
    }
}
