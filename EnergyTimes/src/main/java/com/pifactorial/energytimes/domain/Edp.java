package com.pifactorial.energytimes.domain;

public class Edp {

    public static Plan[] fillEdp() {
        return new Plan[] {cicloDiario(), cicloSemanal()};
    }

    public static Plan cicloSemanal() {
        Plan cicloSemanal  = new Plan("BTN Ciclo Semanal");

        Hours nightA     = new Hours ( 00, 00, 01, 59);
        Hours morningA   = new Hours ( 02, 00, 05, 59);
        Hours morningA   = new Hours ( 06, 00, 06, 59);
        Hours morningB   = new Hours ( 07, 00, 09, 29);
        Hours morningC   = new Hours ( 09, 30, 11, 59);
        Hours afternoonA = new Hours ( 12, 00, 18, 29);
        Hours afternoonB = new Hours ( 18, 30, 20, 59);
        Hours night      = new Hours ( 21, 00, 23, 59);

        // Weekdays
        Schedule[] sWeekdayWinterA = Schedule.getWinterSchedule(00, 00, 01, 59, TypeDay.Weekday() , PricePlan.VAZIO);
        Schedule[] sWeekdayWinterB = Schedule.getWinterSchedule(02, 00, 05, 59, TypeDay.Weekday() , PricePlan.SUPER_VAZIO);
        Schedule[] sWeekdayWinterC = Schedule.getWinterSchedule(06, 00, 06, 59, TypeDay.Weekday() , PricePlan.VAZIO);
        Schedule[] sWeekdayWinterD = Schedule.getWinterSchedule(07, 00, 09, 29, TypeDay.Weekday() , PricePlan.CHEIAS);
        Schedule[] sWeekdayWinterE = Schedule.getWinterSchedule(09, 30, 11, 59, TypeDay.Weekday() , PricePlan.PONTA);
        Schedule[] sWeekdayWinterF = Schedule.getWinterSchedule(12, 00, 18, 29, TypeDay.Weekday() , PricePlan.CHEIAS);
        Schedule[] sWeekdayWinterG = Schedule.getWinterSchedule(18, 30, 20, 59, TypeDay.Weekday() , PricePlan.PONTA);
        Schedule[] sWeekdayWinterH = Schedule.getWinterSchedule(21, 00, 23, 59, TypeDay.Weekday() , PricePlan.CHEIAS);

        Schedule[] sWeekdaySummerA = Schedule.getSummerSchedule(00, 00, 01, 59, TypeDay.Weekday() , PricePlan.VAZIO);
        Schedule[] sWeekdaySummerB = Schedule.getSummerSchedule(02, 00, 05, 59, TypeDay.Weekday() , PricePlan.SUPER_VAZIO);
        Schedule[] sWeekdaySummerC = Schedule.getSummerSchedule(06, 00, 06, 59, TypeDay.Weekday() , PricePlan.VAZIO);
        Schedule[] sWeekdaySummerD = Schedule.getSummerSchedule(07, 00, 09, 14, TypeDay.Weekday() , PricePlan.CHEIAS);
        Schedule[] sWeekdaySummerE = Schedule.getSummerSchedule(09, 15, 12, 14, TypeDay.Weekday() , PricePlan.PONTA);
        Schedule[] sWeekdaySummerF = Schedule.getSummerSchedule(12, 15, 23, 59, TypeDay.Weekday() , PricePlan.CHEIAS);

        cicloSemanal.addSchedule(sWeekdayWinterA);
        cicloSemanal.addSchedule(sWeekdayWinterB);
        cicloSemanal.addSchedule(sWeekdayWinterC);
        cicloSemanal.addSchedule(sWeekdayWinterE);
        cicloSemanal.addSchedule(sWeekdayWinterF);
        cicloSemanal.addSchedule(sWeekdayWinterG);
        cicloSemanal.addSchedule(sWeekdayWinterH);

        cicloSemanal.addSchedule(sWeekdaySummerA);
        cicloSemanal.addSchedule(sWeekdaySummerB);
        cicloSemanal.addSchedule(sWeekdaySummerC);
        cicloSemanal.addSchedule(sWeekdaySummerE);
        cicloSemanal.addSchedule(sWeekdaySummerF);

        // Saturday
        Schedule[] sSaturdayWinterA = Schedule.getWinterSchedule(00, 00, 01, 59, TypeDay.Saturday(), PricePlan.VAZIO);
        Schedule[] sSaturdayWinterB = Schedule.getWinterSchedule(02, 00, 05, 59, TypeDay.Saturday(), PricePlan.SUPER_VAZIO);
        Schedule[] sSaturdayWinterC = Schedule.getWinterSchedule(06, 00, 09, 29, TypeDay.Saturday(), PricePlan.VAZIO);
        Schedule[] sSaturdayWinterD = Schedule.getWinterSchedule(09, 30, 12, 59, TypeDay.Saturday(), PricePlan.CHEIAS);
        Schedule[] sSaturdayWinterE = Schedule.getWinterSchedule(13, 00, 18, 29, TypeDay.Saturday(), PricePlan.VAZIO);
        Schedule[] sSaturdayWinterF = Schedule.getWinterSchedule(18, 30, 21, 59, TypeDay.Saturday(), PricePlan.CHEIAS);
        Schedule[] sSaturdayWinterG = Schedule.getWinterSchedule(22, 00, 23, 59, TypeDay.Saturday(), PricePlan.VAZIO);

        Schedule[] sSaturdaySummerA = Schedule.getSummerSchedule(00, 00, 01, 59, TypeDay.Saturday(), PricePlan.VAZIO);
        Schedule[] sSaturdaySummerB = Schedule.getSummerSchedule(02, 00, 05, 59, TypeDay.Saturday(), PricePlan.SUPER_VAZIO);
        Schedule[] sSaturdaySummerC = Schedule.getSummerSchedule(06, 00, 08, 59, TypeDay.Saturday(), PricePlan.VAZIO);
        Schedule[] sSaturdaySummerD = Schedule.getSummerSchedule(09, 00, 13, 59, TypeDay.Saturday(), PricePlan.CHEIAS);
        Schedule[] sSaturdaySummerE = Schedule.getSummerSchedule(14, 00, 19, 59, TypeDay.Saturday(), PricePlan.VAZIO);
        Schedule[] sSaturdaySummerF = Schedule.getSummerSchedule(20, 00, 21, 59, TypeDay.Saturday(), PricePlan.CHEIAS);
        Schedule[] sSaturdaySummerG = Schedule.getSummerSchedule(22, 00, 23, 59, TypeDay.Saturday(), PricePlan.VAZIO);

        cicloSemanal.addSchedule(sSaturdayWinterA);
        cicloSemanal.addSchedule(sSaturdayWinterB);
        cicloSemanal.addSchedule(sSaturdayWinterC);
        cicloSemanal.addSchedule(sSaturdayWinterD);
        cicloSemanal.addSchedule(sSaturdayWinterE);
        cicloSemanal.addSchedule(sSaturdayWinterF);
        cicloSemanal.addSchedule(sSaturdayWinterG);

        cicloSemanal.addSchedule(sSaturdaySummerA);
        cicloSemanal.addSchedule(sSaturdaySummerB);
        cicloSemanal.addSchedule(sSaturdaySummerC);
        cicloSemanal.addSchedule(sSaturdaySummerD);
        cicloSemanal.addSchedule(sSaturdaySummerE);
        cicloSemanal.addSchedule(sSaturdaySummerF);
        cicloSemanal.addSchedule(sSaturdaySummerG);

        //Sunday
        Schedule[] sSundayA = Schedule.getAllYear(00, 00, 01, 59, TypeDay.SundayAndHoliday(), PricePlan.VAZIO);
        Schedule[] sSundayB = Schedule.getAllYear(01, 59, 05, 59, TypeDay.SundayAndHoliday(), PricePlan.SUPER_VAZIO);
        Schedule[] sSundayC = Schedule.getAllYear(06, 00, 23, 59, TypeDay.SundayAndHoliday(), PricePlan.VAZIO);

        Schedule[] sSunday = Schedule.getAllYear(sundayHour, TypeDay.SundayAndHoliday(), PricePlan.VAZIO);
        cicloSemanal.addSchedule(sSundayA);
        cicloSemanal.addSchedule(sSundayB);
        cicloSemanal.addSchedule(sSundayC);

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
