
package com.example.myapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Phonebook;
import common.PhonebookAdapter;
import common.doGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AskTopActivity extends Activity{
    /** Called when the activity is first created. */
	
	private String strUri = "http://myapp612.appspot.com/getQuestionList?user_id=0";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_top);
        
        final Handler handler = new Handler();
        //í êMíÜ
        final ProgressDialog dialog;
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setMessage("í êMíÜ");
        dialog.show();

        Thread thread = new Thread(new Runnable(){public void run(){
        final String json = doGet.doget(strUri);

        handler.post(new Runnable(){public void run(){
	    	  try {
	    	   JSONObject rootObject = new JSONObject(json);
	    	   if(rootObject.getString("comp") == "false"){
	    		   dialog.dismiss();
	    	   	   return;
	    	   }
	    	   JSONArray questionArray = rootObject.getJSONArray("question");
	    	   int count = questionArray.length();
	    	   JSONObject[] qObject = new JSONObject[count];

	    	   ListView list = (ListView) findViewById(R.id.jsonlistview);
	           list.setClickable(true);
	           final List<Phonebook> listOfPhonebook = new ArrayList<Phonebook>();

	           for (int i=0; i<count; i++) {
	        	   qObject[i] = questionArray.getJSONObject(i);
		           listOfPhonebook.add(new Phonebook(qObject[i].getString("id"), qObject[i].getString("name"), qObject[i].getString("content")));
	           }

	           PhonebookAdapter adapter = new PhonebookAdapter(AskTopActivity.this, listOfPhonebook);
	           list.setAdapter(adapter);
	           
	           dialog.dismiss();
	           
	           list.setOnItemClickListener(new OnItemClickListener() {
	               public void onItemClick(AdapterView<?> parent, View view, int position, long index) {
	                   //showToast(listOfPhonebook.get(position).getName());
	            	   Intent intent = new Intent(AskTopActivity.this, QuestionViewActivity.class);
	            	   intent.putExtra("ID",listOfPhonebook.get(position).getId());
	            	   startActivity(intent);
	               }
	           });
	           
	    	  } catch (JSONException e) {  
	    	   e.printStackTrace();
	    	  } 
        }});
            }});
        thread.start();      
    }
}