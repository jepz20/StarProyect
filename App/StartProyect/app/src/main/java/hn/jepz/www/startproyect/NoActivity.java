package hn.jepz.www.startproyect;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.Calendar;

public class NoActivity extends AppCompatActivity {
    public static final String PREFERENCIAS_STAR = "PrefStarProyect";
    public static final String PREF_ULTIMO_REPORTE = "PrefUltimoReporte";
    public static final String PREF_AULA = "PrefAula";
    public static final String PREF_DEPORTES = "PrefDeportes";
    public static final String PREF_CULTURAL = "PrefCultural";
    public static final String PREF_CIENCIA = "PrefCiencia";
    public static final String PREF_INTERCAMBIO = "PrefIntercambio";
    private SharedPreferences misPreferencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_no);
        misPreferencias = getSharedPreferences(PREFERENCIAS_STAR, Context.MODE_PRIVATE);
        Calendar hoy = Calendar.getInstance();
        String strHoy = String.format("%d", hoy.get(Calendar.DAY_OF_YEAR)); //TODO Halar fecha de hoy en formato dd/mm/yyyy
        SharedPreferences.Editor editor = misPreferencias.edit();
        editor.putString(PREF_ULTIMO_REPORTE, strHoy);
        editor.commit();
        LinearLayout llCerrado = (LinearLayout) findViewById(R.id.llCerrada);
        LinearLayout llHuelga = (LinearLayout) findViewById(R.id.llHuelga);
        LinearLayout llNoLLego = (LinearLayout) findViewById(R.id.llNoLLego);
        LinearLayout llCatastrofe = (LinearLayout) findViewById(R.id.llCatastrofe);
        LinearLayout llOtro = (LinearLayout) findViewById(R.id.llOtro);

        llCerrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) findViewById(R.id.cbCerrada);
                cb.setChecked(!cb.isChecked());
            }
        });
        llHuelga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) findViewById(R.id.cbHuelga);
                cb.setChecked(!cb.isChecked());
            }
        });
        llNoLLego.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 CheckBox cb = (CheckBox) findViewById(R.id.cbNoLLego);
                 cb.setChecked(!cb.isChecked());
             }
         });
        llCatastrofe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) findViewById(R.id.cbCatastrofe);
                cb.setChecked(!cb.isChecked());
            }
        });
        llOtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) findViewById(R.id.cbOtro);
                cb.setChecked(!cb.isChecked());
            }
        });

        Button btnFinalizar = (Button) findViewById(R.id.btnFinalizar);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean seleccionoAlguno = false;
                SharedPreferences.Editor editor = misPreferencias.edit();
                if (((CheckBox) findViewById(R.id.cbCerrada)).isChecked()) {
//                    int dias = misPreferencias.getInt(PREF_AULA,0);
//                    editor.putInt(PREF_AULA, dias + 1);
                    seleccionoAlguno = true;
                }
                if (((CheckBox) findViewById(R.id.cbHuelga)).isChecked()) {
//                    editor.putBoolean(PREF_DEPORTES,true);
                    seleccionoAlguno = true;
                }
                if (((CheckBox) findViewById(R.id.cbNoLLego)).isChecked()) {
//                    editor.putBoolean(PREF_CULTURAL,true);
                    seleccionoAlguno = true;
                }
                if (((CheckBox) findViewById(R.id.cbCatastrofe)).isChecked()) {
//                    editor.putBoolean(PREF_CIENCIA,true);
                    seleccionoAlguno = true;
                }
                if (((CheckBox) findViewById(R.id.cbOtro)).isChecked()) {
//                    editor.putBoolean(PREF_INTERCAMBIO,true);
                    seleccionoAlguno = true;
                }
//                editor.commit();
                if (seleccionoAlguno) {
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                } else {
                    Snackbar.make(v, "Porfavor seleccione al menos una razon", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });
    }
}
