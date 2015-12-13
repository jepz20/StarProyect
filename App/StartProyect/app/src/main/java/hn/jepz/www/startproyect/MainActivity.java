
package hn.jepz.www.startproyect;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.facebook.FacebookSdk;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    public static final String PREFERENCIAS_STAR = "PrefStarProyect";
    public static final String PREF_ULTIMO_REPORTE = "PrefUltimoReporte";
    public static final String PREF_AULA = "PrefAula";
    public static final String PREF_DEPORTES = "PrefDeportes";
    public static final String PREF_CULTURAL = "PrefCultural";
    public static final String PREF_CIENCIA = "PrefCiencia";
    public static final String PREF_INTERCAMBIO = "PrefIntercambio";
    public static final String PRIMERA_VEZ_REGISTRO = "PrefPRimeraVez";
    private SharedPreferences misPreferencias;
    public static final int ACTIVIDAD_PRESENCIALES = 0;
    public static final int ACTIVIDAD_DEPORTES = 1;
    public static final int ACTIVIDAD_CULTURALES = 2;
    public static final int ACTIVIDAD_CIENCIAS = 3;
    public static final int ACTIVIDAD_INTERCAMBIOS = 4;
    public static final int ACTIVIDAD_CERRADO = 5;
    public static final int ACTIVIDAD_HUELGA = 6;
    public static final int ACTIVIDAD_NO_PROFESOR = 7;
    public static final int ACTIVIDAD_CATASTROFE = 8;
    public static final int ACTIVIDAD_OTRO = 9;
    MatrixCursor matrixcursor;
    FeedPrincipalAdapter mFeedPrincipalAdapter ;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        misPreferencias = getSharedPreferences(PREFERENCIAS_STAR, Context.MODE_PRIVATE);
        boolean primeraVez = misPreferencias.getBoolean(PRIMERA_VEZ_REGISTRO,true);
        if (primeraVez) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            intent = new Intent(this, LoginActivity.class);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    startActivity(intent);
                    Intent intent = new Intent(getBaseContext(), SeleccionarActivity.class);
                    startActivity(intent);
                }
            });
            Button btnSiActividad = (Button) findViewById(R.id.btnSiActividad);
            Button btnNoActividad = (Button) findViewById(R.id.btnNoActividad);
            btnSiActividad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), SiActivity.class);
                    startActivity(intent);
                }
            });
            btnNoActividad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), NoActivity.class);
                    startActivity(intent);
                }
            });
            /**
             Si va te pasa al activity de Si hay clases
             No te pasa al activity de No hay Clases
             **/

            String ultimoReporte =misPreferencias.getString(PREF_ULTIMO_REPORTE,"0");
            Calendar hoy = Calendar.getInstance();
            String strHoy = String.format("%d", hoy.get(Calendar.DAY_OF_YEAR)); //TODO Halar fecha de hoy en formato dd/mm/yyyy
            LinearLayout llPreguntaActividades;
            llPreguntaActividades = (LinearLayout) findViewById(R.id.llPreguntaActividades);
            if (!ultimoReporte.equals(strHoy)) {
                llPreguntaActividades.setVisibility(View.VISIBLE);
            } else {
                llPreguntaActividades.setVisibility(View.VISIBLE);
            }
            //TODO tener un feed inicial de mentiritas con Fotos y comentarios de la gente si le da
            //si le da click a una foto que llame un lightbox
            String[] columnas = new String[] {
                    "_id", "Escuela","Departamento","Municipio", "Fecha",
                    "HuboActividades", "Actividades", "Grado", "Seccion",
                    "Jornada", "Comentario","Nombre",
                    "Foto1", "Foto2", "Foto3", "Foto4"
            };

            matrixcursor = new MatrixCursor(columnas);
            matrixcursor.addRow(new Object[]{
                    1, "15 De Septiembre", "Francisco Morazan"
                    ,"Nueva Armenia" ,"20151212", "true", "{1,2}"
                    ,"4", "U", "Matutina",
                    "Torneo de Futbolito con mi hija Griseldita, y mi hijo Jose en la feria de ciencias"
                    ,"Griselda Zelaya" //Vespertina , Nocturna
                    ,"1","2","",""});
            startManagingCursor(matrixcursor);
            stopManagingCursor(matrixcursor);

        }

        //mFeedPrincipalAdapter = new FeedPrincipalAdapter(this,matrixcursor,0);
        //listView.setAdapter(mFeedPrincipalAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        recreate();
    }
}

