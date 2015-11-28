package codebusters.hackathon.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class NewEventActivity extends Activity {

    private Spinner sidliskoSpinner;
    private Spinner streetSpinner;
    private EditText title;
    private EditText info;


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

        ArrayAdapter sidliskoAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, townDistrict);
        sidliskoSpinner.setAdapter(sidliskoAdapter);
        //sidliskoSpinner.setTe

        streetSpinner.setAdapter(sidliskoAdapter);
    }

    public void onCancelClicked(View view) {
        Intent start = new Intent(this, StartActivity.class);
        startActivity(start);
        finish();
    }

    public void onSendClicked(View view) {

    }

    public void addPhoto(View view) {

    }
}
