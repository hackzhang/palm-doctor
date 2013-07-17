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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SymptomBodyList extends Activity{
	
	@SuppressWarnings("rawtypes")
	public DBManager dbhelper;  
    SQLiteDatabase sqldb;
    Cursor cur;
	private ListView listView;
	private TextView textView;
    List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>(); 
    private SimpleAdapter simpleAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.symptom_body_list);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		this.findview();
		this.initData();	
		}
    private void initData(){
         resList = getData();
         simpleAdapter = new SearchAdapter(
    		this,resList, R.layout.symptom_body_item, 
    		new String[] {"name"}, 
    		new int[] {R.id.symptomBodyName
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
			    intent.putExtra("flag", "body");
			    intent.putExtra("part", getIntent().getStringExtra("part"));
			    intent.putExtra("bname", getIntent().getStringExtra("name"));
			    intent.putExtra("bodyid", getIntent().getStringExtra("id"));
			    intent.putExtra("id", did);
			    intent.putExtra("namedata", namedata);
				intent.setClass(SymptomBodyList.this,SymptomDetail.class);
				SymptomBodyList.this.startActivity(intent);
				SymptomBodyList.this.finish();
//			    Toast.makeText(SymptomBodyList.this, did ,Toast.LENGTH_LONG).show();  
			   }  
			        });  
	 }
    
	 private void findview(){
			listView = (ListView) findViewById(R.id.list_symptom);
			textView = (TextView) findViewById(R.id.symptomName);
			textView.setText(getIntent().getStringExtra("name"));
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
	        cur = sqldb.rawQuery("select * from symptomchecker_symptom WHERE sex = 3 and id in (select symptom_id from symptomchecker_symptom_bodies where bodypart_id = "+getIntent().getStringExtra("id")+") order by name_order" , null);
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
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){  
				String part = getIntent().getStringExtra("part");
				if(part.equals("head")){
				    Intent intent = new Intent();
				    intent.putExtra("part", "head");
				    intent.putExtra("sex", getIntent().getBooleanExtra("sex", true));
				    intent.putExtra("pos", getIntent().getIntExtra("pos", 0));
					intent.setClass(SymptomBodyList.this,BodyPart.class);
					SymptomBodyList.this.startActivity(intent);
					SymptomBodyList.this.finish();
				}
				if(part.equals("neck")){
					Intent intent = new Intent();
					intent.putExtra("part", part);
					intent.putExtra("sex", getIntent().getBooleanExtra("sex", true));
				    intent.putExtra("pos", getIntent().getIntExtra("pos", 0));
					intent.setClass(SymptomBodyList.this,BodyPart.class);
					SymptomBodyList.this.startActivity(intent);
					SymptomBodyList.this.finish();
				}
				if(part.equals("")){
					Intent intent = new Intent();
					intent.putExtra("part", getIntent().getStringExtra("part"));
					intent.putExtra("sex", getIntent().getBooleanExtra("sex", true));
				    intent.putExtra("pos", getIntent().getIntExtra("pos", 0));
				    intent.putExtra("name", getIntent().getStringExtra("name"));
					intent.setClass(SymptomBodyList.this,SymptomActivity.class);
					SymptomBodyList.this.startActivity(intent);
					SymptomBodyList.this.finish();
				}
			}  
			return super.onKeyDown(keyCode, event);
		}
}
