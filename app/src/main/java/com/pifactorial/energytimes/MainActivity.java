package com.pifactorial.energytimes;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;

import android.os.Build;
import android.os.Bundle;

import android.preference.Preference;
import android.preference.PreferenceFragment;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;

import android.util.Log;
import android.util.TypedValue;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.pifactorial.energytimes.domain.DayWithoutPlanException;
import com.pifactorial.energytimes.domain.Edp;
import com.pifactorial.energytimes.domain.Period;
import com.pifactorial.energytimes.domain.PlanNotFoundException;
import com.pifactorial.energytimes.domain.PricePlan;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import junit.runner.Version;

import org.joda.time.DateTime;


public class MainActivity extends Activity {
    public static final int REFRESH_TIME_IN_MINUTES = 1;
    public static final String PLAN_TYPE_HOUR = "planHour";

    private String selectedPlan = "BTN Ciclo Semanal";
    private ManagePreferences mPrefs;
    private Spinner spinner;
    private int sdk_version;

    Edp edp = new Edp();

    private TextView tvVazio;
    private TextView tvCheia;
    private TextView tvPonta;
    private TextView tvStart;
    private TextView tvEnd;

    String typeHour;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        sdk_version = android.os.Build.VERSION.SDK_INT;

        // Store view references
        tvVazio = (TextView) findViewById(R.id.vazio);
        tvCheia = (TextView) findViewById(R.id.cheias);
        tvPonta = (TextView) findViewById(R.id.ponta);
        spinner = (Spinner) findViewById(R.id.planets_spinner);

        tvStart = (TextView) findViewById(R.id.start);
        tvEnd = (TextView) findViewById(R.id.end);

        // Get a reference to the preferences
        mPrefs = new ManagePreferences(this);

        // Create an update Period thread
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

        // Period the task to run starting now and then every hour...
        timer.schedule(hourlyTask, 0l, 1000 * 60 * REFRESH_TIME_IN_MINUTES);

        // Configure the spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.plans_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        if(position == 0) {
                            selectedPlan = "BTN Ciclo Semanal";
                            mPrefs.setPlanPreference("BTN Ciclo Semanal");
                        }
                        else {
                            selectedPlan = "BTN Ciclo Diario";
                            mPrefs.setPlanPreference("BTN Ciclo Diario");
                        }
                    setCurrentTime();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        Log.d(Constants.LOG, "Spinner with nothing selected");
                    }});

        // Make sure that the the current plan time fits in a single line
        ViewTreeObserver vto = tvEnd.getViewTreeObserver();
        ViewTreeObserver.OnGlobalLayoutListener listener = new ViewTreeObserver.OnGlobalLayoutListener () {
            @Override
            public void onGlobalLayout() {
                final int MIN_PIXEL_SIZE = 10;
                if (tvEnd.getLineCount() > 1 && tvEnd.getTextSize() > MIN_PIXEL_SIZE) {
                    tvStart.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvStart.getTextSize() - 1);
                    tvEnd.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvEnd.getTextSize() - 1);
                }
            }
        };
        vto.addOnGlobalLayoutListener(listener);
    }

    @Override
    public void onResume() {
        super.onResume();
        selectedPlan = mPrefs.getPlanPreference();
        setCurrentTime();
    }

    public void setCurrentTime() {

        DateTime now = new DateTime();

        try {
            // TODO - Fetch the preference for this boolean
            typeHour = mPrefs.getTypeHourPlan();
            boolean biHour = (new TypeHourEnum(typeHour)).isBiHour();

            Period s = edp.checkCurrentPeriod(now, selectedPlan, biHour);
            PricePlan price = s.getPrice();

            if(biHour == false){
                makeAllVisible();
                if(price.isVazio()) {
                    highlight(tvVazio);
                }
                else if(price.isCheia()) {
                    highlight(tvCheia);
                }
                else if(price.isPonta()) {
                    highlight(tvPonta);
                }
            }

            else if(biHour == true) {
                erase(tvCheia);
                if(price.isVazio()) {
                    highlight(tvVazio);
                }

                else {
                    highlight(tvPonta);
                }
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

            tvStart.setText(String.format(Locale.US, "%02dh%02dm", startHour, startMinute));
            tvEnd.setText(String.format(Locale.US, "%02dh%02dm", endHour, endMinute));

        } catch (DayWithoutPlanException e) {
            Log.e(Constants.LOG, e.getMessage());
        } catch (PlanNotFoundException e) {
            Log.e(Constants.LOG, e.getMessage());
        }
    }

    private void makeAllVisible() {
        tvVazio.setVisibility(View.VISIBLE);
        tvCheia.setVisibility(View.VISIBLE);
        tvPonta.setVisibility(View.VISIBLE);
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
        highlight(tv, true);
    }

    private void highlight(TextView tv, boolean onlyHighlight) {
        if(onlyHighlight){
            removeAllHighlights();
        }

        tv.setTextColor(Color.parseColor("#ffbb33"));
        SpannableString content = new SpannableString(tv.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tv.setText(content);

        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
    }

    private void erase(TextView tv) {
        tv.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if( sdk_version >= 14) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.options, menu);
            return super.onCreateOptionsMenu(menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.set_plan:
                Intent intentSetPref = new Intent(getApplicationContext(), HoursPreferenceActivity.class);
                startActivityForResult(intentSetPref, 1);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
