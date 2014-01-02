package com.pifactorial.energytimes.domain;

import java.util.HashSet;
import java.util.Set;

import android.text.format.Time;

public enum TypeDay {

        Weekday(0), Saturday(1), Sunday(2), Holiday(3);

        private int code;

        private TypeDay(int c) {
            code = c;
        }

        public int getTypeDay() {
            return code;
        }

        public static Set<TypeDay> SundayAndHoliday(){
            Set<TypeDay> s = new HashSet<TypeDay>();

            s.add(Sunday);
            s.add(Holiday);
            return s;
        }

        public static Set<TypeDay> Weekday(){
            Set<TypeDay> s = new HashSet<TypeDay>();

            s.add(Weekday);
            return s;
        }

        public static Set<TypeDay> Saturday(){
            Set<TypeDay> s = new HashSet<TypeDay>();

            s.add(Saturday);
            return s;
        }

        public static Set<TypeDay> All(){
            Set<TypeDay> s = new HashSet<TypeDay>();

            s.add(Weekday);
            s.add(Saturday);
            s.add(Sunday);
            s.add(Holiday);

            return s;
        }

        public static Boolean MatchTypeDay(Time t, Set<TypeDay> s) {

        	// Holidays have priority over the other type days
        	if(isPortugueseHoliday(t))
                return true;
        	
        	if(t.weekDay == 0 && s.contains(Sunday))
                return true;

            if(t.weekDay >=1 && t.weekDay <= 5 && s.contains(Weekday))
                return true;

            if(t.weekDay == 6 && s.contains(Saturday))
                return true;

            else
                return false;
        }
        
        public static Boolean isPortugueseHoliday(Time t) {
        	int m = t.month;
        	int d = t.monthDay;
        	
        	// 1 January - New Year
        	if(m == 0 && d == 1)
        		return true;

        	// 25 Abril - Freedom day
        	else if(m == 3 && d == 25)
        		return true;
        	
        	// 1 May - Labour day
        	else if(m == 4 && d == 1)
        		return true;

        	// 10 June - National day
        	else if(m == 5 && d == 10)
        		return true;
        	
        	// 15 August - Assumption Day
        	else if(m == 7 && d == 15)
        		return true;
        	
        	// 5 October - Republic day
        	else if(m == 9 && d == 5)
        		return true;

        	// No longer a holiday - 1 November - All saints
        	//else if(m == 10 && d == 1)
        	//	return true;

        	// No longer a holiday - 1 December - Independence
        	//else if(m == 11 && d == 1)
        	//	return true;

        	// 8 December - IMMACULATE_CONCEPTION
        	else if(m == 11 && d == 8)
        		return true;

        	// 25 December - Christmas
        	else if(m == 11 && d == 25)
        		return true;

        	// EASTER - This changes year - Update code when year changes or do algorithm
        	else if(m == 3 && d == 20)
        		return true;
    		
        	// GOOD Friday - This changes year - Update code when year changes or do algorithm
        	else if(m == 3 && d == 18)
        		return true;
        	
        	// GOOD Friday - This changes year - Update code when year changes or do algorithm
        	else if(m == 0 && d == 1)
        		return true;
        	
        	return false;
        }
}
