package com.mac.maskedittext;


import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneEditText extends CommonEditTextLayout {
    public PhoneEditText(final Context context) {
        super(context);
        init();
    }

    public PhoneEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PhoneEditText(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

   /* public void setText(String text){
        if(getEditText().getInputType() == InputType.TYPE_CLASS_PHONE){
            StringBuilder builder = new StringBuilder(text);
            char[] textChars = text.toCharArray();
            if(textChars.length > 0 && textChars[0] != '('){
                builder.insert(0,"(");
            }
            if(textChars.length > 4 && textChars[4] != ')'){
                builder.insert(4,") ");
            }
            if(textChars.length > 6 && textChars[6] != '-'){
                builder.insert(9,"-");
            }
            getEditText().setText(builder.toString());
        }
        else{
            super.setText(text);
        }
    }

    public void setPhoneNumber() {
        String regex = "\\d+";

        getEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        InputFilter phoneFilter = (source, start, end, dest, dstart, dend) -> {
            if(source.length() > 1){
                return null;
            }
            Matcher matcher = Pattern.compile(regex).matcher(source);
            if(!matcher.matches()){
                return "";
            }
            if (dest.length() == 0 && source.length() > 0) {
                return "(";
            } else if (dest.length() == 3 && source.length() > 0) {
                return source + ") ";
            } else if (dest.length() == 8 && source.length() > 0) {
                return source + "-";
            } else if (dest.length() == 14) {
                return "";
            }
            return null;
        };
        getEditText().setFilters(new InputFilter[]{phoneFilter});
    }

    public void setInputTypeNumber() {
        getEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
    }*/

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        super.setOnTouchListener(null);
        super.setOnFocusChangeListener(null);
        getEditText().setOnFocusChangeListener(null);
        getEditText().setOnTouchListener(null);
        getEditText().setTextSize(20);
        getEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        //setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        getEditText().addTextChangedListener(setMaskInput());
    }
    public TextWatcher setMaskInput(){
        return  new MaskWatcher("(###) ###-####");
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onFocusChange(final View view, final boolean hasFocus) {
    }

    @Override
    public boolean onTouch(final View view, final MotionEvent motionEvent) {

        return true;
    }
}
