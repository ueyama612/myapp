package com.example.myapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import common.PinItemizedOverlay;
import common.ConvertPoint;

public class AnswerInfoActivity extends MapActivity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_info);
        
        final TextView textView1=(TextView)findViewById(R.id.name);      
        final TextView textView2=(TextView)findViewById(R.id.content);
        Button eva = (Button)findViewById(R.id.eva);
        //ボタンのクリックイベントリスナー　を設定
        eva.setOnClickListener(this);
        
        final MapView map = (MapView)findViewById(R.id.map_view1);
        map.setBuiltInZoomControls(true);
        
        double lat = 0;
        double lng = 0;
        
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        
        Bundle extras = getIntent().getExtras();
        String no = extras.getString("NO");
        Log.d("debugNO",no);

               
       //場所の名前から座標を取得
       try{
    	   
    	  if(Integer.parseInt(no)==0){
	          List<Address> addressList = geocoder.getFromLocationName("スターバックスコーヒー六本木", 1);
	          Address address = addressList.get(0);
	
	          lat = address.getLatitude();
	          lng = address.getLongitude();
	          //String adr=Double.toString(lat)+","+Double.toString(lng);
	           
	          //座標から位置情報を取得
	          ConvertPoint Cpoint = new ConvertPoint();
	          String loc=Cpoint.point2address(lat, lng, this);
	           
	          textView1.setText(loc);
	          textView2.setText("スターバックスコーヒー六本木店に空席がございますのでぜひお越しください");
    	  }
    	  
    	  if(Integer.parseInt(no)==1){
	          List<Address> addressList = geocoder.getFromLocationName("マールブランシュカフェ", 1);
	          Address address = addressList.get(0);
	
	          lat = address.getLatitude();
	          lng = address.getLongitude();
	          //String adr=Double.toString(lat)+","+Double.toString(lng);
	           
	           //座標から位置情報を取得
	           ConvertPoint Cpoint = new ConvertPoint();
	           String loc=Cpoint.point2address(lat, lng, this);
	           
	           textView1.setText(loc);
	           textView2.setText("マールブランシュカフェがが空いてますよ");
    	  }
         
       }catch(IOException e){
             textView1.setText("IOException 発生");
       }
       
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
  }
	
	public void onClick(View v) {
		Button b = (Button)v;
        Log.d("MyInfo", "Button");
        
        try {
            if (b.getId() == R.id.eva) {
            Intent intent = new Intent(AnswerInfoActivity.this, EvaluateActivity.class);
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