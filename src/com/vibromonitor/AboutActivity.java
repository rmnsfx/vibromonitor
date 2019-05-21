package com.vibromonitor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity  extends Activity 

{

	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        Sensor mAccelerometerSensor;
     
        
        TextView info = (TextView)findViewById(R.id.about_content4);
        
        SensorManager mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

      
        String stringInfo = "Акселерометр: "+"\n"+"\n"
                + "Name: " + mAccelerometerSensor.getName() + "\n"
                + "Type: " + mAccelerometerSensor.getType() + "\n"
                + "Vendor: " + mAccelerometerSensor.getVendor() + "\n"
                + "Version: " + mAccelerometerSensor.getVersion() + "\n"
    //            + "Class: " + mAccelerometerSensor.getClass() + "\n"
                + "Resolution: " + mAccelerometerSensor.getResolution() + "\n"
                + "MaximumRange: " + mAccelerometerSensor.getMaximumRange() + "\n"
                + "Power: " + mAccelerometerSensor.getPower() + "\n";
                 
                info.setText(stringInfo);
        
        
    }
	
}
