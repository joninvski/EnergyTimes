package com.pifactorial.energytimes.domain;

public class Endesa extends Company {
    public static final int YEAR = 2014;

    public Endesa(){
        super("Endesa");
    }

    public Plan[] getPlans() {
        Plan[] p = {cicloDiario(), cicloSemanal()};
        return p;
    }

    public static Plan cicloSemanal() {
        Plan cicloSemanal  = new Plan("BTN Ciclo Semanal");

        LocalTimeInterval morning = new LocalTimeInterval(0, 0, 6, 59);
        LocalTimeInterval day = new LocalTimeInterval(7, 0, 23,59);

        // Weekdays
        Period[] sWeekdayWinterMorning = Period.getWinterPeriod(morning, TypeDay.Weekday(), PricePlan.VAZIO, YEAR);
        Period[] sWeekdayWinterDay = Period.getWinterPeriod(day, TypeDay.Weekday(), PricePlan.CHEIA, YEAR);

        Period[] sWeekdaySummerMorning = Period.getSummerPeriod(morning, TypeDay.Weekday(), PricePlan.VAZIO, YEAR);
        Period[] sWeekdaySummerDay = Period.getSummerPeriod(day, TypeDay.Weekday(), PricePlan.CHEIA, YEAR);

        cicloSemanal.addPeriod(sWeekdayWinterMorning);
        cicloSemanal.addPeriod(sWeekdayWinterDay);
        cicloSemanal.addPeriod(sWeekdaySummerMorning);
        cicloSemanal.addPeriod(sWeekdaySummerDay);

        // Saturday
        LocalTimeInterval saturdayWinterA = new LocalTimeInterval(0, 0, 9, 29);
        LocalTimeInterval saturdayWinterB = new LocalTimeInterval(9, 30, 12, 59);
        LocalTimeInterval saturdayWinterC = new LocalTimeInterval(13, 0, 18, 29);
        LocalTimeInterval saturdayWinterD = new LocalTimeInterval(18, 30, 21, 59);
        LocalTimeInterval saturdayWinterE = new LocalTimeInterval(22, 0, 23, 59);

        Period[] sSaturdayWinterA = Period.getWinterPeriod(saturdayWinterA, TypeDay.Saturday(), PricePlan.VAZIO, YEAR);
        Period[] sSaturdayWinterB = Period.getWinterPeriod(saturdayWinterB, TypeDay.Saturday(), PricePlan.CHEIA, YEAR);
        Period[] sSaturdayWinterC = Period.getWinterPeriod(saturdayWinterC, TypeDay.Saturday(), PricePlan.VAZIO, YEAR);
        Period[] sSaturdayWinterD = Period.getWinterPeriod(saturdayWinterD, TypeDay.Saturday(), PricePlan.CHEIA, YEAR);
        Period[] sSaturdayWinterE = Period.getWinterPeriod(saturdayWinterE, TypeDay.Saturday(), PricePlan.VAZIO, YEAR);

        LocalTimeInterval saturdaySummerA = new LocalTimeInterval(0, 0, 8, 59);
        LocalTimeInterval saturdaySummerB = new LocalTimeInterval(9, 00, 13, 59);
        LocalTimeInterval saturdaySummerC = new LocalTimeInterval(14, 0, 19, 59);
        LocalTimeInterval saturdaySummerD = new LocalTimeInterval(20, 0, 21, 59);
        LocalTimeInterval saturdaySummerE = new LocalTimeInterval(22, 0, 23, 59);

        Period[] sSaturdaySummerA = Period.getSummerPeriod(saturdaySummerA, TypeDay.Saturday(), PricePlan.VAZIO, YEAR);
        Period[] sSaturdaySummerB = Period.getSummerPeriod(saturdaySummerB, TypeDay.Saturday(), PricePlan.CHEIA, YEAR);
        Period[] sSaturdaySummerC = Period.getSummerPeriod(saturdaySummerC, TypeDay.Saturday(), PricePlan.VAZIO, YEAR);
        Period[] sSaturdaySummerD = Period.getSummerPeriod(saturdaySummerD, TypeDay.Saturday(), PricePlan.CHEIA, YEAR);
        Period[] sSaturdaySummerE = Period.getSummerPeriod(saturdaySummerE, TypeDay.Saturday(), PricePlan.VAZIO, YEAR);

        cicloSemanal.addPeriod(sSaturdayWinterA);
        cicloSemanal.addPeriod(sSaturdayWinterB);
        cicloSemanal.addPeriod(sSaturdayWinterC);
        cicloSemanal.addPeriod(sSaturdayWinterD);
        cicloSemanal.addPeriod(sSaturdayWinterE);

        cicloSemanal.addPeriod(sSaturdaySummerA);
        cicloSemanal.addPeriod(sSaturdaySummerB);
        cicloSemanal.addPeriod(sSaturdaySummerC);
        cicloSemanal.addPeriod(sSaturdaySummerD);
        cicloSemanal.addPeriod(sSaturdaySummerE);

        //Sunday
        LocalTimeInterval sundayHour = LocalTimeInterval.Allday();
        Period[] sSunday = Period.getAllYear(sundayHour, TypeDay.SundayAndHoliday(), PricePlan.VAZIO, YEAR);
        cicloSemanal.addPeriod(sSunday);

        return cicloSemanal;
    }

    public static Plan cicloDiario() {
        Plan cicloDiario  = new Plan("BTN Ciclo Diario");

        LocalTimeInterval winterMorning = new LocalTimeInterval(0, 0, 7, 59);
        LocalTimeInterval winterDay = new LocalTimeInterval(8, 0, 21,59);
        LocalTimeInterval winterNight = new LocalTimeInterval(22, 0, 23,59);

        LocalTimeInterval summerMorning = new LocalTimeInterval(0, 0, 7, 59);
        LocalTimeInterval summerDay = new LocalTimeInterval(8, 0, 21,59);
        LocalTimeInterval summerNight = new LocalTimeInterval(22, 0, 23,59);

        Period[] sWinterMorning = Period.getWinterPeriod(winterMorning, TypeDay.All(), PricePlan.VAZIO, YEAR);
        Period[] sWinterDay = Period.getWinterPeriod(winterDay, TypeDay.All(), PricePlan.CHEIA, YEAR);
        Period[] sWinterNight = Period.getWinterPeriod(winterNight, TypeDay.All(), PricePlan.VAZIO, YEAR);

        Period[] sSummerMorning = Period.getSummerPeriod(summerMorning, TypeDay.All(), PricePlan.VAZIO, YEAR);
        Period[] sSummerDay = Period.getSummerPeriod(summerDay, TypeDay.All(), PricePlan.CHEIA, YEAR);
        Period[] sSummerNight = Period.getSummerPeriod(summerNight, TypeDay.All(), PricePlan.VAZIO, YEAR);

        cicloDiario.addPeriod(sWinterMorning);
        cicloDiario.addPeriod(sWinterDay);
        cicloDiario.addPeriod(sWinterNight);

        cicloDiario.addPeriod(sSummerMorning);
        cicloDiario.addPeriod(sSummerDay);
        cicloDiario.addPeriod(sSummerNight);

        return cicloDiario;
    }
}
