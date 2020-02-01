package com.example.motionsensorsapp;

import android.content.Context;
import android.hardware.Sensor;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;




public class Accelerometer {

    public interface Listener // this interface has one method
    {
        void onTranslation(float tx, float ty, float tz); // three arguments, these are the translation along the x y z axes

    }

    private Listener listener;

    public void setListener(Listener l)
    {
        listener = l;
    }

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    Accelerometer(Context context)
    {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent  sensorEvent) {

                if(listener != null) // here we call our listener method on translation
                {
                    listener.onTranslation(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }
    public void register()
    {
        sensorManager.registerListener(sensorEventListener, sensor, sensorManager.SENSOR_DELAY_NORMAL);


    }

    public void unregister()
    {
        sensorManager.unregisterListener(sensorEventListener);



    }

}
