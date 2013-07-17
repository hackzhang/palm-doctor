package com.doctor.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.doctor.main.R;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;


public class DBManager<ContactAdapter> {
	private final int BUFFER_SIZE = 400000;  
	public static final String TB_NAME = "symptomchecker_condition";
    public static final String DB_NAME = "spring.db";  
    public static final String PACKAGE_NAME = "com.doctor.main";  
    public static final String DB_PATH = "/data"  
                        + Environment.getDataDirectory().getAbsolutePath() + "/"  
                        + PACKAGE_NAME;  //在手机里存放数据库的位置
    private SQLiteDatabase database;  
    private Context context;
    private ContactAdapter contactAdapter;
      
    public DBManager(Context context){  
        this.context = context;  
          
    }  
    public DBManager(ContactAdapter contactAdapter) {
		this.contactAdapter = contactAdapter;
	}
	public void openDatabase(){  
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);  
    }  
    public SQLiteDatabase openDatabase(String path){  
  
        try {  
            if(!(new File(path).exists())){  
                InputStream is = this.context.getResources().openRawResource(R.raw.spring);//导入数据库  
                FileOutputStream fos = new FileOutputStream(path);  
                byte [] buffer = new byte[BUFFER_SIZE];  
                int count = 0;  
  
                while((count = is.read(buffer))>0){  
                    fos.write(buffer , 0 , count);
                }  
                fos.close();  
                is.close();  
  
            }  
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path, null);  
            return db;  
              
        } catch (NotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
  
  
        return null;  
    }  
    
    public void closeCursor(Cursor cur){  
    	if (!cur.isClosed()) {  
	    	cur.close();
		    cur.deactivate();
    	}
    }  
    public void closeDatabase(){  
        this.database.close();
    }  
    
    /**
     * 关闭游标和数据库
     */
    @SuppressWarnings("unused")
	private void close(Cursor cursor, SQLiteDatabase database)
    {
            try
            {
                    if (cursor != null && !cursor.isClosed())
                    {
                            cursor.close();
                    }
            }
            catch (Exception e)
            {
            }
            try
            {
            	this.database.close();
            }
            catch (Exception e)
            {
            }
    }
}
