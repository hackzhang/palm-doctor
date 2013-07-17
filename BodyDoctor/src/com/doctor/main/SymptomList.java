package com.doctor.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doctor.entity.Symptom;
import com.doctor.tool.DBManager;
import com.doctor.tool.SearchAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SymptomList extends Activity implements OnClickListener {
	
	@SuppressWarnings("rawtypes")
	public DBManager dbhelper;  
    SQLiteDatabase sqldb;
    Cursor cur;
	private TextView etdata;
	private ListView listView;
    List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>(); 
    private SimpleAdapter simpleAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.symptom_list);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		this.findview();
		this.initData();
	}
	
	 private void initData(){
         resList = getData();
         simpleAdapter = new SearchAdapter(
    		this,resList, R.layout.all_symptom_item, 
    		new String[] {"name"}, 
    		new int[] {R.id.allSymptomName
        });
         
		 listView.setAdapter(simpleAdapter);
		 simpleAdapter.notifyDataSetChanged();
		 
		 listView.setOnItemClickListener(new OnItemClickListener(){  
			   @SuppressWarnings("unchecked")  
			   @Override  
			   public void onItemClick(AdapterView<?> parent, View view,  
			     int position, long id) {  
			    ListView listView = (ListView)parent;  
			    HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);  
			    String did = map.get(Symptom.ID);
			    String namedata = map.get(Symptom.NAME);
			    Intent intent = new Intent();
			    intent.putExtra("flag", "symptom");
			    intent.putExtra("id", did);
			    intent.putExtra("namedata", namedata);
				intent.putExtra("part", getIntent().getStringExtra("part"));
				intent.putExtra("sex", getIntent().getBooleanExtra("sex", true));
			    intent.putExtra("pos", getIntent().getIntExtra("pos", 0));
			    intent.putExtra("name", getIntent().getStringExtra("name"));
				intent.setClass(SymptomList.this,SymptomDetail.class);
				SymptomList.this.startActivity(intent);
				SymptomList.this.finish();
//			    Toast.makeText(SymptomList.this, did ,Toast.LENGTH_LONG).show();  
			   }  
			        });  
	 }
	 
	 private void findview(){
			listView = (ListView) findViewById(R.id.list_symptom);
			etdata = (TextView) findViewById(R.id.edit_symptom_search);
	    	etdata.setOnClickListener(this);
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
	        cur = sqldb.rawQuery("select * from symptomchecker_symptom order by name_order" , null);
	        for (cur.moveToFirst();!cur.isAfterLast();cur.moveToNext()) {
	    	   Map<String, Object> resMap = new HashMap<String, Object>();
	    	   int id = cur.getColumnIndex(Symptom.ID);
	    	   int nameColumn = cur.getColumnIndex(Symptom.NAME);
	           resMap.put("id",cur.getString(id));
        	   resMap.put("name",cur.getString(nameColumn));
		       resList.add(resMap);
		   }
	       dbhelper.closeCursor(cur);
	       return resList;
	   }
	   
		@Override  
		protected void finalize() {
		    dbhelper.closeDatabase();
		}
	   
		
		@Override
		public void onClick(View v) {
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
					intent.putExtra("part", getIntent().getStringExtra("part"));
					intent.putExtra("sex", getIntent().getBooleanExtra("sex", true));
				    intent.putExtra("pos", getIntent().getIntExtra("pos", 0));
				    intent.putExtra("name", getIntent().getStringExtra("name"));
					intent.setClass(SymptomList.this,SymptomActivity.class);
					SymptomList.this.startActivity(intent);
					SymptomList.this.finish();
			}  
			return super.onKeyDown(keyCode, event);
		}
	 
}
