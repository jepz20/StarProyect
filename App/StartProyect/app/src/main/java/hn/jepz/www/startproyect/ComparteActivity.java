package hn.jepz.www.startproyect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ComparteActivity extends AppCompatActivity {
    public static final String PREFERENCIAS_STAR = "PrefStarProyect";
    public static final String PREF_ULTIMO_REPORTE = "PrefUltimoReporte";
    public static final String PREF_AULA = "PrefAula";
    public static final String PREF_DEPORTES = "PrefDeportes";
    public static final String PREF_CULTURAL = "PrefCultural";
    public static final String PREF_CIENCIA = "PrefCiencia";
    public static final String PREF_INTERCAMBIO = "PrefIntercambio";
    boolean imagen1 = false;
    boolean imagen2 = false;
    boolean imagen3 = false;
    boolean imagen4 = false;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final String IMAGE_DIRECTORY_NAME = "star";
    public static final int MEDIA_TYPE_IMAGE = 1;
    private Uri fileUri;
    boolean previewCamara = false;
    private SharedPreferences misPreferencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_comparte);
        Button btnCamara = (Button) findViewById(R.id.btnCamara);
        ((ImageView) findViewById(R.id.previewFoto1)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.previewFoto2)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.previewFoto3)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.previewFoto4)).setVisibility(View.GONE);
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        previewCamara = true;
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                previewCamara = true;
                startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
            }
        });
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {


        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        Log.v("ruta",mediaStorageDir.getPath() + File.separator);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");
        return mediaFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    ImageView imageView;
                    if (!imagen1) {
                        imageView = (ImageView) findViewById(R.id.previewFoto1);
                        imageView.setVisibility(View.VISIBLE);
                        imagen1 = true;
                    }
                    if (!imagen2) {
                        imageView = (ImageView) findViewById(R.id.previewFoto2);
                        imageView.setVisibility(View.VISIBLE);
                        imagen2 = true;
                    }
                    if (!imagen3) {
                        imageView = (ImageView) findViewById(R.id.previewFoto3);
                        imageView.setVisibility(View.VISIBLE);
                        imagen3 = true;
                    }
                    if (!imagen4) {
                        imageView = (ImageView) findViewById(R.id.previewFoto4);
                        imageView.setVisibility(View.VISIBLE);
                        imagen4 = true;
                    } else {
                        imageView = (ImageView) findViewById(R.id.previewFoto1);
                        imageView.setVisibility(View.VISIBLE);
                        imagen1 = true;
                    }
                    imageView.setBackgroundResource(0);
                    imageView.setImageResource(android.R.color.transparent);
                    imageView.setVisibility(View.VISIBLE);

                    Bitmap bitmap;
                    Bitmap scaled = null;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 8;
                    bitmap = BitmapFactory.decodeFile(fileUri.getPath());
                    scaled = Bitmap.createScaledBitmap(bitmap, imageView.getWidth(), imageView.getHeight(), true);
                    imageView.setImageBitmap(scaled);
                    previewCamara = false;

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
