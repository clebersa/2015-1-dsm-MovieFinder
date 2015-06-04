package br.ufg.inf.es.dsm.netflixfinder.assyncTask;

import android.content.Context;
import android.net.Uri;
import br.ufg.inf.es.dsm.netflixfinder.interfaces.WebServiceConsumer;

/**
 * Created by Bruno on 03/06/2015.
 * Edited by Cleber on 03/06/2015.
 */
public class MovieDetailedAsyncTask extends AbstractAssyncTask<Void> {
    private Integer movieId;

    public MovieDetailedAsyncTask(WebServiceConsumer handler, Context context, Integer movieId) {
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
