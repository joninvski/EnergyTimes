package com.pifactorial.energytimes.domain;

public class Hours {

    public int _startHour;
    public int _endHour;
    public int _startMinute;
    public int _endMinute;

    public Hours(int startHour, int startMinute, int endHour, int endMinute){
        _startHour = startHour;
        _endHour = endHour;

        _startMinute = startMinute;
        _endMinute = endMinute;
    }

    public boolean matches(int hour, int minute){
        if (_startHour <= hour && _endHour <= hour)
            if(_startMinute <= minute && _endMinute <= minute)
                return true;
        return false;
    }
}
