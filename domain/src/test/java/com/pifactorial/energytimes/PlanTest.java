package com.pifactorial.energytimes.domain;

import com.pifactorial.energytimes.domain.Period;
import com.pifactorial.energytimes.domain.PricePlan;
import com.pifactorial.energytimes.domain.TypeDay;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import org.junit.*;

import static org.junit.Assert.*;
import com.pifactorial.energytimes.domain.LocalTimeInterval;

public class PlanTest {

    int YEAR = 2014;
    boolean bihour = true;
    boolean trihour = false;

    LocalDate startYear = new LocalDate(YEAR, DateTimeConstants.JANUARY, 01);
    LocalDate middleJanuary = new LocalDate(YEAR, DateTimeConstants.JANUARY, 15);
    LocalDate endFebruary = new LocalDate(YEAR, DateTimeConstants.FEBRUARY, 27);
    LocalDate startMarch = new LocalDate(YEAR, DateTimeConstants.MARCH, 01);
    LocalDate endMay = new LocalDate(YEAR, DateTimeConstants.MAY, 31);
    LocalTimeInterval night = new LocalTimeInterval(00, 00, 07, 59);
    LocalTimeInterval morning = new LocalTimeInterval( 8, 00, 11, 59);
    LocalTimeInterval afternoon = new LocalTimeInterval( 12, 00, 18, 59);
    LocalTimeInterval nightAndMorning = new LocalTimeInterval( 0, 00, 11, 59);
    LocalTimeInterval nightAndMorningAndAfternoon  = new LocalTimeInterval( 0, 00, 18, 59);
    Period startYearPeriodNight = new Period(startYear, endFebruary, night, TypeDay.All(), PricePlan.PONTA);
    Period startYearPeriodMorning = new Period(startYear, endFebruary, morning, TypeDay.All(), PricePlan.PONTA);
    Period startYearPeriodNightWeekday = new Period(startYear, endFebruary, night, TypeDay.Weekday(), PricePlan.PONTA);
    Period startYearPeriodNightHoliday = new Period(startYear, endFebruary, night, TypeDay.SundayAndHoliday(), PricePlan.PONTA);
    Period startYearPeriodAfternoon = new Period(startYear, endFebruary, afternoon, TypeDay.All(), PricePlan.PONTA);
    Period middleYearPeriodNight = new Period(startMarch, endMay, night, TypeDay.All(), PricePlan.PONTA);
    Period middleYearPeriodMorning = new Period(startMarch, endMay, morning, TypeDay.All(), PricePlan.PONTA);
    Period middleYearPeriodAfternoon = new Period(startMarch, endMay, afternoon, TypeDay.All(), PricePlan.PONTA);

    Plan p = new Plan("test name");

    @BeforeClass
    public static void testSetup() {
    }

    @Test
    public void testSimpleSearh() throws Exception {
        p.addPeriod(startYearPeriodNight);
        assertEquals(startYearPeriodNight, p.searchPeriod(new DateTime(YEAR, 01, 01, 01, 01), trihour));
        assertEquals(startYearPeriodNight, p.searchPeriod(new DateTime(YEAR, 01, 01, 01, 01), bihour));
    }

    public void testSearhHolidayPriority() throws Exception {
        p.addPeriod(startYearPeriodNightHoliday);
        p.addPeriod(startYearPeriodNightWeekday);

        Period middleYearPeriodNightWeekday = new Period(startMarch, endMay, night, TypeDay.Weekday(), PricePlan.PONTA);
        Period middleYearPeriodNightHoliday = new Period(startMarch, endMay, night, TypeDay.SundayAndHoliday(), PricePlan.PONTA);

        p.addPeriod(middleYearPeriodNightHoliday);
        p.addPeriod(middleYearPeriodNightWeekday);
        // This should be the new year that on 2014 was on a wednesday
        assertEquals(startYearPeriodNightHoliday, p.searchPeriod(new DateTime(YEAR, 01, 01, 01, 01), trihour));
        assertEquals(startYearPeriodNightHoliday, p.searchPeriod(new DateTime(YEAR, 01, 01, 01, 01), bihour));
        assertFalse(startYearPeriodNightWeekday.equals(p.searchPeriod(new DateTime(YEAR, 01, 01, 01, 01), bihour)));

        // Lets search for easter
        assertEquals(middleYearPeriodNightHoliday, p.searchPeriod(new DateTime(YEAR, 04, 18, 01, 01), trihour));

    }

    @Test(expected = DayWithoutPlanException.class)
    public void testPlanNotFoundBecauseDate() throws Exception {
        p.addPeriod(middleYearPeriodNight);
        p.searchPeriod(new DateTime(YEAR, 01, 01,  1, 01), trihour);
    }

    @Test(expected = DayWithoutPlanException.class)
    public void testPlanNotFoundBecauseHour() throws Exception {
        p.addPeriod(startYearPeriodNight);
        p.searchPeriod(new DateTime(YEAR, 01, 01,  8, 00), trihour);
    }

    @Test
    public void testSearchEndPeriodTriHourSamePrice() throws Exception {
        p.addPeriod(startYearPeriodNight);
        p.addPeriod(startYearPeriodMorning);
        Period found = p.getNextPeriodDifferentPrice(startYearPeriodNight, trihour);
        assertEquals(startYearPeriodMorning, found);
        assertFalse(startYearPeriodNight.equals(found));

        p.addPeriod(startYearPeriodAfternoon);
        found = p.getNextPeriodDifferentPrice(startYearPeriodNight, trihour);
        assertEquals(startYearPeriodAfternoon, found);
    }

    @Test
    public void testSearchEndPeriodTriHourDifferentPrice() throws Exception {
        p.addPeriod(startYearPeriodNight);
        p.addPeriod(startYearPeriodMorning);
        Period startYearPeriodAfternoon = new Period(startYear, endFebruary, afternoon, TypeDay.All(), PricePlan.CHEIA);
        p.addPeriod(startYearPeriodAfternoon);

        Period found = p.getNextPeriodDifferentPrice(startYearPeriodNight, trihour);
        assertEquals(startYearPeriodMorning, found);
        assertFalse(startYearPeriodAfternoon.equals(found));
    }

    @Test
    public void testSearchSearchTriHour() throws Exception {
        p.addPeriod(startYearPeriodNight);
        p.addPeriod(startYearPeriodMorning);
        Period found = p.searchPeriodTriHour(new DateTime(YEAR, 01, 01,  0, 00));

        assertEquals(startYearPeriodNight, found);
        assertFalse(startYearPeriodMorning.equals(found));

        found = p.searchPeriodTriHour(new DateTime(YEAR, 01, 01,  9, 00));
        assertEquals(startYearPeriodMorning, found);
        assertFalse(startYearPeriodNight.equals(found));
    }
}
