package hn.jepz.www.startproyect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EstrellasActivity extends Activity {
    public static final String PREFERENCIAS_STAR = "PrefStarProyect";
    public static final String PREF_ULTIMO_REPORTE = "PrefUltimoReporte";
    public static final String PREF_AULA = "PrefAula";
    public static final String PREF_DEPORTES = "PrefDeportes";
    public static final String PREF_CULTURAL = "PrefCultural";
    public static final String PREF_CIENCIA = "PrefCiencia";
    public static final String PREF_INTERCAMBIO = "PrefIntercambio";
    public static final String PRIMERA_VEZ_REGISTRO = "PrefPRimeraVez";
    private SharedPreferences misPreferencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estrellas);
        misPreferencias = getSharedPreferences(PREFERENCIAS_STAR, Context.MODE_PRIVATE);
        int estrellasSi = 0;
        String mensaje ="";
        if (misPreferencias.getInt(PREF_AULA,0) >=200) {
            estrellasSi++;
        }
        if (misPreferencias.getBoolean(PREF_DEPORTES, false)) {
            estrellasSi++;
        }
        if (misPreferencias.getBoolean(PREF_CULTURAL, false)) {
            estrellasSi++;
        }
        if (misPreferencias.getBoolean(PREF_CIENCIA, false)) {
            estrellasSi++;
        }
        if (misPreferencias.getBoolean(PREF_INTERCAMBIO, false)) {
            estrellasSi++;
        }

        //Primera Estrella
        ImageView ivEstella1 = (ImageView) findViewById(R.id.ivEstrella1);
        ivEstella1.setBackgroundResource(R.drawable.estrella_contorno);
        ImageView ivEstella2 = (ImageView) findViewById(R.id.ivEstrella2);
        ivEstella2.setBackgroundResource(R.drawable.estrella_contorno);
        ImageView ivEstella3 = (ImageView) findViewById(R.id.ivEstrella3);
        ivEstella3.setBackgroundResource(R.drawable.estrella_contorno);
        ImageView ivEstella4 = (ImageView) findViewById(R.id.ivEstrella4);
        ivEstella4.setBackgroundResource(R.drawable.estrella_contorno);
        ImageView ivEstella5 = (ImageView) findViewById(R.id.ivEstrella5);
        ivEstella5.setBackgroundResource(R.drawable.estrella_contorno);
        //Copa
        ImageView ivCopa = (ImageView) findViewById(R.id.ivCopa);
        if (estrellasSi >=1) {
            ivEstella1.setBackgroundResource(R.drawable.estrella_dorada);
            ivCopa.setVisibility(View.GONE);
            mensaje = "Buen Inicio, siga reportando las actividades";
        }
        if (estrellasSi >=2) {
            ivEstella2.setBackgroundResource(R.drawable.estrella_dorada);
            ivCopa.setVisibility(View.GONE);
            mensaje = "Ya esta en el segundo paso para ser una escuela 5 Estellas";
        }
        if (estrellasSi >=3) {
            ivEstella3.setBackgroundResource(R.drawable.estrella_dorada);
            ivCopa.setVisibility(View.VISIBLE);
            ivCopa.setBackgroundResource(R.drawable.copa_bronce);
            mensaje = "Ha llegado a medio camino, Animos";
        }
        if (estrellasSi >=4) {
            ivEstella4.setBackgroundResource(R.drawable.estrella_dorada);
            ivCopa.setVisibility(View.VISIBLE);
            ivCopa.setBackgroundResource(R.drawable.copa_plata);
            mensaje = "Ya casi llega a la meta, Adelante";
        }
        if (estrellasSi >=5) {
            ivEstella5.setBackgroundResource(R.drawable.estrella_dorada);
            ivCopa.setVisibility(View.VISIBLE);
            ivCopa.setBackgroundResource(R.drawable.copa);
            mensaje = "Ya es una escuela 5 estrellas";
        }
        TextView tvTitulo = (TextView) findViewById(R.id.tituloEstrella);
        tvTitulo.setText(mensaje);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startActivity(new Intent(this,MainActivity.class));
    }
}
