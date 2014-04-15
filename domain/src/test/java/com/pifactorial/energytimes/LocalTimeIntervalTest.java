package com.pifactorial.energytimes;

import static org.junit.Assert.*;
import org.junit.*;
import com.pifactorial.energytimes.domain.LocalTimeInterval;

import org.joda.time.LocalTime;

public class LocalTimeIntervalTest {
    LocalTime from = new LocalTime(8, 00);
    LocalTime to = new LocalTime(8, 59);

    LocalTimeInterval i = new LocalTimeInterval(from, to);
    LocalTimeInterval instantBeginning = new LocalTimeInterval(from, from.plusMinutes(1));
    LocalTimeInterval instantEnd = new LocalTimeInterval(to, to.plusMinutes(1));

    @BeforeClass
    public static void testSetup() {
    }

    @Test
    public void testOverlapWithItself(){
        assertTrue(i.overlapsWith(i));
    }

    @Test
    public void testOverlapTimeIntervalOneMinuteDurationBeginning(){
        assertTrue(i.overlapsWith(instantBeginning));
    }

    @Test
    public void testOverlapTimeIntervalOneMinuteDurationEnd(){
        assertFalse(i.overlapsWith(instantEnd));
    }
}
