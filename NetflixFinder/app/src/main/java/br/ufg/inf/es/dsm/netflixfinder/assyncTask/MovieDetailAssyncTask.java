package br.ufg.inf.es.dsm.netflixfinder.assyncTask;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;

import br.ufg.inf.es.dsm.netflixfinder.interfaces.WebserviceConsumer;
import br.ufg.inf.es.dsm.netflixfinder.model.WebserviceResponse;


/**
 * Created by Bruno on 03/06/2015.
 */
public class MovieDetailAssyncTask extends AbstractAssyncTask<Integer> {
    private Integer movieId;

    public MovieDetailAssyncTask(WebserviceConsumer handler, Context context, Integer movieId) {
        super(handler, context);
        this.movieId = movieId;
    }

    @Override
    protected void appendUriBuilder(Uri.Builder uriBuilder) {
        uriBuilder.appendPath("movie")
                .appendPath(movieId.toString())
                .appendQueryParameter("append_to_response",
                        "similar,videos,credits,alternative_titles");
    }
}