package codebusters.hackathon.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void cancel(View view) {
        Intent start = new Intent(this, StartActivity.class);
        startActivity(start);
        finish();
    }

    public void login(View view) {
        //todo: poriesit podmienku ci su udaje spravne vyplnene
        Intent event = new Intent(this, NewEventActivity.class);
        startActivity(event);
        finish();
    }

    public void registration(View view) {
        Toast.makeText(this, "Na ficure sa pracuje :) daujee za pouzivanie tejto aplikacie...", Toast.LENGTH_SHORT).show();
    }
}
