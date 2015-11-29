package codebusters.hackathon.hackathon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by McDominick on 29.11.2015.
 */
public class EventAdapter extends ArrayAdapter {

    private ArrayList<String> data;
    private Context context;

    public EventAdapter(Context context, ArrayList<String> data) {
        super(context, R.layout.one_row, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.one_row, parent, false);

        final TextView title = (TextView) rowView.findViewById(R.id.titleProblem);
        final TextView location = (TextView) rowView.findViewById(R.id.location);

        final LinearLayout linlay = (LinearLayout) rowView.findViewById(R.id.linLay);

        title.setText(getInfo(this.data.get(position))[0]);
        location.setText(getInfo(this.data.get(position))[1] + ", " + getInfo(this.data.get(position))[2]);

        linlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent event = new Intent(getContext(), EventActivity.class);
                event.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                String titles = (String) title.getText();
                String locations = (String) location.getText();
                event.putExtra("title", titles);
                event.putExtra("location", locations);
                getContext().startActivity(event);
            }
        });

        if (!getInfo(this.data.get(position))[2].equals("null")) {

        }
        return rowView;
    }

    private String[] getInfo(String info) {
        return info.split(",");
    }
}
