package com.mac.maskedittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CommonEditTextLayout extends TextInputLayout implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {

    protected Drawable mClearTextIcon;
    private View.OnFocusChangeListener mOnFocusChangeListener;
    private View.OnTouchListener mOnTouchListener;
    private TextInputEditText editText;
    private boolean showClearButton = false;

    public void showClearButton(boolean show) {
        showClearButton = show;
        setClearIconVisible(editText.getText().length() > 0);
    }
    public CommonEditTextLayout(final Context context) {
        super(context);
        init(context);
    }

    public CommonEditTextLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CommonEditTextLayout(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void setInputType(int inputType){
        editText.setInputType(inputType);
    }

    public EditText getEditText(){
        return editText;
    }

    public void setCursorVisible(boolean cursorVisible){
        getEditText().setCursorVisible(cursorVisible);
    }

    public void clear(){
        if(editText.getText() != null)
            editText.getText().clear();
    }

    public void setText(String text){
        editText.setText(text);
    }

    public String getText(){
        return editText.getText().toString();
    }

    public void setRawInputType(int rawInputType){
        editText.setRawInputType(rawInputType);
    }

    @Override
    public void setOnFocusChangeListener(final View.OnFocusChangeListener onFocusChangeListener) {
        mOnFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void setOnTouchListener(final View.OnTouchListener onTouchListener) {
        mOnTouchListener = onTouchListener;
    }

    private void init(final Context context) {
        editText = new TextInputEditText(getContext());
        final Drawable drawable = ContextCompat.getDrawable(context, R.mipmap.iconcross);
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable); //Wrap the drawable so that it can be tinted pre Lollipop
        DrawableCompat.setTint(wrappedDrawable, editText.getCurrentHintTextColor());
        mClearTextIcon = wrappedDrawable;
        mClearTextIcon.setBounds(0, 0, mClearTextIcon.getIntrinsicHeight(), mClearTextIcon.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        createEditText(editText);
    }

    public void setDrawableEnd(Drawable drawableEnd){
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawableEnd); //Wrap the drawable so that it can be tinted pre Lollipop
        DrawableCompat.setTint(wrappedDrawable, editText.getCurrentHintTextColor());
        wrappedDrawable.setBounds(0, 0, wrappedDrawable.getIntrinsicHeight(), wrappedDrawable.getIntrinsicHeight());
        final Drawable[] compoundDrawables = editText.getCompoundDrawables();
        editText.setCompoundDrawables(
                compoundDrawables[0],
                compoundDrawables[1],
                wrappedDrawable,
                compoundDrawables[3]);
    }

    private void createEditText(TextInputEditText editText){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        editText.setLayoutParams(layoutParams);
        editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_CLASS_TEXT);
        editText.addTextChangedListener(this);
        addView(editText);
    }

    protected void setClearIconVisible(final boolean visible) {
        if(showClearButton) {
            final Drawable drawable = ContextCompat.getDrawable(getContext(), R.mipmap.iconcross);
            final Drawable wrappedDrawable = DrawableCompat.wrap(drawable); //Wrap the drawable so that it can be tinted pre Lollipop
            DrawableCompat.setTint(wrappedDrawable, editText.getCurrentHintTextColor());
            mClearTextIcon = wrappedDrawable;
            mClearTextIcon.setBounds(0, 0, mClearTextIcon.getIntrinsicHeight(), mClearTextIcon.getIntrinsicHeight());
            mClearTextIcon.setVisible(visible, false);
            final Drawable[] compoundDrawables = editText.getCompoundDrawables();
            editText.setCompoundDrawables(
                    compoundDrawables[0],
                    compoundDrawables[1],
                    visible ? mClearTextIcon : null,
                    compoundDrawables[3]);
        }
    }

    public void showSpinnerArrow(boolean visible){
        final Drawable bottomArrow = getContext().getDrawable( R.drawable.grey_bottom_arrow);
        final Drawable wrappedDrawable = DrawableCompat.wrap(bottomArrow); //Wrap the drawable so that it can be tinted pre Lollipop
        DrawableCompat.setTint(wrappedDrawable, editText.getCurrentHintTextColor());
        wrappedDrawable.setBounds(0, 0, wrappedDrawable.getIntrinsicHeight(), wrappedDrawable.getIntrinsicHeight());
        final Drawable[] compoundDrawables = editText.getCompoundDrawables();
        editText.setCompoundDrawables(
                compoundDrawables[0],
                compoundDrawables[1],
                visible ? wrappedDrawable : null,
                compoundDrawables[3]);
    }

    public void showCalendarIcon(boolean visible){
        final Drawable calendarIcon = getContext().getDrawable( R.drawable.calendar);
        final Drawable wrappedDrawable = DrawableCompat.wrap(calendarIcon); //Wrap the drawable so that it can be tinted pre Lollipop
        DrawableCompat.setTint(wrappedDrawable, editText.getCurrentHintTextColor());
        wrappedDrawable.setBounds(0, 0, wrappedDrawable.getIntrinsicHeight(), wrappedDrawable.getIntrinsicHeight());
        final Drawable[] compoundDrawables = editText.getCompoundDrawables();
        editText.setCompoundDrawables(
                compoundDrawables[0],
                compoundDrawables[1],
                visible ? wrappedDrawable : null,
                compoundDrawables[3]);
    }

    @Override
    public final void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
        //if (isFocused()) {
            setClearIconVisible(s.length() > 0);
        //}
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        setClearIconVisible(s.length() > 0);
    }

    @Override
    public void onFocusChange(final View view, final boolean hasFocus) {
        editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        if (hasFocus) {
            setClearIconVisible(editText.getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(view, hasFocus);
        }
    }

    @Override
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        final int x = (int) motionEvent.getX();
        if (mClearTextIcon.isVisible() && x > getWidth() - getPaddingRight() - mClearTextIcon.getIntrinsicWidth()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                setError(null);
                clear();
                setClearIconVisible(false);
            }
            return true;
        }
        return mOnTouchListener != null && mOnTouchListener.onTouch(view, motionEvent);
    }


}
