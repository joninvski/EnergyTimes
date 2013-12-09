package com.pifactorial.energytimes.domain;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import android.text.format.Time;
import android.util.Log;

public class Schedule {
        private MyDate _start;
        private MyDate _end;
        private Set<TypeDay> _validOnDaySet;

        public Schedule(MyDate start, MyDate end){
            _start = start;
            _end = end;
            _validOnDaySet = new HashSet<TypeDay>();
        }

        public void addTypeDay(TypeDay t){
            _validOnDaySet.add(t);
        }

        public boolean matches(Time t) {
            Log.d("EnergyTimes", String.format("Checking\nDay:\t%d\t%d", t.monthDay, t.month));
            Log.d("EnergyTimes", String.format("Start\t%s", _start.toString()));
            Log.d("EnergyTimes", String.format("End\t%s", _end.toString()));

            // Start time
            Time s = new Time();
            s.set(_start.day, _start.month, t.year);

            // End time
            Time e = new Time();
            e.set(_end.day, _end.month, t.year);

            if(t.after(s) && t.before(e)){
                Log.d("EnergyTimes", "Matched");
                return true;
            }

            Log.d("EnergyTimes", "Not matched");
            return false;
        }

        public String toString(){
            return String.format("Start:\t%s\tEnd:\t%s", _start.toString(), _end.toString());
        }
}
