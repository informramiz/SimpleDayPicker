package com.github.informramiz.daypickerlibrary.views;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.github.informramiz.daypickerlibrary.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ramiz on 1/31/18.
 */

public class DayPickerView extends FrameLayout implements View.OnClickListener {
    private static final String LOG_TAG = DayPickerView.class.getSimpleName();

    public static final int DAY_SUNDAY = 0;
    public static final int DAY_MONDAY = 1;
    public static final int DAY_TUESDAY = 2;
    public static final int DAY_WEDNESDAY = 3;
    public static final int DAY_THURSDAY = 4;
    public static final int DAY_FRIDAY = 5;
    public static final int DAY_SATURDAY = 6;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(value = {DAY_SUNDAY, DAY_MONDAY, DAY_TUESDAY, DAY_WEDNESDAY, DAY_THURSDAY, DAY_FRIDAY, DAY_SATURDAY})
    @interface PickerDay{}
    private static final int TOTAL_DAYS = 7;

    private boolean isMultiSelectionAllowed = true;
    private CircularTextView[] dayViews = new CircularTextView[TOTAL_DAYS];

    public DayPickerView(@NonNull Context context) {
        super(context);
        init();
    }

    public DayPickerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DayPickerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_day_picker_view,
                this,
                true);
        dayViews[DAY_SUNDAY] = findViewById(R.id.view_sunday);
        dayViews[DAY_MONDAY] = findViewById(R.id.view_monday);
        dayViews[DAY_TUESDAY] = findViewById(R.id.view_tuesday);
        dayViews[DAY_WEDNESDAY] = findViewById(R.id.view_wednesday);
        dayViews[DAY_THURSDAY] = findViewById(R.id.view_thursday);
        dayViews[DAY_FRIDAY] = findViewById(R.id.view_friday);
        dayViews[DAY_SATURDAY] = findViewById(R.id.view_saturday);

        for (CircularTextView dayView : dayViews) {
            dayView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        decideOtherViewsSelection(v);
        Log.i(LOG_TAG, "Total days selected=" + countTrue(getSelectedDays()));
    }

    public void setDaySelected(@PickerDay int day, boolean isSelected) {
        dayViews[day].setSelected(isSelected);
        decideOtherViewsSelection(dayViews[day]);
    }

    public void setDaysSelected(@Size(TOTAL_DAYS) boolean[] selectedDays) {
        for (int i = 0; i < dayViews.length; i++) {
            dayViews[i].setSelected(selectedDays[i]);
        }
    }

    public boolean[] getSelectedDays() {
        boolean[] selectedDays = new boolean[TOTAL_DAYS];
        for (int i = 0; i < dayViews.length; i++) {
            selectedDays[i] = dayViews[i].isSelected();
        }

        return selectedDays;
    }

    public boolean isMultiSelectionAllowed() {
        return isMultiSelectionAllowed;
    }

    public void setMultiSelectionAllowed(boolean multiSelectionAllowed) {
        isMultiSelectionAllowed = multiSelectionAllowed;
    }

    private void decideOtherViewsSelection(View viewToIgnore) {
        if (isMultiSelectionAllowed) {
            return;
        }

        for (CircularTextView dayView : dayViews) {
            if (dayView != viewToIgnore) {
                dayView.setSelected(false);
            }
        }
    }

    private int countTrue(boolean[] values) {
        int count = 0;
        for (boolean value : values) {
            if (value) {
                count++;
            }
        }

        return count;
    }
}
