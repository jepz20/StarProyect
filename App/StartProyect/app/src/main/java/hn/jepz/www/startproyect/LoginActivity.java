package hn.jepz.www.startproyect;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    // UI references.
    private View mProgressView;
    private View mLoginFormView;

    public static final String PREFERENCIAS_STAR = "PrefStarProyect";
    public static final String PREF_ULTIMO_REPORTE = "PrefUltimoReporte";
    public static final String PREF_AULA = "PrefAula";
    public static final String PREF_DEPORTES = "PrefDeportes";
    public static final String PREF_CULTURAL = "PrefCultural";
    public static final String PREF_CIENCIA = "PrefCiencia";
    public static final String PREF_INTERCAMBIO = "PrefIntercambio";
    public static final String PRIMERA_VEZ_REGISTRO = "PrefPRimeraVez";
    public static final String TELEFONO = "PrefTelefono";
    public static final String NOMBRE = "PrefNombre";
    public static final String IDENTIDAD = "PrefIdentidad";
    public static final String CORREO = "PrefCorreo";
    public static final String CATEGORIA = "PrefCategoria";

    private SharedPreferences misPreferencias;

    TextView number;
    TextView correo;
    TextView nombre;
    TextView identidad;
    RadioButton rbtProfesor;
    RadioButton rbtEstudiante;
    RadioButton rbtPadreFamilia;
    private String rbtSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        nombre = (TextView) findViewById(R.id.nombre);
        identidad = (TextView) findViewById(R.id.identidad);
        rbtProfesor = (RadioButton) findViewById(R.id.rbtProfesor);
        rbtEstudiante = (RadioButton) findViewById(R.id.rbtEstudiante);
        rbtPadreFamilia = (RadioButton) findViewById(R.id.rbtPadreFamilia);
        misPreferencias = getSharedPreferences(PREFERENCIAS_STAR, Context.MODE_PRIVATE);

        number = (TextView) findViewById(R.id.telefono);
        TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //number.setText(tManager.getLine1Number());
        number.setText("89246110");

        correo = (TextView) findViewById(R.id.email);
        String email = getEmail(this);
        if(email!=null){
            correo.setText(email);
        } else {
            correo.setText(null);
        }

        Button btnSeleccionarCentro = (Button) findViewById(R.id.btnRegistrar);

        btnSeleccionarCentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = misPreferencias.edit();
                editor.putString(TELEFONO,number.getText().toString());
                editor.putString(NOMBRE,nombre.getText().toString());
                editor.putString(IDENTIDAD,identidad.getText().toString());
                editor.putString(CORREO,correo.getText().toString());

                if(rbtEstudiante.isChecked())
                    rbtSeleccionado = rbtEstudiante.getText().toString();
                else if (rbtProfesor.isChecked())
                    rbtSeleccionado = rbtProfesor.getText().toString();
                else if (rbtPadreFamilia.isChecked())
                    rbtSeleccionado = rbtPadreFamilia.getText().toString();

                editor.putString(CATEGORIA,rbtSeleccionado);
                editor.commit();
                showProgress(true);
                Intent intent = new Intent(getBaseContext(), SeleccionarActivity.class);
                startActivity(intent);
            }
        });

    }

    static String getEmail(Context context){
        AccountManager accManager = AccountManager.get(context);
        Account account = getAccount(accManager);

        if(account == null){
            return null;
        } else {
            return account.name;
        }
    }

    private static Account getAccount(AccountManager accManager){
        Account[] accounts = accManager.getAccountsByType("com.google");
        Account account;
        if(accounts.length > 0){
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}