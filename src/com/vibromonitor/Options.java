package com.vibromonitor;



import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;





public class Options extends PreferenceActivity

{

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
               
                
               
                
               // PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().clear().commit();
                

        		
        		
                
            	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD)  
                
                getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

            	else addPreferencesFromResource(R.xml.pref);
	
	}

	
	
	public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            
            addPreferencesFromResource(R.xml.pref);
        }
    }
	

	
}