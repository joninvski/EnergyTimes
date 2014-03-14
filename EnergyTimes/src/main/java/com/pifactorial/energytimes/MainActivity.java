package com.pifactorial.energytimes;

import android.app.Activity;

import android.content.Context;
import android.content.SharedPreferences;

import android.graphics.Color;

import android.os.Build;
import android.os.Bundle;

import android.text.format.Time;

import android.util.Log;
import android.util.TypedValue;

import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.pifactorial.energytimes.domain.DayWithoutPlanException;
import com.pifactorial.energytimes.domain.PlanNotFoundException;
import com.pifactorial.energytimes.domain.Populate;
import com.pifactorial.energytimes.domain.PricePlan;
import com.pifactorial.energytimes.domain.Schedule;

import java.util.Timer;
import java.util.TimerTask;

import junit.runner.Version;
import android.content.BroadcastReceiver;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;


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
    public static final String PLAN_SHARED_PREFERENCE = "planPreference";

    private String selectedPlan = "BTN Ciclo Semanal";
    private SharedPreferences mPrefs;
    private Spinner spinner;
    private int sdk_version;

    TextView tvVazio;
    TextView tvCheia;
    TextView tvPonta;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        sdk_version = android.os.Build.VERSION.SDK_INT;

        // Store view references
        tvVazio = (TextView) findViewById(R.id.vazio);
        tvCheia = (TextView) findViewById(R.id.cheias);
        tvPonta = (TextView) findViewById(R.id.ponta);
        spinner = (Spinner) findViewById(R.id.planets_spinner);

        // Get a reference to the preferences
		mPrefs = getPreferences(Context.MODE_PRIVATE);

        // Create an update schedule thread
        Timer timer = new Timer();
        TimerTask hourlyTask = new TimerTask() {
            @Override
            public void run() {
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

        // Configure the spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.plans_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id){
                Log.d(Constants.LOG, "Spinner selected pos: " + Integer.toString(position));

                if(position == 0){
                    selectedPlan = "BTN Ciclo Semanal";
                    setPlanPreference("BTN Ciclo Semanal");
                }
                else{
                    selectedPlan = "BTN Ciclo Diario";
                    setPlanPreference("BTN Ciclo Diario");
                }
                setCurrentTime();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView){
                Log.d(Constants.LOG, "Spinner with nothing selected");
            }
        });
    }

    private String getPlanPreference(){
        return mPrefs.getString(PLAN_SHARED_PREFERENCE, "BTN Ciclo Semanal");
    }

    private void setPlanPreference(String plan) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(PLAN_SHARED_PREFERENCE, plan);
        if( sdk_version < 9) {
            editor.commit(); // TODO - Check the return value
        }
        else{
            editor.apply();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        selectedPlan = getPlanPreference();
        setCurrentTime();
    }

    public void setCurrentTime() {
        TextView tvStart = (TextView) findViewById(R.id.start);
        TextView tvEnd = (TextView) findViewById(R.id.end);

        Time now = new Time();
        now.setToNow();

        Populate state = new Populate();

        try {
            Schedule s = state.edp.checkCurrentSchedule(now, selectedPlan);
            PricePlan price = s.getPrice();

            if(price.isVazio()) {
                highlight(tvVazio);
            }

            if(price.isCheia()) {
                highlight(tvCheia);
            }

            if(price.isPonta()) {
                highlight(tvPonta);
            }


            if(selectedPlan.equals("BTN Ciclo Semanal")){
                spinner.setSelection(0);
            }
            else{
                spinner.setSelection(1);
            }

            int startHour = s.getHours().getStartHour();
            int startMinute = s.getHours().getStartMinute();
            int endHour = s.getHours().getEndHour();
            int endMinute = s.getHours().getEndMinute();

            tvStart.setText(String.format("%02dh%02dm", startHour, startMinute));
            tvEnd.setText(String.format("%02dh%02dm", endHour, endMinute));
        } catch (DayWithoutPlanException e) {
            Log.e(Constants.LOG, e.getMessage());
        } catch (PlanNotFoundException e) {
            Log.e(Constants.LOG, e.getMessage());
        }
    }

    private void removeAllHighlights() {
        tvVazio.setText(tvVazio.getText().toString());
        tvCheia.setText(tvCheia.getText().toString());
        tvPonta.setText(tvPonta.getText().toString());

        tvVazio.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
        tvCheia.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
        tvPonta.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);

        tvVazio.setTextColor(Color.DKGRAY);
        tvCheia.setTextColor(Color.DKGRAY);
        tvPonta.setTextColor(Color.DKGRAY);
    }

    private void highlight(TextView tv){
        removeAllHighlights();

        tv.setTextColor(Color.parseColor("#ffbb33"));
        SpannableString content = new SpannableString(tv.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tv.setText(content);

        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
    }

}
