package com.not_a_name.helloworld;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class MyView extends View {

    private LinkedList<LinkedList<Point>> lines,recycle;


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.CYAN);

        lines = new LinkedList<>();
        recycle = new LinkedList<>();
    }

    private class Point
    {
        float x,y;

        Point(float x,float y)
        {
            this.x=x;
            this.y=y;
        }

    }

    public void clear()
    {
        lines.clear();
        recycle.clear();
        invalidate();
    }

    public void undo()
    {
       // recycle.clear();
        if(lines.size()>0)
        {
            recycle.add(lines.removeLast());
            invalidate();
        }
    }
    public void redo()
    {
        if(recycle.size()>0)
        {
            lines.add(recycle.removeLast());
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX(), ey=event.getY();
        // Log.v("Not_A_Name",""+ex+":\t"+ey);
        Point point = new Point(ex,ey);

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                LinkedList<Point> line = new LinkedList<>();
                lines.add(line);

                Log.v("Not_A_Name","ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.v("Not_A_Name","ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.v("Not_A_Name","ACTION_MOVE");
                break;
            default:
                break;
        }

        lines.getLast().add(point);
        invalidate();
        return true;//super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
     //   canvas.drawLine(0,0,200,200,paint);

        for(LinkedList<Point>line : lines)
        {
            for(int i=1;i<line.size();i++) {
                Point p0 = line.get(i - 1);
                Point p1 = line.get(i);
                canvas.drawLine(p0.x, p0.y, p1.x, p1.y, paint);
            }
        }


    }
}
