package com.example.giroscopio;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class Pelota extends View implements SensorEventListener {
    Paint pincel = new Paint();
    int alto, ancho;
    int tamano = 70;
    int borde = 40;
    float ejeX=10, ejeY=10, ejez=10;
    String X,Y,Z;

    public Pelota(Context context) {
        super(context);
        SensorManager snAdministrador = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor snsRotation = snAdministrador.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        snAdministrador.registerListener(this, snsRotation, SensorManager.SENSOR_DELAY_FASTEST);
        Display pantalla = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        ancho = pantalla.getWidth();
        alto = pantalla.getHeight();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ejeX = event.values[0];
        X= Float.toString(ejeX);
        if (ejeX <(tamano+borde)){
            ejeX = (tamano+borde);
        }
        else if(ejeX> (ancho -(tamano+borde))){
            ejeX = ancho -(tamano+borde);
        }
        ejeY = event.values[1];
        Y= Float.toString(ejeY);
        if (ejeY < (tamano+borde)){
            ejeY = (tamano+borde);
        }
        else if(ejeY > (alto - tamano -170)){
            ejeY = alto- tamano -170;
        }
        ejez = event.values[2];
        //Z = String.format("%.2f",ejez);
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
    public void onDraw(Canvas lienzo){
        pincel.setColor(Color.RED);
        lienzo.drawCircle(ejeX,ejeY,ejez+tamano,pincel);
        pincel.setColor(Color.WHITE);
        pincel.setTextSize(25);
        lienzo.drawText("sensor",ejeX-35,ejeY+3,pincel);
    }
}
