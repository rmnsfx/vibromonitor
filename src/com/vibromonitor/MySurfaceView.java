package com.vibromonitor;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback 

{

	public static final String TAG = "myLogs";

	SurfaceHolder holder;
	
	public SurfaceThread thread1;

	
public MySurfaceView(Context context) 

{
    super(context);
    holder = getHolder();
    getHolder().addCallback(this);
    setFocusable(true);
    thread1 = new SurfaceThread(holder);
 //   holder.setFormat(PixelFormat.TRANSPARENT);
 
    
}

@Override
public void surfaceCreated(SurfaceHolder holder) 

{
	MainActivity.run=true;
	SurfaceThread thread1 = new SurfaceThread(holder);
	thread1.setPriority(9);
	thread1.start();
	
	
	
}


@Override
public void surfaceChanged(SurfaceHolder arg0, int arg1,
		int arg2, int arg3) 
{

}


@Override
public void surfaceDestroyed(SurfaceHolder holder) 

{
	
synchronized (thread1) 
	
	
	
//	Log.e (TAG, "Пре Дестрой");
	
//	if (MainActivity.run=false)
		
	{
	
	MainActivity.D=false;
	MainActivity.run=false;
	MainActivity.toogleButton.setChecked(false);
		
	boolean aaa = true;
	while (aaa==true) {	
	try { 
		
		
		thread1.join();
		aaa=false;
		Log.e (TAG, "Дестрой");
		} 
		
	catch (InterruptedException e) 
		{
		
		e.printStackTrace();
		
		}
	}
	
	}
	
}



public void restartSurface() 

	{
		
//	   if (thread1.getState() == Thread.State.TERMINATED)
	   
	   {
		   SurfaceThread thread1 = new SurfaceThread(holder);
		   thread1.setPriority(7);
		   thread1.start();
	   }
	   
//	   else 
	
		{
	    	synchronized (this)
	    	{
	        this.notify();
	    	}
		}
}


public void pausaSurface() 

{

	synchronized (thread1) 
	
	{
		Log.e (TAG, "Пауза основного потока");
		try {
		thread1.wait();
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
}







}
