# SimpleDayPicker
A simple material design week day picker just like Time and Date pickers of Android support library. It's simple, light weight and works just like native Date and Time pickers you are used to.

![dayPickerView.png](demo-images/s1.png) ![dayPickerView.png](demo-images/s2.png)
![dayPickerView.png](demo-images/s3.png) ![dayPickerView.png](demo-images/s4.png)

You can use it as a view like any other view and also define it in XML code like below.

### XML

```
<com.github.informramiz.daypickerlibrary.views.DayPickerView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:isMultiSelectionAllowed="true"/>
```

If you want to use it as a dialog like you use native Android Date/Time pickers, it's simple.

```
DayPickerDialog.Builder builder = new DayPickerDialog.Builder(this)
                .setMultiSelectionAllowed(false)
                .setOnDaysSelectedListener(new DayPickerDialog.OnDaysSelectedListener() {
                    @Override
                    public void onDaysSelected(DayPickerView dayPickerView, boolean[] selectedDays) {
							//do something with selected days
                    }
                });
builder.build().show();
```

###Theme 

**There are no hardcoded colors**, just like native Android pickers, this picker uses the theme colors you will define in your theme, including text and background colors. So you don't have to specify color manually, **the picker will automatically adapt to your app theme**. Still, if you prefer, you can specify a theme just any other alert dialog.