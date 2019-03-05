package com.codepath.flickster.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.flickster.DetailActivity;
import com.codepath.flickster.Models.Movie;
import com.codepath.flickster.R;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie=movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        //TextView tvTitle;
        //TextView tvOverview;
        //ImageView ivPoster;
        //RelativeLayout container;

        //Alternatvie way using Butter knife
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.tvOverview) TextView tvOverview;
        @BindView(R.id.ivPoster) ImageView ivPoster;
        @BindView(R.id.container) RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //tvTitle=itemView.findViewById(R.id.tvTitle);
            //tvOverview=itemView.findViewById(R.id.tvOverview);
            //ivPoster=itemView.findViewById(R.id.ivPoster);
            //container=itemView.findViewById(R.id.container);

            //Alternative way using Butter knife
            ButterKnife.bind(this,itemView);
        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            Glide.with(context).load(movie.getPosterPath()).into(ivPoster);
            container.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });
        }
    }
}
