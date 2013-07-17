package com.doctor.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doctor.entity.Condition;
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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class DiseaseCollect extends Activity {

	@SuppressWarnings("rawtypes")
	public DBManager dbhelper;  
    SQLiteDatabase sqldb;
    Cursor cur;
	private ListView listView;
    List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>(); 
    private SimpleAdapter simpleAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.disease_collect);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		this.findview();
		this.initData();
        
	}
	
	 private void initData(){
         resList = getData();
         simpleAdapter = new SearchAdapter(
        	   		this,resList, R.layout.symptom_item, 
        	   		new String[] {"name","description"}, 
        	   		new int[] {R.id.symptomTitle,R.id.symptomDesc
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
			    Intent intent = new Intent();
			    intent.putExtra("flag", "collect");
			    intent.putExtra("id", did);
				intent.setClass(DiseaseCollect.this,SymptomDetailInfo.class);
				DiseaseCollect.this.startActivity(intent);
				DiseaseCollect.this.finish();
//			    Toast.makeText(DiseaseCollect.this, did ,Toast.LENGTH_LONG).show();  
			   }  
			        });  
	 }
	   
	 private void findview(){
			listView = (ListView) findViewById(R.id.list_symptom);
	    }
	   
	   /**
	  * 获取数据库数据
	  */
	   @SuppressWarnings("rawtypes")
	public List<Map<String, Object>> getData(){
		   
		    dbhelper = new DBManager(this);  
	        dbhelper.openDatabase();  
	        sqldb = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/" + DBManager.DB_NAME, null);
	        cur = sqldb.rawQuery("select * from symptomchecker_condition where rank_score=1" , null);
	        for (cur.moveToFirst();!cur.isAfterLast();cur.moveToNext()) {
		    	   Map<String, Object> resMap = new HashMap<String, Object>();
		    	   int id = cur.getColumnIndex(Symptom.ID);
		    	   int nameColumn = cur.getColumnIndex(Symptom.NAME);
		    	   int des = cur.getColumnIndex(Condition.DESCRIPTION);
	        	   resMap.put("name",cur.getString(nameColumn));
	        	   resMap.put("id",cur.getString(id));
	        	   resMap.put("description",cur.getString(des));
	        	   System.out.println(cur.getString(id)+cur.getString(nameColumn));
			       resList.add(resMap);
			   }
	       dbhelper.closeDatabase();  
	       dbhelper.closeCursor(cur);
	       return resList;
	   }
	   
		@Override  
		protected void finalize() {
		    dbhelper.closeDatabase();
		}
		
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){  
				Intent intent = new Intent();
				intent.setClass(DiseaseCollect.this,SpringRainDoctor.class);
				DiseaseCollect.this.startActivity(intent);
				DiseaseCollect.this.finish();
			}  
			return super.onKeyDown(keyCode, event);
		}
}
