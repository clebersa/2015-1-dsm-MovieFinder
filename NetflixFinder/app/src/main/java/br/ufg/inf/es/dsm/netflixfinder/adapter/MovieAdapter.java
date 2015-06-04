package br.ufg.inf.es.dsm.netflixfinder.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.es.dsm.netflixfinder.FinderApplication;
import br.ufg.inf.es.dsm.netflixfinder.R;
import br.ufg.inf.es.dsm.netflixfinder.activity.DetailActivity;
import br.ufg.inf.es.dsm.netflixfinder.model.Configuration;
import br.ufg.inf.es.dsm.netflixfinder.model.Movie;

/**
 * Created by alunoinf on 04/06/2015.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movieList;
    private Configuration configuration;

    public MovieAdapter(List<Movie> movieList, Configuration configuration ) {
        this.movieList = movieList;
        this.configuration = configuration;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void changeData( List<Movie> movieList ) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {
        Movie ci = movieList.get(i);
        movieViewHolder.id = ci.getId();
        String imageUrl = configuration.getImageBaseUrl() + configuration.getSizePath("poster", Configuration.SMALL) + ci.getPosterPath();
        Picasso.with(movieViewHolder.context).load(imageUrl).placeholder(R.drawable.loading_image).error(R.drawable.not_found_image).into(movieViewHolder.vPosterImage);
        //movieViewHolder.vTitle.setText(ci.getTitle());
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View itemView = LayoutInflater.
                from(context).
                inflate(R.layout.movie_card_layout, viewGroup, false);

        return new MovieViewHolder(itemView, context);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView vPosterImage;
        //public TextView vTitle;
        public Integer id;
        public final Context context;

        public MovieViewHolder(View v, final Context context) {
            super(v);
            vPosterImage = (ImageView)  v.findViewById(R.id.posterImage);
            //vTitle = (TextView) v.findViewById(R.id.title);
            this.context = context;

            v.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("movieId", id);
                    context.startActivity(intent);
                }
            });
        }
    }
}
