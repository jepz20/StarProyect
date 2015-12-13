package hn.jepz.www.startproyect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarActivity extends Activity {
    public static final String PRIMERA_VEZ_REGISTRO = "PrefPRimeraVez";
    private SharedPreferences misPreferencias;
    public static final String PREFERENCIAS_STAR = "PrefStarProyect";
    private SharedPreferences prefs;
    private String prefName = "spinner_value";
    int id=0;

    private AutoCompleteTextView actv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar);
        misPreferencias = getSharedPreferences(PREFERENCIAS_STAR, Context.MODE_PRIVATE);
        final List<String> listCentrosEducativos=new ArrayList<String>();
        listCentrosEducativos.add("Nueva Esperanza");
        listCentrosEducativos.add("San Angel");
        listCentrosEducativos.add("Rincon de Amor");
        listCentrosEducativos.add("Ramon Rosa");
        listCentrosEducativos.add("Jesus Es Mi Amigo");

        Button btnContinuar = (Button) findViewById(R.id.btnContinuar);

        btnContinuar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (misPreferencias.getBoolean(PRIMERA_VEZ_REGISTRO,true)){
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    SharedPreferences.Editor editor = misPreferencias.edit();
                    editor.putBoolean(PRIMERA_VEZ_REGISTRO,false);
                    editor.commit();
                } else {
                    Intent intent = new Intent(getBaseContext(), SiActivity.class);
                    startActivity(intent);
                }

            }
        });

        final Spinner spCentrosEducativos=(Spinner) findViewById(R.id.spinnerCentrosEducativos);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listCentrosEducativos);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCentrosEducativos.setAdapter(adp);

        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        id = prefs.getInt("last_val", 0);
        spCentrosEducativos.setSelection(id);

        actv = (AutoCompleteTextView) findViewById(R.id.txtUbicacion);
        String[] ubicaciones = getResources().getStringArray(R.array.ubicaciones_array);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ubicaciones);
        actv.setAdapter(adapter);

        spCentrosEducativos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0,
                                       View arg1, int pos, long arg3) {

                prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                //---save the values in the EditText view to preferences---
                editor.putInt("last_val", pos);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        NumberPicker npJornadas = (NumberPicker) findViewById(R.id.npJornada);
        final String[] valuesJornadas= {"Todas", "Matutina","Vespertina", "Nocturna"};
        npJornadas.setMinValue(0);
        npJornadas.setMaxValue(valuesJornadas.length-1);
        npJornadas.setDisplayedValues(valuesJornadas);
        npJornadas.setWrapSelectorWheel(true);

        NumberPicker npGrados = (NumberPicker) findViewById(R.id.npGrado);
        final String[] valuesGrados= {"Todos", "Primero","Segundo", "Tercero","Cuarto","Quinto","Sexto","Septimo","Octavo","Noveno"};
        npGrados.setMinValue(0);
        npGrados.setMaxValue(valuesGrados.length-1);
        npGrados.setDisplayedValues(valuesGrados);
        npGrados.setWrapSelectorWheel(true);

        NumberPicker npSecciones = (NumberPicker) findViewById(R.id.npSeccion);
        final String[] valuesSecciones= {"Todas", "A","B", "C","D","E"};
        npSecciones.setMinValue(0);
        npSecciones.setMaxValue(valuesSecciones.length-1);
        npSecciones.setDisplayedValues(valuesSecciones);
        npSecciones.setWrapSelectorWheel(true);

        /* Listener para el StringPicker
        npJornadas.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                txt.setText("Selected value : " + valuesJornadas[newVal]);
            }
        });
        */

    }

}
