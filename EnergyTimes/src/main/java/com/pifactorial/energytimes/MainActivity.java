package com.pifactorial.energytimes;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.pifactorial.energytimes.domain.DayWithoutPlanException;
import com.pifactorial.energytimes.domain.PlanNotFoundException;
import com.pifactorial.energytimes.domain.Populate;
import com.pifactorial.energytimes.domain.Schedule;

/**
 * The entry point to the BasicNotification sample.
 */
public class MainActivity extends Activity {
	/**
	 * A numeric value that identifies the notification that we'll be sending.
	 * This value needs to be unique within this app, but it doesn't need to be
	 * unique system-wide.
	 */
	public static final int NOTIFICATION_ID = 1;
	public static final int REFRESH_TIME_IN_MINUTES = 1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		Timer timer = new Timer();
		TimerTask hourlyTask = new TimerTask() {
			@Override
			public void run() {
				Log.d(Constants.LOG, "Timer called schedule ");
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						setCurrentTime();
					}
				});
			}
		};

		// schedule the task to run starting now and then every hour...
		timer.schedule(hourlyTask, 0l, 1000 * 60 * REFRESH_TIME_IN_MINUTES);

        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.plans_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
	}

    @Override
	public void onResume() {
        super.onResume();
    }

	public void setCurrentTime() {
		TextView tvPlan = (TextView) findViewById(R.id.plan);
		TextView tvStart = (TextView) findViewById(R.id.start);
		TextView tvEnd = (TextView) findViewById(R.id.end);

		Time now = new Time();
		now.setToNow();
		tvCurrentPeriod.setText("");

		Populate state = new Populate();

		try {
			Schedule s = state.edp.checkCurrentSchedule(now,
					"BTN Ciclo Semanal");
			tvPlan.setText(String.format("%s", s.getPrice().getPricePlan()));
			int startHour = s.getHours().getStartHour();
			int startMinute = s.getHours().getStartMinute();
			int endHour = s.getHours().getEndHour();
			int endMinute = s.getHours().getEndMinute();

			tvStart.setText(String.format("%02dh%02dm", startHour, startMinute));
			tvEnd.setText(String.format("%02dh%02dm", endHour, endMinute));

			Log.d(Constants.LOG, "Found schedule " + s.toString());
		} catch (DayWithoutPlanException e) {
			Log.e(Constants.LOG, e.getMessage());
            warnUser();
		} catch (PlanNotFoundException e) {
			Log.e(Constants.LOG, e.getMessage());
            warnUser();
		}
	}

    private void warnUser(){
		TextView tvCurrentPeriod = (TextView) findViewById(R.id.tvCurrentPeriod);
        tvCurrentPeriod.setText("Exception");
    }
}
