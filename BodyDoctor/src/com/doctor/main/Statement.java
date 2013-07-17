package com.doctor.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Statement extends Activity {
	private WebView webView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.statement);
		this.findview();
		this.initData();
	}
	
	public void findview() {
		webView = (WebView) findViewById(R.id.webview);
	}
	
	private void initData() {
	   WebSettings wSet = webView.getSettings();     
       wSet.setJavaScriptEnabled(true);
       webView.loadUrl("file:///android_asset/law.html");
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){  
			Intent intent = new Intent();
			intent.setClass(Statement.this,SpringRainDoctor.class);
			Statement.this.startActivity(intent);
			Statement.this.finish();
		}  
		return super.onKeyDown(keyCode, event);
	}
}
