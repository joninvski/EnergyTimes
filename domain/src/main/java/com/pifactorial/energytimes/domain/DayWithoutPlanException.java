package com.pifactorial.energytimes.domain;

public class DayWithoutPlanException extends Exception {

    private static final long serialVersionUID = 1L;

    public DayWithoutPlanException(String msg) {
        super(msg);
    }
}
