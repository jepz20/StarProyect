package hn.jepz.www.startproyect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class SiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_si);
        LinearLayout llAula = (LinearLayout) findViewById(R.id.llAula);
        LinearLayout llDeporte = (LinearLayout) findViewById(R.id.llDeporte);
        LinearLayout llCultural = (LinearLayout) findViewById(R.id.llCultural);
        LinearLayout llCiencia = (LinearLayout) findViewById(R.id.llCiencia);
        LinearLayout llIntercambio = (LinearLayout) findViewById(R.id.llIntercambio);

        llAula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) findViewById(R.id.cbAula);
                cb.setChecked(!cb.isChecked());
            }
        });
    }

}
