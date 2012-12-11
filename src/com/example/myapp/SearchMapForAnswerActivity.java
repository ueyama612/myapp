package com.example.myapp;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import common.ConvertPoint;
import common.PinItemizedOverlay;

public class SearchMapForAnswerActivity extends MapActivity implements OnClickListener{
	double lat = 0;
    double lng = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map_for_answer);
        
        Button Kettei = (Button)findViewById(R.id.kettei);
        //�{�^���̃N���b�N�C�x���g���X�i�[�@��ݒ�
        Kettei.setOnClickListener(this);
        
        MapView map = (MapView)findViewById(R.id.map_view1);
        map.setBuiltInZoomControls(true);
        
        Bundle extras = getIntent().getExtras();
        String loc_name = extras.getString("LOCNAME");

        TextView textView1=(TextView)findViewById(R.id.name);
        //TextView textView2=(TextView)findViewById(R.id.lng);
        TextView textView3=(TextView)findViewById(R.id.address);
        
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        
        //�ꏊ�̖��O������W���擾
        try{
           List<Address> addressList = geocoder.getFromLocationName(loc_name, 1);
           Address address = addressList.get(0);
 
           lat = address.getLatitude();
           lng = address.getLongitude();
           //String adr=Double.toString(lat)+","+Double.toString(lng);
            
            //���W����ʒu�����擾
           ConvertPoint Cpoint = new ConvertPoint();
	       String loc=Cpoint.point2address(lat, lng, this);
            
           textView1.setText(loc_name);
           //textView2.setText(Double.toString(lng));
           textView3.setText(loc);
          
        }catch(IOException e){
              textView3.setText("�������[�h��ς��Ă�������");
        }
        
        //�s���̕`��
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

   		Intent getintent = getIntent();
        final String locname = getintent.getStringExtra("LOCNAME");
   		
   		//TextView textView1=(TextView)findViewById(R.id.lat);
        //TextView textView2=(TextView)findViewById(R.id.lng);
        TextView textView3=(TextView)findViewById(R.id.address);
    	Intent intent = new Intent(this, AnswerFormActivity.class);
    	intent.putExtra("LAT",Double.toString(lat));
    	intent.putExtra("LNG",Double.toString(lng));
    	intent.putExtra("ADDRESS",textView3.getText().toString());
    	intent.putExtra("LOCNAME",locname);
    	
        startActivity(intent);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

}