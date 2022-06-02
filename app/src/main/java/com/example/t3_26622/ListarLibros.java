package com.example.t3_26622;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.t3_26622.Adapters.LibrosAdapter;
import com.example.t3_26622.Entities.Libro;
import com.example.t3_26622.Factories.RetrofitFactory;
import com.example.t3_26622.Services.LibroServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListarLibros extends AppCompatActivity {
    List<Libro> libros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_libros);

        Retrofit retrofit = RetrofitFactory.build();

        LibroServices service = retrofit.create(LibroServices.class);
        Call<List<Libro>> call = service.getLibros();

        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if (response.isSuccessful()) {
                    libros = response.body();

                    LibrosAdapter adapter = new LibrosAdapter(libros);

                    RecyclerView rv = findViewById(R.id.rvLibros);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {

            }
        });

    }
}
