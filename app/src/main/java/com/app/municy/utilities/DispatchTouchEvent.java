package com.app.municy.utilities;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class DispatchTouchEvent extends LinearLayout {

    public interface onDispatchEvent
    {
        void dispatchEvent(MotionEvent e);
    }

    private onDispatchEvent dispatchEvent;

    public DispatchTouchEvent(Context context) {
        super(context);
    }

    public DispatchTouchEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchTouchEvent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDispatchEvent(onDispatchEvent dispatchEvent)
    {
        this.dispatchEvent=dispatchEvent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(dispatchEvent!=null)
        {
            dispatchEvent.dispatchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

}