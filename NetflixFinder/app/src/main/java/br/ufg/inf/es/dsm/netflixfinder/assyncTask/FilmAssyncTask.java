package br.ufg.inf.es.dsm.netflixfinder.assyncTask;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import br.ufg.inf.es.dsm.netflixfinder.interfaces.WebserviceConsumer;
import br.ufg.inf.es.dsm.netflixfinder.model.WebserviceResponse;

/**
 * Created by Bruno on 03/06/2015.
 */
public class FilmAssyncTask extends AbstractAssyncTask<Void> {
    private String title;

    public FilmAssyncTask(WebserviceConsumer handler, Context context, String title) {
        super(handler, context);

        try {
            this.title = URLEncoder.encode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {}
    }

    @Override
    protected void appendUriBuilder(Uri.Builder uriBuilder) {
        uriBuilder.appendPath("search")
                .appendPath("movie")
                .appendQueryParameter("include_adult", "false")
                .appendQueryParameter("query", title);
    }
}
