package com.example.sensorassignment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class PlotViewLight extends View {

    ArrayList<Float> al = new ArrayList<Float>(10);
    ArrayList<Integer> time = new ArrayList<>(10);
    ArrayList<Float> sVals = new ArrayList<>(10);
    ArrayList<Float> StDev = new ArrayList<>(10);



    public PlotViewLight(Context context) {
        super(context);
    }

    public PlotViewLight(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlotViewLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PlotViewLight(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint blue = new Paint();
        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.FILL);

        Paint green = new Paint();
        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.FILL);

        Paint greenline = new Paint();
        greenline.setColor(Color.GREEN);
        greenline.setStyle(Paint.Style.STROKE);
        greenline.setStrokeWidth(6f);

        Paint yellow = new Paint();
        yellow.setColor(Color.YELLOW);
        yellow.setStyle(Paint.Style.FILL);

        Paint yellowline = new Paint();
        yellowline.setColor(Color.YELLOW);
        yellowline.setStyle(Paint.Style.STROKE);
        yellowline.setStrokeWidth(6f);

        Paint black = new Paint();
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.STROKE);
        black.setStrokeWidth(4f);

        Paint blackText = new Paint();
        blackText.setColor(Color.BLACK);
        blackText.setStyle(Paint.Style.FILL);
        blackText.setTextSize(50);

        Paint blackmargins = new Paint();
        blackmargins.setColor(Color.BLACK);
        blackmargins.setStyle(Paint.Style.FILL);
        blackmargins.setTextSize(20);

        Paint blueline = new Paint();
        blueline.setColor(Color.BLUE);
        blueline.setStyle(Paint.Style.STROKE);
        blueline.setStrokeWidth(6f);

        Paint blackline = new Paint();
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.FILL);


        canvas.drawLine(200, 600, 750, 600, black);
        canvas.drawLine(200, 600, 200, 100, black);
        canvas.drawText("Time Passed (x100 ms)", 270, 750, blackText);
        canvas.drawText("Light", 10, 320, blackText);
        canvas.drawText("(lux)", 10, 380, blackText);

        canvas.drawText(time.get(0).toString(), 200, 660, blackmargins);
        canvas.drawText(time.get(1).toString(), 255, 660, blackmargins);
        canvas.drawText(time.get(2).toString(), 310, 660, blackmargins);
        canvas.drawText(time.get(3).toString(), 365, 660, blackmargins);
        canvas.drawText(time.get(4).toString(), 420, 660, blackmargins);
        canvas.drawText(time.get(5).toString(), 475, 660, blackmargins);
        canvas.drawText(time.get(6).toString(), 530, 660, blackmargins);
        canvas.drawText(time.get(7).toString(), 585, 660, blackmargins);
        canvas.drawText(time.get(8).toString(), 640, 660, blackmargins);
        canvas.drawText(time.get(9).toString(), 695, 660, blackmargins);

        canvas.drawLine(255, 600, 255, 100, blackline);
        canvas.drawLine(310, 600, 310, 100, blackline);
        canvas.drawLine(365, 600, 365, 100, blackline);
        canvas.drawLine(420, 600, 420, 100, blackline);
        canvas.drawLine(475, 600, 475, 100, blackline);
        canvas.drawLine(530, 600, 530, 100, blackline);
        canvas.drawLine(585, 600, 585, 100, blackline);
        canvas.drawLine(640, 600, 640, 100, blackline);
        canvas.drawLine(695, 600, 695, 100, blackline);
        canvas.drawLine(750, 600, 750, 100, blackline);

        canvas.drawText("0", 140, 600, blackmargins);
        canvas.drawText("60", 140, 550, blackmargins);
        canvas.drawText("120", 140, 500, blackmargins);
        canvas.drawText("180", 140, 450, blackmargins);
        canvas.drawText("240", 140, 400, blackmargins);
        canvas.drawText("300", 140, 350, blackmargins);
        canvas.drawText("360", 140, 300, blackmargins);
        canvas.drawText("420", 140, 250, blackmargins);
        canvas.drawText("480", 140, 200, blackmargins);
        canvas.drawText("540", 140, 150, blackmargins);
        canvas.drawText("600", 140, 100, blackmargins);

        canvas.drawLine(200, 550, 750, 550, blackline);
        canvas.drawLine(200, 500, 750, 500, blackline);
        canvas.drawLine(200, 450, 750, 450, blackline);
        canvas.drawLine(200, 400, 750, 400, blackline);
        canvas.drawLine(200, 350, 750, 350, blackline);
        canvas.drawLine(200, 300, 750, 300, blackline);
        canvas.drawLine(200, 250, 750, 250, blackline);
        canvas.drawLine(200, 200, 750, 200, blackline);
        canvas.drawLine(200, 150, 750, 150, blackline);
        canvas.drawLine(200, 100, 750, 100, blackline);

        canvas.drawCircle(60,660,10, blue);
        canvas.drawCircle(100, 660, 10, blue);
        canvas.drawLine(60,660,100,660, blueline);
        canvas.drawText("Value", 120, 660, blackmargins);
        canvas.drawCircle(60,700,10, green);
        canvas.drawCircle(100, 700, 10, green);
        canvas.drawLine(60,700,100,700, greenline);
        canvas.drawText("Mean", 120, 700, blackmargins);
        canvas.drawCircle(60,740,10, yellow);
        canvas.drawCircle(100, 740, 10, yellow);
        canvas.drawText("Variance", 120, 740, blackmargins);
        canvas.drawLine(60,740,100,740, yellowline);



        for (int i = 0; i < al.size(); i++){
                canvas.drawCircle(((float) (i*55) + 200), (600 - (float) ((al.get(i) / 2) +100)), 10, blue);

        }
        for (int i = 0; i < al.size() - 1; i++) {
                canvas.drawLine(((float) (i*55) + 200),(600 - (float) ((al.get(i) / 2) +100) ), ((float) ((i+1)*55) + 200), (600 - (float)((al.get(i + 1) / 2) +100)), blueline);
        }

        for (int i = 0; i < sVals.size(); i++){
                canvas.drawCircle(((float) (i*55) + 200), (600 - (float) ((sVals.get(i) / 2) +100)), 10, green);
        }
        for (int i = 0; i < sVals.size() - 1; i++) {
            canvas.drawLine(((float) (i*55) + 200),(600 - (float) ((sVals.get(i) / 2) +100)), ((float) ((i+1)*55) + 200), (600 - (float) ((sVals.get(i+1) / 2) +100)), greenline);
        }

        for (int i = 0; i < StDev.size(); i++){
            canvas.drawCircle(((float) (i * 55) + 200), (600 - (float) ((StDev.get(i)/200))), 10, yellow);
        }
        for (int i = 0; i < StDev.size() - 1; i++) {
            canvas.drawLine(((float) (i * 55) + 200),(600 - (float) ((StDev.get(i)/200))), ((float) ((i+1) *55) + 200), (600 - (float) ((StDev.get(i+1)/200))), yellowline);
        }

    }

    public void addPoint(float point) {
        if (al.size() >= 10) {
            al.remove(0);
            al.add(point);
        } else {
            al.add(point);
        }
    }

    public void addSvals(float point) {
        if (sVals.size() >= 10) {
            sVals.remove(0);
            sVals.add(point);
        } else {
            sVals.add(point);
        }
    }

    public void addStDev(float point) {
        if (StDev.size() >= 10) {
            StDev.remove(0);
            StDev.add(point);
        } else {
            StDev.add(point);
        }
    }



    public void updateAxes(int timeInt) {
        if (time.size() >= 10) {
            time.remove(0);
            time.add(timeInt);
        } else {
            time.add(timeInt);
        }
    }

    public void clearList() {
        al.clear();
    }


}
