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

        MyHours morning = new MyHours(0, 0, 7, 59);
        MyHours day = new MyHours(8, 0, 21,59);
        MyHours night = new MyHours(22,0, 23,59);

        Schedule winterFirstPart_morning = new Schedule(firstDayYear, lastDayWinter, morning);
        Schedule summerTime_morning = new Schedule(firstDaySummer, lastDaySummer,morning);
        Schedule winterSecondPart_morning = new Schedule(firstDayWinterSecondPart, lastDayYear, morning);

        Schedule winterFirstPart_day = new Schedule(firstDayYear, lastDayWinter, day);
        Schedule summerTime_day = new Schedule(firstDaySummer, lastDaySummer, day);
        Schedule winterSecondPart_day = new Schedule(firstDayWinterSecondPart, lastDayYear, day);

        btnCicloDiario.addSchedule(summerTime_morning);
        btnCicloDiario.addSchedule(winterFirstPart_morning);
        btnCicloDiario.addSchedule(winterSecondPart_morning);

        btnCicloSemanal.addSchedule(summerTime_morning);
        btnCicloSemanal.addSchedule(winterFirstPart_morning);
        btnCicloSemanal.addSchedule(winterSecondPart_morning);

        edp.addPlan(btnCicloSemanal);
        edp.addPlan(btnCicloDiario);
    }

    public Schedule checkTime(Time t, Plan p) throws DayWithoutPlanException {
        return p.checkTime(t);
    }
}
