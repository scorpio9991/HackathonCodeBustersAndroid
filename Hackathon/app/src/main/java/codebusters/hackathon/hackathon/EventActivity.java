package codebusters.hackathon.hackathon;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EventActivity extends Activity {

    private TextView title;
    private TextView location;
    private TextView sidlisko;
    private TextView street;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        String title;
        String location;
        String sidlisko = null;
        String street = null;

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            title = null;
            location = null;
        } else {
            title = extras.getString("title");
            location = extras.getString("location");
            String[] string = location.split(", ");
            sidlisko = string[0];
            street = string[1];
        }

        this.title = (TextView) findViewById(R.id.titleText);
        this.location = (TextView) findViewById(R.id.infoText);
        this.street = (TextView) findViewById(R.id.streetText);
        this.sidlisko = (TextView) findViewById(R.id.sidliskoText);
        this.title.setText(title);
        this.location.setText(location);
        this.street.setText(street);
        this.sidlisko.setText(sidlisko);
    }
}
