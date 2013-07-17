package com.doctor.tool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

public class MyClickSpan extends ClickableSpan  {
	
	
	private final String clicktext;
	
	public MyClickSpan(String ctext){
		clicktext = ctext;
	}

	
	@Override
	public void onClick(View widget) {
		
		Context context = widget.getContext();
		
/*		if(clicktext.startsWith("@")){
			
			Intent intent = new Intent(context, IndexUserInfo.class);
			Bundle bundle = new Bundle();
			
			bundle.putString("type", "1");
			bundle.putString("username", clicktext.replaceFirst("@", ""));
			intent.putExtras(bundle);
			context.startActivity(intent);
		}else if(clicktext.startsWith("#")){
			
			Intent intent = new Intent(context, SecondSearch.class);
			Bundle bundle = new Bundle();
			bundle.putString("keyword", clicktext);
			intent.putExtras(bundle);
			context.startActivity(intent);
		}else{
			Intent intent = new Intent(context, IndexUserInfo.class);
			Bundle bundle = new Bundle();
			
			bundle.putString("type", "1");
			bundle.putString("username", clicktext.replaceFirst("@", ""));
			intent.putExtras(bundle);
			context.startActivity(intent);
		}*/
		
		
	}

}
