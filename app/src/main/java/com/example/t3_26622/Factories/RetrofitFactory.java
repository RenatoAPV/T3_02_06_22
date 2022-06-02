package com.example.t3_26622.Factories;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    public static Retrofit build() {

        return new Retrofit.Builder()
                .baseUrl("https://6298a148f2decf5bb7479468.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
