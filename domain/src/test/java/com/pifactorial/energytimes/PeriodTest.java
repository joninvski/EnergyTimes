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

public class PeriodTest {

    int YEAR = 2014;
    LocalDate startYear = new LocalDate(YEAR, DateTimeConstants.JANUARY, 01);
    LocalDate middleJanuary = new LocalDate(YEAR, DateTimeConstants.JANUARY, 15);
    LocalDate endFebruary = new LocalDate(YEAR, DateTimeConstants.FEBRUARY, 27);
    LocalDate startMarch = new LocalDate(YEAR, DateTimeConstants.MARCH, 01);
    LocalDate endWinterA = new LocalDate(YEAR, DateTimeConstants.MARCH, 29);
    LocalDate startWinterB = new LocalDate(YEAR, DateTimeConstants.OCTOBER, 26);
    LocalDate startSummer = new LocalDate(YEAR, DateTimeConstants.MARCH, 30);
    LocalDate endSummer = new LocalDate(YEAR, DateTimeConstants.OCTOBER, 25);
    LocalDate endYear = new LocalDate(YEAR, DateTimeConstants.DECEMBER, 31);
    LocalDate endMay = new LocalDate(YEAR, DateTimeConstants.MAY, 31);
    LocalTimeInterval night = new LocalTimeInterval(00, 00, 07, 59);
    LocalTimeInterval morning = new LocalTimeInterval( 8, 00, 11, 59);
    LocalTimeInterval afternoon = new LocalTimeInterval( 12, 00, 18, 59);
    LocalTimeInterval nightAndMorning = new LocalTimeInterval( 0, 00, 11, 59);
    LocalTimeInterval nightAndMorningAndAfternoon  = new LocalTimeInterval( 0, 00, 18, 59);
    Period startYearPeriodNight = new Period(startYear, endFebruary, night, TypeDay.All(), PricePlan.PONTA);
    Period middleYearPeriodNight = new Period(startMarch, endMay, night, TypeDay.All(), PricePlan.PONTA);
    Period middleYearPeriodMorning = new Period(startMarch, endMay, morning, TypeDay.All(), PricePlan.PONTA);
    Period middleYearPeriodAfternoon = new Period(startMarch, endMay, afternoon, TypeDay.All(), PricePlan.PONTA);


    LocalTime threeAM = new LocalTime( 3,00);
    LocalTime eightAM = new LocalTime( 8,00);

    @BeforeClass
    public static void testSetup() {
    }

    @Test
    public void testSimpleMatches() {
        assertTrue(startYearPeriodNight.matches(startYear.toDateTime(threeAM)));
        assertTrue(startYearPeriodNight.matches(startYear, threeAM));
    }

    @Test
    public void testSimpleMatchesMiddlePeriod() {
        assertTrue(startYearPeriodNight.matches(middleJanuary.toDateTimeAtStartOfDay()));
        assertTrue(startYearPeriodNight.matches(middleJanuary, threeAM));
    }

    @Test
    public void testSimpleNotMatches() {
        assertFalse(startYearPeriodNight.matches(startMarch, eightAM));
        assertFalse(startYearPeriodNight.matches(startMarch, threeAM));
        assertFalse(startYearPeriodNight.matches(startMarch.toDateTimeAtStartOfDay()));

        Period middleYearPeriodAfternoonWeekday = new Period(startMarch, endMay, afternoon, TypeDay.Weekday(), PricePlan.PONTA);
        Period middleYearPeriodAfternoonHoliday = new Period(startMarch, endMay, afternoon, TypeDay.SundayAndHoliday(), PricePlan.PONTA);
        assertFalse(middleYearPeriodAfternoonWeekday.equals(middleYearPeriodAfternoonHoliday ));
    }

    @Test
    public void testIsEqualOrAfter() {
        assertTrue(startYearPeriodNight.isAfterStart(startYear));
        assertTrue(startYearPeriodNight.isAfterStart(middleJanuary));
        assertFalse(middleYearPeriodMorning.isAfterStart(startYear));
    }

    @Test
    public void testIsEqualOrBefore() {
        assertTrue(startYearPeriodNight.isBeforeEnd(middleJanuary));
        assertFalse(startYearPeriodNight.isBeforeEnd(startMarch));
    }

    @Test
    public void getMergedPeriod() {
        assertEquals(Period.getMergedPeriod(startYearPeriodNight, middleYearPeriodNight), new Period(startYear, endMay, night, TypeDay.All(), PricePlan.PONTA));
        assertEquals(Period.getMergedPeriod(startYearPeriodNight, middleYearPeriodMorning), new Period(startYear, endMay, nightAndMorning, TypeDay.All(), PricePlan.PONTA));

        // Hours are now adjacent
        assertEquals(Period.getMergedPeriod(startYearPeriodNight, middleYearPeriodAfternoon), new Period(startYear, endMay, nightAndMorningAndAfternoon, TypeDay.All(), PricePlan.PONTA));

        assertFalse(Period.getMergedPeriod(startYearPeriodNight, middleYearPeriodMorning).equals(new Period(startYear, endMay, night, TypeDay.All(), PricePlan.PONTA)));
    }

    @Test
    public void testEquals() {
        assertEquals(new Period(startYear, endMay, night, TypeDay.All(), PricePlan.CHEIA), new Period(startYear, endMay, night, TypeDay.All(), PricePlan.PONTA));
        assertFalse((new Period(startYear, endMay, night, TypeDay.All(), PricePlan.CHEIA)).equals(new Period(startYear, endFebruary, night, TypeDay.All(), PricePlan.CHEIA)));
        assertFalse((new Period(startYear, endMay, night, TypeDay.All(), PricePlan.CHEIA)).equals(new Period(startYear, endMay, morning, TypeDay.All(), PricePlan.CHEIA)));
    }

    @Test
    public void testGetMinuteAfter(){
        assertEquals(startYearPeriodNight.getMinuteAfterThisPeriod(), new DateTime(2014, 02, 27, 8, 00));
        assertEquals(middleYearPeriodMorning.getMinuteAfterThisPeriod(), new DateTime(2014, 05, 31, 12, 00));
    }

    @Test
    public void testGetWinterPeriod(){
        Period winterPart1 = new Period(startYear, endWinterA, morning, TypeDay.Weekday(), PricePlan.CHEIA);
        Period winterPart2 = new Period(startWinterB, endYear, morning, TypeDay.Weekday(), PricePlan.CHEIA);

        Period[] p = {winterPart1, winterPart2};
        assertArrayEquals(Period.getWinterPeriod( 8, 00, 11, 59, TypeDay.Weekday(), PricePlan.CHEIA, 2014), p);
    }

    @Test
    public void testGetSummerPeriod(){
        Period summer = new Period(startSummer, endSummer, morning, TypeDay.Weekday(), PricePlan.CHEIA);

        Period[] p = {summer};
        assertArrayEquals(Period.getSummerPeriod( 8, 00, 11, 59, TypeDay.Weekday(), PricePlan.CHEIA, 2014), p);
    }

    @Test
    public void testAllYear(){
        Period all = new Period(startYear, endYear, morning, TypeDay.Weekday(), PricePlan.CHEIA);

        Period[] p = {all};
        assertArrayEquals(Period.getAllYear( 8, 00, 11, 59, TypeDay.Weekday(), PricePlan.CHEIA, 2014), p);
    }
}
