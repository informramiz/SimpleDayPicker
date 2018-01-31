package com.github.informramiz.daypickerlibrary.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.github.informramiz.daypickerlibrary.R;

/**
 * Created by ramiz on 1/31/18.
 */

public class CircularTextView extends AppCompatTextView implements View.OnClickListener {
    @Nullable
    private OnClickListener onClickListener = null;
    private boolean isAutoSelectEnabled = true;

    public CircularTextView(Context context) {
        super(context);
        init();
    }

    public CircularTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        setGravity(Gravity.CENTER);
        setBackgroundResource(R.drawable.circular_button_selector);
        super.setOnClickListener(this);
    }

    public boolean isAutoSelectEnabled() {
        return isAutoSelectEnabled;
    }

    public void setAutoSelectEnabled(boolean autoSelectEnabled) {
        isAutoSelectEnabled = autoSelectEnabled;
    }

    @Override
    public void onClick(View v) {
        if (isAutoSelectEnabled()) {
            handleAutoSelectEvent();
        }

        if (onClickListener != null) {
            onClickListener.onClick(v);
        }
    }

    private void handleAutoSelectEvent() {
        setActivated(!isActivated());
        if (isActivated()) {
            setTextColor(ContextCompat.getColor(getContext(), android.R.color.primary_text_dark));
        } else {
            setTextColor(ContextCompat.getColor(getContext(), android.R.color.tab_indicator_text));
        }
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}