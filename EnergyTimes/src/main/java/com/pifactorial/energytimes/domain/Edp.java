package com.pifactorial.energytimes.domain;

public class Edp {

    public static Plan[] fillEdp() {
        return new Plan[] {cicloDiario(), cicloSemanal()};
    }

    public static Plan cicloSemanal() {
        Plan cicloSemanal  = new Plan("BTN Ciclo Semanal");

        Hours morning = new Hours(0, 0, 6, 59);
        Hours day = new Hours(7, 0, 23,59);

        // Weekdays
        Schedule[] sWeekdayWinterMorning = Schedule.getWinterSchedule(morning, TypeDay.Weekday(), PricePlan.VAZIO);
        Schedule[] sWeekdayWinterDay = Schedule.getWinterSchedule(day, TypeDay.Weekday(), PricePlan.CHEIAS);

        Schedule[] sWeekdaySummerMorning = Schedule.getSummerSchedule(morning, TypeDay.Weekday(), PricePlan.VAZIO);
        Schedule[] sWeekdaySummerDay = Schedule.getSummerSchedule(day, TypeDay.Weekday(), PricePlan.CHEIAS);

        cicloSemanal.addSchedule(sWeekdayWinterMorning);
        cicloSemanal.addSchedule(sWeekdayWinterDay);
        cicloSemanal.addSchedule(sWeekdaySummerMorning);
        cicloSemanal.addSchedule(sWeekdaySummerDay);

        // Saturday
        Hours saturdayWinterA = new Hours(0, 0, 9, 29);
        Hours saturdayWinterB = new Hours(9, 30, 12, 59);
        Hours saturdayWinterC = new Hours(13, 0, 18, 29);
        Hours saturdayWinterD = new Hours(18, 30, 21, 59);
        Hours saturdayWinterE = new Hours(22, 0, 23, 59);

        Schedule[] sSaturdayWinterA = Schedule.getWinterSchedule(saturdayWinterA, TypeDay.Saturday(), PricePlan.VAZIO);
        Schedule[] sSaturdayWinterB = Schedule.getWinterSchedule(saturdayWinterB, TypeDay.Saturday(), PricePlan.CHEIAS);
        Schedule[] sSaturdayWinterC = Schedule.getWinterSchedule(saturdayWinterC, TypeDay.Saturday(), PricePlan.VAZIO);
        Schedule[] sSaturdayWinterD = Schedule.getWinterSchedule(saturdayWinterD, TypeDay.Saturday(), PricePlan.CHEIAS);
        Schedule[] sSaturdayWinterE = Schedule.getWinterSchedule(saturdayWinterE, TypeDay.Saturday(), PricePlan.VAZIO);

        Hours saturdaySummerA = new Hours(0, 0, 8, 59);
        Hours saturdaySummerB = new Hours(9, 30, 13, 59);
        Hours saturdaySummerC = new Hours(14, 0, 19, 59);
        Hours saturdaySummerD = new Hours(20, 0, 21, 59);
        Hours saturdaySummerE = new Hours(22, 0, 23, 59);

        Schedule[] sSaturdaySummerA = Schedule.getSummerSchedule(saturdaySummerA, TypeDay.Saturday(), PricePlan.VAZIO);
        Schedule[] sSaturdaySummerB = Schedule.getSummerSchedule(saturdaySummerB, TypeDay.Saturday(), PricePlan.CHEIAS);
        Schedule[] sSaturdaySummerC = Schedule.getSummerSchedule(saturdaySummerC, TypeDay.Saturday(), PricePlan.VAZIO);
        Schedule[] sSaturdaySummerD = Schedule.getSummerSchedule(saturdaySummerD, TypeDay.Saturday(), PricePlan.CHEIAS);
        Schedule[] sSaturdaySummerE = Schedule.getSummerSchedule(saturdaySummerE, TypeDay.Saturday(), PricePlan.VAZIO);

        cicloSemanal.addSchedule(sSaturdayWinterA);
        cicloSemanal.addSchedule(sSaturdayWinterB);
        cicloSemanal.addSchedule(sSaturdayWinterC);
        cicloSemanal.addSchedule(sSaturdayWinterD);
        cicloSemanal.addSchedule(sSaturdayWinterE);

        cicloSemanal.addSchedule(sSaturdaySummerA);
        cicloSemanal.addSchedule(sSaturdaySummerB);
        cicloSemanal.addSchedule(sSaturdaySummerC);
        cicloSemanal.addSchedule(sSaturdaySummerD);
        cicloSemanal.addSchedule(sSaturdaySummerE);

        //Sunday
        Hours sundayHour = Hours.Allday();
        Schedule[] sSunday = Schedule.getAllYear(sundayHour, TypeDay.SundayAndHoliday(), PricePlan.VAZIO);
        cicloSemanal.addSchedule(sSunday);

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

        Schedule[] sWinterMorning = Schedule.getWinterSchedule(winterMorning, TypeDay.All(), PricePlan.VAZIO);
        Schedule[] sWinterDay = Schedule.getWinterSchedule(winterDay, TypeDay.All(), PricePlan.CHEIAS);
        Schedule[] sWinterNight = Schedule.getWinterSchedule(winterNight, TypeDay.All(), PricePlan.VAZIO);

        Schedule[] sSummerMorning = Schedule.getSummerSchedule(summerMorning, TypeDay.All(), PricePlan.VAZIO);
        Schedule[] sSummerDay = Schedule.getSummerSchedule(summerDay, TypeDay.All(), PricePlan.CHEIAS);
        Schedule[] sSummerNight = Schedule.getSummerSchedule(summerNight, TypeDay.All(), PricePlan.VAZIO);

        cicloDiario.addSchedule(sWinterMorning);
        cicloDiario.addSchedule(sWinterDay);
        cicloDiario.addSchedule(sWinterNight);

        cicloDiario.addSchedule(sSummerMorning);
        cicloDiario.addSchedule(sSummerDay);
        cicloDiario.addSchedule(sSummerNight);

        return cicloDiario;
    }

}
