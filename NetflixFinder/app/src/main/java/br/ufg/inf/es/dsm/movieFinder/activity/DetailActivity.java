package br.ufg.inf.es.dsm.movieFinder.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.ufg.inf.es.dsm.movieFinder.FinderApplication;
import br.ufg.inf.es.dsm.movieFinder.R;
import br.ufg.inf.es.dsm.movieFinder.assyncTask.MovieDetailedAsyncTask;
import br.ufg.inf.es.dsm.movieFinder.interfaces.WebServiceConsumer;
import br.ufg.inf.es.dsm.movieFinder.model.Configuration;
import br.ufg.inf.es.dsm.movieFinder.model.Movie;
import br.ufg.inf.es.dsm.movieFinder.model.WebServiceResponse;


public class DetailActivity extends ActionBarActivity implements WebServiceConsumer {
    private Movie movie;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         progressDialog = ProgressDialog.show(DetailActivity.this,"Loading...",
                "Loading information about the movie...", false, false);

        Integer movieId = getIntent().getIntExtra( "movieId", 0 );
        MovieDetailedAsyncTask service = new MovieDetailedAsyncTask(this, this, movieId);
        service.execute();
    }

    @Override
    public void receiveResponse(WebServiceResponse response) {
        if( !response.isSuccess() ) {
            Log.d("receiveResponse", "Request error.");
            return;
        }

        Configuration configuration = ((FinderApplication) this.getBaseContext().getApplicationContext()).getConfiguration();
        setContentView(R.layout.activity_detail);
        movie = new Movie( response.getBody() );

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(movie.getTitle());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String imgUrl = configuration.getImageBaseUrl() + configuration.getSizePath("poster", Configuration.MEDIUM) + movie.getPosterPath();
        ImageView poster = (ImageView) findViewById(R.id.poster);
        Picasso.with(this).load( imgUrl ).placeholder(R.drawable.loading_image).error(R.drawable.no_poster).into(poster);

        TextView showTitle = (TextView) findViewById( R.id.showTitle );
        showTitle.setText(movie.getTitle());

        TextView releaseYear = (TextView) findViewById( R.id.releaseYear );
        releaseYear.setText( movie.getReleaseDate() );

        TextView runtime = (TextView) findViewById( R.id.runtime );
        runtime.setText(movie.getRuntime().toString());

        float rating = (float)(movie.getVoteAverrage() / 2);
        RatingBar ratingBar = (RatingBar) findViewById( R.id.ratingBar );
        ratingBar.setRating(rating);

        TextView summary = (TextView) findViewById( R.id.summaryValue );
        summary.setText(movie.getOverview());

        TextView showCast = (TextView) findViewById(R.id.showCastValue);
        showCast.setText(movie.getShowCastString());

        TextView genres = (TextView) findViewById(R.id.genres);
        genres.setText(movie.getGenresString());

        progressDialog.dismiss();
    }
}
