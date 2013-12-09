package com.pifactorial.energytimes.domain;

public class MyDate {

    public enum Month {
        JAN(0), FEV(1), MAR(2), APR(3), MAY(4), JUN(5),
        JUL(6), AUG(7), SEP(8), OCT(9), NOV(10), DEZ(11);

        private int code;

        private Month(int c) {
            code = c;
        }

        public int getMonth() {
            return code;
        }
    }

    public int day;
    public int month;

    public MyDate(int day, Month m){
        this.day = day;
        this.month = m.getMonth();
    }

    public String toString() {
        return String.valueOf(day) + " " + String.valueOf(month);
    }
}
