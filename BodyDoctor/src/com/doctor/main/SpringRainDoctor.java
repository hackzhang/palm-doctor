package com.doctor.main;

import com.baidu.mobstat.StatService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class SpringRainDoctor extends Activity {

	private static final int About = Menu.FIRST;
	private static final int Menu_Item = 0;
	private static final int Collect = Menu.FIRST + 1;
	private static final int More = Menu.FIRST + 2;
	private static final int FeedBack = Menu.FIRST + 3;
	private static final int Share = Menu.FIRST + 4;
	private static final int Statement = Menu.FIRST+5;
	private ImageView disease,symptom;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.index);
		findView();
		onClick();
	}
   public void onClick() {
		symptom.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SpringRainDoctor.this,AllDiseaseListview.class);
				SpringRainDoctor.this.startActivity(intent);
				SpringRainDoctor.this.finish();
			}
		});
		disease.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SpringRainDoctor.this,SymptomActivity.class);
				int i=0;
				intent.putExtra("part", "index");
				intent.putExtra("sex", true);
			    intent.putExtra("pos", i);
			    intent.putExtra("name", "");
				SpringRainDoctor.this.startActivity(intent);
				SpringRainDoctor.this.finish();
			}
		});
   }
	
   public void findView() {
		symptom = (ImageView) findViewById(R.id.symptom_index);
		disease = (ImageView) findViewById(R.id.disease_index);
   }
	//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		 if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  
//	            dialog();  
//	            return true;  
//	        }  
//	        return true; 
//	}
	protected void dialog() {  
        AlertDialog.Builder builder = new Builder(SpringRainDoctor.this);  
        builder.setMessage(R.string.exit);  
        builder.setTitle(R.string.prompt);  
        builder.setPositiveButton(R.string.ok,  
        new android.content.DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                android.os.Process.killProcess(android.os.Process.myPid());  
            }  
        });  
        builder.setNegativeButton(R.string.cancle,  
        new android.content.DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
            }  
        });  
        builder.create().show();  
    }
	/*����menu*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu_Item, Collect, Menu.NONE, R.string.collect).setIcon(R.drawable.info_collection);
		SubMenu subMenu = menu.addSubMenu(Menu_Item, More, Menu.NONE, R.string.more).setIcon(R.drawable.info_about);
		//MenuItem menuitem = subMenu.add(Menu_Item, More, Menu.NONE, "����").setIcon(R.drawable.info);
		subMenu.add(Menu_Item, About, Menu.NONE, R.string.about_us);
		subMenu.add(Menu_Item, FeedBack, Menu.NONE, R.string.feedback);
		subMenu.add(Menu_Item, Share, Menu.NONE, R.string.share);
		subMenu.add(Menu_Item, Statement, Menu.NONE, R.string.statement);
		return true;
	}
	/*����˵��¼�*/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId())
		{
			case About:
				new AboutDialog(this).show();
				return true;
			case Collect:
				forward();
				return true;
		    case FeedBack:
		    	feedback();
		    	return true;
		    case Share:
		    	share();
		        return true;
		    case Statement:
		    	statement();
		        return true;
		}
		return true;
	}
	//��ת����
	public void feedback() {
    	Intent intent = new Intent();
    	intent.putExtra("leave", "spring");
  		intent.setClass(SpringRainDoctor.this,FeedBack.class);
  		SpringRainDoctor.this.startActivity(intent);
  		SpringRainDoctor.this.finish();
	}
	
	//��ת����
	public void share() {
		Intent intent=new Intent(Intent.ACTION_SEND);   
		intent.setType("text/plain");  //������������� 
		intent.putExtra(Intent.EXTRA_SUBJECT, "subject");  //���� 
		intent.putExtra(Intent.EXTRA_TEXT, "�����Բ�,����ߵ���������!���ص�ַ��http://vip.hiao.com/2011/11/sjkhd/");  //���� 
		startActivity(Intent.createChooser(intent, "һ������"));  //Ŀ��Ӧ��ѡ��Ի���ı���	
	}
	
	//��ת��--����
	public void statement() {
	    Intent intent = new Intent();
		intent.setClass(SpringRainDoctor.this,Statement.class);
		SpringRainDoctor.this.startActivity(intent);
		SpringRainDoctor.this.finish();
	}
	
	//��ת��--�ղ�
	public void forward() {
	    Intent intent = new Intent();
		intent.setClass(SpringRainDoctor.this,DiseaseCollect.class);
		SpringRainDoctor.this.startActivity(intent);
		SpringRainDoctor.this.finish();
	}
}
