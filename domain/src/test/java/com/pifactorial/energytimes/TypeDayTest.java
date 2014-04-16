package com.pifactorial.energytimes.domain;

import static org.junit.Assert.*;
import org.junit.*;
import com.pifactorial.energytimes.domain.TypeDay;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

public class TypeDayTest {

    int year = 2014;
    LocalDate newYear = new LocalDate(year, DateTimeConstants.JANUARY, 1);
    LocalDate freedomDay = new LocalDate(year, DateTimeConstants.APRIL, 25);
    LocalDate labourDay = new LocalDate(year, DateTimeConstants.MAY, 1);
    LocalDate nationalDay = new LocalDate(year, DateTimeConstants.JUNE, 10);
    LocalDate assumptionDay = new LocalDate(year, DateTimeConstants.AUGUST, 15);
    LocalDate imaculate = new LocalDate(year, DateTimeConstants.DECEMBER, 8);
    LocalDate easter = new LocalDate(year, DateTimeConstants.APRIL, 20);
    LocalDate holyFriday = new LocalDate(year, DateTimeConstants.APRIL, 18);

    LocalDate monday = new LocalDate(year, DateTimeConstants.APRIL,  7);
    LocalDate friday = new LocalDate(year, DateTimeConstants.APRIL, 11);
    LocalDate saturday = new LocalDate(year, DateTimeConstants.APRIL, 12);

    @BeforeClass
    public static void testSetup() {
    }

    @Test
    public void testNationalHolidays() {
        assertTrue(TypeDay.isPortugueseHoliday(newYear));
        assertTrue(TypeDay.isPortugueseHoliday(freedomDay));
        assertTrue(TypeDay.isPortugueseHoliday(labourDay));
        assertTrue(TypeDay.isPortugueseHoliday(nationalDay));
        assertTrue(TypeDay.isPortugueseHoliday(assumptionDay));
        assertTrue(TypeDay.isPortugueseHoliday(imaculate));
        assertTrue(TypeDay.isPortugueseHoliday(easter));
        assertTrue(TypeDay.isPortugueseHoliday(holyFriday));
    }

    @Test
    public void testMatchTypeDay() {
        assertTrue(TypeDay.MatchTypeDay(newYear, TypeDay.SundayAndHoliday()));
        assertTrue(TypeDay.MatchTypeDay(freedomDay, TypeDay.SundayAndHoliday()));
        assertTrue(TypeDay.MatchTypeDay(monday, TypeDay.Weekday()));
        assertTrue(TypeDay.MatchTypeDay(friday, TypeDay.Weekday()));
        assertTrue(TypeDay.MatchTypeDay(saturday, TypeDay.Saturday()));
    }
}
