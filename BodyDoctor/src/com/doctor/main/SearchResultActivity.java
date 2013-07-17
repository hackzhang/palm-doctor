package com.doctor.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doctor.entity.Condition;
import com.doctor.tool.DBManager;
import com.doctor.tool.SearchAdapter;
import com.doctor.tool.SecTool;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class SearchResultActivity extends Activity implements OnClickListener{

	@SuppressWarnings("rawtypes")
	public DBManager dbhelper;  
    SQLiteDatabase sqldb;
    Cursor cur;
    List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>(); 
    private SimpleAdapter simpleAdapter;
    private ListView listView;
	private TextView tvquery,tvdata;
	private TextView edit;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.searchresult);
	    findview();
		initData(getIntent().getStringExtra(SearchManager.QUERY));
	}
	
	 private void initData(String query){
         resList = getData(query);
         simpleAdapter = new SearchAdapter(
    		this,resList, R.layout.all_disease_item, 
    		new String[] {"title","alias"}, 
    		new int[] {R.id.stationTitle,R.id.diseaseDesc
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
			    String did = map.get(Condition.ID);
			    Intent intent = new Intent();
			    intent.putExtra("id", did);
				intent.setClass(SearchResultActivity.this,DiseaseDetail.class);
				SearchResultActivity.this.startActivity(intent);
				SearchResultActivity.this.finish();
			   }  
			        });  
	 }

	
	  private void findview(){
			listView = (ListView) findViewById(R.id.list1);
			tvquery=(TextView)findViewById(R.id.tvquery);
			tvdata=(TextView)findViewById(R.id.tvdata);
			edit=(TextView)findViewById(R.id.edit);
			edit.setText(getIntent().getStringExtra(SearchManager.QUERY));
			doSearchQuery();
			edit.setOnClickListener(this);
			
	    }
	
	/**
	  * 获取数据库数据
	  */
	   @SuppressWarnings("rawtypes")
	public List<Map<String, Object>> getData(String query){
		   
		   try {
			    String qString = SecTool.filterSQLInjection(query);
			    qString.replace("", "%");
			    qString.replace("'", "%");
			    dbhelper = new DBManager(this);  
		        dbhelper.openDatabase();  
		        dbhelper.closeDatabase();  
		        sqldb = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/" + DBManager.DB_NAME, null);
		        cur = sqldb.rawQuery("select * from symptomchecker_condition where name like '%"+qString+"%'" , null);
		       for (cur.moveToFirst();!cur.isAfterLast();cur.moveToNext()) {
		    	   Map<String, Object> resMap = new HashMap<String, Object>();
		    	   int id = cur.getColumnIndex(Condition.ID);
		    	   int nameColumn = cur.getColumnIndex(Condition.NAME);
		    	   int nameDesc = cur.getColumnIndex(Condition.ALIAS);
		           resMap.put("id",cur.getString(id));
		    	   resMap.put("title", cur.getString(nameColumn));
		    	   resMap.put("alias", cur.getString(nameDesc));
			       resList.add(resMap);
			       cur.moveToNext(); 
			   }
			
			} catch (Exception e) {
				 Toast.makeText(SearchResultActivity.this, R.string.check ,Toast.LENGTH_LONG).show(); 
			}
		   dbhelper.closeCursor(cur);
	       return resList;
		   
	   }
	   
	   
	@Override  
	protected void finalize() {  
	    dbhelper.closeDatabase();
	}
	
	public void doSearchQuery(){
		final Intent intent = getIntent();
		//获得搜索框里值
		String query=intent.getStringExtra(SearchManager.QUERY);
		tvquery.setText(query);
		//保存搜索记录
		SearchRecentSuggestions suggestions=new SearchRecentSuggestions(this,
				SearchSuggestionSampleProvider.AUTHORITY, SearchSuggestionSampleProvider.MODE);
		suggestions.saveRecentQuery(query, null);
		if(Intent.ACTION_SEARCH.equals(intent.getAction())){
			//获取传递的数据
			Bundle bundled=intent.getBundleExtra(SearchManager.APP_DATA);
			if(bundled!=null){
				String ttdata=bundled.getString("data");
				tvdata.setText(ttdata);

			}else{
				tvdata.setText("no data");
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		onSearchRequested();
	}
	
	@Override
	public boolean onSearchRequested(){
		
		startSearch("", false, null, false);
		return true;
	}
	
	@Override
	public void onNewIntent(Intent intent){
		super.onNewIntent(intent);
		//获得搜索框里值
		String query=intent.getStringExtra(SearchManager.QUERY);
		initData(query);
		edit.setText(query);
		//保存搜索记录
		SearchRecentSuggestions suggestions=new SearchRecentSuggestions(this,
				SearchSuggestionSampleProvider.AUTHORITY, SearchSuggestionSampleProvider.MODE);
		suggestions.saveRecentQuery(query, null);
		if(Intent.ACTION_SEARCH.equals(intent.getAction())){
			//获取传递的数据
			Bundle bundled=intent.getBundleExtra(SearchManager.APP_DATA);
			if(bundled!=null){
				String ttdata=bundled.getString("data");
				tvdata.setText(ttdata);

			}else{
				tvdata.setText("no data");
			}
		}
	}
}
