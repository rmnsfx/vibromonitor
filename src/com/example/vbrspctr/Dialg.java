package com.example.vbrspctr;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Dialg extends DialogFragment implements OnClickListener {

	
final String LOG_TAG = "myLogs";
	
	
	public Dialog onCreateDialog(Bundle savedInstanceState) 
	
	{
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 builder.setTitle("Выйти из приложения?");
//		    builder.setMessage("Выйти из приложения?");
//					.setIcon(R.drawable.ic_launcher)
			builder.setCancelable(false);

		    builder.setPositiveButton("Да", this);

		    builder.setNegativeButton("Нет", this);
		
		    return builder.create();
	
	}
	
	
	
	@Override
	public void onClick(DialogInterface arg0, int arg1) 
	
	{
		
		   int i = 0;
		    
		   switch (arg1) 
		   
		   {
		    
		   case Dialog.BUTTON_POSITIVE:
		      i = 1;
		      break;
		      
		   case Dialog.BUTTON_NEGATIVE:
		      i = 2;
		      break;
		    
		   case Dialog.BUTTON_NEUTRAL:
		      i = 3;
		      break;
		    }
		   
		   if (i > 0)
		      Log.d(LOG_TAG, "Dialog 2: " + getResources().getString(i));
		  
	}
	
	
	  public void onDismiss(DialogInterface dialog) {
		    super.onDismiss(dialog);
		    Log.d(LOG_TAG, "Dialog 2: onDismiss");
		  }

	  
	  public void onCancel(DialogInterface dialog) {
		    super.onCancel(dialog);
		    Log.d(LOG_TAG, "Dialog 2: onCancel");
		  }
	
	
}
