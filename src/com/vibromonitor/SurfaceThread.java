package com.vibromonitor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.SurfaceHolder;

public class SurfaceThread extends Thread {

	
	private static final String TAG = null;

	SurfaceHolder surfaceHolder;
	
	int getWidth=0;
	int getHeight=0;
	
	

	SurfaceThread() 
    	
    	{
    		super();
    	}
    	
	public SurfaceThread(SurfaceHolder surfaceHolder)
	//public SurfaceThread(SurfaceHolder surfaceHolder, Context context)
	    
		{
			this.surfaceHolder = surfaceHolder;
		}
		
	
	void pausa() 
	
		{
			
			
			
				
				Paint paint2 = new Paint (Paint.ANTI_ALIAS_FLAG);
				paint2.setColor(Color.GRAY);
				paint2.setStrokeWidth(1);
				paint2.setStyle(Paint.Style.FILL);
				Canvas canvas = new Canvas();
				paint2.setTextSize(getWidth/20);
				
		
				int p=-21;
				
				canvas = surfaceHolder.lockCanvas();
				
				getWidth = canvas.getWidth();
				getHeight = canvas.getHeight();
				paint2.setTextSize(getWidth/20);
				
				canvas.drawColor(Color.WHITE);
						
						
				for (int j=0; j<7; j++)
					
				{
					canvas.drawLine(getWidth/6 + (j*getWidth/9)+1, getHeight/6, getWidth/6 + (j*getWidth/9)+1, getHeight-getHeight/6, paint2);
					canvas.drawLine(getWidth/6, getHeight-getHeight/6 - (j*getHeight/9)-1, getWidth-getWidth/6+1, getHeight-getHeight/6 - (j*getHeight/9)-1, paint2);
					
					p=p+7;
				}	
							

				canvas.drawText("-21", getWidth/6-getWidth/11, getHeight-getHeight/6 - (0*getHeight/9)+2, paint2);
				canvas.drawText("-14", getWidth/6-getWidth/11, getHeight-getHeight/6 - (1*getHeight/9)+2, paint2);
				canvas.drawText("-7", getWidth/6-getWidth/11, getHeight-getHeight/6 - (2*getHeight/9)+2, paint2);
				canvas.drawText("0", getWidth/6-getWidth/11, getHeight-getHeight/6 - (3*getHeight/9)+2, paint2);
				canvas.drawText("7", getWidth/6-getWidth/11, getHeight-getHeight/6 - (4*getHeight/9)+2, paint2);
				canvas.drawText("14", getWidth/6-getWidth/11, getHeight-getHeight/6 - (5*getHeight/9)+2, paint2);
				canvas.drawText("21", getWidth/6-getWidth/11, getHeight-getHeight/6 - (6*getHeight/9)+2, paint2);
				
				canvas.drawText("Виброускорение "+0+" м/с2", getWidth/5, getHeight/10, paint2);
				canvas.drawText("СКЗ "+0.0+" м/с2", getWidth/6, getHeight-getHeight/14, paint2);
				canvas.drawText("Пик. "+0.0+" м/с2", getWidth/2, getHeight-getHeight/14, paint2);

				canvas.rotate(-90, getWidth/24, getHeight/2);
				canvas.drawText("м/с2", getWidth/6-getWidth/6, getHeight-getHeight/6 - (3*getHeight/9)+2, paint2);
							
				surfaceHolder.unlockCanvasAndPost(canvas);	
						
					
			}
		
		 
	
	void rabota() 
	
	{
	
		
		Paint paint = new Paint (Paint.ANTI_ALIAS_FLAG);
		Paint paint2 = new Paint (Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.parseColor("#ffa02f"));
		paint.setStrokeWidth(getWidth/60);
		paint.setStyle(Paint.Style.STROKE);
		paint2.setColor(Color.GRAY);
		paint2.setStrokeWidth(1);
		paint2.setStyle(Paint.Style.FILL);
		
		Canvas canvas = new Canvas();
		int width = getWidth - getWidth/6+3;
		float [] bigdata = new float [1000];
		float [] data = new float [1000];
		float [] vibro = new float [1000];
		double skz = 0;


		float maximum = 0;
		long myElapsedMillis = 0;


		
		paint2.setTextSize(getWidth/20);
		
		int KM =getHeight/60;
		
		//if (getHeight<400) KM = getHeight/55; else KM = getHeight/60;
		
		long starttime=System.currentTimeMillis();
			
		for (int x=0; x<getWidth; x++) 
		{
			data[x]=getHeight/2;
			vibro[x]=getHeight/2;
		}	
		
		
		 
		
		int x = getWidth/6;
		int xcount = getWidth/6;
		
		
		double q = 0;
		int cykl=0;
		
		
		
		while (MainActivity.D==true) 
		
		{
			

//Усреднение 2 сек.
			

			myElapsedMillis = System.currentTimeMillis() - starttime;

			
			if (myElapsedMillis<=2000)
			
			{
			
			for (int a=0; a<getWidth; a++)
				
	
				
				{	
					
				
				data[a] = MainActivity.os;
				

				if (MainActivity.chbmenu2==true) 
				
				{
				//Фильтрация (метод скользящего среднего)
				float alpha=(float) 0.8;
				bigdata[a] = alpha * bigdata[a] + (1 - alpha) * data[a];
				data[a] = data[a] - bigdata[a]; 
				
//				Log.e (TAG, "Фильтр");
				}

				
				//Cглаживающее окно Хемминга
				//data[a] = (float) (data[a] * (0.54-0.46*Math.cos(2*Math.PI*a/getWidth)));
				
				//СКЗ
				q =  q + Math.sqrt(data[a]*data[a])/getWidth;
				
				
				// Пиковое значение
				if (data[a] >= maximum) maximum = data[a];
				

				if (a==getWidth-1) cykl=cykl+1;
				
				}
			}
			
			else 
				
			{	
				
		
				skz=q/cykl; 
				q=0;		
				cykl=0;
				
				
				starttime=System.currentTimeMillis(); 
			
			}
			
		
// Отрисовка
			

				
				for (x=getWidth/6; x<getWidth/6+1; x++)

						
				{
				
					canvas = surfaceHolder.lockCanvas();
					canvas.drawColor(Color.WHITE);
				
//					data[x]= MainActivity.os;	
				
					for (int j=0; j<7; j++)
					
					{	
	
					canvas.drawLine(getWidth/6 + (j*getWidth/9)+1, getHeight/6, getWidth/6 + (j*getWidth/9)+1, getHeight-getHeight/6, paint2);
					canvas.drawLine(getWidth/6, getHeight-getHeight/6 - (j*getHeight/9)-1, getWidth-getWidth/6+1, getHeight-getHeight/6 - (j*getHeight/9)-1, paint2);
					
				
					}
					
					canvas.drawText("-21", getWidth/6-getWidth/11, getHeight-getHeight/6 - (0*getHeight/9)+2, paint2);
					canvas.drawText("-14", getWidth/6-getWidth/11, getHeight-getHeight/6 - (1*getHeight/9)+2, paint2);
					canvas.drawText("-7", getWidth/6-getWidth/11, getHeight-getHeight/6 - (2*getHeight/9)+2, paint2);
					canvas.drawText("0", getWidth/6-getWidth/11, getHeight-getHeight/6 - (3*getHeight/9)+2, paint2);
					canvas.drawText("7", getWidth/6-getWidth/11, getHeight-getHeight/6 - (4*getHeight/9)+2, paint2);
					canvas.drawText("14", getWidth/6-getWidth/11, getHeight-getHeight/6 - (5*getHeight/9)+2, paint2);
					canvas.drawText("21", getWidth/6-getWidth/11, getHeight-getHeight/6 - (6*getHeight/9)+2, paint2);
					
					canvas.rotate(-90, getWidth/24, getHeight/2);
					canvas.drawText("м/с2", getWidth/6-getWidth/6, getHeight-getHeight/6 - (3*getHeight/9)+2, paint2);
					canvas.rotate(90, getWidth/24, getHeight/2);
					
					
					
					canvas.drawText("Виброускорение "+(int) data[x]+" м/с2", getWidth/5, getHeight/10, paint2);
					//canvas.drawText("Виброускорение "+(int) MainActivity.os+" м/с2", getWidth/5, getHeight/10, paint2);
					canvas.drawText("СКЗ "+(Math.floor(skz*10)/10)+" м/с2", getWidth/6, getHeight-getHeight/14, paint2);
					canvas.drawText("Пик. "+(Math.floor(maximum*10)/10)+" м/с2", getWidth/2, getHeight-getHeight/14, paint2);
					
					
					vibro[x]= getHeight/2 - data[x]*KM;

					
					

					for (int x1=xcount; x1>getWidth/6-1; x1--)
							
													
							{
									
								canvas.drawLine(x1,vibro[x1], x1-1, vibro[x1-1], paint);
						
								vibro[x1+1] = vibro[x1];
								
								
							}										
						
							
					if (xcount<width-1) xcount = xcount+1;
				
					
				
					surfaceHolder.unlockCanvasAndPost(canvas);	
					
/*					Path path = new Path();
					path.moveTo(getWidth/6, getHeight/2);
					canvas.drawPath(path, paint);
					path.lineTo(x1, vibro[x1]);
*/
					
					
				}
				
		}
			
	}
			

	

 
	
	

public void run() 
	    
{
		
		
		while (MainActivity.run==true)	
		
			{
				if (MainActivity.D==false) pausa();
				else rabota();
			} 
		

}
	

			
}
		
		
		
		
    	
