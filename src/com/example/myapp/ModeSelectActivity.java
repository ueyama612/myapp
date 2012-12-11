package com.example.myapp;

import com.example.myapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.view.Menu;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class ModeSelectActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_mode);  
      
        // Resource object to get Drawables  
        Resources res = getResources();   
      
        // The activity TabHost  
        TabHost tabHost = getTabHost();    
      
        // Resusable TabSpec for each tab  
        TabHost.TabSpec spec;  
      
        // Reusable Intent for each tab  
        Intent intent;  
      
        // Create an Intent to launch an Activity   
        // for the tab (to be reused)  
        intent = new Intent().setClass(this, AskTopActivity.class);  
      
        // Initialize a TabSpec for each tab and   
        // add it to the TabHost  
        spec = tabHost.newTabSpec("tab2")  
                      .setIndicator("��Ȋm�F�����X�g")  
                      .setContent(intent);  
        tabHost.addTab(spec);  
      
        // Do the same for the other tabs  
        intent = new Intent().setClass(this, AskFormActivity.class);  
        spec = tabHost.newTabSpec("tab1")  
                      .setIndicator("�V�K��Ȋm�F")  
                      .setContent(intent);  
        tabHost.addTab(spec);  
      
        intent = new Intent().setClass(this, HogeActivity.class);  
        spec = tabHost.newTabSpec("tab3")  
                      .setIndicator("hoge")  
                      .setContent(intent);  
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, ConfigActivity.class);  
        spec = tabHost.newTabSpec("tab4")  
                      .setIndicator("�ݒ�")  
                      .setContent(intent);  
        tabHost.addTab(spec);  
      
        tabHost.setCurrentTab(0);  
    }

}
