package com.doctor.tool;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StringUtils {
	
	public static String PROFILE_IMG_URLSUFFIX="";
	public static String DOWNLAODPROFILE_IMG_URLSUFFIX="";
	public static String ATTACH_IMG_URLSUFFIX="";
	public static String ATTACH_VIEWIMG_URLSUFFIX="";
	/** 用来操作SharePreferences的标识 */
	public static String SYS_SET_SHARE = "QD_SYS_SET_SHARE";
	/** 用来操作SharePreferences的标识 */
	public static String SHARE_LOGIN_TAG = "QD_SHARE_LOGIN_TAG";
	
	/** 如果登录成功后,用于保存用户名到SharedPreferences,以便下次不再输入 */
	public  static String SHARE_LOGIN_USERNAME = "QD_LOGIN_USERNAME";

	/** 如果登录成功后,用于保存PASSWORD到SharedPreferences,以便下次不再输入 */
	public static String SHARE_LOGIN_PASSWORD = "QD_LOGIN_PASSWORD";
	
	/** 记录当前登录的用户ID **/
	public static String SHARE_LOGIN_USERID = "QD_LOGIN_USERID";
	
	/** 第一次成功登录的手机号码 **/
	public static String SHARE_LOGIN_FISRTPHONE = "MAP_LOGIN_FIRSTPHONE";
	
	/** 记录当前登录的用户名称 **/
	public static String SHARE_LOGIN_INDEXUSERNAME = "MAP_LOGIN_INDEXUSERNAME";
	
	/** 记录当前登录的用户Screen_name**/
	public static String SHARE_LOGIN_SCREEN_NAME = "MAP_LOGIN_SCREEN_NAME";
	
	/** 记录当前登录的用户的COOKIE **/
	public static String SHARE_LOGIN_COOKIE = "MAP_LOGIN_COOKIE";
	
	/** 是否自动登录*/
	public static String SHARE_LOGIN_AUTO = "MAP_LOGIN_AUTO";
	
	/** 临时记录用户写的微博文字*/
	public static String SHARE_MICROBLOG_TEXT = "MAP_MICROBLOG_TEXT";
	/** 临时记录用户写的私信文字*/
	public static String SHARE_PRIVATEMESSAGE_TEXT = "MAP_PRIVATEMESSAGE_TEXT";
	
	/**通知的版本*/
	public static String SHARE_VERSION = "MAP_VERSION";
	/**系统版本*/
	public static String SHARE_APPVERSION = "MAP_APPVERSION";
	
	public static String getTextContentwithoutPicURL(String text){
		
		String reg = "(http://pic.qingdaonews.com/)+[a-zA-Z0-9/]+(.)+(jpg|gif|bmp|png)";
		return text.replaceFirst(reg, "");
		
	}
	
	public static String getPicURL(String text){
		
		
		String reg = "(http://pic.qingdaonews.com/)+[a-zA-Z0-9/]+(.)+(jpg|gif|bmp|png)";
		
		Pattern p = Pattern.compile(reg); 
		Matcher m = p.matcher(text.toLowerCase()); 
		StringBuffer sb = new StringBuffer(); 
		while(m.find()) {
			
		    sb.append(m.group(0)); 
		    break;
		} 

		return sb.toString();
		
	}
	
	public static String getRemoteHost(String url){
		
		if(url.contains("qingdaonews.com")){
			
			//return url.substring(0, url.indexOf("/image")).replace("http://", "");
			return "pic.qingdaonews.com";
		}
		
		return "";
	}
	
	public static String getHtmlURL(String text){			
		String reg = "(http://pic.qingdaonews.com/)+[a-zA-Z0-9/]+(.)+(jpg|gif|bmp|png)";
		
		Pattern p = Pattern.compile(reg); 
		Matcher m = p.matcher(text); 
		StringBuffer sb = new StringBuffer(); 
		while(m.find()) { 
			sb.append(m.group(0)); 
		    break;
		} 

		return sb.toString();
		
	}
	
	public static String JsonToString(JSONObject json){
		
		if(null!=json){
			
			return json.toString();
		}else{
			
			return null;
		}
		
	}
	
	public static JSONObject StringToJson(String str){
		
		if(null!=str){
			try {
				JSONObject json = new JSONObject(str);
				return json;
			} catch (JSONException e) {
				return null;
			}
		}else{
			
			return null;
		}
	}
	
	public static JSONArray StringToJsonArray(String str){
		
		if(null!=str){
			try {
				 JSONArray jsonArray = new JSONArray(str);
				return jsonArray;
			} catch (JSONException e) {
				return null;
			}
		}else{
			
			return null;
		}
	}
	
	/**
	 * 判断字符串是否正常
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isFine(String str)
	{
		try
		{
			if (str == null || str.trim().length() == 0)
			{
				return false;
			}
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public static SpannableStringBuilder getStyleContentText(String str,int colorstyle){
		
		
		SpannableStringBuilder style=new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(colorstyle),3,8,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
        return style;
	}
	
	public static String getTextFromURL(String txt){
		
		String rst = "";
		String regex="<a.*?/a>";    
		 
        Pattern pt=Pattern.compile(regex);
        Matcher mt=pt.matcher(txt);
        while(mt.find()){
        	
        	String s2=">.*?</a>";//标题部分
            String s3="href=.*?>";
            
            Pattern pt2=Pattern.compile(s2);
            Matcher mt2=pt2.matcher(mt.group());
            Pattern pt3=Pattern.compile(s3);
            Matcher mt3=pt3.matcher(mt.group());
            
            while(mt2.find()&&mt3.find()){
            	 
                rst = rst + mt2.group().replaceAll(">|</a>","")+ " ";
            }
        }
        
        if("".equals(rst)){
        	
        	rst = txt;
        }
		
		return rst;
		
	}
	
	public static void setDetailRetweetUsername(TextView detail_retweetusername,String info){
		SpannableStringBuilder style=new SpannableStringBuilder(info);
	
		 style.setSpan(new MyClickSpan(info.substring(2, info.length()-4)), 2, info.length()-4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		 //style.setSpan(new ForegroundColorSpan(Color.rgb(30, 80, 162)), 2, info.length()-4,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		 detail_retweetusername.setMovementMethod(LinkMovementMethod.getInstance());
		 detail_retweetusername.setAutoLinkMask(1);
		 detail_retweetusername.setText(style);
	}
	
	public static void setDetailRelpyUsername(TextView detail_relpyusername,String info,int offset){
		SpannableStringBuilder style=new SpannableStringBuilder(info);
	
		 style.setSpan(new MyClickSpan(info.substring(0, offset)), 0, offset, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		 style.setSpan(new ForegroundColorSpan(Color.rgb(30, 80, 162)), 0, offset,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		 detail_relpyusername.setMovementMethod(LinkMovementMethod.getInstance());
		 detail_relpyusername.setAutoLinkMask(1);
		 detail_relpyusername.setText(style);
	}
	
	public static void setDetailTextHtml(TextView detail_info,String showinfo){
		

		
		
		Pattern p = Pattern.compile("#([\u4e00-\u9fa5a-zA-Z0-9+|]+)");  
		Matcher m = p.matcher(showinfo);
		String wow = "";
		
		ArrayList valuelist = new ArrayList();
		
		while(m.find()){
			
			String groupstr = m.group();
			
			int start = showinfo.indexOf(groupstr);
			int end = start + groupstr.length();
			
			valuelist.add(""+start+","+end);
		}
		SpannableStringBuilder style=new SpannableStringBuilder(showinfo);
		for(int index=0;index<valuelist.size();index++){
			
			 String v = (String)valuelist.get(index);
			 style.setSpan(new MyClickSpan(showinfo.substring(new Integer(v.split(",")[0]), new Integer(v.split(",")[1]))), new Integer(v.split(",")[0]), new Integer(v.split(",")[1]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			 style.setSpan(new ForegroundColorSpan(Color.BLUE), new Integer(v.split(",")[0]), new Integer(v.split(",")[1]),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		
		 p = Pattern.compile("@([\u4e00-\u9fa5a-zA-Z0-9]+)");  
		 m = p.matcher(showinfo);
		valuelist = new ArrayList();
		
		while(m.find()){
			
			String groupstr = m.group();
			
			int start = showinfo.indexOf(groupstr);
			int end = start + groupstr.length();
			
			valuelist.add(""+start+","+end);
		}
		
		for(int index=0;index<valuelist.size();index++){
			
			 String v = (String)valuelist.get(index);
			 style.setSpan(new MyClickSpan(showinfo.substring(new Integer(v.split(",")[0]), new Integer(v.split(",")[1]))), new Integer(v.split(",")[0]), new Integer(v.split(",")[1]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			 style.setSpan(new ForegroundColorSpan(Color.rgb(30, 80, 162)), new Integer(v.split(",")[0]), new Integer(v.split(",")[1]),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		 p = Pattern.compile("(http://126.fm/)+[a-zA-Z0-9]+");  
		 m = p.matcher(showinfo);
		valuelist = new ArrayList();
		
		while(m.find()){
			
			String groupstr = m.group();
			
			int start = showinfo.indexOf(groupstr);
			int end = start + groupstr.length();
			
			valuelist.add(""+start+","+end);
		}
		
		for(int index=0;index<valuelist.size();index++){
			
			 String v = (String)valuelist.get(index);
			 style.setSpan(new ForegroundColorSpan(Color.rgb(30, 80, 162)), new Integer(v.split(",")[0]), new Integer(v.split(",")[1]),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		
		 detail_info.setText(style);
		
	
	}
	
	public static void setTextHtml(TextView detail_info ,String showinfo){
		
		
		Pattern p = Pattern.compile("#([\u4e00-\u9fa5a-zA-Z0-9+|]+)");  
		Matcher m = p.matcher(showinfo);
		String wow = "";
		
		ArrayList valuelist = new ArrayList();
		
		while(m.find()){
			
			String groupstr = m.group();
			
			int start = showinfo.indexOf(groupstr);
			int end = start + groupstr.length();
			
			valuelist.add(""+start+","+end);
		}
		SpannableStringBuilder style=new SpannableStringBuilder(showinfo);
		for(int index=0;index<valuelist.size();index++){
			
			 String v = (String)valuelist.get(index);
			 
			 style.setSpan(new ForegroundColorSpan(Color.BLUE), new Integer(v.split(",")[0]), new Integer(v.split(",")[1]),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		
		 p = Pattern.compile("@([\u4e00-\u9fa5a-zA-Z0-9]+)");  
		 m = p.matcher(showinfo);
		 
		valuelist.clear();
		
		while(m.find()){
			
			String groupstr = m.group();
			
			int start = showinfo.indexOf(groupstr);
			int end = start + groupstr.length();
			
			valuelist.add(""+start+","+end);
		}
		
		for(int index=0;index<valuelist.size();index++){
			
			 String v = (String)valuelist.get(index);
			
			 style.setSpan(new ForegroundColorSpan(Color.rgb(30, 80, 162)), new Integer(v.split(",")[0]), new Integer(v.split(",")[1]),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		 p = Pattern.compile("(http://qingdaonews.fm/)+[a-zA-Z0-9]+");  
		 m = p.matcher(showinfo);
		 
		 valuelist.clear();
		
		while(m.find()){
			
			String groupstr = m.group();
			
			int start = showinfo.indexOf(groupstr);
			int end = start + groupstr.length();
			
			valuelist.add(""+start+","+end);
		}
		
		for(int index=0;index<valuelist.size();index++){
			
			 String v = (String)valuelist.get(index);
			 style.setSpan(new ForegroundColorSpan(Color.rgb(30, 80, 162)), new Integer(v.split(",")[0]), new Integer(v.split(",")[1]),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		valuelist.clear();
		valuelist = null;
		 detail_info.setText(style);
		
	}
	
	public static String decodeHtml(String str){
		
		String rst = str;
		
		rst = rst.replaceAll("&lt;", "<");
		rst = rst.replaceAll("&gt;", ">");
		rst = rst.replaceAll("&quot;", "\"");
		rst = rst.replaceAll("&amp;", "&");
		
		
		return rst;
		
	}
	
	public static String getRealImgUrl(String imgurl){
		
		String realurl = "";
		
		if(null!=imgurl){
			if(imgurl.contains("url=") && imgurl.indexOf("url=")+4<imgurl.length()){
				
				realurl = imgurl.substring(imgurl.indexOf("url=")+4, imgurl.length());
					
			}else{
				realurl = imgurl;
			}
		}else{
			realurl = "";
		}
		
		return realurl;
	}
	
	public static void main(String[] args){
		
		 
		 
		 System.out.println(decodeHtml("&quot;&gt;_&lt;&quot;"));
		
	}

}
