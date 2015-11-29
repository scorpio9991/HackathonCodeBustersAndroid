package codebusters.hackathon.hackathon;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;

public class FileUpload extends Activity {
    Bitmap bm;
    private byte[] byteImage_photo;
    private StringBuilder s;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_start);
       // try {
            // bm = BitmapFactory.decodeResource(getResources(),
            // R.drawable.forest);
        //    bm = BitmapFactory.decodeFile("/sdcard/DCIM/forest.png");
        //    executeMultipartPost();
      //  } catch (Exception e) {
      //      Log.e(e.getClass().getName(), e.getMessage());
      //  }
    }

    @SuppressWarnings("deprecation")
    public int executeMultipartPost(String source) throws Exception {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();

            options.inSampleSize = 4;
            options.inPurgeable = true;
            System.out.println("SOURCE IN FILEUPLOAD"+source);
            Bitmap bm = BitmapFactory.decodeFile(source,options);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            bm.compress(Bitmap.CompressFormat.JPEG,50,baos);


            // bitmap object

            byteImage_photo = baos.toByteArray();

            //generate base64 string of image

            String encodedImage = Base64.encodeToString(byteImage_photo, Base64.DEFAULT);

            //send this encoded string to server
           // bm = BitmapFactory.decodeFile(source);
          //  ByteArrayOutputStream bos = new ByteArrayOutputStream();
          //  bm.compress(CompressFormat.JPEG, 75, bos);
           // byte[] data = bos.toByteArray();
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(
                    "http://ples.bossqone.eu/images/upload");
           // ByteArrayBody bab = new ByteArrayBody(data, name);
         //   MultipartEntity reqEntity = new MultipartEntity(
               //     HttpMultipartMode.BROWSER_COMPATIBLE);

          //  System.err.println("IMAGE CODE:" + encodedImage);

            postRequest.setEntity(new StringEntity(encodedImage));

           // HttpResponse resp = httpClient.execute(postRequest);
           // HttpEntity ent = resp.getEntity();
            HttpResponse response = httpClient.execute(postRequest);
            if(response==null){
                System.err.println("RESPONSE IS NULL");
            } else {
                System.err.println("RESPONE IS: "+response);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String sResponse;
             s = new StringBuilder();
          //  System.out.println(reader);
           while ((sResponse = reader.readLine()) != null) {
               s = s.append(sResponse);

           }
            System.err.println("Response: " + s);
       } catch (Exception e) {
            System.err.println("I CATCHED SOMETHING: "+e);
   //         // handle exception here
    //        Log.e(e.getClass().getName(), e.getMessage());
    //        bm.recycle();
        }
        return Integer.parseInt(s.toString());
    }
}
