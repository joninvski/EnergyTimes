package com.pifactorial.energytimes.domain;

public class MyHours{

    private int _start_hour;
    private int _start_minute;

    private int _end_hour;
    private int _end_minute;

    public MyHours(int start_hour, int start_minute,  int end_hour, int end_minute) {
        _start_hour = start_hour;
        _start_minute = start_minute;

        _end_hour = end_hour;
        _end_minute = end_minute;
    }

    public boolean isInInterval(int hour, int minute) {
        // TODO - 
        return false;
    }
}

