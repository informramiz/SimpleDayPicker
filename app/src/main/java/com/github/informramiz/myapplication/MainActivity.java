package com.github.informramiz.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.github.informramiz.daypickerlibrary.views.DayPickerDialog;
import com.github.informramiz.daypickerlibrary.views.DayPickerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDayPickerDialog();
            }
        });

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
    }

    void showDayPickerDialog() {
        DayPickerDialog.Builder builder = new DayPickerDialog.Builder(this)
                .setMultiSelectionAllowed(true)
                .setOnDaysSelectedListener(new DayPickerDialog.OnDaysSelectedListener() {
                    @Override
                    public void onDaysSelected(DayPickerView dayPickerView, boolean[] selectedDays) {

                    }
                })
                ;
        builder.show();
    }

    void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, null, 0, 0, false);
        timePickerDialog.show();
    }

    void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, null, 0, 0, 0);
        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
