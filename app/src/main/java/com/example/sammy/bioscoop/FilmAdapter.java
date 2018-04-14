package com.example.sammy.bioscoop;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sammy on 3/30/18.
 */

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {
    private static final String TAG = "FilmAdapter";

    private ArrayList<Film> films;
    private Context mContext;

    public FilmAdapter(ArrayList<Film> films, Context mContext) {
        this.films = films;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Film film = films.get(position);
//        Picasso.with(holder.itemView.getContext()).load(listItem.getImageURL()).into(holder.imageView);

        Picasso.with(mContext).load(film.getPosterPath()).into(holder.imageView);
        holder.textTitle.setText(String.valueOf(film.getTitel()));
        holder.textDirector.setText(String.valueOf(film.getDirector()));
        holder.textGenre.setText(String.valueOf(film.getGenres()));
        holder.textCertification.setText(String.valueOf(film.getCertification()));

        holder.itemListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick clicked on");
                Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();

                Intent itemDetailIntent = new Intent(view.getContext().getApplicationContext(), FilmDetailActivity.class);

                itemDetailIntent.putExtra("MyClass", (Serializable) film);


//                itemDetailIntent.putExtra("imageURL", listItem.getImageURL());
//                itemDetailIntent.putExtra("cameraName", listItem.getCameraName());

                view.getContext().startActivity(itemDetailIntent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return films.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private static final String TAG = "ViewHolder";

        private RelativeLayout itemListView;
        private TextView textTitle;
        private TextView textDirector;
        private TextView textGenre;
        private TextView textCertification;
        private ImageView imageView;

        public ViewHolder(View itemView) {

            super(itemView);

            itemListView = itemView.findViewById(R.id.listItem);
            textTitle = itemView.findViewById(R.id.titleText);
            textDirector = itemView.findViewById(R.id.directorText);
            textGenre = itemView.findViewById(R.id.genreText);
            textCertification = itemView.findViewById(R.id.certificationText);
            imageView = itemView.findViewById(R.id.imgView);
        }
    }
}

