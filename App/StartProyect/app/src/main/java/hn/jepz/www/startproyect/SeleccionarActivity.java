package hn.jepz.www.startproyect;

import android.os.Bundle;
import android.app.Activity;
import android.widget.NumberPicker;

public class SeleccionarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar);

        NumberPicker npJornadas = (NumberPicker) findViewById(R.id.npJornada);
        final String[] valuesJornadas= {"Todo el dia", "Matutina","Vespertina", "Nocturna"};
        npJornadas.setMinValue(0);
        npJornadas.setMaxValue(valuesJornadas.length-1);
        npJornadas.setDisplayedValues(valuesJornadas);
        npJornadas.setWrapSelectorWheel(true);

        NumberPicker npGrados = (NumberPicker) findViewById(R.id.npGrado);
        final String[] valuesGrados= {"Todos los grados", "Primero","Segundo", "Tercero","Cuarto","Quinto","Sexto","Septimo","Octavo","Noveno"};
        npGrados.setMinValue(0);
        npGrados.setMaxValue(valuesGrados.length-1);
        npGrados.setDisplayedValues(valuesGrados);
        npGrados.setWrapSelectorWheel(true);

        NumberPicker npSecciones = (NumberPicker) findViewById(R.id.npSeccion);
        final String[] valuesSecciones= {"Todas secciones", "A","B", "C","D","E"};
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
