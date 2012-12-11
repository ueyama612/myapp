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
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class AskFormActivity extends Activity implements OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_form);
        
        Button GoSearch = (Button)findViewById(R.id.Search);
        //�{�^���̃N���b�N�C�x���g���X�i�[�@��ݒ�
        GoSearch.setOnClickListener(this);
        
        Button btn = (Button)findViewById(R.id.submit);
        btn.setOnClickListener(this);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // �A�C�e����ǉ����܂�
        adapter.add("���ׂ�");
        adapter.add("�J�t�F");
        adapter.add("�t�@�X�g�t�[�h");
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // �A�_�v�^�[��ݒ肵�܂�
        spinner.setAdapter(adapter);
        
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
            	
            	final Handler handler = new Handler();
            	//�ʐM���_�C�A���O
            	final ProgressDialog dialog;
                dialog = new ProgressDialog(AskFormActivity.this);
                dialog.setIndeterminate(true);
                dialog.setMessage("�ʐM��");
                dialog.show();
                
                Intent getintent = getIntent();
                final String Lat = getintent.getStringExtra("LAT");
                final String Lng = getintent.getStringExtra("LNG");
            	            	
           		EditText E_loc_name = (EditText)findViewById(R.id.name);
           		EditText E_limit_time= (EditText)findViewById(R.id.limit_time);
           		EditText E_max_point = (EditText)findViewById(R.id.max_point);
           		EditText E_content = (EditText)findViewById(R.id.content);
           		TextView T_Address = (TextView)findViewById(R.id.address);

           		final String location_name = E_loc_name.getText().toString();
           		final String limit_time = E_limit_time.getText().toString();
           		final String max_point = E_max_point.getText().toString();
           		final String content = E_content.getText().toString();
           		final String Address = T_Address.getText().toString();
                
                Thread thread = new Thread(new Runnable(){public void run(){
           			post(location_name, limit_time, max_point, content, Address, Lat, Lng);           			
           	        try {
                        Thread.sleep(2000);
		        	} catch (InterruptedException e) {
		            }
           			handler.post(new Runnable(){public void run(){
           				//dialog.dismiss();
           				Intent intent = new Intent(AskFormActivity.this, ModeSelectActivity.class);
                        startActivity(intent);	
           			}});
                }});
           		thread.start();
                
           		

       			

           		
            }
            else if (b.getId() == R.id.Search) {
            	EditText loc_name = (EditText)findViewById(R.id.name);
            	Intent intent = new Intent(AskFormActivity.this, SearchMapActivity.class);
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

	    // URL
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
	      httpClient.getConnectionManager().shutdown();
	    }
	  }

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}
}


