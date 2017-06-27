package com.pathfinder.anup.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class NewTodoScreen extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private EditText mTodoTitle, mTodoDesc;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Button btnSave, mSetDate, mSetTime;
    private String btnText = new String();
    StringBuilder dateBuilder;// = new StringBuilder();


    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo_screen);

        this.setFinishOnTouchOutside(false);
        ViewGroup.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width  = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        initScreen();
    }

    private void initScreen(){
        mTodoTitle = (EditText) findViewById(R.id.todo_title);
        mTodoTitle.addTextChangedListener(this);
        mTodoDesc = (EditText) findViewById(R.id.todo_desc);
        mTodoDesc.addTextChangedListener(this);
        mSetDate = (Button) findViewById(R.id.todo_set_date);
        mSetDate.setOnClickListener(this);
        mSetTime = (Button) findViewById(R.id.todo_set_time);
        mSetTime.setOnClickListener(this);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        setBtnText(btnSave.getText().toString());
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_save && getBtnText().equalsIgnoreCase(getResources().getString(R.string.btn_save))){
            try {
                Log.i("Anup", "Anup - Save Clicked!");
                checkFieldValidation();
                Toast.makeText(getApplicationContext(), "Save", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        } else if(view.getId() == R.id.btn_save && getBtnText().equalsIgnoreCase(getResources().getString(R.string.btn_dismiss))){
            Log.i("Anup", "Anup - Dismiss Clicked!");
            this.finish();
        } else if(view.getId() == R.id.todo_set_date){
            String date = setDate();
            Log.i("Anup", "Anup - Date -"+date);
            mSetDate.setText(date);
        } else if (view.getId() == R.id.todo_set_time){
            String time = setTime();
        }
    }

    private void checkFieldValidation() throws ValidationException {
        if (mTodoTitle.getText().toString().isEmpty()) {
            throw new ValidationException("Title can not be empty!");
        } else if (mTodoDesc.getText().toString().isEmpty()) {
            throw new ValidationException("Description can not be empty!");
        } /*else if (mSetDate.getText().toString().isEmpty()) {
            throw new ValidationException("Date will help generate reports!");
        } else if(mSetDate.getText().toString().isEmpty()){
            throw new ValidationException("Time will remind the task");
        }*/
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setBtnText(btnSave.getText().toString());
        btnSave.setText(getResources().getString(R.string.btn_dismiss));
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(i < i2){
            btnSave.setText(getResources().getString(R.string.btn_save));
        } else {
            btnSave.setText(getResources().getString(R.string.btn_dismiss));
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        /**
         * */
       if(mTodoTitle.getText().length()> 0 ) {
            setBtnText(btnSave.getText().toString());
            btnSave.setText(getResources().getString(R.string.btn_save));
        } else {
            setBtnText( btnSave.getText().toString());
            btnSave.setText(getResources().getString(R.string.btn_dismiss));
        }

    }


    private class ValidationException extends Exception{
        public ValidationException(String message) {
            super(message);
        }
    }

    private String setDate(){
        dateBuilder = new StringBuilder();
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                mSetDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                dateBuilder.append(String.valueOf(dayOfMonth));
                dateBuilder.append("-");
                dateBuilder.append(String.valueOf(String.valueOf(monthOfYear  + 1)));
                dateBuilder.append("-");
                dateBuilder.append(String.valueOf(year));

                Toast.makeText(getApplicationContext(), dateBuilder, Toast.LENGTH_LONG).show();
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
        return  dateBuilder.toString();
    }

    private String setTime(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                mSetTime.setText(hour +" : " + minute);
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
        return "";
    }



}
