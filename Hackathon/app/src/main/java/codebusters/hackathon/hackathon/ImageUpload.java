package codebusters.hackathon.hackathon;

import android.os.AsyncTask;

/**
 * Created by Ján on 29/11/2015.
 */
public class ImageUpload extends AsyncTask<String, Void, Integer> {
    @Override
    protected Integer doInBackground(String... params) {
        FileUpload parser = new FileUpload();

        try {
            return parser.executeMultipartPost(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
