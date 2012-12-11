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
        //ボタンのクリックイベントリスナー　を設定
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
    
    //質問内容をポストするメソッド
	private void post(String location_name, String limit_time, String max_point, String content, String address, String lat, String lng) {

	    Log.d("posttest", "postします");
	    String ret = "";

	    URI url = null;
	    try {
	      url = new URI( "http://myapp612.appspot.com/addQuestion" );
	      //url = new URI( "http://localhost:8080/addQuestion" );
	      Log.d("posttest", "URLはOK");
	    } catch (URISyntaxException e) {
	      e.printStackTrace();
	      ret = e.toString();
	    }

	    // POSTパラメータ付きでPOSTリクエストを構築
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
	      // 送信パラメータのエンコードを指定
	      request.setEntity(new UrlEncodedFormEntity(post_params, "UTF-8"));
	    } catch (UnsupportedEncodingException e1) {
	      e1.printStackTrace();
	    }

	    // POSTリクエストを実行
	    DefaultHttpClient httpClient = new DefaultHttpClient();
	    try {
	      Log.d("posttest", "POST開始");
	      ret = httpClient.execute(request, new ResponseHandler<String>() {

	        //@Override
	        public String handleResponse(HttpResponse response) throws IOException
	        {
	          Log.d(
	            "posttest", 
	            "レスポンスコード：" + response.getStatusLine().getStatusCode()
	          );

	          // 正常に受信できた場合は200
	          switch (response.getStatusLine().getStatusCode()) {
	          case HttpStatus.SC_OK:
	            Log.d("posttest", "レスポンス取得に成功");

	            // レスポンスデータをエンコード済みの文字列として取得する
	            return EntityUtils.toString(response.getEntity(), "UTF-8");

	          case HttpStatus.SC_NOT_FOUND:
	            Log.d("posttest", "データが存在しない");
	            return null;

	          default:
	            Log.d("posttest", "通信エラー");
	            return null;
	          }
	        }        
	      });

	    } catch (IOException e) {
	      Log.d("posttest", "通信に失敗：" + e.toString());
	    } finally {
	      // shutdownすると通信できなくなる
	      httpClient.getConnectionManager().shutdown();
	    }

	    // 受信結果をUIに表示
	    //tv.setText( ret );

	  }
}


