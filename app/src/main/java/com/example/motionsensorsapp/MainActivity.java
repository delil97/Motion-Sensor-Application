package com.example.motionsensorsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private Accelerometer accelerometer;
    private Gyroscope gyroscope;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);

        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                if(tx > 1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.BLACK);


                }
                else if (tx < -1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW); // This code will make the screen black or yellow as the device will translate back and fort on the x axes



                }

            }
        });

        gyroscope.setLister(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                if (rz > 1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);


                }
                else if (rz < -1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);


                }


            }
        });
    }
    // now we will override the unresumed method of the activity


    @Override
    protected void onResume() {
        super.onResume();
        accelerometer.register();
        gyroscope.register();
    }

    @Override
    protected void onPause() {
        super.onPause();

        accelerometer.unregister();
        gyroscope.unregister();
    }
}
