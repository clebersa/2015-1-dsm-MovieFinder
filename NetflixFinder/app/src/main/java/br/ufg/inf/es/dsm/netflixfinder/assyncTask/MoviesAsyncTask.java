package br.ufg.inf.es.dsm.netflixfinder.assyncTask;

import android.content.Context;
import android.net.Uri;
import br.ufg.inf.es.dsm.netflixfinder.interfaces.WebServiceConsumer;

/**
 * Created by Bruno on 03/06/2015.
 * Edited by Cleber on 03/06/2015.
 */
public class MoviesAsyncTask extends AbstractAssyncTask<Void> {
    private String title;

    public MoviesAsyncTask(WebServiceConsumer handler, Context context, String title) {
        super(handler, context);
        this.title = title;
    }

    @Override
    protected void appendUriBuilder(Uri.Builder uriBuilder) {
        uriBuilder.appendPath("search")
                .appendPath("movie")
                .appendQueryParameter("include_adult", "false")
                .appendQueryParameter("query", title);
    }
}
