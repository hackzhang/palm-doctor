package com.doctor.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SymptomActivity extends Activity implements OnClickListener{
    
	private static final int About = Menu.FIRST;
	private static final int Menu_Item = 0;
	private static final int Collect = Menu.FIRST + 1;
	private static final int More = Menu.FIRST + 2;
	private static final int FeedBack = Menu.FIRST + 3;
	private static final int Share = Menu.FIRST + 4;
	private ImageView set,gender,site,body,head,chest,neck,abdomen,pelvis,legs,feet,arms,hands,shoulders,hips;
	private Button headButton,chestButton,neckButton,abdomenButton,
	pelvisButton,legsleft,legsright,hipsleft,hipsright,feetleft,feetright,armsleft,armsright,handsleft,handsright,shouldersleft,shouldersright;
	private Boolean flag=true;//��
	private int i=0;//����
	private TextView etdata;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.symptom);
        findView();
        final String part = getIntent().getStringExtra("part");
        final boolean sex= getIntent().getBooleanExtra("sex", true);
        final int pos= getIntent().getIntExtra("pos", 0);
        String name= getIntent().getStringExtra("name");
        flag = sex;
        i = pos;
        if (part.equals("index")&&sex==true&&pos==0) {
            onBodyClick();
            onBodyPartClick();
		}if (part.equals("head")) {
	       	if(sex==true&&i==0){
	       		    body.setBackgroundResource(R.drawable.avt_male);
	    			head.setBackgroundResource(R.drawable.avt_m_front_sel_head);
	    			hidden(0);
	    			method();
	    		}
	    		if(sex==true&&i==1){
	    			body.setBackgroundResource(R.drawable.avt_male_back);
	    			head.setBackgroundResource(R.drawable.avt_m_back_sel_head);
	    			hidden(0);
	    			method();
	    		}
	    		if(sex==false&&i==0){
	    			body.setBackgroundResource(R.drawable.avt_female);
	    			head.setBackgroundResource(R.drawable.avt_f_front_sel_head);
	    			hidden(0);
	    			method();
	    		}
	    		if(sex==false&&i==1){
	    			body.setBackgroundResource(R.drawable.avt_female_back);
	    			head.setBackgroundResource(R.drawable.avt_f_back_sel_head);
	    			hidden(0);
	    			method();
	    		}
		}if (part.equals("neck")) {
			if(flag==true&&i==0){
				body.setBackgroundResource(R.drawable.avt_male);
				neck.setBackgroundResource(R.drawable.avt_m_front_sel_neck);
				method();
			}
			if(flag==true&&i==1){
				body.setBackgroundResource(R.drawable.avt_male_back);
				neck.setBackgroundResource(R.drawable.avt_m_back_sel_neck);
				method();
			}
			if(flag==false&&i==0){
				body.setBackgroundResource(R.drawable.avt_female);
				neck.setBackgroundResource(R.drawable.avt_f_front_sel_neck);
				method();
			}
			if(flag==false&&i==1){
				body.setBackgroundResource(R.drawable.avt_female_back);
				neck.setBackgroundResource(R.drawable.avt_f_back_sel_neck);
				method();
			}
		}if (part.equals("")&&name.equals("�ز�")) {
			if(flag==true&&i==0){
				body.setBackgroundResource(R.drawable.avt_male);
				chest.setBackgroundResource(R.drawable.avt_m_front_sel_chest);
				method();
			}
			if(flag==true&&i==1){
				body.setBackgroundResource(R.drawable.avt_male_back);
				chest.setBackgroundResource(R.drawable.avt_m_back_sel_back);
				method();
			}
			if(flag==false&&i==0){
				body.setBackgroundResource(R.drawable.avt_female);
				neck.setBackgroundResource(R.drawable.avt_f_front_sel_chest);
				method();
			}
			if(flag==false&&i==1){
				body.setBackgroundResource(R.drawable.avt_female_back);
				neck.setBackgroundResource(R.drawable.avt_f_back_sel_back);
				method();
			}
		}if (part.equals("")&&name.equals("����")) {
			if(flag==true&&i==0){
				body.setBackgroundResource(R.drawable.avt_male);
				abdomen.setBackgroundResource(R.drawable.avt_m_front_sel_abdomen);
				method();
			}
			if(flag==true&&i==1){
				body.setBackgroundResource(R.drawable.avt_male_back);
				abdomen.setBackgroundResource(R.drawable.avt_m_back_sel_lower_back);
				method();
			}
			if(flag==false&&i==0){
				body.setBackgroundResource(R.drawable.avt_female);
				abdomen.setBackgroundResource(R.drawable.avt_f_front_sel_abdomen);
				method();
			}
			if(flag==false&&i==1){
				body.setBackgroundResource(R.drawable.avt_female_back);
				abdomen.setBackgroundResource(R.drawable.avt_f_back_sel_lower_back);
				method();
			}  
		}if (part.equals("")&&name.equals("����")) {
			if(flag==true&&i==0){
				body.setBackgroundResource(R.drawable.avt_male);
				pelvis.setBackgroundResource(R.drawable.avt_m_front_sel_pelvis);
				method();
			}
			if(flag==true&&i==1){
				body.setBackgroundResource(R.drawable.avt_male_back);
				 pelvis.setBackgroundResource(R.drawable.avt_m_back_sel_buttocks);
				 method();
			}
			if(flag==false&&i==0){
				body.setBackgroundResource(R.drawable.avt_female);
				 pelvis.setBackgroundResource(R.drawable.avt_f_front_sel_pelvis);
				 method();
			}
			if(flag==false&&i==1){
				body.setBackgroundResource(R.drawable.avt_female_back);
				 pelvis.setBackgroundResource(R.drawable.avt_f_back_sel_buttocks);
				 method();
			}
		}if (part.equals("")&&name.equals("�β�")) {
			if(flag==true&&i==0){
				body.setBackgroundResource(R.drawable.avt_male);
				hips.setBackgroundResource(R.drawable.avt_m_front_sel_hips);
				method();
			}
			if(flag==true&&i==1){
				body.setBackgroundResource(R.drawable.avt_male_back);
				hips.setBackgroundResource(R.drawable.avt_m_back_sel_hips);
				method();
			}
			if(flag==false&&i==0){
				body.setBackgroundResource(R.drawable.avt_female);
				hips.setBackgroundResource(R.drawable.avt_f_front_sel_hips);
				method();
			}
			if(flag==false&&i==1){
				body.setBackgroundResource(R.drawable.avt_female_back);
				hips.setBackgroundResource(R.drawable.avt_f_back_sel_hips);
				method();
			}  
		}if (part.equals("")&&name.equals("��֫ | �Ȳ�")) {
	    	if(flag==true&&i==0){
				body.setBackgroundResource(R.drawable.avt_male);
	    		legs.setBackgroundResource(R.drawable.avt_m_front_sel_legs);
	    		method();
			}
			if(flag==true&&i==1){
				body.setBackgroundResource(R.drawable.avt_male_back);
				legs.setBackgroundResource(R.drawable.avt_m_back_sel_legs);
				method();
			}
			if(flag==false&&i==0){
				body.setBackgroundResource(R.drawable.avt_female);
				legs.setBackgroundResource(R.drawable.avt_f_front_sel_legs);
				method();
			}
			if(flag==false&&i==1){
				body.setBackgroundResource(R.drawable.avt_female_back);
				legs.setBackgroundResource(R.drawable.avt_f_back_sel_legs);
				method();
			}  
		}if (part.equals("")&&name.equals("�㲿")) {
		   	if(flag==true&&i==0){
				body.setBackgroundResource(R.drawable.avt_male);
	    		feet.setBackgroundResource(R.drawable.avt_m_front_sel_feet);
				method();
			}
			if(flag==true&&i==1){
				body.setBackgroundResource(R.drawable.avt_male_back);
				feet.setBackgroundResource(R.drawable.avt_m_back_sel_feet);
				method();
			}
			if(flag==false&&i==0){
				body.setBackgroundResource(R.drawable.avt_female);
				feet.setBackgroundResource(R.drawable.avt_f_front_sel_feet);
				method();
			}
			if(flag==false&&i==1){
				body.setBackgroundResource(R.drawable.avt_female_back);
				feet.setBackgroundResource(R.drawable.avt_f_back_sel_feet);
				method();
			}  
		}if (part.equals("")&&name.equals("��֫ | �첲")) {
		   	if(flag==true&&i==0){
				body.setBackgroundResource(R.drawable.avt_male);
				arms.setBackgroundResource(R.drawable.avt_m_front_sel_arms);
				method();
			}
			if(flag==true&&i==1){
				body.setBackgroundResource(R.drawable.avt_male_back);
				arms.setBackgroundResource(R.drawable.avt_m_back_sel_arms);
				method();
			}
			if(flag==false&&i==0){
				body.setBackgroundResource(R.drawable.avt_female);
				arms.setBackgroundResource(R.drawable.avt_f_front_sel_arms);
				method();
			}
			if(flag==false&&i==1){
				body.setBackgroundResource(R.drawable.avt_female_back);
				arms.setBackgroundResource(R.drawable.avt_f_back_sel_arms);
				method();
			}  
		}if (part.equals("")&&name.equals("�ֲ�")) {
		   	if(flag==true&&i==0){
				body.setBackgroundResource(R.drawable.avt_male);
				hands.setBackgroundResource(R.drawable.avt_m_front_sel_hands);
				method();
			}
			if(flag==true&&i==1){
				body.setBackgroundResource(R.drawable.avt_male_back);
				hands.setBackgroundResource(R.drawable.avt_m_back_sel_hands);
				method();
			}
			if(flag==false&&i==0){
				body.setBackgroundResource(R.drawable.avt_female);
				hands.setBackgroundResource(R.drawable.avt_f_front_sel_hands);
				method();
			}
			if(flag==false&&i==1){
				body.setBackgroundResource(R.drawable.avt_female_back);
				hands.setBackgroundResource(R.drawable.avt_f_back_sel_hands);
				method();
			}  
		}if (part.equals("")&&name.equals("�粿")) {
	    	if(flag==true&&i==0){
				body.setBackgroundResource(R.drawable.avt_male);
				shoulders.setBackgroundResource(R.drawable.avt_m_front_sel_shoulders);
				method();
			}
			if(flag==true&&i==1){
				body.setBackgroundResource(R.drawable.avt_male_back);
				shoulders.setBackgroundResource(R.drawable.avt_m_back_sel_shoulders);
				method();
			}
			if(flag==false&&i==0){
				body.setBackgroundResource(R.drawable.avt_female);
				shoulders.setBackgroundResource(R.drawable.avt_f_front_sel_shoulders);
				method();
			}
			if(flag==false&&i==1){
				body.setBackgroundResource(R.drawable.avt_female_back);
				shoulders.setBackgroundResource(R.drawable.avt_f_back_sel_shoulders);
				method();
			}  
		}

    }
    public void method() {
    	onBodyClick();
        onBodyPartClick();
	}
    public void hidden(int flag) {
    	if(flag==0){
    		chest.setBackgroundResource(0);
    		neck.setBackgroundResource(0);
    		abdomen.setBackgroundResource(0);
    		pelvis.setBackgroundResource(0);
    		legs.setBackgroundResource(0);
    		feet.setBackgroundResource(0);
    		arms.setBackgroundResource(0);
    		hands.setBackgroundResource(0);
    		shoulders.setBackgroundResource(0);
    		hips.setBackgroundResource(0);
    	}
    	if(flag==1){
    		head.setBackgroundResource(0);
    		chest.setBackgroundResource(0);
    		abdomen.setBackgroundResource(0);
    		pelvis.setBackgroundResource(0);
    		legs.setBackgroundResource(0);
    		feet.setBackgroundResource(0);
    		arms.setBackgroundResource(0);
    		hands.setBackgroundResource(0);
    		shoulders.setBackgroundResource(0);
    		hips.setBackgroundResource(0);
    	}
    	if(flag==2){
    		head.setBackgroundResource(0);
    		neck.setBackgroundResource(0);
    		abdomen.setBackgroundResource(0);
    		pelvis.setBackgroundResource(0);
    		legs.setBackgroundResource(0);
    		feet.setBackgroundResource(0);
    		arms.setBackgroundResource(0);
    		hands.setBackgroundResource(0);
    		shoulders.setBackgroundResource(0);
    		hips.setBackgroundResource(0);
    	}
    	if(flag==3){
    		head.setBackgroundResource(0);
    		chest.setBackgroundResource(0);
    		neck.setBackgroundResource(0);
    		pelvis.setBackgroundResource(0);
    		legs.setBackgroundResource(0);
    		feet.setBackgroundResource(0);
    		arms.setBackgroundResource(0);
    		hands.setBackgroundResource(0);
    		shoulders.setBackgroundResource(0);
    		hips.setBackgroundResource(0);
    	}
    	if(flag==4){
    		head.setBackgroundResource(0);
    		chest.setBackgroundResource(0);
    		neck.setBackgroundResource(0);
    		abdomen.setBackgroundResource(0);
    		pelvis.setBackgroundResource(0);
    		legs.setBackgroundResource(0);
    		feet.setBackgroundResource(0);
    		arms.setBackgroundResource(0);
    		hands.setBackgroundResource(0);
    		shoulders.setBackgroundResource(0);
    	}
    	if(flag==5){
    		head.setBackgroundResource(0);
    		chest.setBackgroundResource(0);
    		neck.setBackgroundResource(0);
    		abdomen.setBackgroundResource(0);
    		legs.setBackgroundResource(0);
    		feet.setBackgroundResource(0);
    		arms.setBackgroundResource(0);
    		hands.setBackgroundResource(0);
    		shoulders.setBackgroundResource(0);
    		hips.setBackgroundResource(0);
    	}
    	if(flag==6){
    		head.setBackgroundResource(0);
    		chest.setBackgroundResource(0);
    		neck.setBackgroundResource(0);
    		abdomen.setBackgroundResource(0);
    		pelvis.setBackgroundResource(0);
    		feet.setBackgroundResource(0);
    		arms.setBackgroundResource(0);
    		hands.setBackgroundResource(0);
    		shoulders.setBackgroundResource(0);
    		hips.setBackgroundResource(0);
    	}
    	if(flag==7){
    		head.setBackgroundResource(0);
    		chest.setBackgroundResource(0);
    		neck.setBackgroundResource(0);
    		abdomen.setBackgroundResource(0);
    		pelvis.setBackgroundResource(0);
    		legs.setBackgroundResource(0);
    		arms.setBackgroundResource(0);
    		hands.setBackgroundResource(0);
    		shoulders.setBackgroundResource(0);
    		hips.setBackgroundResource(0);
    	}
    	if(flag==8){
    		head.setBackgroundResource(0);
    		chest.setBackgroundResource(0);
    		neck.setBackgroundResource(0);
    		abdomen.setBackgroundResource(0);
    		pelvis.setBackgroundResource(0);
    		legs.setBackgroundResource(0);
    		feet.setBackgroundResource(0);
    		hands.setBackgroundResource(0);
    		shoulders.setBackgroundResource(0);
    		hips.setBackgroundResource(0);
    	}
    	if(flag==9){
    		head.setBackgroundResource(0);
    		chest.setBackgroundResource(0);
    		neck.setBackgroundResource(0);
    		abdomen.setBackgroundResource(0);
    		pelvis.setBackgroundResource(0);
    		legs.setBackgroundResource(0);
    		feet.setBackgroundResource(0);
    		arms.setBackgroundResource(0);
    		shoulders.setBackgroundResource(0);
    		hips.setBackgroundResource(0);
    	}
       	if(flag==10){
       	  	head.setBackgroundResource(0);
    		chest.setBackgroundResource(0);
    		neck.setBackgroundResource(0);
    		abdomen.setBackgroundResource(0);
    		pelvis.setBackgroundResource(0);
    		legs.setBackgroundResource(0);
    		feet.setBackgroundResource(0);
    		arms.setBackgroundResource(0);
    		hands.setBackgroundResource(0);
    		hips.setBackgroundResource(0);
    	}
		
	}
    //����ȫ����͸����λ
    public void hiddenWhole() {
    	head.setBackgroundResource(0);
		chest.setBackgroundResource(0);
		neck.setBackgroundResource(0);
		abdomen.setBackgroundResource(0);
		pelvis.setBackgroundResource(0);
		legs.setBackgroundResource(0);
		feet.setBackgroundResource(0);
		arms.setBackgroundResource(0);
		hands.setBackgroundResource(0);
		shoulders.setBackgroundResource(0);
		hips.setBackgroundResource(0);
	}
    
    public void hips() {
    	if(flag==true&&i==0){
			hips.setBackgroundResource(R.drawable.avt_m_front_sel_hips);
			hidden(4);
		}
		if(flag==true&&i==1){
			hips.setBackgroundResource(R.drawable.avt_m_back_sel_hips);
			hidden(4);
		}
		if(flag==false&&i==0){
			hips.setBackgroundResource(R.drawable.avt_f_front_sel_hips);
			hidden(4);
		}
		if(flag==false&&i==1){
			hips.setBackgroundResource(R.drawable.avt_f_back_sel_hips);
			hidden(4);
		}  
	}
    public void legs() {
    	if(flag==true&&i==0){
    		legs.setBackgroundResource(R.drawable.avt_m_front_sel_legs);
			hidden(6);
		}
		if(flag==true&&i==1){
			legs.setBackgroundResource(R.drawable.avt_m_back_sel_legs);
			hidden(6);
		}
		if(flag==false&&i==0){
			legs.setBackgroundResource(R.drawable.avt_f_front_sel_legs);
			hidden(6);
		}
		if(flag==false&&i==1){
			legs.setBackgroundResource(R.drawable.avt_f_back_sel_legs);
			hidden(6);
		}  
	}
    public void feet() {
    	if(flag==true&&i==0){
    		feet.setBackgroundResource(R.drawable.avt_m_front_sel_feet);
			hidden(7);
		}
		if(flag==true&&i==1){
			feet.setBackgroundResource(R.drawable.avt_m_back_sel_feet);
			hidden(7);
		}
		if(flag==false&&i==0){
			feet.setBackgroundResource(R.drawable.avt_f_front_sel_feet);
			hidden(7);
		}
		if(flag==false&&i==1){
			feet.setBackgroundResource(R.drawable.avt_f_back_sel_feet);
			hidden(7);
		}  
	}
    public void arms() {
    	if(flag==true&&i==0){
			arms.setBackgroundResource(R.drawable.avt_m_front_sel_arms);
			hidden(8);
		}
		if(flag==true&&i==1){
			arms.setBackgroundResource(R.drawable.avt_m_back_sel_arms);
			hidden(8);
		}
		if(flag==false&&i==0){
			arms.setBackgroundResource(R.drawable.avt_f_front_sel_arms);
			hidden(8);
		}
		if(flag==false&&i==1){
			arms.setBackgroundResource(R.drawable.avt_f_back_sel_arms);
			hidden(8);
		}  
	}
    public void hands() {
    	if(flag==true&&i==0){
			hands.setBackgroundResource(R.drawable.avt_m_front_sel_hands);
			hidden(9);
		}
		if(flag==true&&i==1){
			hands.setBackgroundResource(R.drawable.avt_m_back_sel_hands);
			hidden(9);
		}
		if(flag==false&&i==0){
			hands.setBackgroundResource(R.drawable.avt_f_front_sel_hands);
			hidden(9);
		}
		if(flag==false&&i==1){
			hands.setBackgroundResource(R.drawable.avt_f_back_sel_hands);
			hidden(9);
		}  
	}
    public void shoulders() {
    	if(flag==true&&i==0){
			shoulders.setBackgroundResource(R.drawable.avt_m_front_sel_shoulders);
			hidden(10);
		}
		if(flag==true&&i==1){
			shoulders.setBackgroundResource(R.drawable.avt_m_back_sel_shoulders);
			hidden(10);
		}
		if(flag==false&&i==0){
			shoulders.setBackgroundResource(R.drawable.avt_f_front_sel_shoulders);
			hidden(10);
		}
		if(flag==false&&i==1){
			shoulders.setBackgroundResource(R.drawable.avt_f_back_sel_shoulders);
			hidden(10);
		}  
	}
    
    public void intent(String id,String name,Boolean sex,int pos) {
	    Intent intent = new Intent();
	    intent.putExtra("part", "");
	    intent.putExtra("id", id);
	    intent.putExtra("name", name);
	    intent.putExtra("sex", sex);
	    intent.putExtra("pos", pos);
		intent.setClass(SymptomActivity.this,SymptomBodyList.class);
		SymptomActivity.this.startActivity(intent);
		SymptomActivity.this.finish();
	}
    public void intenthead(Boolean sex,int pos) {
	    Intent intent = new Intent();
	    intent.putExtra("part", "head");
	    intent.putExtra("sex", sex);
	    intent.putExtra("pos", pos);
		intent.setClass(SymptomActivity.this,BodyPart.class);
		SymptomActivity.this.startActivity(intent);
		SymptomActivity.this.finish();
	}
    public void intentneck(Boolean sex,int pos) {
	    Intent intent = new Intent();
	    intent.putExtra("part", "neck");
	    intent.putExtra("sex", sex);
	    intent.putExtra("pos", pos);
		intent.setClass(SymptomActivity.this,BodyPart.class);
		SymptomActivity.this.startActivity(intent);
		SymptomActivity.this.finish();
	}
    
    public void onBodyPartClick() {
    	//�������λ����¼�
    	headButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(flag==true&&i==0){
					head.setBackgroundResource(R.drawable.avt_m_front_sel_head);
					hidden(0);
					intenthead(flag,i);
				}
				if(flag==true&&i==1){
					head.setBackgroundResource(R.drawable.avt_m_back_sel_head);
					hidden(0);
					intenthead(flag,i);
				}
				if(flag==false&&i==0){
					head.setBackgroundResource(R.drawable.avt_f_front_sel_head);
					hidden(0);
					intenthead(flag,i);
				}
				if(flag==false&&i==1){
					head.setBackgroundResource(R.drawable.avt_f_back_sel_head);
					hidden(0);
					intenthead(flag,i);
				}

			}
		});
        
        neckButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(flag==true&&i==0){
					neck.setBackgroundResource(R.drawable.avt_m_front_sel_neck);
					hidden(1);
					intentneck(flag,i);
				}
				if(flag==true&&i==1){
					neck.setBackgroundResource(R.drawable.avt_m_back_sel_neck);
					hidden(1);
					intentneck(flag,i);
				}
				if(flag==false&&i==0){
					neck.setBackgroundResource(R.drawable.avt_f_front_sel_neck);
					hidden(1);
					intentneck(flag,i);
				}
				if(flag==false&&i==1){
					neck.setBackgroundResource(R.drawable.avt_f_back_sel_neck);
					hidden(1);
					intentneck(flag,i);
				}  
			}
		});
        
        chestButton.setOnClickListener(new OnClickListener() {
			
   			@Override
   			public void onClick(View v) {
   				if(flag==true&&i==0){
					chest.setBackgroundResource(R.drawable.avt_m_front_sel_chest);
					hidden(2);
					intent("7", "�ز�",flag,i);
				}
				if(flag==true&&i==1){
					chest.setBackgroundResource(R.drawable.avt_m_back_sel_back);
					hidden(2);
					intent("7", "�ز�",flag,i);
				}
				if(flag==false&&i==0){
					chest.setBackgroundResource(R.drawable.avt_f_front_sel_chest);
					hidden(2);
					intent("7", "�ز�",flag,i);
				}
				if(flag==false&&i==1){
					chest.setBackgroundResource(R.drawable.avt_f_back_sel_back);
					hidden(2);
					intent("7", "�ز�",flag,i);
				}  
   			}
   		});
        
        abdomenButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(flag==true&&i==0){
					abdomen.setBackgroundResource(R.drawable.avt_m_front_sel_abdomen);
					hidden(3);
					intent("13", "����",flag,i);
				}
				if(flag==true&&i==1){
					abdomen.setBackgroundResource(R.drawable.avt_m_back_sel_lower_back);
					hidden(3);
					intent("13", "����",flag,i);
				}
				if(flag==false&&i==0){
					abdomen.setBackgroundResource(R.drawable.avt_f_front_sel_abdomen);
					hidden(3);
					intent("13", "����",flag,i);
				}
				if(flag==false&&i==1){
					abdomen.setBackgroundResource(R.drawable.avt_f_back_sel_lower_back);
					hidden(3);
					intent("13", "����",flag,i);
				}  
			}
		});
        
        
        pelvisButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(flag==true&&i==0){
					pelvis.setBackgroundResource(R.drawable.avt_m_front_sel_pelvis);
					hidden(5);
					intent("14", "����",flag,i);
				}
				if(flag==true&&i==1){
					 pelvis.setBackgroundResource(R.drawable.avt_m_back_sel_buttocks);
					hidden(5);
					intent("14", "����",flag,i);
				}
				if(flag==false&&i==0){
					 pelvis.setBackgroundResource(R.drawable.avt_f_front_sel_pelvis);
					hidden(5);
					intent("14", "����",flag,i);
				}
				if(flag==false&&i==1){
					 pelvis.setBackgroundResource(R.drawable.avt_f_back_sel_buttocks);
					hidden(5);
					intent("14", "����",flag,i);
				}  
			}
		});
        
        hipsleft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hips();
				intent("15", "�β�",flag,i);
			}
		});
        
        hipsright.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hips();
				intent("15", "�β�",flag,i);
			}
		});
        
        legsleft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				legs();
				intent("17", "��֫ | �Ȳ�",flag,i);
			}
		});
        
        legsright.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				legs();
				intent("17", "��֫ | �Ȳ�",flag,i);
			}
		});
        
        feetleft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				feet();
				intent("18", "�㲿",flag,i);
			}
		});
        
        feetright.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				feet();
				intent("18", "�㲿",flag,i);
			}
		});
        
        
        armsleft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				arms();
				intent("11", "��֫ | �첲",flag,i);
			}
		});
        armsright.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				arms(); 
				intent("11", "��֫ | �첲",flag,i);
			}
		});
        handsleft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hands(); 
				intent("12", "�ֲ�",flag,i);
			}
		});
        handsright.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hands();
				intent("12", "�ֲ�",flag,i);
			}
		});
        shouldersleft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				shoulders();
				intent("10", "�粿",flag,i);
			}
		});
        shouldersright.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				shoulders();
				intent("10", "�粿",flag,i);
			}
		});
       
    }
    
    public void onBodyClick() {
    	 //����ͼ���Ա��ǰ�� �¼�
        set.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent();
				intent.setClass(SymptomActivity.this,SymptomList.class);
					intent.putExtra("part", getIntent().getStringExtra("part"));
					intent.putExtra("name", getIntent().getStringExtra("name"));
				    intent.putExtra("sex", flag);
				    intent.putExtra("pos", i);
				SymptomActivity.this.startActivity(intent);
				SymptomActivity.this.finish();
//				Toast.makeText(SymptomActivity.this, "��ת����ģʽ"+flag+i+getIntent().getStringExtra("name") ,Toast.LENGTH_LONG).show();  
			}
		});
        gender.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(flag==false){//Ů
					if(i==0){//����
						body.setBackgroundResource(R.drawable.avt_male);
						gender.setBackgroundResource(R.drawable.button_gender_male);
						hiddenWhole();
						flag=true;
						i=0;
					}else {//����
						body.setBackgroundResource(R.drawable.avt_male_back);
						gender.setBackgroundResource(R.drawable.button_gender_male);
						hiddenWhole();
						flag=true;
						i=1;
					}
					
				}
				else{//�� 
					if(i==0){//����
						body.setBackgroundResource(R.drawable.avt_female);
						gender.setBackgroundResource(R.drawable.button_gender_female);
						hiddenWhole();
						flag=false;
						i=0;
					}else {//����
						body.setBackgroundResource(R.drawable.avt_female_back);
						gender.setBackgroundResource(R.drawable.button_gender_female);
						hiddenWhole();
						flag=false;
						i=1;
					}
				}
				
			}
		});
        site.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(i==0){//����
					if(flag==true){//��
						body.setBackgroundResource(R.drawable.avt_male_back);
						site.setBackgroundResource(R.drawable.button_back);
						hiddenWhole();
						i=1;
						flag=true;
						
					}else {//Ů
						body.setBackgroundResource(R.drawable.avt_female_back);
						site.setBackgroundResource(R.drawable.button_back);
						hiddenWhole();
						i=1;
						flag=false;
					}
					
				}
				else{//����
					if(flag==false){//Ů
						body.setBackgroundResource(R.drawable.avt_female);
						site.setBackgroundResource(R.drawable.button_front);
						hiddenWhole();
						i=0;
						flag=false;
					}else {//��
						body.setBackgroundResource(R.drawable.avt_male);
						site.setBackgroundResource(R.drawable.button_front);
						hiddenWhole();
						i=0;
						flag=true;
					}
				}
			}
		});
    }
    
    public void findView() {
    	set = (ImageView) findViewById(R.id.ImageView04);
        gender = (ImageView) findViewById(R.id.ImageView02);
        body = (ImageView) findViewById(R.id.ImageView01);
        site = (ImageView) findViewById(R.id.ImageView03);
        head = (ImageView) findViewById(R.id.head);
        chest = (ImageView) findViewById(R.id.chest);
        neck = (ImageView) findViewById(R.id.neck);
        abdomen = (ImageView) findViewById(R.id.abdomen);
        legs = (ImageView) findViewById(R.id.legs);
        feet = (ImageView) findViewById(R.id.feet);
        arms = (ImageView) findViewById(R.id.arms);
        hands = (ImageView) findViewById(R.id.hands);
        shoulders = (ImageView) findViewById(R.id.shoulders);
        hips = (ImageView) findViewById(R.id.hips);
        pelvis = (ImageView) findViewById(R.id.pelvis);
        headButton = (Button) findViewById(R.id.headbutton);
        chestButton = (Button) findViewById(R.id.chestbutton);
        neckButton = (Button) findViewById(R.id.neckbutton);
        abdomenButton = (Button) findViewById(R.id.abdomenbutton);
        pelvisButton = (Button) findViewById(R.id.pelvisbutton);
        legsleft = (Button) findViewById(R.id.legsleft);
        legsright = (Button) findViewById(R.id.legsright);
        feetleft = (Button) findViewById(R.id.feetleft);
        feetright = (Button) findViewById(R.id.feetright);
        armsleft = (Button) findViewById(R.id.armsleft);
        armsright = (Button) findViewById(R.id.armsright);
        handsleft = (Button) findViewById(R.id.handsleft);
        handsright = (Button) findViewById(R.id.handsright);
        shouldersleft = (Button) findViewById(R.id.shouldersleft);
        shouldersright = (Button) findViewById(R.id.shouldersright);
        hipsleft = (Button) findViewById(R.id.hipsleft);
        hipsright = (Button) findViewById(R.id.hipsright);
        
        etdata = (TextView) findViewById(R.id.edit_symptom_main);
        etdata.setOnClickListener(this);
    }
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){  
			Intent intent = new Intent();
			intent.setClass(SymptomActivity.this,SpringRainDoctor.class);
			SymptomActivity.this.startActivity(intent);
			SymptomActivity.this.finish();
		}  
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		onSearchRequested();
	}
	
	@Override
	public boolean onSearchRequested(){
		
		String text=etdata.getText().toString();
		Bundle bundle=new Bundle();
		bundle.putString("data", text);
		
		//�򿪸��������򣨵�һ������Ĭ����ӵ��������ֵ��
		//bundleΪ���ݵ�����
		startSearch("", false, bundle, false);
		//����ط�һ��Ҫ������ ���ֻ��super.onSearchRequested����
		//����onSearchRequested��������Ĭ��ֵ���޷���ӵ���������
		//bundleҲ�޷����ݳ�ȥ
		return true;
	}
	/*����menu*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu_Item, Collect, Menu.NONE, R.string.collect).setIcon(R.drawable.info_collection);
		SubMenu subMenu = menu.addSubMenu(Menu_Item, More, Menu.NONE, R.string.more).setIcon(R.drawable.info_about);
		//MenuItem menuitem = subMenu.add(Menu_Item, More, Menu.NONE, "����").setIcon(R.drawable.info);
		subMenu.add(Menu_Item, About, Menu.NONE, R.string.about_us);
		subMenu.add(Menu_Item, FeedBack, Menu.NONE, R.string.feedback);
		subMenu.add(Menu_Item, Share, Menu.NONE, R.string.share);
		return true;
	}
	/*����˵��¼�*/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId())
		{
			case About:
				new AboutDialog(this).show();
				return true;
			case Collect:
				forward();
				return true;
		    case FeedBack:
		    	feedback();
		    	return true;
		    case Share:
		    	share();
		        return true;
		}
		return true;
	}
	//��ת����
	public void feedback() {
    	Intent intent = new Intent();
    	intent.putExtra("leave", "symptom");
  		intent.setClass(SymptomActivity.this,FeedBack.class);
  		SymptomActivity.this.startActivity(intent);
  		SymptomActivity.this.finish();
	}
	
	//��ת����
	public void share() {
		Intent intent=new Intent(Intent.ACTION_SEND);   
		intent.setType("text/plain");  //������������� 
		intent.putExtra(Intent.EXTRA_SUBJECT, "����ҽ��");  //���� 
		intent.putExtra(Intent.EXTRA_TEXT, "�����Բ�,����ߵ���������!���ص�ַ��http://vip.hiao.com/2011/11/sjkhd/ ");  //���� 
		startActivity(Intent.createChooser(intent, "һ������"));  //Ŀ��Ӧ��ѡ��Ի���ı���	
	}
	
	//��ת��--�ղ�
	public void forward() {
	    Intent intent = new Intent();
		intent.setClass(SymptomActivity.this,DiseaseCollect.class);
		SymptomActivity.this.startActivity(intent);
		SymptomActivity.this.finish();
	}
}
