package com.doctor.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doctor.entity.Condition;
import com.doctor.tool.DBManager;
import com.doctor.tool.SearchAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AllDiseaseListview extends Activity implements OnClickListener{
	@SuppressWarnings("rawtypes")
	private static final int About = Menu.FIRST;
	private static final int Menu_Item = 0;
	private static final int Collect = Menu.FIRST + 1;
	private static final int More = Menu.FIRST + 2;
	private static final int FeedBack = Menu.FIRST + 3;
	private static final int Share = Menu.FIRST + 4;
	private DBManager dbhelper;  
    private SQLiteDatabase sqldb;
    private Cursor cur;
	private TextView etdata;
	private ListView listView,list;
	private List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>(); 
	private SimpleAdapter simpleAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.all_disease_list_view);
		findview();
		initData();
	}


	private void initData(){
		  resList = getData();
	      simpleAdapter = new SearchAdapter(
	 		this,resList, R.layout.disease_item, 
	 		new String[] {"name"}, 
	 		new int[] {R.id.diseaseTitle
	      });
		 listView.setAdapter(simpleAdapter);
		 simpleAdapter.notifyDataSetChanged();
		 listView.setOnItemClickListener(new OnItemClickListener(){  
		   @SuppressWarnings("unchecked")  
		   @Override  
		   public void onItemClick(AdapterView<?> parent, View view,  
		     int position, long id) {  
			HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
			String did = map.get(Condition.ID);
//			Toast.makeText(AllDiseaseListview.this, did ,Toast.LENGTH_LONG).show(); 
		    Intent intent = new Intent();
		    intent.putExtra("id", did);
			intent.setClass(AllDiseaseListview.this,DiseaseDetail.class);
			AllDiseaseListview.this.startActivity(intent);
			AllDiseaseListview.this.finish();
		   }  
		 });  
	}
    
	   /**
	  * 获取数据库数据
	  */
	   @SuppressWarnings("rawtypes")
	public List<Map<String, Object>> getData(){
		    dbhelper = new DBManager(this);  
	        dbhelper.openDatabase();  
	        dbhelper.closeDatabase();  
	        sqldb = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/" + DBManager.DB_NAME, null);
	        cur = sqldb.rawQuery("select * from symptomchecker_condition order by name_order" , null);
	        for (cur.moveToFirst();!cur.isAfterLast();cur.moveToNext()) {
	    	   Map<String, Object> resMap = new HashMap<String, Object>();
	    	   int id = cur.getColumnIndex(Condition.ID);
	    	   int nameColumn = cur.getColumnIndex(Condition.NAME);
	           resMap.put("id",cur.getString(id));
	           resMap.put("name",cur.getString(nameColumn));
		       resList.add(resMap);
		   }
	       dbhelper.closeCursor(cur);
	       return resList;
	   }
	   
	 private void findview(){
			listView = (ListView) findViewById(R.id.list);
			etdata = (TextView) findViewById(R.id.edit1);
	    	etdata.setOnClickListener(this);
	    }
	   
		@Override  
		protected void finalize() {
		    dbhelper.closeDatabase();
		}
	   
	   @Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			onSearchRequested();
		}
		
		@Override
		public boolean onSearchRequested(){
			String text=etdata.getText().toString();
			Bundle bundle=new Bundle();
			bundle.putString("data", text);
			//打开浮动搜索框（第一个参数默认添加到搜索框的值）
			//bundle为传递的数据
			startSearch("", false, bundle, false);
			//这个地方一定要返回真 如果只是super.onSearchRequested方法
			//不但onSearchRequested（搜索框默认值）无法添加到搜索框中
			//bundle也无法传递出去
			return true;
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){  
				Intent intent = new Intent();
				intent.setClass(AllDiseaseListview.this,SpringRainDoctor.class);
				AllDiseaseListview.this.startActivity(intent);
				AllDiseaseListview.this.finish();
			}  
			return super.onKeyDown(keyCode, event);
		}
		/*创建menu*/
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			menu.add(Menu_Item, Collect, Menu.NONE, R.string.collect).setIcon(R.drawable.info_collection);
			SubMenu subMenu = menu.addSubMenu(Menu_Item, More, Menu.NONE, R.string.more).setIcon(R.drawable.info_about);
			//MenuItem menuitem = subMenu.add(Menu_Item, More, Menu.NONE, "更多").setIcon(R.drawable.info);
			subMenu.add(Menu_Item, About, Menu.NONE, R.string.about_us);
			subMenu.add(Menu_Item, FeedBack, Menu.NONE, R.string.feedback);
			subMenu.add(Menu_Item, Share, Menu.NONE, R.string.share);
			return true;
		}
		/*处理菜单事件*/
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
			}
			return true;
		}
		//跳转留言
		public void feedback() {
	    	Intent intent = new Intent();
	    	intent.putExtra("leave", "disease");
	  		intent.setClass(AllDiseaseListview.this,FeedBack.class);
	  		AllDiseaseListview.this.startActivity(intent);
	  		AllDiseaseListview.this.finish();
		}
		
		//跳转分享
		public void share() {
			Intent intent=new Intent(Intent.ACTION_SEND);   
			intent.setType("text/plain");  //分享的数据类型 
			intent.putExtra(Intent.EXTRA_SUBJECT, "subject");  //主题 
			intent.putExtra(Intent.EXTRA_TEXT, "疾病自查,您身边的生活助手!下载地址：http://vip.hiao.com/2011/11/sjkhd/");  //内容 
			startActivity(Intent.createChooser(intent, "一键分享"));  //目标应用选择对话框的标题	
		}
		
		//跳转到--收藏
		public void forward() {
		    Intent intent = new Intent();
			intent.setClass(AllDiseaseListview.this,DiseaseCollect.class);
			AllDiseaseListview.this.startActivity(intent);
			AllDiseaseListview.this.finish();
		}
}
