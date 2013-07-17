package com.doctor.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doctor.entity.Condition;
import com.doctor.entity.Symptom;
import com.doctor.tool.DBManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.TextView;

public class DiseaseDetail extends Activity {

    private static final String G_TEXT = "g_text";
    private static final String G_PIC = "g_pic";
	@SuppressWarnings("rawtypes")
	public DBManager dbhelper;  
	SQLiteDatabase sqldb;
	Cursor cur;
	private TextView textView,collect;
	private static final String C_TEXT1 = "c_text1";
	private static final String C_TEXT2 = "c_text1";
    List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
    List<Map<String, Integer>> picData = new ArrayList<Map<String, Integer>>();
    List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
    ExAdapter adapter;
    ExpandableListView exList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.disease_detail);
        findview();
        initData();
    }
    @SuppressWarnings({ "unused", "rawtypes" })
	private void initData(){
        dbhelper = new DBManager(this);  
        dbhelper.openDatabase();
        dbhelper.closeDatabase();  
        sqldb = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/" + DBManager.DB_NAME, null);
        cur = sqldb.rawQuery("select * from symptomchecker_condition where id='"+ getIntent().getStringExtra("id")+"'", null);
        String style[]={"概述","症状","检查","分科","治疗","预防"};
        Integer[] images = new Integer[]{R.drawable.description,R.drawable.symptomdescription,R.drawable.test,
        		R.drawable.specialty, R.drawable.cure, R.drawable.prevention};
        for (int i = 0; i < style.length; i++) {
            Map<String, String> curGroupMap = new HashMap<String, String>();
            Map<String, Integer> picMap = new HashMap<String, Integer>();
            groupData.add(curGroupMap);
            picData.add(picMap);
            curGroupMap.put(G_TEXT, style[i]);
            picMap.put(G_PIC, images[i]);
            for (cur.moveToFirst();!cur.isAfterLast();cur.moveToNext()) {
               int id = cur.getColumnIndex(Condition.ID);
               int name = cur.getColumnIndex(Condition.NAME);
  	    	   int des = cur.getColumnIndex(Condition.DESCRIPTION);
  	           int sys_des = cur.getColumnIndex(Condition.SYMPTOM_DESCRIPTION);
  	           int test = cur.getColumnIndex(Condition.TEST);
  	           int specialty = cur.getColumnIndex(Condition.SPECIALTY);
  	           int prevention = cur.getColumnIndex(Condition.PREVENTION);
  	           int cure = cur.getColumnIndex(Condition.CURE);
 	            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
 	            Map<String, String> curChildMap = new HashMap<String, String>();
 	            children.add(curChildMap);
 	            curChildMap.put(C_TEXT1, cur.getString(des));
 	            textView.setText(cur.getString(name));
 	            childData.add(children);
 	            List<Map<String, String>> children2 = new ArrayList<Map<String, String>>();
 	            Map<String, String> curChildMap2 = new HashMap<String, String>();
 	            children2.add(curChildMap2);
 	            curChildMap2.put(C_TEXT1, cur.getString(sys_des));
 	            childData.add(children2);
 	            List<Map<String, String>> children3 = new ArrayList<Map<String, String>>();
 	            Map<String, String> curChildMap3 = new HashMap<String, String>();
 	            children3.add(curChildMap3);
 	            curChildMap3.put(C_TEXT1, cur.getString(test));
 	            childData.add(children3);
 		       
 	            List<Map<String, String>> children4 = new ArrayList<Map<String, String>>();
	            Map<String, String> curChildMap4 = new HashMap<String, String>();
	            children4.add(curChildMap4);
	            curChildMap4.put(C_TEXT1, cur.getString(specialty));
	            childData.add(children4);
	            List<Map<String, String>> children5 = new ArrayList<Map<String, String>>();
	            Map<String, String> curChildMap5 = new HashMap<String, String>();
	            children5.add(curChildMap5);
	            curChildMap5.put(C_TEXT1, cur.getString(cure));
	            childData.add(children5);
	            List<Map<String, String>> children6 = new ArrayList<Map<String, String>>();
	            Map<String, String> curChildMap6 = new HashMap<String, String>();
	            children6.add(curChildMap6);
	            curChildMap6.put(C_TEXT1, cur.getString(prevention));
	            childData.add(children6);
 		   }
            
        }
        dbhelper.closeCursor(cur);
        adapter=new ExAdapter(DiseaseDetail.this);
		exList.setAdapter(adapter);
		exList.expandGroup(0);
		exList.setGroupIndicator(null);
		exList.setDivider(null);
		exList.setOnGroupExpandListener(new OnGroupExpandListener(){
			@Override
			public void onGroupExpand(int arg0) {
			        // TODO Auto-generated method stub
			        for(int i=0;i<adapter.getGroupCount();i++)
			        {
			                if(arg0!=i)
			                {
			                	exList.collapseGroup(i);
			                }
			        }
			}
			               
			  });
		
		exList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				exList.collapseGroup(groupPosition);
                return false;  
    	}
		});
    }
    
    @SuppressWarnings("rawtypes")
	private void findview(){
    	 textView = (TextView) findViewById(R.id.textview);
    	 exList = (ExpandableListView) findViewById(R.id.list_detail);
    	 collect = (TextView) findViewById(R.id.collect_button_disease);
    	 dbhelper = new DBManager(this);  
         dbhelper.openDatabase();
         sqldb = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/" + DBManager.DB_NAME, null);
         cur = sqldb.rawQuery("select * from symptomchecker_condition where id="+getIntent().getStringExtra("id") , null);
         for (cur.moveToFirst();!cur.isAfterLast();cur.moveToNext()) {
	    	   @SuppressWarnings("unused")
			int id = cur.getColumnIndex(Symptom.ID);
	    	   int nameColumn = cur.getColumnIndex(Symptom.RANK_SCORE);
	    	   String flag = cur.getString(nameColumn).toString();
	    	   if(flag.equals("1")){
	    		   collect.setText(R.string.cancle_collect);
	    	   }else {
	    		   collect.setText(R.string.collect);
	    	   }
		   }
	     dbhelper.closeCursor(cur);
    	 collect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(collect.getText().equals("收藏")){
					sqldb.execSQL("update symptomchecker_condition set rank_score=1 where id="+getIntent().getStringExtra("id"));
					collect.setText(R.string.cancle_collect);
					//Toast.makeText(DiseaseDetail.this, "收藏成功" ,Toast.LENGTH_LONG).show();
				}else {
					sqldb.execSQL("update symptomchecker_condition set rank_score='0' where id="+getIntent().getStringExtra("id"));
					collect.setText(R.string.collect);
					//Toast.makeText(DiseaseDetail.this, "取消成功" ,Toast.LENGTH_LONG).show();
				}
//				sqldb.close();
			}
		});
    }
	@Override  
	protected void finalize() {
	    dbhelper.closeDatabase();
	}
    class ExAdapter extends BaseExpandableListAdapter {
    	DiseaseDetail exlistview;

    	public ExAdapter(DiseaseDetail elv) {
    		super();
    		exlistview = elv;
    	}
    	public View getGroupView(int groupPosition, boolean isExpanded,
    			View convertView, ViewGroup parent) {
	
			View view = convertView;
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.member_listview, null);
			}

			TextView title = (TextView) view.findViewById(R.id.content_001);
			title.setText(getGroup(groupPosition).toString());
			ImageView img=(ImageView) view.findViewById(R.id.ImageView01);
			img.setBackgroundResource((Integer) getPic(groupPosition));
			//img.setBackgroundResource(R.drawable.disease_description);
			ImageView image=(ImageView) view.findViewById(R.id.tubiao);
			if(isExpanded)
				image.setBackgroundResource(R.drawable.btn_browser2);
			
			else image.setBackgroundResource(R.drawable.btn_browser);
				
    		return view;
    	}


    	public long getGroupId(int groupPosition) {
    		return groupPosition;
    	}
    	
    	public Object getGroup(int groupPosition) {
    		return groupData.get(groupPosition).get(G_TEXT).toString();
    	}
    	
    	public Object getPic(int picPosition) {
    		return picData.get(picPosition).get(G_PIC);
    	}

    	public int getGroupCount() {
			return groupData.size();

    	}
    	//**************************************
    	public View getChildView(int groupPosition, int childPosition,
    			boolean isLastChild, View convertView, ViewGroup parent) {
    		View view = convertView;
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.member_childitem, null);	
			}
				final TextView title2 = (TextView) view.findViewById(R.id.child_text2);
					title2.setText(childData.get(groupPosition).get(childPosition).get(C_TEXT2).toString());
			 
			return view;
    	}

    	public long getChildId(int groupPosition, int childPosition) {
    		return childPosition;
    	}
    	
    	public Object getChild(int groupPosition, int childPosition) {
    		return childData.get(groupPosition).get(childPosition).get(C_TEXT1).toString();
    	}

    	public int getChildrenCount(int groupPosition) {
    		return childData.get(groupPosition).size();
    	}
    	//**************************************
    	public boolean hasStableIds() {
    		return true;
    	}

    	public boolean isChildSelectable(int groupPosition, int childPosition) {
    		
    		return true;
    	}

    }
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){  
			Intent intent = new Intent();
			intent.setClass(DiseaseDetail.this,AllDiseaseListview.class);
			DiseaseDetail.this.startActivity(intent);
			DiseaseDetail.this.finish();
		}  
		return super.onKeyDown(keyCode, event);
	}
}
