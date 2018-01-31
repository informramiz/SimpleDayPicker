package com.github.informramiz.daypickerlibrary.views;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import com.github.informramiz.daypickerlibrary.R;

/**
 * Created by ramiz on 1/31/18.
 */

public class DayPickerDialog extends AlertDialog implements DialogInterface.OnClickListener {
    private static final String KEY_SELECTED_DAYS = "selectedDays";
    private static final String KEY_IS_MULTI_SELECTION_ALLOWED = "isMultiSelectionAllowed";

    private DayPickerView mDayPickerView;
    @Nullable
    private boolean[] mInitialSelectedDays = new boolean[DayPickerView.TOTAL_DAYS];
    private boolean mIsMultiSelectionAllowed = true;
    @Nullable
    private OnDaysSelectedListener mOnDaysSelectedListener;

    /**
     * Callback to indicate that user is done selecting days
     */
    public interface OnDaysSelectedListener {
        /**
         *
         * @param dayPickerView the view associated with this listener
         * @param selectedDays a 7-element array(SUNDAY=0, SATURDAY=6) with days that are selected
         * set as true
         */
        void onDaysSelected(DayPickerView dayPickerView, boolean[] selectedDays);
    }

    public DayPickerDialog(Builder builder) {
        super(builder.context);
        this.mInitialSelectedDays = builder.initialSelectedDays;
        this.mOnDaysSelectedListener = builder.onDaysSelectedListener;
        this.mIsMultiSelectionAllowed = builder.isMultiSelectionAllowed;
        View view = LayoutInflater.from(getContext()).inflate(R.layout.day_picker_dialog, null);
        setView(view);

        setButton(BUTTON_POSITIVE, getContext().getString(android.R.string.ok), this);
        setButton(BUTTON_NEGATIVE, getContext().getString(android.R.string.cancel), this);

        mDayPickerView = view.findViewById(R.id.dayPickerView);
        if (mInitialSelectedDays != null) {
            mDayPickerView.setDaysSelected(mInitialSelectedDays);
        }
        mDayPickerView.setMultiSelectionAllowed(mIsMultiSelectionAllowed);
        mDayPickerView.setOnDaysSelectionChangedListener((dayPickerView, selectedDays1) -> {
            Log.i(DayPickerDialog.class.getSimpleName(), "Days selection changed");
        });
    }

    @Override
    public void show() {
        super.show();

        getButton(BUTTON_POSITIVE).setOnClickListener(v -> {
            if (mDayPickerView.validateInput()) {
                onClick(DayPickerDialog.this, BUTTON_POSITIVE);
                dismiss();
            }
        });
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case BUTTON_POSITIVE:
                if (mOnDaysSelectedListener != null) {
                    mOnDaysSelectedListener.onDaysSelected(mDayPickerView, mDayPickerView.getSelectedDays());
                }
                break;

            case BUTTON_NEGATIVE:
                cancel();
                break;
        }
    }

    public void setInitialSelectedDays(@NonNull boolean[] initialSelectedDays) {
        this.mInitialSelectedDays = initialSelectedDays;
    }

    public void setIsMultiSelectionAllowed(boolean isMultiSelectionAllowed) {
        this.mIsMultiSelectionAllowed = isMultiSelectionAllowed;
    }

    @NonNull
    @Override
    public Bundle onSaveInstanceState() {
        Bundle state = super.onSaveInstanceState();
        state.putBooleanArray(KEY_SELECTED_DAYS, mDayPickerView.getSelectedDays());
        state.putBoolean(KEY_IS_MULTI_SELECTION_ALLOWED, mDayPickerView.isMultiSelectionAllowed());
        return state;
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        boolean[] selectedDays = savedInstanceState.getBooleanArray(KEY_SELECTED_DAYS);
        mDayPickerView.setDaysSelected(selectedDays);
        mDayPickerView.setMultiSelectionAllowed(savedInstanceState.getBoolean(KEY_IS_MULTI_SELECTION_ALLOWED));
    }

    public static class Builder {
        @NonNull
        Context context;
        @Nullable
        boolean[] initialSelectedDays = null;
        boolean isMultiSelectionAllowed = true;
        @Nullable
        OnDaysSelectedListener onDaysSelectedListener = null;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        public Builder setInitialSelectedDays(@Nullable boolean[] initialSelectedDays) {
            this.initialSelectedDays = initialSelectedDays;
            return this;
        }

        public Builder setMultiSelectionAllowed(boolean multiSelectionAllowed) {
            isMultiSelectionAllowed = multiSelectionAllowed;
            return this;
        }

        public Builder setOnDaysSelectedListener(@Nullable OnDaysSelectedListener onDaysSelectedListener) {
            this.onDaysSelectedListener = onDaysSelectedListener;
            return this;
        }

        public DayPickerDialog build() {
            return new DayPickerDialog(this);
        }

        public void show() {
            build().show();
        }
     }

}