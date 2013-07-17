package com.doctor.main;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

public class AboutDialog extends AlertDialog {

	public AboutDialog(Context context) {
		super(context);
		final View view = getLayoutInflater().inflate(R.layout.about_dialog,null);
		setButton(context.getText(R.string.ok), (OnClickListener) null);
		setIcon(R.drawable.info);
		setTitle(R.string.about_app_name);
		setView(view);
		} 
}
