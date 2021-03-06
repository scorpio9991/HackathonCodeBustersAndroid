package codebusters.hackathon.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends Activity {

    private ListView list;
    private String pathS;
    private ArrayList<String> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        list = (ListView) findViewById(R.id.list);

        lists = new ArrayList<>();
        String[] events = {"Spadnutý strom na ceste, Sever, Atletická 15, null",
                "Autonehoda, Západ, Bernolákova 28"
        };
        for (String string : events) {
            lists.add(string);
        }

        ArrayAdapter adapter = new EventAdapter(getApplicationContext(), lists);
        list.setAdapter(adapter);


    }

    public void addClicked(View view) {
       // Intent event = new Intent(this, LoginActivity.class);
       // startActivity(event);
        //finish();

        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, 0);
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent
            imageReturnedIntent){
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    if (cursor.moveToFirst()) {
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        pathS = cursor.getString(columnIndex);
                        Log.e("PATHS1:", pathS);
                    }
                    cursor.close();
                } else {
                    Toast.makeText(this, "You haven't picked Image",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
        FileUpload upload = new FileUpload();
        int image_id = 0;
        try {
            image_id = upload.executeMultipartPost(pathS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Jsonuploader test = new Jsonuploader();

        try {
            test.executeMultipartPost(1,"Kosice, Jedlikova",0,"Vsetko je zadrbane",image_id,"Vsade je smrad a bordel");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void possitiveFilter(View view) {

    }

    public void negativeFilter(View view) {

    }
}
