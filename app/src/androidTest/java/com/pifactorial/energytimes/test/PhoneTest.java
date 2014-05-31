package com.pifactorial.energytimes.test;

import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.pifactorial.energytimes.MainActivity;

public class PhoneTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public PhoneTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testRun() {

        int timeout = 5;

        // Wait for activity: 'course.labs.fragmentslab.MainActivity'
        assertTrue("MainActivity not found", solo.waitForActivity(
                       com.pifactorial.energytimes.MainActivity.class, 2000));

    }
}
