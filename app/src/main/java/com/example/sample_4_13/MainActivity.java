package com.example.sample_4_13;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private SensorManager mySensorManager;
    private Sensor mSensor;
    Bitmap yuan;
    Bitmap shang;
    Bitmap zuo;
    Bitmap qiuzuo;
    Bitmap qiushang;
    Bitmap qiuzhong;

    MyView mv;

    private SensorEventListener mel = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            mv.dx = values[0];
            mv.dy = values[1];
            mv.dz = values[2];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        yuan = BitmapFactory.decodeResource(getResources(),R.drawable.yuan);
        zuo = BitmapFactory.decodeResource(getResources(),R.drawable.zuo);
        shang = BitmapFactory.decodeResource(getResources(),R.drawable.shang);
        qiuzuo = BitmapFactory.decodeResource(getResources(),R.drawable.qiuzuo);
        qiushang = BitmapFactory.decodeResource(getResources(),R.drawable.qiushang);
        qiuzhong = BitmapFactory.decodeResource(getResources(),R.drawable.qiuzhong1);

        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mv = new MyView(this);
        setContentView(mv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySensorManager.registerListener(mel,mSensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySensorManager.unregisterListener(mel);
    }
}