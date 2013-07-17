package com.doctor.main;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class BodyDoctorActivity extends Activity {
	Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
		
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.main);
        mContext = this;
        
        PackageManager pm = getPackageManager();
		try {
			PackageInfo pi = pm.getPackageInfo("com.doctor.main", 0);
			TextView versionNumber = (TextView) findViewById(R.id.helloNumber);
			versionNumber.setText("’∆…œ“Ω…˙"+" Version " + pi.versionName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
 
		new Handler().postDelayed(new Runnable() {
			public void run() {
				/* Create an Intent that will start the Main Activity. */
				Intent intent = new Intent(mContext, SpringRainDoctor.class);
				BodyDoctorActivity.this.finish();
				startActivity(intent);
				//overridePendingTransition(R.anim.translucent_enter, R.anim.translucent_exit);
			}
		}, 2500);
    }   
}