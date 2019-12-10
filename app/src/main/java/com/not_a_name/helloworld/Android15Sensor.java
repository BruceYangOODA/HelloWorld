package com.not_a_name.helloworld;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Android15Sensor extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensor;
    private MyListener myListener;
    private TextView x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android15_sensor);

        x= findViewById(R.id.x);
        y= findViewById(R.id.y);
        z= findViewById(R.id.z);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

//        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
//        for(Sensor sensor: sensors){
//            Log.v("aaa",sensor.getName()+":"+sensor.getVendor());
//        }

       //
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
       // sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    @Override
    protected void onResume() {
        super.onResume();
        myListener = new MyListener();
        sensorManager.registerListener(myListener,sensor,sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(myListener);
    }

    private class MyListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] data = event.values;
            x.setText("x = "+(int)(data[0]*10)/10f);
            y.setText("y = "+(int)(data[1]*10)/10f);
            z.setText("z = "+(int)(data[2]*10)/10f);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
