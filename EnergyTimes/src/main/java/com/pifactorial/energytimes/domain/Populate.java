package com.pifactorial.energytimes.domain;

import android.text.format.Time;

public class Populate {
    public Company edp;

    public Populate() {
        edp = new Company("EDP");

        Plan[] p = Edp.fillEdp();
        edp.addPlan(p);
    }

    public Schedule checkTime(Time t, Plan p) throws DayWithoutPlanException {
        return p.checkTime(t);
    }
}
