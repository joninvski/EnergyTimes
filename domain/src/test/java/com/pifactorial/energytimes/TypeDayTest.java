package com.pifactorial.energytimes;

import static org.junit.Assert.*;
import org.junit.*;
import com.pifactorial.energytimes.domain.TypeDay;

import org.joda.time.DateTimeConstants;
import org.joda.time.MonthDay;

public class TypeDayTest {

    MonthDay newYear = new MonthDay(DateTimeConstants.JANUARY, 1);
    MonthDay freedomDay = new MonthDay(DateTimeConstants.APRIL, 25);
    MonthDay labourDay = new MonthDay(DateTimeConstants.MAY, 1);
    MonthDay nationalDay = new MonthDay(DateTimeConstants.JUNE, 10);
    MonthDay assumptionDay = new MonthDay(DateTimeConstants.AUGUST, 15);
    MonthDay imaculate = new MonthDay(DateTimeConstants.DECEMBER, 8);
    MonthDay easter = new MonthDay(DateTimeConstants.APRIL, 20);
    MonthDay holyFriday = new MonthDay(DateTimeConstants.APRIL, 18);

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
