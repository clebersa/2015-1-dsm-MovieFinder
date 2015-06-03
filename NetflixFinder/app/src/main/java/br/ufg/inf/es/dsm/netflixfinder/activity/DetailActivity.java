package br.ufg.inf.es.dsm.netflixfinder.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.ufg.inf.es.dsm.netflixfinder.R;
import br.ufg.inf.es.dsm.netflixfinder.model.Film;


public class DetailActivity extends ActionBarActivity {
    private Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        film = (Film) getIntent().getSerializableExtra("film");

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle( film.getShowTitle() );

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView poster = (ImageView) findViewById(R.id.poster );
        Picasso.with(this).load( film.getPoster() ).into(poster);

        TextView showTitle = (TextView) findViewById( R.id.showTitle );
        showTitle.setText( film.getShowTitle() );

        TextView releaseYear = (TextView) findViewById( R.id.releaseYear );
        releaseYear.setText( film.getReleaseYear() );

        TextView summary = (TextView) findViewById( R.id.summary );
        summary.setText( film.getSummary() );

        RatingBar ratingBar = (RatingBar) findViewById( R.id.ratingBar );
        ratingBar.setRating( Float.valueOf( film.getRating() ) );
    }
}
