package hn.jepz.www.startproyect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_si);
    }

}
