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
            if(t.weekDay == 0 && s.contains(Sunday))
                return true;

            if(t.weekDay >=1 && t.weekDay <= 5 && s.contains(Weekday))
                return true;

            if(t.weekDay == 6 && s.contains(Saturday))
                return true;

            // Still missing holidays

            else
                return false;
        }
}
