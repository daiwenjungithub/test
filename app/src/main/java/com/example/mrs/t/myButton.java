package com.example.mrs.t;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;



public class myButton extends Button
{

    private int cx,cy;
    private int radius;
    private Paint mPaint;
    private int paintColor;


    public myButton(Context context)
    {
        super(context);
        init();
    }

    public myButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public myButton(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void setPaintColor(int color){
        paintColor = color;
        mPaint.setColor(paintColor);
        invalidate();
    }
    public void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setText("");

    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        int width = getWidth();
        int hight = getHeight();

        cx = width/2;
        cy = hight/2;
        radius = cx < cy ? cx -10:cy-10;
        canvas.drawCircle(cx,cy,radius,mPaint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                setPaintColor(Color.YELLOW);
                return true;

            case MotionEvent.ACTION_UP:
                setPaintColor(Color.GREEN);
                return true;
        }
        return super.onTouchEvent(event);
    }
}
