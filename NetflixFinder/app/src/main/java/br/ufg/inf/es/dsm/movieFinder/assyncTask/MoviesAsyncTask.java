package br.ufg.inf.es.dsm.movieFinder.assyncTask;

import android.content.Context;
import android.net.Uri;

import br.ufg.inf.es.dsm.movieFinder.interfaces.WebServiceConsumer;

/**
 * Created by Bruno on 03/06/2015.
 * Edited by Cleber on 03/06/2015.
 */
public class MoviesAsyncTask extends AbstractAssyncTask<Void> {
    private String title;
    private Integer page;

    public MoviesAsyncTask(WebServiceConsumer handler, Context context, String title, Integer page) {
        super(handler, context);
        this.title = title;
        this.page = page;
    }

    @Override
    protected void appendUriBuilder(Uri.Builder uriBuilder) {
        uriBuilder.appendPath("search")
                .appendPath("movie")
                .appendQueryParameter("include_adult", "false")
                .appendQueryParameter("query", title)
                .appendQueryParameter("page", String.valueOf(page));
    }
}
