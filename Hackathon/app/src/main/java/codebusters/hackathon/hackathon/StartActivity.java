package codebusters.hackathon.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void addClicked(View view) {
        Intent event = new Intent(this, EventActivity.class);
        startActivity(event);
        finish();
    }
}
