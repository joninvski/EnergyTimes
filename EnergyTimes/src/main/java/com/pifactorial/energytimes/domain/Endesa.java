package com.pifactorial.energytimes.domain;

public class Endesa {

    public static Plan[] fillEndesa() {
        return new Plan[] {cicloDiario(), cicloSemanal()};
    }

    public static Plan cicloSemanal() {
        Plan cicloSemanal  = new Plan("BTN Ciclo Semanal");

        Hours morning = new Hours(0, 0, 6, 59);
        Hours day = new Hours(7, 0, 23,59);

        // Weekdays
        Period[] sWeekdayWinterMorning = Period.getWinterPeriod(morning, TypeDay.Weekday(), PricePlan.VAZIO);
        Period[] sWeekdayWinterDay = Period.getWinterPeriod(day, TypeDay.Weekday(), PricePlan.CHEIA);

        Period[] sWeekdaySummerMorning = Period.getSummerPeriod(morning, TypeDay.Weekday(), PricePlan.VAZIO);
        Period[] sWeekdaySummerDay = Period.getSummerPeriod(day, TypeDay.Weekday(), PricePlan.CHEIA);

        cicloSemanal.addPeriod(sWeekdayWinterMorning);
        cicloSemanal.addPeriod(sWeekdayWinterDay);
        cicloSemanal.addPeriod(sWeekdaySummerMorning);
        cicloSemanal.addPeriod(sWeekdaySummerDay);

        // Saturday
        Hours saturdayWinterA = new Hours(0, 0, 9, 29);
        Hours saturdayWinterB = new Hours(9, 30, 12, 59);
        Hours saturdayWinterC = new Hours(13, 0, 18, 29);
        Hours saturdayWinterD = new Hours(18, 30, 21, 59);
        Hours saturdayWinterE = new Hours(22, 0, 23, 59);

        Period[] sSaturdayWinterA = Period.getWinterPeriod(saturdayWinterA, TypeDay.Saturday(), PricePlan.VAZIO);
        Period[] sSaturdayWinterB = Period.getWinterPeriod(saturdayWinterB, TypeDay.Saturday(), PricePlan.CHEIA);
        Period[] sSaturdayWinterC = Period.getWinterPeriod(saturdayWinterC, TypeDay.Saturday(), PricePlan.VAZIO);
        Period[] sSaturdayWinterD = Period.getWinterPeriod(saturdayWinterD, TypeDay.Saturday(), PricePlan.CHEIA);
        Period[] sSaturdayWinterE = Period.getWinterPeriod(saturdayWinterE, TypeDay.Saturday(), PricePlan.VAZIO);

        Hours saturdaySummerA = new Hours(0, 0, 8, 59);
        Hours saturdaySummerB = new Hours(9, 00, 13, 59);
        Hours saturdaySummerC = new Hours(14, 0, 19, 59);
        Hours saturdaySummerD = new Hours(20, 0, 21, 59);
        Hours saturdaySummerE = new Hours(22, 0, 23, 59);

        Period[] sSaturdaySummerA = Period.getSummerPeriod(saturdaySummerA, TypeDay.Saturday(), PricePlan.VAZIO);
        Period[] sSaturdaySummerB = Period.getSummerPeriod(saturdaySummerB, TypeDay.Saturday(), PricePlan.CHEIA);
        Period[] sSaturdaySummerC = Period.getSummerPeriod(saturdaySummerC, TypeDay.Saturday(), PricePlan.VAZIO);
        Period[] sSaturdaySummerD = Period.getSummerPeriod(saturdaySummerD, TypeDay.Saturday(), PricePlan.CHEIA);
        Period[] sSaturdaySummerE = Period.getSummerPeriod(saturdaySummerE, TypeDay.Saturday(), PricePlan.VAZIO);

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
        Hours sundayHour = Hours.Allday();
        Period[] sSunday = Period.getAllYear(sundayHour, TypeDay.SundayAndHoliday(), PricePlan.VAZIO);
        cicloSemanal.addPeriod(sSunday);

        return cicloSemanal;
    }

    public static Plan cicloDiario() {
        Plan cicloDiario  = new Plan("BTN Ciclo Diario");

        Hours winterMorning = new Hours(0, 0, 7, 59);
        Hours winterDay = new Hours(8, 0, 21,59);
        Hours winterNight = new Hours(22, 0, 23,59);

        Hours summerMorning = new Hours(0, 0, 7, 59);
        Hours summerDay = new Hours(8, 0, 21,59);
        Hours summerNight = new Hours(22, 0, 23,59);

        Period[] sWinterMorning = Period.getWinterPeriod(winterMorning, TypeDay.All(), PricePlan.VAZIO);
        Period[] sWinterDay = Period.getWinterPeriod(winterDay, TypeDay.All(), PricePlan.CHEIA);
        Period[] sWinterNight = Period.getWinterPeriod(winterNight, TypeDay.All(), PricePlan.VAZIO);

        Period[] sSummerMorning = Period.getSummerPeriod(summerMorning, TypeDay.All(), PricePlan.VAZIO);
        Period[] sSummerDay = Period.getSummerPeriod(summerDay, TypeDay.All(), PricePlan.CHEIA);
        Period[] sSummerNight = Period.getSummerPeriod(summerNight, TypeDay.All(), PricePlan.VAZIO);

        cicloDiario.addPeriod(sWinterMorning);
        cicloDiario.addPeriod(sWinterDay);
        cicloDiario.addPeriod(sWinterNight);

        cicloDiario.addPeriod(sSummerMorning);
        cicloDiario.addPeriod(sSummerDay);
        cicloDiario.addPeriod(sSummerNight);

        return cicloDiario;
    }
}
