package com.pifactorial.energytimes.domain;

import android.text.format.Time;

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

        MyHours morning = new MyHours(0,0, 9,0);

        Schedule winterFirstPart = new Schedule(firstDayYear, lastDayWinter, morning);
        Schedule summerTime = new Schedule(firstDaySummer, lastDaySummer,morning);
        Schedule winterSecondPart = new Schedule(firstDayWinterSecondPart, lastDayYear, morning);

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
