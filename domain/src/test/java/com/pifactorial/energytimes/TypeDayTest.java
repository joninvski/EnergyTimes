package com.pifactorial.energytimes;

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

}
