package com.mygeekbranch.tempapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

// Кастомная въюшка для температуры
public class TemperatureView extends View {
    //Цвет тела
    private int bodyColor = Color.GRAY;
    //Цвет температуры
    private int temperatureColor = Color.RED;
    // Изображение тела
    private RectF bodyRetangle = new RectF();
    // Изображение уровня температуры
    private RectF temperatureRetangle = new RectF();
    // Изображение головки градусника
    private RectF headRestangle = new RectF();
    //Краска тела
    private Paint bodyPaint;
    //Краска головы
    private Paint headPaint;
    //Краска температуры
    private Paint temperaturePaint;
    //Ширина элемента
    private int width = 0;
    //Высота элемента
    private int height = 0;
    // Уровень температуры
    private float temperatureLevel = 100.0f;
    //константы
    //Отступы элементов
    private  final  static int padding = 10;
    //Скругление углов градусника
    private  final static int round = 30;



    public TemperatureView(Context context) {
        super(context);
        init();

    }


    public TemperatureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        init();
    }

    public TemperatureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        init();
    }

    public TemperatureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(context, attrs);
        init();
    }


    // Начальная инициализация полей класса
    private void init() {
        bodyPaint = new Paint();
        bodyPaint.setColor(bodyColor);
        bodyPaint.setStyle(Paint.Style.STROKE);

        headPaint = new Paint();
        headPaint.setColor(bodyColor);
        headPaint.setStyle(Paint.Style.STROKE);


        temperaturePaint = new Paint();
        temperaturePaint.setColor(temperatureColor);
        temperaturePaint.setStyle(Paint.Style.FILL);


    }

    // Инициализация атрибутов пользовательского элемента из xml
    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TemperatureView, 0, 0);
        bodyColor = typedArray.getColor(R.styleable.TemperatureView_body_color, Color.GRAY);
        temperatureColor =typedArray.getColor(R.styleable.TemperatureView_body_color, Color.RED);
        temperatureLevel = typedArray.getFloat(R.styleable.TemperatureView_temperature_level, 100.0f);
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w - getPaddingLeft() - getPaddingRight();
        height = h - getPaddingTop() - getPaddingBottom();
        bodyRetangle.set(
                2*padding,
                3*padding,
                width-padding,
                height-3*padding);
        temperatureRetangle.set(
                12*padding,
                6*padding,
                (int)((width - 2* padding )*((double)temperatureLevel/(double)50)),
                height - 6* padding);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(bodyRetangle,round,round,bodyPaint);

        canvas.drawRect(temperatureRetangle,temperaturePaint);
        canvas.drawCircle(height/2,height/2,height/2,bodyPaint);

    }
    public  void setTemperatureLevel (Float temp){
        temperatureLevel = temp;
        invalidate();
    }
}
