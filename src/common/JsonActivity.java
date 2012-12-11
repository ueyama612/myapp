package common;
 
import java.util.ArrayList;  
import java.util.List;  
import org.apache.http.HttpResponse;  
import org.apache.http.HttpStatus;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.util.EntityUtils;  
import org.json.JSONArray;  
import org.json.JSONException;  
import org.json.JSONObject;  

import com.example.myapp.R;
import com.example.myapp.R.id;
import com.example.myapp.R.layout;

import android.app.Activity;  
import android.os.Bundle;  
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;  
import android.widget.ListView;  
import android.widget.TextView;

  
public class JsonActivity extends Activity {  
  
 //private String strUri = "http://1.latest.slim3demo001.appspot.com/AjaxTweet.json";
 private String strUri = "http://myapp612.appspot.com/getQuestionList?user_id=0";
 
 public String doGet( String url )
 {
     try
     {
         HttpGet method = new HttpGet( url );

         DefaultHttpClient client = new DefaultHttpClient();

         // ヘッダを設定する
         method.setHeader( "Connection", "Keep-Alive" );
         
         HttpResponse response = client.execute( method );
         int status = response.getStatusLine().getStatusCode();
         if ( status != HttpStatus.SC_OK )
             throw new Exception( "" );
         String result = EntityUtils.toString(response.getEntity(), "UTF-8" );
         client.getConnectionManager().shutdown();
         return result;
     }
     catch ( Exception e )
     {
         return "era-";
     }
 }
  
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_json);
        //final TextView tv = new TextView(this);
        final Handler handler = new Handler();
        //final TextView textview = new TextView(this);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1); 
        
        Thread thread = new Thread(new Runnable(){public void run(){
        	final String json = doGet(strUri);
         
        handler.post(new Runnable(){public void run(){
	    	  try {/*
	    	   Log.d("MyInfo", "0");
	    	   JSONArray jsonArray = new JSONArray(json);
	    	   Log.d("MyInfo", "1");
	    	   int cnt = jsonArray.length();
	    	   Log.d("MyInfo", "2");
	    	   List<JSONObject> jlist = new ArrayList<JSONObject>();
	    	   Log.d("MyInfo", "3");
	    	   for (int i = 0; i < cnt; i++) {  
	    	    jlist.add(jsonArray.getJSONObject(i));  
	    	   }  
	    	   for (JSONObject obj : jlist) {  
	    	    String item = obj.getString("name");  
	    	    adapter.add(item);  
	    	   }  
	    	  } catch (JSONException e) {  
	    	   e.printStackTrace();  
	    	  } 
	    	  ListView listView = (ListView)findViewById(R.id.jsonlistview);  
	    	  listView.setAdapter(adapter); */
	    		  //右のページ見たらわかりそう；
	    	   Log.d("MyInfo", "0");
	    	   JSONObject rootObject = new JSONObject(json);
	    	   //JSONObject questionObject = rootObject.getJSONObject("question");
	    	   JSONArray questionArray = rootObject.getJSONArray("question");
	    	   //JSONArray contentArray = questionObject.getJSONArray("content");
	    	   int count = questionArray.length();
	    	   JSONObject[] nameObject = new JSONObject[count];
	    	   
	    	   for (int i=0; i<count; i++) {  
	    		   nameObject[i] = questionArray.getJSONObject(i);
	    	       adapter.add(nameObject[i].getString("name"));  
	    	   }  
	    	  } catch (JSONException e) {  
	    	   e.printStackTrace();  
	    	  } 
	    	  ListView listView = (ListView)findViewById(R.id.jsonlistview);  
	    	  listView.setAdapter(adapter);

        }});
            }});
        thread.start();
        /*
        Thread thread = new Thread(new Runnable(){public void run(){
        	final String text = doGet(strUri);
         
        handler.post(new Runnable(){public void run(){
        	
            textview.setText(text);
            setContentView(textview);
        }});
            }});
        thread.start();*/
    }  
}  
