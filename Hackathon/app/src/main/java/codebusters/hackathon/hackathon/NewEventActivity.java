package codebusters.hackathon.hackathon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class NewEventActivity extends Activity {

    private static final int SELECT_PICTURE_REQUEST_CODE = 0;
    private Spinner sidliskoSpinner;
    private Spinner streetSpinner;
    private EditText title;
    private EditText info;

    private Uri outputFileUri;
    private String[] streets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        sidliskoSpinner = (Spinner) findViewById(R.id.sidliskoSpinner);
        streetSpinner = (Spinner) findViewById(R.id.streetSpinner);
        title = (EditText) findViewById(R.id.edit_title_problem);
        info = (EditText) findViewById(R.id.infoProblem);

        String[] townDistrict = {"Kavecany", "Tahanovce", "Sever", "Stare mesto", "Lorincik", "Peres",
                "Myslava", "Zapad", "Dargovskych Hrdinov", "Kosicka Nova Ves", "Barca", "Sebastovce",
                "Krasna", "Nad Jazerom", "Juh", "Saca", "Polov", "Sidlisko Tahanovce", "KVP", "Dzungla",
                "Vysne Opatske", "Lunik IX"};

        String[] streets = getStreets();

        ArrayAdapter sidliskoAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, townDistrict);
        sidliskoSpinner.setAdapter(sidliskoAdapter);
        //sidliskoSpinner.setTe

        ArrayAdapter streetsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, streets);
        streetSpinner.setAdapter(streetsAdapter);
    }

    public void onCancelClicked(View view) {
        Intent start = new Intent(this, StartActivity.class);
        startActivity(start);
        finish();
    }

    public void onSendClicked(View view) {

    }

    public void addPhoto(View view) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    //viewImage.setImageBitmap(bitmap);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                        Log.e("path of image from gallery......******************.........", file.getPath() + "");
                        Toast.makeText(this, "PATH CAM: " + file.getPath(), Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.e("path*****", picturePath + "");
                Toast.makeText(this, "PATH GAL: " + picturePath, Toast.LENGTH_LONG).show();

                //viewImage.setImageBitmap(thumbnail);
            }
        }
    }

    public String[] getStreets() {
        String name = sidliskoSpinner.getSelectedItem().toString();
        String[] streets = null;
        Log.e("NAME: ", name);
        String path = name + ".txt";
        try {
            InputStream in = getApplication().getAssets().open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "windows-1250"));
            String line = reader.readLine();
            streets = line.split(", ");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return streets;
    }
}
