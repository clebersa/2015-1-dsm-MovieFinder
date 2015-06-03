package br.ufg.inf.es.dsm.netflixfinder.activity;

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

import br.ufg.inf.es.dsm.netflixfinder.R;
import br.ufg.inf.es.dsm.netflixfinder.assyncTask.MovieDetailedAsyncTask;
import br.ufg.inf.es.dsm.netflixfinder.interfaces.WebServiceConsumer;
import br.ufg.inf.es.dsm.netflixfinder.model.Movie;
import br.ufg.inf.es.dsm.netflixfinder.model.WebServiceResponse;


public class DetailActivity extends ActionBarActivity implements WebServiceConsumer {
    private Movie movie;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         progressDialog = ProgressDialog.show(DetailActivity.this,"Loading...",
                "Loading application View, please wait...", false, false);

        Integer movieId = getIntent().getIntExtra( "movieId", 0 );
<<<<<<< HEAD
        MovieDetailAssyncTask service = new MovieDetailAssyncTask(this, this, movieId);
        service.execute();
=======
        MovieDetailedAsyncTask service = new MovieDetailedAsyncTask(this);
        service.execute(movieId);
>>>>>>> 5cc90274e9b0b0583e9880b15ea44788d64ea61b
    }

    @Override
    public void receiveResponse(WebServiceResponse response) {
        if( !response.isSuccess() ) {
            Log.d("receiveResponse", "Erro na requisição");
        }

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

        ImageView poster = (ImageView) findViewById(R.id.poster );
        Picasso.with(this).load( movie.getPosterPath() ).into(poster);

        TextView showTitle = (TextView) findViewById( R.id.showTitle );
        showTitle.setText( movie.getTitle() );

        TextView releaseYear = (TextView) findViewById( R.id.releaseYear );
        releaseYear.setText( movie.getReleaseDate() );

        TextView runtime = (TextView) findViewById( R.id.runtime );
        runtime.setText(movie.getRuntime().toString());

        float rating = (float)(movie.getVoteAverrage() / 2);
        RatingBar ratingBar = (RatingBar) findViewById( R.id.ratingBar );
        ratingBar.setRating(rating);

        TextView summary = (TextView) findViewById( R.id.summary );
        summary.setText( movie.getOverview() );

        progressDialog.dismiss();
    }
}
