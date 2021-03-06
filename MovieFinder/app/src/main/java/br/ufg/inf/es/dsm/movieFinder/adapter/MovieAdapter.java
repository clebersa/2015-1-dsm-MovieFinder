package br.ufg.inf.es.dsm.movieFinder.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.ufg.inf.es.dsm.movieFinder.R;
import br.ufg.inf.es.dsm.movieFinder.activity.DetailActivity;
import br.ufg.inf.es.dsm.movieFinder.model.Configuration;
import br.ufg.inf.es.dsm.movieFinder.model.Movie;

/**
 * Created by alunoinf on 04/06/2015.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movieList;
    private Configuration configuration;
    private TextView noTitle;

    public MovieAdapter(List<Movie> movieList, Configuration configuration, TextView noTitle ) {
        this.movieList = movieList;
        this.configuration = configuration;
        this.noTitle = noTitle;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void refreshData() {
        if( this.movieList.size() <= 0 ) {
            this.noTitle.setVisibility( View.VISIBLE );
        } else {
            this.noTitle.setVisibility( View.GONE );
        }
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
        Picasso.with(movieViewHolder.context).load(imageUrl).placeholder(R.drawable.loading_image).error(R.drawable.no_poster).into(movieViewHolder.vPosterImage);
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
