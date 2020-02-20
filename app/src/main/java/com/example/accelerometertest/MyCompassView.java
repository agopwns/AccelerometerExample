package com.example.accelerometertest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyCompassView extends View {

    private Paint paint;
    private float xAccelerate = 0;
    private float yAccelerate = 0;
    private float zAccelerate = 0;

    public MyCompassView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setTextSize(35);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int xPoint = getMeasuredWidth() / 2;
        int yPoint = getMeasuredHeight() / 2;

        float radius = (float) (Math.max(xPoint, yPoint) * 0.4);
        canvas.drawCircle(xPoint, yPoint, radius, paint);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        // 3.143 is a good approximation for the circle
        canvas.drawLine(
                xPoint,
                yPoint,
                (float) (xPoint + radius
                        * Math.sin((double) (-xAccelerate) / 180 * 3.143)),
                (float) (yPoint - radius
                        * Math.cos((double) (-xAccelerate) / 180 * 3.143)), paint);

        canvas.drawText(String.valueOf(xAccelerate), xPoint, yPoint, paint);
        canvas.drawText(String.valueOf(yAccelerate), xPoint, yPoint + 35, paint);
        canvas.drawText(String.valueOf(zAccelerate), xPoint, yPoint + 70, paint);
    }

    public void updateData(float xAccelerate,float yAccelerate,float zAccelerate) {
        this.xAccelerate = xAccelerate;
        this.yAccelerate = yAccelerate;
        this.zAccelerate = zAccelerate;
        invalidate();
    }

}