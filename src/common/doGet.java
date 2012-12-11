package common;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class doGet {
	
	public static String doget( String url )
	 {
	     try
	     {
	         HttpGet method = new HttpGet( url );

	         DefaultHttpClient client = new DefaultHttpClient();

	         // ÉwÉbÉ_Çê›íËÇ∑ÇÈ
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

}
