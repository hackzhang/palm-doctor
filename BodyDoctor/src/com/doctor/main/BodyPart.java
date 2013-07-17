package com.doctor.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.doctor.tool.SearchAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class BodyPart extends Activity {
	
	private ListView listView;
	private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>(); 
	 @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.body_part_list);
	    listView = (ListView) findViewById(R.id.list_body_part);
	    final String part = getIntent().getStringExtra("part");
	    if(part.equals("head")){
	    	resList = getHeadData();
	    }
	    if(part.equals("neck")){
	    	resList = getNeckData();
	    }
	    simpleAdapter = new SearchAdapter(
	    		this,resList, R.layout.symptom_bodypart_item, 
	    		new String[] {"name" }, 
	    		new int[] {R.id.symptomBodyPartName
	        });
	    listView.setAdapter(simpleAdapter);
		listView.setOnItemClickListener(new OnItemClickListener(){  
			   public void onItemClick(AdapterView<?> parent, View view,  
			     int position, long id) {  
			    Intent intent = new Intent();
			    intent.putExtra("part", part);
			    intent.putExtra("sex", getIntent().getStringExtra("sex"));
			    intent.putExtra("pos", getIntent().getStringExtra("pos"));
			    intent.putExtra("id", resList.get(position).get("id").toString());
			    intent.putExtra("name", resList.get(position).get("name").toString());
			    intent.setClass(BodyPart.this,SymptomBodyList.class);
				BodyPart.this.startActivity(intent);
				BodyPart.this.finish();
			   }  
		 });
	 }
	 
	 /**
	  * 获取数据
	  */
	   public List<Map<String, Object>> getHeadData(){
		    Map<String, Object> resMap = new HashMap<String, Object>();
		    resMap.put("id", " 22");
	        resMap.put("name", "口腔");
	        resList.add(resMap);
	        
	        resMap = new HashMap<String, Object>();
	        resMap.put("id", "1");
	        resMap.put("name", "头部");
	        resList.add(resMap);
	        
	        resMap = new HashMap<String, Object>();
	        resMap.put("id", "3");
	        resMap.put("name", "眼部");
	        resList.add(resMap);
	        
	        resMap = new HashMap<String, Object>();
	        resMap.put("id", "4");
	        resMap.put("name", "耳鼻");
	        resList.add(resMap);
	        
	        resMap = new HashMap<String, Object>();
	        resMap.put("id", "5");
	        resMap.put("name", "面部");
	        resList.add(resMap);
	        return resList;
	   }
	   
	   public List<Map<String, Object>> getNeckData(){
		    Map<String, Object> resMap = new HashMap<String, Object>();
		    resMap.put("id", " 2");
	        resMap.put("name", "颈部");
	        resList.add(resMap);
	        
	        resMap = new HashMap<String, Object>();
	        resMap.put("id", "6");
	        resMap.put("name", "喉咙");
	        resList.add(resMap);
	        return resList;
	   }
	   
	    @Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){  
				Intent intent = new Intent();
				intent.setClass(BodyPart.this,SymptomActivity.class);
				intent.putExtra("part", getIntent().getStringExtra("part"));
				intent.putExtra("name", getIntent().getStringExtra("name"));
				intent.putExtra("sex", getIntent().getBooleanExtra("sex", true));
			    intent.putExtra("pos", getIntent().getIntExtra("pos", 0));
				BodyPart.this.startActivity(intent);
				BodyPart.this.finish();
			}  
			return super.onKeyDown(keyCode, event);
		}
}
