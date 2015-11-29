package codebusters.hackathon.hackathon;

import android.os.StrictMode;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Ján on 29/11/2015.
 */
public class Jsonuploader {

    private StringBuilder s;

    @SuppressWarnings("deprecation")
    public void executeMultipartPost(int user_id,String location,int category_id, String title,int image_id, String Description) throws Exception {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        JSONObject json = new JSONObject();
        json.put("user_id",user_id);
        json.put("location",location);
        json.put("category_id",category_id);
        json.put("title",title);
        json.put("image_id",image_id);
        json.put("description",Description);
        try {
        //    BitmapFactory.Options options = new BitmapFactory.Options();

         //   options.inSampleSize = 4;
         ///   options.inPurgeable = true;
           // Bitmap bm = BitmapFactory.decodeFile(source,options);

          //  ByteArrayOutputStream baos = new ByteArrayOutputStream();




            // bitmap object



            //generate base64 string of image



            //send this encoded string to server
            // bm = BitmapFactory.decodeFile(source);
            //  ByteArrayOutputStream bos = new ByteArrayOutputStream();
            //  bm.compress(CompressFormat.JPEG, 75, bos);
            // byte[] data = bos.toByteArray();
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(
                    "http://ples.bossqone.eu/entries/create");
            // ByteArrayBody bab = new ByteArrayBody(data, name);
            //   MultipartEntity reqEntity = new MultipartEntity(
            //     HttpMultipartMode.BROWSER_COMPATIBLE);

            //  System.err.println("IMAGE CODE:" + encodedImage);

            postRequest.setEntity(new StringEntity(json.toString()));
            // HttpResponse resp = httpClient.execute(postRequest);
            // HttpEntity ent = resp.getEntity();
            HttpResponse response = httpClient.execute(postRequest);
            if(response==null){
                System.err.println("RESPONSE IS NULL");
            } else {
                System.err.println("NEW OF JSON RESPONE IS: "+response);
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
        //return Integer.parseInt(s.toString());
    }
}
