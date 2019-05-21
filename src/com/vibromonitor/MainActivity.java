package com.vibromonitor;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;




public class MainActivity extends Activity implements SensorEventListener, OnCheckedChangeListener {	
	
	
	SensorManager mSensorManager;
	SensorManager mSensorManager2;
	
    Sensor mAccelerometerSensor;
    float[] mAccelerometerSensor2;
    
    Sensor mGravitySensor;
    TextView mXValueText;
    TextView mXValueText2;
	public static float osx;
	public static float osy;
	public static float osz;
	public static float gosx;
	public static float gosy;
	public static float gosz;
	
	public static float gosx2;
	public static float gosy2;
	public static float gosz2;
	
	public static int vosx;
	public static int vosy;
	public static int vosz;
	public static float linerosx;
	public static float linerosy;
	public static float linerosz;
	
	public  float azimuth;
	public  float pitch;
	public  float roll;
	
	
	Handler handler = new Handler ();
	
	public float e;
	SurfaceHolder surfaceHolder;
	Canvas canvas;
	

 
    protected static final String TAG = null;
	static boolean D;
	static boolean flagmenu;
	static boolean run;
	public MySurfaceView mySurfaceView;

	public Thread thread;
	
	static ToggleButton toogleButton;

	
	
	SharedPreferences mSettings;
	//CheckBoxPreference chbmenu;
	
	static String axis;
	static boolean chbmenu2;
	
	public static float os;
	public SharedPreferences prefs;

	

	
	

	@Override
	public void onCreate(Bundle savedInstanceState) 
	
	{
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mySurfaceView = new MySurfaceView (this);
	
	
		
		setContentView(R.layout.activity_main);
		
		PreferenceManager.setDefaultValues(getApplicationContext(), R.xml.pref, false);
		prefs = PreferenceManager.getDefaultSharedPreferences(this); 
		
		
// рисуем 
		
		
		RelativeLayout canvas = (RelativeLayout) findViewById(R.id.canvas);
	
		canvas.addView(mySurfaceView);	
		
		

// подключаем датчик 
		
		mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		
//		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD)  
		
		mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		
//		else
			
//		mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY); 
//		mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);  


	
		//слушаем кнопку		
		toogleButton = (ToggleButton) findViewById(R.id.tglBtn);
		toogleButton.setOnCheckedChangeListener(this);
		
	
		
	}
	
	

	
// акселерометр
	
	@Override	
	public void onAccuracyChanged(Sensor arg0, int arg1) 
	
	{
	
	}

	public void onSensorChanged(SensorEvent event) 
	
	{
		


			
		
//		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) 
			
//		{
		
		osx = (float) (event.values[0]);
		osy = (float) (event.values[1]);
		osz = (float) (event.values[2]);

//		}
		

	
		
		if (axis.contains("Ось X")) os = osx;
		if (axis.contains("Ось Y")) os = osy;
		if (axis.contains("Ось Z")) os = osz;
		
		
		
		
	}    
	


	
	@Override	
	protected void onResume() 
	
	{
				
		mSensorManager.registerListener(this, mAccelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
		
//		mSensorManager.registerListener(this, mAccelerometerSensor2, SensorManager.SENSOR_DELAY_GAME);


		super.onResume();
	
	
// Читаем настройки в меню
		
		mSettings = PreferenceManager.getDefaultSharedPreferences(this);
				
		axis = mSettings.getString("axis", "");     
		
		if (prefs.getBoolean("chbmenu", false)) chbmenu2=true;
		else chbmenu2=false;
		
		
//Держим экран включенным

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	
// Попытка добавить программную кнопку вызова меню		
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

	
		
		if (mySurfaceView != null) 
		
		{
			Log.e (TAG, "Поток возобновляет работу");
			
			synchronized (mySurfaceView.thread1)
				
				{
				
			
				//thread1.notifyAll();
				
				}	
		}
		
    }
	
	@Override
    protected void onPause()  
	
	{
		
		
		mSensorManager.unregisterListener(this); 
		
		super.onPause();
		
		if(mySurfaceView == null)
			
		{
		Log.e (TAG, "Пауза");
		
		toogleButton.setChecked(false);
	
		mySurfaceView.surfaceDestroyed(surfaceHolder); 
		}
			
		
//		if(mySurfaceView != null)
//		mySurfaceView.pausaSurface();
		
		
  	}


	public void restartSurface() 
	
	{
		
		//SurfaceThread thread1 = new SurfaceThread(surfaceHolder, this);
		//thread1.start();  // Start a new thread
		   
	}
	

// кнопка Toggle Button	

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
		
	{
		if (isChecked==true)
			
			{
			
			D=true;
			
				
			}
		
			
			
		
		else 
		
			{
			
			D=false;
			
			}
	}	
		

//Кнопка back 


@Override
public void onBackPressed()
	  
 {
	    super.onBackPressed();
	    
	   
	    
	    D=false;
	    toogleButton.setChecked(false);
	    run=false;

/*
	    synchronized (mySurfaceView.thread1)
		
		{
		
	
	    
	    //   openQuitDialog();
   
//	    mySurfaceView.pausaSurface();
	 
	   runOnUiThread(new Runnable() 
	   
	   {
		   
		   @Override
		   public void run() 
		   
		   {
			   Log.e (TAG, "Пошел второй поток");
			   openQuitDialog();
			   
		   }
		   
	   });
	   
		}
	  
	   
		

	    
*/	   

	    	    	
	   
 
 }


private void openQuitDialog() 

{
	Log.e (TAG, "Пошел диалог");
	
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	
	builder.setTitle("Выйти из приложения?");
	
	builder.setCancelable(false);
	
	builder.setPositiveButton("Да",	new DialogInterface.OnClickListener() {
		
		public void onClick(DialogInterface dialog, int id) {
			
			e=2;
			dialog.dismiss();
		}
	});
	
	
	builder.setNegativeButton("Нет", null);
	
	builder.create();
	
	
	builder.show();

	
	
}
	  

	

//Меню
		
		@Override
	    public boolean onCreateOptionsMenu(Menu menu) 
		{
			
		
			
			menu.add(Menu.NONE, 1, Menu.NONE, "Настройки");
			menu.add(Menu.NONE, 2, Menu.NONE, "О программе");
			menu.add(Menu.NONE, 3, Menu.NONE, "ООО НПП «ТИК»");
			menu.add(Menu.NONE, 4, Menu.NONE, "Выход");
			
			return true;
	    }
	    
		
		public boolean onOptionsItemSelected(MenuItem item)
		
		{
		    
			switch (item.getItemId()) 
			
			{
				
			
			case 1:	
				
			options (null);
			return true;
			
			case 2:	
				
			logo_click (null);
			return true;
			
			case 3:	
						
			about_company (null);
			return true;
				
			case 4:	
			
			run=false;	
			finish();
			
			}		
			
			return true;
		}
		
	  
		
//Меню 
		
		
			public void options(View v)
			{
				toogleButton.setChecked(false);
				
				run=false;
					
				Intent intent = new Intent(this, Options.class);
				startActivity(intent);
			}
		
			public void logo_click(View v)
			{
				
				toogleButton.setChecked(false);
				
				run=false;
			
				Intent intent = new Intent(this, AboutActivity.class);
			    startActivity(intent);
				
			}
			
			public void about_company(View v)
			{
				toogleButton.setChecked(false);
				
				run=false;
				
				Intent intent = new Intent(this, AboutCompanyActivity.class);
			    startActivity(intent);
			}
		
		
		
	
	
}
		



	


		
			
	

		
		
	
		

		
		
	
		