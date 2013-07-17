package com.doctor.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import android.R.bool;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedBack extends Activity {

    //android 中网络变化时所发的Intent的名字
	 private EditText help_feedback=null;
	 private EditText help_email=null;
     private ProgressDialog proDialog;
     private String appVersion = "版本名：";
     private int appVersionCode = 0;
	 private String tips = "";
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.feedback);
	        PackageManager manager = this.getPackageManager();
            try { PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
             appVersion = info.versionName;   //版本名
             appVersionCode = info.versionCode;//版本号
             } catch (NameNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             }
	        
	        //获取按钮
	       Button  but_help_feedback=(Button)findViewById(R.id.but_help_feedback);
	       help_feedback=(EditText)findViewById(R.id.help_feedback);
	       help_email=(EditText)findViewById(R.id.help_email);
	     
	        //添加点击事件 ，保存文本信息，并生成提示，同时跳转到主界面 
	       but_help_feedback.setOnClickListener(new  Button.OnClickListener()
	        {
	            public void onClick(View v){
	                String Context =help_feedback.getText().toString();
	                String Email =help_email.getText().toString();
	                
	                //保存
	                try{
	                    //调用网络接口，实现登陆指令
	                	if(!Context.equals("")){
	                		proDialog = ProgressDialog.show(FeedBack.this, "", "留言提交中...", true);
	            			new AsyncTask<String,Integer,String>(){
	            				protected String doInBackground(String... params) {
	            					String xurl = "http://khd.qingdaonews.com/plus/liuyan.php";
	            					String rst = "";
	            					try {
	            						HttpPost httpRequest = new HttpPost(xurl);
	            	                	//获得软件版本信息和系统版本信息
	            	                	String phoneInfo = "Product(制造商): " + android.os.Build.PRODUCT;
	            	                	phoneInfo += ", CPU_ABI（CPU信息）: " + android.os.Build.CPU_ABI;
	            	                    phoneInfo += ", TAGS（标签）: " + android.os.Build.TAGS;
	            	                    phoneInfo += ", VERSION_CODES.BASE（基带版本号）: " + android.os.Build.VERSION_CODES.BASE;
	            	                    phoneInfo += ", MODEL（型号）: " + android.os.Build.MODEL;
	            	                    phoneInfo += ", SDK(软件开发工具包): " + android.os.Build.VERSION.SDK;
	            	                    phoneInfo += ", VERSION.RELEASE（Android系统版本）: " + android.os.Build.VERSION.RELEASE;
	            	                    phoneInfo += ", DEVICE（设备）: " + android.os.Build.DEVICE;
	            	                    phoneInfo += ", DISPLAY（显示器）: " + android.os.Build.DISPLAY;
	            	                    phoneInfo += ", BRAND（品牌）: " + android.os.Build.BRAND;
	            	                    phoneInfo += ", BOARD（主板）: " + android.os.Build.BOARD;
	            	                    phoneInfo += ", FINGERPRINT（指纹传感器）: " + android.os.Build.FINGERPRINT;
	            	                    phoneInfo += ", ID: " + android.os.Build.ID;
	            	                    phoneInfo += ", MANUFACTURER（制造商）: " + android.os.Build.MANUFACTURER;
	            	                    phoneInfo += ", USER（使用者）: " + android.os.Build.USER;
	            	                    //获得软件版本信息
	            						List<NameValuePair> para = new ArrayList<NameValuePair>();
	            						para.add(new BasicNameValuePair("c", help_feedback.getText().toString()));
	            						para.add(new BasicNameValuePair("e", help_email.getText().toString()));
	            						para.add(new BasicNameValuePair("v", appVersion+"版本号："+appVersionCode));
	            						para.add(new BasicNameValuePair("x", phoneInfo));
	            						// 添加请求参数到请求对象
	            						httpRequest.setEntity(new UrlEncodedFormEntity(para,
	            								HTTP.UTF_8));
	            						// 发送请求并等待响应

	            						HttpParams httpParameters = new BasicHttpParams();
	            						int timeoutConnection = 60000;
	            						HttpConnectionParams.setConnectionTimeout(httpParameters,timeoutConnection);
	            						int timeoutSocket = 60000;
	            						HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
	            						DefaultHttpClient regclient = new DefaultHttpClient(httpParameters);

	            						try {
	            							StringBuffer sb = new StringBuffer();
	            							String inputLine = "";
	            							HttpResponse httpResponse = regclient.execute(httpRequest);
	            							if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	            								InputStreamReader is = new InputStreamReader(httpResponse.getEntity().getContent());
	            								BufferedReader in = new BufferedReader(is);
	            								while ((inputLine = in.readLine()) != null) {
	            									sb.append(inputLine);
	            								}
	            								in.close();
	            								if(sb.toString().equals("used")){
	            									tips="此信息已经存在";
	            								}else{
	            									tips=sb.toString().trim();
	            								}

	            							} else if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_GATEWAY_TIMEOUT) {
	            								tips = "code:" + HttpStatus.SC_GATEWAY_TIMEOUT;
	            							} else if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
	            								tips="code:" + HttpStatus.SC_NOT_FOUND;
	            							} else if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_BAD_GATEWAY) {
	            								tips="code:" + HttpStatus.SC_BAD_GATEWAY;
	            							} else if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_SERVICE_UNAVAILABLE) {
	            								tips= "code:"+ HttpStatus.SC_SERVICE_UNAVAILABLE;
	            							} else {

	            								tips="";

	            							}
	            						} catch (Exception e) {
	            							// TODO Auto-generated catch block
	            							e.printStackTrace();
	            						} finally {
	            							regclient.getConnectionManager().shutdown();
	            						}				

	            					} catch (Exception e) {
	            						tips="留言反馈过程发生错误";

	            					}
	            					
	            					Message msg = new Message();
	            					msg.what=1;
	            					reflashormoreDataHandler.sendMessage(msg);
	            					
	            					return null;
	            				}
	            			}.execute("");
	                		
	                	}else {


	  	                  Toast.makeText(FeedBack.this, "请填写反馈信息,谢谢您的支持！", Toast.LENGTH_SHORT).show();
	  	                 // finish();
						}

	               }
	               catch(Exception e)
	               {
	                   e.printStackTrace();                            
	               }
	               finally{
	                       
	               }    
	            }
	        });
	    }
	    Handler reflashormoreDataHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what){
				case 1://
					if(null!=proDialog && proDialog.isShowing()){
						proDialog.dismiss();
					}
					if(!"1".equals(tips)){
						Toast.makeText(FeedBack.this, "请进行网络设置!"+tips,Toast.LENGTH_SHORT).show();
				        Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
				        startActivity(intent);
					}else{
	  	                Toast.makeText(FeedBack.this, "感谢您的反馈,我们会尽快处理您的意见。", Toast.LENGTH_SHORT).show();
						FeedBack.this.finish();					
					}
				break;
				}
			}
		};
	    
	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	    	String flag = getIntent().getStringExtra("leave");
	        if(keyCode == KeyEvent.KEYCODE_BACK){
	    	    if(flag.equals("spring")){
		        	Intent intent = new Intent();
		    		intent.setClass(FeedBack.this,SpringRainDoctor.class);
		    		FeedBack.this.startActivity(intent);
		    		FeedBack.this.finish();
	    	    }
	    	    if(flag.equals("disease")){
		        	Intent intent = new Intent();
		    		intent.setClass(FeedBack.this,AllDiseaseListview.class);
		    		FeedBack.this.startActivity(intent);
		    		FeedBack.this.finish();
	    	    }
	    	    if(flag.equals("symptom")){
		        	Intent intent = new Intent();
		    		int i=0;
		    		intent.putExtra("part", "index");
		    		intent.putExtra("sex", true);
		    	    intent.putExtra("pos", i);
		    	    intent.putExtra("name", "");
		    		intent.setClass(FeedBack.this,SymptomActivity.class);
		    		FeedBack.this.startActivity(intent);
		    		FeedBack.this.finish();
	    	    }
	        }
	            return false;
	    }
}
