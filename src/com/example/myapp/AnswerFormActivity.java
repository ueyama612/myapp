package com.example.myapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.example.myapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class AnswerFormActivity extends Activity implements OnClickListener {
	
	//private TextView tv = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_form);
        
        Button GoSearch = (Button)findViewById(R.id.Search);
        //�{�^���̃N���b�N�C�x���g���X�i�[�@��ݒ�
        GoSearch.setOnClickListener(this);
        
        Button btn = (Button)findViewById(R.id.submit);
        btn.setOnClickListener(this);
        
        TextView T_Address=(TextView)findViewById(R.id.address);
        EditText E_locname=(EditText)findViewById(R.id.name);
        
        Intent intent = getIntent();
        String Address = intent.getStringExtra("ADDRESS");
        String locname = intent.getStringExtra("LOCNAME");
        T_Address.setText(Address);
        E_locname.setText(locname);
        
    }   

   	public void onClick(View v) {
   		Button b = (Button)v;
   		
   		try {
            if (b.getId() == R.id.submit) {
            	Intent getintent = getIntent();
                final String Lat = getintent.getStringExtra("LAT");
                final String Lng = getintent.getStringExtra("LNG");
            	            	
           		EditText E_loc_name = (EditText)findViewById(R.id.name);
           		EditText E_content = (EditText)findViewById(R.id.content);
           		TextView T_Address = (TextView)findViewById(R.id.address);

           		final String location_name = E_loc_name.getText().toString();
           		final String content = E_content.getText().toString();
           		final String Address = T_Address.getText().toString();
           		
           		
           		Thread thread = new Thread(new Runnable(){public void run(){
           			post(location_name, "", "", content, Address, Lat, Lng);
           		}});
           		thread.start();
           		
           		Intent intent = new Intent(this, AskTopActivity.class);
                startActivity(intent);
            }
            else if (b.getId() == R.id.Search) {
            	EditText loc_name = (EditText)findViewById(R.id.name);
            	Intent intent = new Intent(AnswerFormActivity.this, SearchMapForAnswerActivity.class);
            	intent.putExtra("LOCNAME",loc_name.getText().toString());
                startActivity(intent);
            }
        } catch (Exception e) {
            Log.d("MyInfo", "Error", e);
        }
   	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_ask_form, menu);
        return true;
    }
    
    //������e���|�X�g���郁�\�b�h
	private void post(String location_name, String limit_time, String max_point, String content, String address, String lat, String lng) {

	    Log.d("posttest", "post���܂�");
	    String ret = "";

	    URI url = null;
	    try {
	      url = new URI( "http://myapp612.appspot.com/addQuestion" );
	      //url = new URI( "http://localhost:8080/addQuestion" );
	      Log.d("posttest", "URL��OK");
	    } catch (URISyntaxException e) {
	      e.printStackTrace();
	      ret = e.toString();
	    }

	    // POST�p�����[�^�t����POST���N�G�X�g���\�z
	    HttpPost request = new HttpPost( url );
	    List<NameValuePair> post_params = new ArrayList<NameValuePair>();
	    post_params.add(new BasicNameValuePair("location_name", location_name));
	    post_params.add(new BasicNameValuePair("limit_time", limit_time));
	    post_params.add(new BasicNameValuePair("max_point", max_point));
	    post_params.add(new BasicNameValuePair("content", content));
	    post_params.add(new BasicNameValuePair("address", address));
	    post_params.add(new BasicNameValuePair("lat", lat));
	    post_params.add(new BasicNameValuePair("lng", lng));
	    try {
	      // ���M�p�����[�^�̃G���R�[�h���w��
	      request.setEntity(new UrlEncodedFormEntity(post_params, "UTF-8"));
	    } catch (UnsupportedEncodingException e1) {
	      e1.printStackTrace();
	    }

	    // POST���N�G�X�g�����s
	    DefaultHttpClient httpClient = new DefaultHttpClient();
	    try {
	      Log.d("posttest", "POST�J�n");
	      ret = httpClient.execute(request, new ResponseHandler<String>() {

	        //@Override
	        public String handleResponse(HttpResponse response) throws IOException
	        {
	          Log.d(
	            "posttest", 
	            "���X�|���X�R�[�h�F" + response.getStatusLine().getStatusCode()
	          );

	          // ����Ɏ�M�ł����ꍇ��200
	          switch (response.getStatusLine().getStatusCode()) {
	          case HttpStatus.SC_OK:
	            Log.d("posttest", "���X�|���X�擾�ɐ���");

	            // ���X�|���X�f�[�^���G���R�[�h�ς݂̕�����Ƃ��Ď擾����
	            return EntityUtils.toString(response.getEntity(), "UTF-8");

	          case HttpStatus.SC_NOT_FOUND:
	            Log.d("posttest", "�f�[�^�����݂��Ȃ�");
	            return null;

	          default:
	            Log.d("posttest", "�ʐM�G���[");
	            return null;
	          }
	        }        
	      });

	    } catch (IOException e) {
	      Log.d("posttest", "�ʐM�Ɏ��s�F" + e.toString());
	    } finally {
	      // shutdown����ƒʐM�ł��Ȃ��Ȃ�
	      httpClient.getConnectionManager().shutdown();
	    }

	    // ��M���ʂ�UI�ɕ\��
	    //tv.setText( ret );

	  }
}


