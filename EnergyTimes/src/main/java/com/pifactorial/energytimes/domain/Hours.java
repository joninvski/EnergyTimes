package com.pifactorial.energytimes.domain;

public class Hours {

    public int getStartHour() {
		return _startHour;
	}

	public int getEndHour() {
		return _endHour;
	}

	public int getStartMinute() {
		return _startMinute;
	}

	public int getEndMinute() {
		return _endMinute;
	}

	private int _startHour;
    private int _endHour;
    private int _startMinute;
    private int _endMinute;

    public Hours(int startHour, int startMinute, int endHour, int endMinute){
        _startHour = startHour;
        _endHour = endHour;

        _startMinute = startMinute;
        _endMinute = endMinute;
    }

    public boolean matches(int hour, int minute){
        if (_startHour <= hour && _endHour >= hour){
            if(_startHour == hour && _startMinute > minute)
                return false;
            if(_endHour == hour && _endMinute < minute)
                return false;
            return true;
        }

        return false;
    }

	public static Hours Allday() {
        return new Hours(0,0, 23, 59);
	}

    public String toString(){
        return String.format("%d %d --> %d %d", _startHour, _startMinute, _endHour, _endMinute);
    }
}

