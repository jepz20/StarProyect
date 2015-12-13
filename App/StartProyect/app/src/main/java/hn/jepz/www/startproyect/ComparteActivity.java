package hn.jepz.www.startproyect;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

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
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    Bitmap bitmap1,bitmap2,bitmap3,bitmap4;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final String IMAGE_DIRECTORY_NAME = "star";
    public static final int MEDIA_TYPE_IMAGE = 1;
    private Uri fileUri;
    Activity thisActivity;
    boolean previewCamara = false;
    private SharedPreferences misPreferencias;
    private LinearLayout llContenedorImagenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_comparte);
//        FacebookSdk.sdkInitialize(getApplicationContext());
        Button btnCamara = (Button) findViewById(R.id.btnCamara);
        llContenedorImagenes = (LinearLayout) findViewById(R.id.llContenedorImagenes);
        llContenedorImagenes.setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.previewFoto1)).setVisibility(View.INVISIBLE);
        ((ImageView) findViewById(R.id.previewFoto2)).setVisibility(View.INVISIBLE);
        ((ImageView) findViewById(R.id.previewFoto3)).setVisibility(View.INVISIBLE);
        ((ImageView) findViewById(R.id.previewFoto4)).setVisibility(View.INVISIBLE);
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
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), EstrellasActivity.class));
            }
        });

        Button btnFacebook = (Button) findViewById(R.id.btnCompartirFb);
        thisActivity = this;
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                startActivity(new Intent(thisActivity,EstrellasActivity.class));
            }

            @Override
            public void onCancel() {
                startActivity(new Intent(thisActivity,EstrellasActivity.class));
            }

            @Override
            public void onError(FacebookException error) {
                startActivity(new Intent(thisActivity,EstrellasActivity.class));
            }
        });
        String descripcion = ((EditText) findViewById(R.id.etComentario)).getText().toString();
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .setContentTitle("Star Project")
                .setContentDescription(descripcion)
//                        .setImageUrl(fileUri)
                .build();
        ShareButton shareButton = (ShareButton)findViewById(R.id.btnCompartirFb);
        shareButton.setShareContent(content);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descripcion = ((EditText) findViewById(R.id.etComentario)).getText().toString();
//                ShareLinkContent content = new ShareLinkContent.Builder()
//                        .setContentTitle("Start Project")
//                        .setContentDescription(descripcion)
//                        .setImageUrl(fileUri)
//                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
//                        .build();
                boolean almenos1 = false;
//                SharePhoto photo1 = new SharePhoto.Builder()
//                        .setBitmap(bitmap1)
//                        .build();;
//                SharePhoto photo2 = new SharePhoto.Builder()
//                        .setBitmap(bitmap1)
//                        .build();;
//                SharePhoto photo3 = new SharePhoto.Builder()
//                        .setBitmap(bitmap1)
//                        .build();
//                SharePhoto photo4 = new SharePhoto.Builder()
//                        .setBitmap(bitmap1)
//                        .build();
//                SharePhotoContent content;
//                content = new SharePhotoContent.Builder().build();
                almenos1 = true;
//                SharePhoto photo1 = new SharePhoto.Builder()
//                        .setBitmap(bitmap1)
//                        .build();
//                SharePhotoContent content = new SharePhotoContent.Builder()
//                        .addPhoto(photo1)
//                        .build();
                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
                        .setContentTitle("Star Project")
                        .setContentDescription(descripcion)
//                        .setImageUrl(fileUri)
                        .build();
//                if (imagen1) {
//                }
//                if (imagen2) {
//                    photo2 = new SharePhoto.Builder()
//                            .setBitmap(bitmap2)
//                            .build();
//                    almenos1 = true;
//                    content = new SharePhotoContent.Builder()
//                            .addPhoto(photo1)
//                            .addPhoto(photo2)
//                            .build();
//                }
//                if (imagen3) {
//                    photo3 = new SharePhoto.Builder()
//                            .setBitmap(bitmap3)
//                            .build();
//                    content = new SharePhotoContent.Builder()
//                            .addPhoto(photo1)
//                            .addPhoto(photo2)
//                            .addPhoto(photo3)
//                            .build();
//                    almenos1 = true;
//                }
//                if (imagen4) {
//                    photo4 = new SharePhoto.Builder()
//                            .setBitmap(bitmap4)
//                            .build();
//                    almenos1 = true;
//                    content = new SharePhotoContent.Builder()
//                            .addPhoto(photo1)
//                            .addPhoto(photo2)
//                            .addPhoto(photo3)
//                            .addPhoto(photo4)
//                            .build();
//                }
                if (almenos1) {
                    shareDialog.show(thisActivity, content);
                } else {
                    Snackbar.make(v, "Porfavor agrega al menos una foto", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
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
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bitmap bitmap;
                Bitmap scaled = null;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                bitmap = BitmapFactory.decodeFile(fileUri.getPath());
                scaled = Bitmap.createScaledBitmap(bitmap, 80, 95, true);
                try {
                    ImageView imageView;
                    if (!imagen1) {
                        llContenedorImagenes.setVisibility(View.VISIBLE);
                        imageView = (ImageView) findViewById(R.id.previewFoto1);
                        imageView.setVisibility(View.VISIBLE);
                        imagen1 = true;
                        bitmap1 = bitmap;
                    } else  if (!imagen2) {
                        imageView = (ImageView) findViewById(R.id.previewFoto2);
                        imageView.setVisibility(View.VISIBLE);
                        imagen2 = true;
                        bitmap2 = bitmap;
                    } else if (!imagen3) {
                        imageView = (ImageView) findViewById(R.id.previewFoto3);
                        imageView.setVisibility(View.VISIBLE);
                        imagen3 = true;
                        bitmap3 = bitmap;
                    } else if (!imagen4) {
                        imageView = (ImageView) findViewById(R.id.previewFoto4);
                        imageView.setVisibility(View.VISIBLE);
                        imagen4 = true;
                        bitmap4 = bitmap;
                    } else {
                        imageView = (ImageView) findViewById(R.id.previewFoto1);
                        imageView.setVisibility(View.VISIBLE);
                        imagen1 = true;
                    }
                    imageView.setBackgroundResource(0);
                    imageView.setImageResource(android.R.color.transparent);
                    imageView.setVisibility(View.VISIBLE);

                    imageView.setImageBitmap(scaled);
                    previewCamara = false;

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
