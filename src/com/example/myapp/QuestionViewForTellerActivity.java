package com.example.myapp;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import common.PinItemizedOverlay;
import common.doGet;

public class QuestionViewForTellerActivity extends MapActivity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view_for_teller);
        
        final TextView textView1=(TextView)findViewById(R.id.name);      
        final TextView textView2=(TextView)findViewById(R.id.content);
        final TextView textView3=(TextView)findViewById(R.id.point);
        Button answer = (Button)findViewById(R.id.answer);
        //ボタンのクリックイベントリスナー　を設定
        answer.setOnClickListener(this);
        
        final MapView map = (MapView)findViewById(R.id.map_view1);
        map.setBuiltInZoomControls(true);
        
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("ID");
        
        final String strUri = "http://myapp612.appspot.com/getQuestion?question_id="+id;
        
        final Handler handler = new Handler();
        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1); 
        
        Thread thread = new Thread(new Runnable(){public void run(){
        //doGet get = new doGet();
        final String json = doGet.doget(strUri);

        handler.post(new Runnable(){public void run(){
	    	  try {
	    	   JSONObject rootObject = new JSONObject(json);

        	   double lat = Double.parseDouble(rootObject.getString("lat"));
        	   double lng = Double.parseDouble(rootObject.getString("lng"));
        	   String content = rootObject.getString("content");
        	   String name = rootObject.getString("name");
        	   //一時的にマックスポイントを現在のポイントとして表示している
        	   String point = rootObject.getString("max_point");
        	   
        	   textView1.setText(name);
        	   textView2.setText(content);
        	   textView3.setText(point);
               
               //ピンの描画
               Drawable pin = getResources().getDrawable(R.drawable.pin);
               PinItemizedOverlay pinOverlay = new PinItemizedOverlay(pin);
               map.getOverlays().add(pinOverlay);

               GeoPoint loc_point = new GeoPoint( (int)(lat*1E6), (int)(lng*1E6));
               //GeoPoint osaka = new GeoPoint( 34701895, 135494975);
               pinOverlay.addPoint(loc_point);
               //pinOverlay.addPoint(osaka);
               
               MapController controller = map.getController();
               controller.setZoom(17);
               map.getController().setCenter(loc_point);
        	   
	    	  } catch (JSONException e) {  
	    	   e.printStackTrace();
	    	  } 
        }});
            }});
        thread.start();
    }
    
    public void onClick(View v) {
		Button b = (Button)v;
        Log.d("MyInfo", "Button");
        
        try {
            if (b.getId() == R.id.answer) {
            Intent intent = new Intent(this, AnswerFormActivity.class);
            startActivity(intent);
            Log.i("MyInfo", "Start activity ok");
            }
        } catch (Exception e) {
            Log.d("MyInfo", "Erroraaaa", e);
        }
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

}