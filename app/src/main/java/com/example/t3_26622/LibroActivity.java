package com.example.t3_26622;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.t3_26622.Entities.Libro;
import com.google.gson.Gson;

public class LibroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro);

        String libroJSON = getIntent().getStringExtra("Libro");
        Libro libro = new Gson().fromJson(libroJSON, Libro.class);

        ImageView ivLibroDetalle = findViewById(R.id.ivLibroDetalle);
        TextView tvTitulo = findViewById(R.id.tvNombreLibroDetalle);
        TextView tvResumen = findViewById(R.id.tvResumenLibroDetalle);
        TextView tvAutor = findViewById(R.id.tvAutorLibroDetalle);
        TextView tvFecha = findViewById(R.id.tvFechaLibroDetalle);
        TextView tvTienda_1 = findViewById(R.id.tvTienda1Detalle);
        TextView tvTienda_2 = findViewById(R.id.tvTienda2Detalle);
        TextView tvTienda_3 = findViewById(R.id.tvTienda3Detalle);

        ivLibroDetalle.setImageBitmap(convertB(libro.imagen));
        tvTitulo.setText(libro.titulo);
        tvResumen.setText(libro.resumen);
        tvAutor.setText(libro.autor);
        tvFecha.setText(libro.fecha);
        tvTienda_1.setText(libro.tienda_1);
        tvTienda_2.setText(libro.tienda_2);
        tvTienda_3.setText(libro.tienda_3);


        tvTienda_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

                intent.putExtra("Libro", libro.tienda_1);
                startActivity(intent);
            }
        });
        tvTienda_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("Libro", libro.tienda_2);
                startActivity(intent);
            }
        });
        tvTienda_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("Libro", libro.tienda_3);
                startActivity(intent);
            }
        });
    }
    public static Bitmap convertB(String base64Str) throws IllegalArgumentException
    {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",")  + 1),
                Base64.DEFAULT
        );

        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}