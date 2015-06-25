package com.example.shim.reminder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import android.app.Dialog;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity{

    public static String EXTRA_MESSAGE = "";
    String filename = "reminderList";
    TextView textViewOutput;
    EditText editTextInput;

    DatePicker pickerDate;
    TimePicker pickerTime;
    Button buttonSetAlarm;
    TextView info;
    EditText editItem;
    EditText editLocation;

    final static int RQS_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (TextView)findViewById(R.id.info);
        editItem = (EditText)findViewById(R.id.editItem);
        editLocation = (EditText)findViewById(R.id.editLocation);
        pickerDate = (DatePicker)findViewById(R.id.editTextDate);
        pickerTime = (TimePicker)findViewById(R.id.editTextTime);

        Calendar now = Calendar.getInstance();

        pickerDate.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);

        pickerTime.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        pickerTime.setCurrentMinute(now.get(Calendar.MINUTE));

        buttonSetAlarm = (Button)findViewById(R.id.setalarm);
        buttonSetAlarm.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Calendar current = Calendar.getInstance();

                Calendar cal = Calendar.getInstance();
                cal.set(pickerDate.getYear(),
                        pickerDate.getMonth(),
                        pickerDate.getDayOfMonth(),
                        pickerTime.getCurrentHour(),
                        pickerTime.getCurrentMinute(),
                        00);

                if(cal.compareTo(current) <= 0){
                    Toast.makeText(getApplicationContext(),
                            "Invalid Date/Time",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Reminder saved SUCCESSFULLY" , Toast.LENGTH_LONG).show();
                    setAlarm(cal);
                }

            }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setAlarm(Calendar targetCal){


        info.setText("Reminder is set at " + targetCal.getTime() + " to buy "  + editItem.getText().toString() + " at " + editLocation.getText().toString() );

        EXTRA_MESSAGE = info.getText().toString();

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);

        intent.putExtra(EXTRA_MESSAGE, info.getText().toString());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);




    }

}
