package com.pifactorial.energytimes.domain;

import static org.junit.Assert.*;
import org.junit.*;
import com.pifactorial.energytimes.domain.LocalTimeInterval;

import org.joda.time.LocalTime;

public class LocalTimeIntervalTest {
    LocalTime from = new LocalTime(8, 00);
    LocalTime to = new LocalTime(8, 59);

    LocalTime fromNine = new LocalTime(9, 00);
    LocalTime toTen = new LocalTime(10, 59);

    LocalTime midnight = LocalTime.MIDNIGHT;

    LocalTimeInterval i = new LocalTimeInterval(from, to);
    LocalTimeInterval h = new LocalTimeInterval(fromNine, toTen);
    LocalTimeInterval j = new LocalTimeInterval(midnight, toTen);

    LocalTimeInterval instantBeginning = new LocalTimeInterval(from, from.plusMinutes(1));
    LocalTimeInterval instantEnd = new LocalTimeInterval(to, to.plusMinutes(1));

    @BeforeClass
    public static void testSetup() {
    }

    @Test
    public void testOverlapWithItself() {
        assertTrue(i.overlapsWith(i));
    }

    @Test
    public void testOverlapTimeIntervalOneMinuteDurationBeginning() {
        assertTrue(i.overlapsWith(instantBeginning));
    }

    @Test
    public void testOverlapTimeIntervalOneMinuteDurationEnd() {
        assertFalse(i.overlapsWith(instantEnd));
    }

    @Test
    public void testOverlapTimeIntervalWithLocalTimeBeginning() {
        assertTrue(i.overlapsWith(from));
    }

    @Test
    public void testSimpleMerge() {
        assertEquals(LocalTimeInterval.getMergedTimeInterval(i, h), new LocalTimeInterval(from, toTen));
    }

    @Test
    public void testAttributes() {
        assertEquals(i.getStartHour(), 8);
        assertEquals(i.getStartMinute(), 0);
        assertEquals(i.getEndHour(), 8);
        assertEquals(i.getEndMinute(), 59);
    }

    public void testConstructorWithInts() {
        assertEquals(i, new LocalTimeInterval(8,0,8,59));
    }

    public void testMidnightOverlaps() {
        assertTrue(j.overlapsWith(midnight));
    }
}
