package com.pifactorial.energytimes;

import static org.junit.Assert.*;
import org.junit.*;
import com.pifactorial.energytimes.domain.PricePlan;

public class PricePlanTest {

    PricePlan sv = PricePlan.VAZIO;
    PricePlan v = PricePlan.VAZIO;
    PricePlan c = PricePlan.CHEIA;
    PricePlan p = PricePlan.PONTA;

    @BeforeClass
    public static void testSetup() {
    }

    @Test
    public void checkIsVazio(){
        assertTrue(v.isVazio());
        assertTrue(sv.isVazio());
        assertFalse(c.isVazio());
        assertFalse(p.isVazio());
    }

    @Test
    public void checkIsCheia(){
        assertFalse(v.isCheia());
        assertFalse(p.isCheia());
        assertTrue(c.isCheia());
    }

    @Test
    public void checkIsPonta(){
        assertFalse(v.isPonta());
        assertFalse(c.isPonta());
        assertTrue(p.isPonta());
    }

    @Test
    public void checkEqualityBiHour() {
        boolean biHour = true;
        assertTrue (v.equals(v, biHour));
        assertFalse(v.equals(c, biHour));
        assertFalse(v.equals(p, biHour));

        assertFalse(c.equals(v, biHour));
        assertTrue (c.equals(c, biHour));
        assertTrue (c.equals(p, biHour));

        assertFalse(p.equals(v, biHour));
        assertTrue (p.equals(c, biHour));
        assertTrue (p.equals(p, biHour));
    }

    @Test
    public void checkEqualityNotBiHour() {
        boolean biHour = false;
        assertTrue (v.equals(v, biHour));
        assertFalse(v.equals(c, biHour));
        assertFalse(v.equals(p, biHour));

        assertFalse(c.equals(p, biHour));
        assertTrue (c.equals(c, biHour));
        assertFalse(c.equals(p, biHour));

        assertFalse(p.equals(v, biHour));
        assertFalse(p.equals(c, biHour));
        assertTrue (p.equals(p, biHour));
    }

}
