package com.pifactorial.energytimes.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.DateTimeConstants;

public enum TypeDay {

    Workday(0), Saturday(1), Sunday(2), Holiday(3);

    private int code;

    private TypeDay(int c) {
        code = c;
    }

    public int getTypeDay() {
        return code;
    }

    public static Set<TypeDay> SundayAndHoliday() {
        Set<TypeDay> s = new LinkedHashSet<TypeDay>();

        s.add(Sunday);
        s.add(Holiday);
        return s;
    }

    public static Set<TypeDay> Weekday() {
        Set<TypeDay> s = new LinkedHashSet<TypeDay>();

        s.add(Workday);
        return s;
    }

    public static Set<TypeDay> Saturday() {
        Set<TypeDay> s = new LinkedHashSet<TypeDay>();

        s.add(Saturday);
        return s;
    }

    public static Set<TypeDay> All() {
        Set<TypeDay> s = new LinkedHashSet<TypeDay>();

        s.add(Workday);
        s.add(Saturday);
        s.add(Sunday);
        s.add(Holiday);

        return s;
    }

    public static boolean CommonDay(Set<TypeDay> a, Set<TypeDay> b) {
        for(TypeDay i : a) {
            if(b.contains(i))
                return true;
        }
        return false;
    }

    public static Boolean MatchTypeDay(LocalDate t, Set<TypeDay> s) {

        // Holidays have priority over the other type days
        if(isPortugueseHoliday(t)) {
            if(s.contains(Holiday))
                return true;
            else {
                return false;
            }
        }

        // If it is sunday
        if(t.getDayOfWeek() == DateTimeConstants.SUNDAY && s.contains(Sunday))
            return true;

        if(t.getDayOfWeek() >= DateTimeConstants.MONDAY && t.getDayOfWeek() <= DateTimeConstants.FRIDAY && s.contains(Workday))
            return true;

        if(t.getDayOfWeek() == DateTimeConstants.SATURDAY && s.contains(Saturday))
            return true;

        else
            return false;
    }

    public static Boolean isPortugueseHoliday(LocalDate t) {
        int m = t.getMonthOfYear();
        int d = t.getDayOfMonth();

        // 1 January - New Year
        if(m == DateTimeConstants.JANUARY && d == 1)
            return true;

        // 25 Abril - Freedom day
        else if(m == DateTimeConstants.APRIL && d == 25)
            return true;

        // 1 May - Labour day
        else if(m == DateTimeConstants.MAY && d == 1)
            return true;

        // 10 June - National day
        else if(m == DateTimeConstants.JUNE && d == 10)
            return true;

        // 15 August - Assumption Day
        else if(m == DateTimeConstants.AUGUST && d == 15)
            return true;

        // No longer a holiday - 5 October - Republic day
        //else if(m == 9 && d == 5)
        //	return true;

        // No longer a holiday - 1 November - All saints
        //else if(m == 10 && d == 1)
        //	return true;

        // No longer a holiday - 1 December - Independence
        //else if(m == 11 && d == 1)
        //	return true;

        // 8 December - IMMACULATE_CONCEPTION
        else if(m == DateTimeConstants.DECEMBER && d == 8)
            return true;

        // 25 December - Christmas
        else if(m == DateTimeConstants.DECEMBER && d == 25)
            return true;

        // EASTER - This changes year - Update code when year changes or do algorithm
        else if(m == DateTimeConstants.APRIL && d == 20)
            return true;

        // GOOD Friday - This changes year - Update code when year changes or do algorithm
        else if(m == DateTimeConstants.APRIL && d == 18)
            return true;

        return false;
    }
}
