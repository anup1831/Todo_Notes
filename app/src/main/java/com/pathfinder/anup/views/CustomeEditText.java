package com.pathfinder.anup.views;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.pathfinder.anup.todo.R;

import java.lang.reflect.Field;

/**
 * Created by Anup on 7/5/2017.
 */

public class CustomeEditText extends android.support.v7.widget.AppCompatEditText {

    private boolean flag;
    private Drawable drawableRight;

    public CustomeEditText(Context context) {
        super(context);
    }

    public CustomeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    private void init(){
        drawableRight = getResources().getDrawable(R.drawable.ic_error);
        drawableRight.setBounds(0,0, drawableRight.getIntrinsicWidth(), drawableRight.getIntrinsicHeight());
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        setMaxLines(1);
        setTextColor(getResources().getColor(R.color.black));
        setHintTextColor(getResources().getColor(R.color.grey));
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        setCursorVisible(true);

        setLongClickable(false);
        setTextIsSelectable(false);
    }

    public void setTextWatcher(){
        addTextChangedListener(new CustomTextWatcher());
    }

    class CustomTextWatcher implements TextWatcher {

        public CustomTextWatcher() {
            flag = false;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    public boolean isEmpty(/*String str*/){
        if(getText().toString().trim().equals("")){
            setDrawableRight();
            setFlag(true);
            return true;
        }
        return false;
    }

    private void setDrawableRight(){
        setCompoundDrawables(null, null, drawableRight, null);
        requestFocus();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            this.setInsertionDisabled();
        }
        return super.onTouchEvent(event);
    }

    private void setInsertionDisabled() {
        try {
            Field editorField = TextView.class.getDeclaredField("mEditor");
            editorField.setAccessible(true);
            Object editorObject = editorField.get(this);

            Class editorClass = Class.forName("android.widget.Editor");
            Field mInsertionControllerEnabledField = editorClass.getDeclaredField("mInsertionControllerEnabled");
            mInsertionControllerEnabledField.setAccessible(true);
            mInsertionControllerEnabledField.set(editorObject, false);
        }
        catch (Exception ignored) {
            // ignore exception here
        }
    }
}
