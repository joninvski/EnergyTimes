package com.pifactorial.energytimes.domain;

import android.text.format.Time;

import com.pifactorial.energytimes.domain.MyDate;
import java.util.Calendar;

public class Populate {
    public Company edp;

    public Populate() {
        edp = new Company("PT");

        Plan btnCicloSemanal = new Plan("BTN Ciclo Semanal");
        Plan btnCicloDiario  = new Plan("BTN Ciclo Diario");

        MyDate firstDayYear = new MyDate(1, MyDate.Month.JAN);
        MyDate lastDayWinter = new MyDate(27, MyDate.Month.FEV);

        MyDate firstDaySummer = new MyDate(1, MyDate.Month.MAR);
        MyDate lastDaySummer = new MyDate(31, MyDate.Month.OCT);

        MyDate firstDayWinterSecondPart = new MyDate(1, MyDate.Month.NOV);

        MyDate lastDayYear = new MyDate(31, MyDate.Month.DEZ);

        Schedule winterFirstPart = new Schedule(firstDayYear, lastDayWinter);
        Schedule summerTime = new Schedule(firstDaySummer, lastDaySummer);
        Schedule winterSecondPart = new Schedule(firstDayWinterSecondPart, lastDayYear);

        btnCicloDiario.addSchedule(summerTime);
        btnCicloDiario.addSchedule(winterFirstPart);
        btnCicloDiario.addSchedule(winterSecondPart);

        btnCicloSemanal.addSchedule(summerTime);
        btnCicloSemanal.addSchedule(winterFirstPart);
        btnCicloSemanal.addSchedule(winterSecondPart);

        edp.addPlan(btnCicloSemanal);
        edp.addPlan(btnCicloDiario);
    }

    public Schedule checkTime(Time t, Plan p) throws DayWithoutPlanException {
        return p.checkTime(t);
    }

}
