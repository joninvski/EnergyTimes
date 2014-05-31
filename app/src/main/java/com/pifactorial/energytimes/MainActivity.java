package com.pifactorial.energytimes;

import android.app.Activity;

import android.content.Intent;

import android.graphics.Color;

import android.net.Uri;

import android.os.Bundle;


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

import butterknife.ButterKnife;

import butterknife.InjectView;

import com.pifactorial.energytimes.domain.DayWithoutPlanException;
import com.pifactorial.energytimes.domain.Edp;
import com.pifactorial.energytimes.domain.Period;
import com.pifactorial.energytimes.domain.PlanNotFoundException;
import com.pifactorial.energytimes.domain.PricePlan;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;


public class MainActivity extends Activity {
    public static final int REFRESH_TIME_IN_MINUTES = 1;
    public static final String PLAN_TYPE_HOUR = "planHour";

    private String selectedPlan = "BTN Ciclo Semanal";
    private ManagePreferences mPrefs;

    Edp edp = new Edp();

    @InjectView(R.id.vazio) TextView tvVazio;
    @InjectView(R.id.cheias) TextView tvCheia;
    @InjectView(R.id.ponta) TextView tvPonta;
    @InjectView(R.id.start) TextView tvStart;
    @InjectView(R.id.end) TextView tvEnd;
    @InjectView(R.id.planets_spinner) Spinner spinner;

    String typeHour;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        // Get a reference to the preferences
        mPrefs = new ManagePreferences(this);

        ButterKnife.inject(this);

        // Create an update Period thread
        Timer timer = new Timer();
        TimerTask updateUITask = new TimerTask() {
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
        timer.schedule(updateUITask, 0l, 1000 * 60 * REFRESH_TIME_IN_MINUTES);

        // Configure the spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.plans_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position == 0) {
                    selectedPlan = "BTN Ciclo Semanal";
                    mPrefs.setPlanPreference("BTN Ciclo Semanal");
                } else {
                    selectedPlan = "BTN Ciclo Diario";
                    mPrefs.setPlanPreference("BTN Ciclo Diario");
                }
                setCurrentTime();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Log.d(Constants.LOG, "Spinner with nothing selected");
            }
        });

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

        LocalTime nowHours = new LocalTime();
        LocalDate nowDate = new LocalDate();

        try {
            // TODO - Fetch the preference for this boolean
            typeHour = mPrefs.getTypeHourPlan();
            boolean biHour = (new TypeHourEnum(typeHour)).isBiHour();

            Period s = edp.checkCurrentPeriod(nowHours, nowDate, selectedPlan, biHour);
            PricePlan price = s.getPrice();

            if(biHour == false) {
                makeAllVisible();
                if(price.isVazio()) {
                    highlight(tvVazio);
                } else if(price.isCheia()) {
                    highlight(tvCheia);
                } else if(price.isPonta()) {
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

            if(selectedPlan.equals("BTN Ciclo Semanal")) {
                spinner.setSelection(0);
            } else {
                spinner.setSelection(1);
            }

            int startHour = s.getHours().getStartHour();
            int startMinute = s.getHours().getStartMinute();
            int endHour = s.getHours().getEndHour();
            int endMinute = s.getHours().getEndMinute();

            tvStart.setText(String.format(Locale.US, "%02dh%02dm", startHour, startMinute));
            tvEnd.setText(String.format(Locale.US, "%02dh%02dm", endHour, endMinute));
            Log.e(Constants.LOG, String.format(Locale.US, "Period: %s", s.toString()));

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

    private void highlight(TextView tv) {
        highlight(tv, true);
    }

    private void highlight(TextView tv, boolean onlyHighlight) {
        if(onlyHighlight) {
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

        case R.id.set_plan:
            Intent intentSetPref = new Intent(getApplicationContext(), HoursPreferenceActivity.class);
            startActivityForResult(intentSetPref, 1);
            break;

        case R.id.about:
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","trindade.joao@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "[energytimes] - Feedback");
            emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.feedback_text_body));
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_email_chooser)));
            break;

        default:
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
