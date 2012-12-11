
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
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TellTopActivity extends Activity {
    /** Called when the activity is first created. */
	
	 private String strUri = "http://myapp612.appspot.com/getQuestionList?user_id=0";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tell_top);
        
        final Handler handler = new Handler();
        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1); 
        
        Thread thread = new Thread(new Runnable(){public void run(){
        //doGet get = new doGet();
        final String json = doGet.doget(strUri);

        handler.post(new Runnable(){public void run(){
	    	  try {
	    	   JSONObject rootObject = new JSONObject(json);
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

	           PhonebookAdapter adapter = new PhonebookAdapter(TellTopActivity.this, listOfPhonebook);
	           list.setAdapter(adapter);
	           
	           list.setOnItemClickListener(new OnItemClickListener() {
	               public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
	            	   Intent intent = new Intent(TellTopActivity.this, QuestionViewForTellerActivity.class);
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