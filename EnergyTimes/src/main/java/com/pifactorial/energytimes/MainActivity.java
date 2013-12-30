package com.pifactorial.energytimes;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pifactorial.energytimes.domain.DayWithoutPlanException;
import com.pifactorial.energytimes.domain.Plan;
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }

    /**
     * Send a sample notification using the NotificationCompat API.
     */
    public void sendNotification(View view) {
        Log.e(Constants.LOG, "Started");

        /** Create an intent that will be fired when the user clicks the notification.
         * The intent needs to be packaged into a {@link android.app.PendingIntent} so that the
         * notification service can fire it on our behalf.
         */
        // Intent intent = new Intent(
        //         Intent.ACTION_VIEW,
        //         Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        // PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
        //         intent, 0);

        // // BEGIN_INCLUDE (build_notification)
        // /**
        //  * Use NotificationCompat.Builder to set up our notification.
        //  */
        // NotificationCompat.Builder builder = new NotificationCompat.Builder(
        //         this);

        // /** Set the icon that will appear in the notification bar. This icon also appears
        //  * in the lower right hand corner of the notification itself.
        //  *
        //  * Important note: although you can use any drawable as the small icon, Android
        //  * design guidelines state that the icon should be simple and monochrome. Full-color
        //  * bitmaps or busy images don't render well on smaller screens and can end up
        // builder.setSmallIcon(R.drawable.ic_stat_notification);

        // // Set the intent that will fire when the user taps the notification.
        // builder.setContentIntent(pendingIntent);

        // // Set the notification to auto-cancel. This means that the notification will disappear
        // // after the user taps it, rather than remaining until it's explicitly dismissed.
        // builder.setAutoCancel(true);

        // /**
        //  *Build the notification's appearance.
        //  * Set the large icon, which appears on the left of the notification. In this
        //  * sample we'll set the large icon to be the same as our app icon. The app icon is a
        //  * reasonable default if you don't have anything more compelling to use as an icon.
        //  */
        // builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
        //         R.drawable.ic_launcher));

        // /**
        //  * Set the text of the notification. This sample sets the three most commononly used
        //  * text areas:
        //  * 1. The content title, which appears in large type at the top of the notification
        //  * 2. The content text, which appears in smaller text below the title
        //  * 3. The subtext, which appears under the text on newer devices. Devices running
        //  *    versions of Android prior to 4.2 will ignore this field, so don't use it for
        //  *    anything vital!
        //  */
        // builder.setContentTitle("BasicNotifications Sample");
        // builder.setContentText("Time to learn about notifications!");
        // builder.setSubText("Tap to view documentation about notifications.");

        // // END_INCLUDE (build_notification)

        // /**
        //  * Send the notification. This will immediately display the notification icon in the
        //  * notification bar.
        //  */
        // NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // notificationManager.notify(NOTIFICATION_ID, builder.build());

        /* Set current time */
        setCurrentTime();
    }

    public void setCurrentTime() {
        TextView textView = (TextView) findViewById(R.id.currentTime);

        Time now = new Time();
        now.setToNow();
        textView.setText(now.format2445());
        Log.e(Constants.LOG, "Mudei");

        Populate state = new Populate();

        try {
            Schedule s = state.edp.checkCurrentSchedule(now, "BTN Ciclo Semanal");
            textView.setText(String.format("%s ---> %s", now.format2445(), s.toString()));
            Log.d(Constants.LOG, "Found schedule " + s.toString());
        } catch (DayWithoutPlanException e) {
            Log.e(Constants.LOG, e.getMessage());
        } catch (PlanNotFoundException e) {
            Log.e(Constants.LOG, e.getMessage());
        }
    }
}
