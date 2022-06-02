package com.example.t3_26622.Adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_26622.Entities.Libro;
import com.example.t3_26622.LibroActivity;
import com.example.t3_26622.R;
import com.google.gson.Gson;

import java.util.List;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.LibroViewHolder>{
    List<Libro> libros;
    public LibrosAdapter(List<Libro> libros){
        this.libros = libros;
    }
    @NonNull
    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libros,parent,false);
        return new LibroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder vh, int position) {
        View itemView = vh.itemView;

        Libro libro = libros.get(position);
        ImageView ivContacto = vh.itemView.findViewById(R.id.ivLibro);
        ivContacto.setImageBitmap(convertB(libro.imagen));
        TextView tvNombreL = vh.itemView.findViewById(R.id.tvNombreLibro);
        tvNombreL.setText(libro.titulo);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), LibroActivity.class);

                String libroJSON = new Gson().toJson(libro);
                intent.putExtra("Libro", libroJSON);

                itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }

    class LibroViewHolder extends RecyclerView.ViewHolder{

        public LibroViewHolder(@NonNull View itemView) {
            super(itemView);
        }
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
