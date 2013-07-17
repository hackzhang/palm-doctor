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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SymptomDetail extends Activity {

    @SuppressWarnings("rawtypes")
	public DBManager dbhelper;  
    private SQLiteDatabase sqldb;
    private TextView textView;
    private Cursor cur;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> resList=null;
    private TextView titeView;
	private Button whole,common,child,deadly;
	private ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.symptom_detail);
        String sql = "SELECT * FROM symptomchecker_condition WHERE id in (select condition_id from symptomchecker_differentialdiagnose WHERE common = 1 and symptom_id in (select id from symptomchecker_symptom where id="+getIntent().getStringExtra("id")+")) order by name_order";
        this.findview();
		this.initData(sql);
		common.setBackgroundResource(R.drawable.press_button);
		whole.setBackgroundResource(0);
		deadly.setBackgroundResource(0);
		child.setBackgroundResource(0);
        
        whole.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 whole.setBackgroundResource(R.drawable.press_button);
				 common.setBackgroundResource(0);
				 deadly.setBackgroundResource(0);
				 child.setBackgroundResource(0);
				 textView.setText("");
				 String sql = "SELECT * FROM symptomchecker_condition WHERE id in (select condition_id from symptomchecker_condition_symptoms WHERE symptom_id in (select id from symptomchecker_symptom where id = "+getIntent().getStringExtra("id")+")) order by name_order";			
				 initData(sql);
			}
		});
        common.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				common.setBackgroundResource(R.drawable.press_button);
				whole.setBackgroundResource(0);
				deadly.setBackgroundResource(0);
				child.setBackgroundResource(0);
				textView.setText("");
		    	String sql = "SELECT * FROM symptomchecker_condition WHERE id in (select condition_id from symptomchecker_differentialdiagnose WHERE common = 1 and symptom_id in (select id from symptomchecker_symptom where id="+getIntent().getStringExtra("id")+")) order by name_order";		
		    	initData(sql);
			}
		});
        child.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				child.setBackgroundResource(R.drawable.press_button);
				whole.setBackgroundResource(0);
				deadly.setBackgroundResource(0);
				common.setBackgroundResource(0);
				textView.setText("");
				String sql ="SELECT * FROM symptomchecker_condition WHERE id in (select condition_id from symptomchecker_differentialdiagnose WHERE pediatric = 1 and symptom_id in (select id from symptomchecker_symptom where id="+getIntent().getStringExtra("id")+")) order by name_order";			
				initData(sql);
			}
		});
        deadly.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				deadly.setBackgroundResource(R.drawable.press_button);
				whole.setBackgroundResource(0);
				common.setBackgroundResource(0);
				child.setBackgroundResource(0);
				textView.setText("");
				String sql = "SELECT * FROM symptomchecker_condition WHERE id in (select condition_id from symptomchecker_differentialdiagnose WHERE life_threatening = 1 and symptom_id in (select id from symptomchecker_symptom where id="+getIntent().getStringExtra("id")+")) order by name_order";		
				initData(sql);
			}
		});
    }
    private void initData(String sql){
    	resList = new ArrayList<Map<String,Object>>();
    	resList = getData(sql);
        //String sq = "SELECT * FROM symptomchecker_condition WHERE id in (select condition_id from symptomchecker_condition_symptoms WHERE symptom_id in (select id from symptomchecker_symptom where id = "+getIntent().getStringExtra("id")+")) order by name_order";
        if(resList.size()==0){
        	textView.setText(R.string.no_data);
        }
        //System.out.println("长度："+resList.size());
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
			    String namedata = map.get(Symptom.NAME);
			    Intent intent = new Intent();
			    intent.putExtra("id", did);
			    intent.putExtra("namedata", namedata);
			    intent.putExtra("title", getIntent().getStringExtra("namedata"));
			    intent.putExtra("symotomid", getIntent().getStringExtra("id"));
			    intent.putExtra("flag", getIntent().getStringExtra("flag"));
			    intent.putExtra("bodyid", getIntent().getStringExtra("bodyid"));
			    intent.putExtra("bname", getIntent().getStringExtra("bname"));
			    intent.putExtra("part", getIntent().getStringExtra("part"));
				intent.putExtra("sex", getIntent().getBooleanExtra("sex", true));
			    intent.putExtra("pos", getIntent().getIntExtra("pos", 0));
			    intent.putExtra("name", getIntent().getStringExtra("name"));
			    intent.putExtra("query", getIntent().getStringExtra("query"));
				intent.setClass(SymptomDetail.this,SymptomDetailInfo.class);
				SymptomDetail.this.startActivity(intent);
				SymptomDetail.this.finish();
			   }  
			        });  
	 }
    
	 private void findview(){
		    whole = (Button) findViewById(R.id.whole);
	        common = (Button) findViewById(R.id.common);
	        child = (Button) findViewById(R.id.child);
	        deadly = (Button) findViewById(R.id.deadly);
	        listView = (ListView) findViewById(R.id.symptom_disease);
	        titeView = (TextView) findViewById(R.id.title);
	        textView = (TextView) findViewById(R.id.warning);
	        titeView.setText(getIntent().getStringExtra("namedata")+"可能的疾病");
	    }
    
    /**
	  * 获取数据库数据
	  */
	   @SuppressWarnings("rawtypes")
	public List<Map<String, Object>> getData(String sql){
		   
		    dbhelper = new DBManager(this);  
	        dbhelper.openDatabase();  
	        dbhelper.closeDatabase();  
	        sqldb = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/" + DBManager.DB_NAME, null);
	        cur = sqldb.rawQuery(sql, null);
	        System.out.println("sql语句："+sql);
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
		       dbhelper.closeCursor(cur);
		       return resList;
	   }
	   
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){  
				if(getIntent().getStringExtra("flag").equals("body")){
					Intent intent = new Intent();
					intent.putExtra("id", getIntent().getStringExtra("bodyid"));
				    intent.putExtra("name", getIntent().getStringExtra("bname"));
				    intent.putExtra("part", getIntent().getStringExtra("part"));
					intent.setClass(SymptomDetail.this,SymptomBodyList.class);
					SymptomDetail.this.startActivity(intent);
					SymptomDetail.this.finish();
				}
				if(getIntent().getStringExtra("flag").equals("symptom")){
					Intent intent = new Intent();
					intent.setClass(SymptomDetail.this,SymptomList.class);
					intent.putExtra("sex", getIntent().getBooleanExtra("sex", true));
				    intent.putExtra("pos", getIntent().getIntExtra("pos", 0));
				    intent.putExtra("name", getIntent().getStringExtra("name"));
				    intent.putExtra("part", getIntent().getStringExtra("part"));
					SymptomDetail.this.startActivity(intent);
					SymptomDetail.this.finish();
				}
				if(getIntent().getStringExtra("flag").equals("symptom_search")){
					Intent intent = new Intent();
					intent.setClass(SymptomDetail.this,SearchResultSymptom.class);
					intent.putExtra("query", getIntent().getStringExtra("query"));
					SymptomDetail.this.startActivity(intent);
					SymptomDetail.this.finish();
				}
			}  
			return super.onKeyDown(keyCode, event);
		}
    
}
