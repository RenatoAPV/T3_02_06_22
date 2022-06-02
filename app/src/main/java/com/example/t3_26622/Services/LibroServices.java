package com.example.t3_26622.Services;

import com.example.t3_26622.Entities.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LibroServices {
    @GET ("libro")
    Call<List<Libro>> getLibros();

    @POST("libro")
    Call<Libro> create(@Body Libro libro);
}
