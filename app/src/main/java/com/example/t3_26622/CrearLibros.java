package com.example.t3_26622;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.t3_26622.Entities.Libro;
import com.example.t3_26622.Factories.RetrofitFactory;
import com.example.t3_26622.Services.LibroServices;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CrearLibros extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1000;
    static final int REQUEST_PICK_IMAGE = 1001;

    static final int REQUEST_CAMERA_PERMISSION = 100;

    String imagen;
    ImageView fotoContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_libros);

        Button btnTomarFoto = findViewById(R.id.btnTomarFoto);
        Button btnGalleria = findViewById(R.id.btnGaleria);

        fotoContacto = findViewById(R.id.ivContactoN);
        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    takePhoto();
                } else {
                    requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                }
            }
        });

        btnGalleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });


        EditText etTitulo = findViewById(R.id.etTituloLC);
        EditText etResumen = findViewById(R.id.etResumenLC);
        EditText etAutor = findViewById(R.id.etAutorLC);
        EditText etFecha = findViewById(R.id.etFechaLC);
        EditText etTienda_1 = findViewById(R.id.etTienda_1LC);
        EditText etTienda_2 = findViewById(R.id.etTienda_2LC);
        EditText etTienda_3 = findViewById(R.id.etTienda_3LC);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetrofitFactory.build();

                LibroServices service = retrofit.create(LibroServices.class);

                Libro libro = new Libro();
                libro.titulo = String.valueOf(etTitulo.getText());
                libro.resumen = String.valueOf(etResumen.getText());
                libro.autor = String.valueOf(etAutor.getText());
                libro.fecha = String.valueOf(etFecha.getText());
                libro.tienda_1 = String.valueOf(etTienda_1.getText());
                libro.tienda_2 = String.valueOf(etTienda_2.getText());
                libro.tienda_3 = String.valueOf(etTienda_3.getText());
                libro.imagen = imagen;
                Call<Libro> call = service.create(libro);

                call.enqueue(new Callback<Libro>() {
                    @Override
                    public void onResponse(Call<Libro> call, Response<Libro> response) {
                        if(response.isSuccessful()){
                            Log.i("registro", new Gson().toJson(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Libro> call, Throwable t) {

                    }
                });
            }
        });

    }
    public static String convertS(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }
    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {

        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagen = convertS(imageBitmap);
            fotoContacto.setImageBitmap(imageBitmap);

        }
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Bitmap imageBitmap = BitmapFactory.decodeStream(bufferedInputStream);
                imagen = convertS(imageBitmap);
                fotoContacto.setImageBitmap(imageBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}