package codebusters.hackathon.hackathon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class StartActivity extends Activity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        list = (ListView) findViewById(R.id.list);
    }

    public void addClicked(View view) {
        Intent event = new Intent(this, LoginActivity.class);
        startActivity(event);
        finish();
    }

    public void possitiveFilter(View view) {

    }

    public void negativeFilter(View view) {

    }
}
