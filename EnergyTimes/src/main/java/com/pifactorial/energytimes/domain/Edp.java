package com.pifactorial.energytimes.domain;

public class Edp {

    public static Plan[] fillEdp() {
        return new Plan[] {cicloDiario(), cicloSemanal()};
    }

    public static Plan cicloSemanal() {
        Plan cicloSemanal  = new Plan("BTN Ciclo Semanal");

        // Weekdays
        Period[] sWeekdayWinterA = Period.getWinterPeriod( 0,  0,  6, 59, TypeDay.Weekday() , PricePlan.VAZIO);
        Period[] sWeekdayWinterD = Period.getWinterPeriod( 7,  0,  9, 29, TypeDay.Weekday() , PricePlan.CHEIA);
        Period[] sWeekdayWinterE = Period.getWinterPeriod( 9, 30, 11, 59, TypeDay.Weekday() , PricePlan.PONTA);
        Period[] sWeekdayWinterF = Period.getWinterPeriod(12,  0, 18, 29, TypeDay.Weekday() , PricePlan.CHEIA);
        Period[] sWeekdayWinterG = Period.getWinterPeriod(18, 30, 20, 59, TypeDay.Weekday() , PricePlan.PONTA);
        Period[] sWeekdayWinterH = Period.getWinterPeriod(21,  0, 23, 59, TypeDay.Weekday() , PricePlan.CHEIA);

        Period[] sWeekdaySummerA = Period.getSummerPeriod( 0,  0,  6, 59, TypeDay.Weekday() , PricePlan.VAZIO);
        Period[] sWeekdaySummerD = Period.getSummerPeriod( 7,  0,  9, 14, TypeDay.Weekday() , PricePlan.CHEIA);
        Period[] sWeekdaySummerE = Period.getSummerPeriod( 9, 15, 12, 14, TypeDay.Weekday() , PricePlan.PONTA);
        Period[] sWeekdaySummerF = Period.getSummerPeriod(12, 15, 23, 59, TypeDay.Weekday() , PricePlan.CHEIA);

        cicloSemanal.addPeriod(sWeekdayWinterA);
        cicloSemanal.addPeriod(sWeekdayWinterD);
        cicloSemanal.addPeriod(sWeekdayWinterE);
        cicloSemanal.addPeriod(sWeekdayWinterF);
        cicloSemanal.addPeriod(sWeekdayWinterG);
        cicloSemanal.addPeriod(sWeekdayWinterH);

        cicloSemanal.addPeriod(sWeekdaySummerA);
        cicloSemanal.addPeriod(sWeekdaySummerD);
        cicloSemanal.addPeriod(sWeekdaySummerE);
        cicloSemanal.addPeriod(sWeekdaySummerF);

        // Saturday
        Period[] sSaturdayWinterA = Period.getWinterPeriod( 0,  0,  9, 29, TypeDay.Saturday(), PricePlan.VAZIO);
        Period[] sSaturdayWinterD = Period.getWinterPeriod( 9, 30, 12, 59, TypeDay.Saturday(), PricePlan.CHEIA);
        Period[] sSaturdayWinterE = Period.getWinterPeriod(13, 00, 18, 29, TypeDay.Saturday(), PricePlan.VAZIO);
        Period[] sSaturdayWinterF = Period.getWinterPeriod(18, 30, 21, 59, TypeDay.Saturday(), PricePlan.CHEIA);
        Period[] sSaturdayWinterG = Period.getWinterPeriod(22, 00, 23, 59, TypeDay.Saturday(), PricePlan.VAZIO);

        Period[] sSaturdaySummerA = Period.getSummerPeriod( 0,  0,  8, 59, TypeDay.Saturday(), PricePlan.VAZIO);
        Period[] sSaturdaySummerD = Period.getSummerPeriod( 9,  0, 13, 59, TypeDay.Saturday(), PricePlan.CHEIA);
        Period[] sSaturdaySummerE = Period.getSummerPeriod(14,  0, 19, 59, TypeDay.Saturday(), PricePlan.VAZIO);
        Period[] sSaturdaySummerF = Period.getSummerPeriod(20,  0, 21, 59, TypeDay.Saturday(), PricePlan.CHEIA);
        Period[] sSaturdaySummerG = Period.getSummerPeriod(22,  0, 23, 59, TypeDay.Saturday(), PricePlan.VAZIO);

        cicloSemanal.addPeriod(sSaturdayWinterA);
        cicloSemanal.addPeriod(sSaturdayWinterD);
        cicloSemanal.addPeriod(sSaturdayWinterE);
        cicloSemanal.addPeriod(sSaturdayWinterF);
        cicloSemanal.addPeriod(sSaturdayWinterG);

        cicloSemanal.addPeriod(sSaturdaySummerA);
        cicloSemanal.addPeriod(sSaturdaySummerD);
        cicloSemanal.addPeriod(sSaturdaySummerE);
        cicloSemanal.addPeriod(sSaturdaySummerF);
        cicloSemanal.addPeriod(sSaturdaySummerG);

        //Sunday
        Period[] sSundayA = Period.getAllYear( 0,  0,  23, 59, TypeDay.SundayAndHoliday(), PricePlan.VAZIO);

        cicloSemanal.addPeriod(sSundayA);

        return cicloSemanal;
    }

    public static Plan cicloDiario() {
        Plan cicloDiario  = new Plan("BTN Ciclo Diario");

        LocalTimeInterval winterMorning = new LocalTimeInterval(0, 0, 7, 59);
        LocalTimeInterval winterDay = new LocalTimeInterval(8, 0, 21,59);
        LocalTimeInterval winterNight = new LocalTimeInterval(22, 0, 23, 59);

        LocalTimeInterval summerMorning = new LocalTimeInterval(0, 0, 7, 59);
        LocalTimeInterval summerDay = new LocalTimeInterval(8, 0, 21,59);
        LocalTimeInterval summerNight = new LocalTimeInterval(22, 0, 23 ,59);

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
