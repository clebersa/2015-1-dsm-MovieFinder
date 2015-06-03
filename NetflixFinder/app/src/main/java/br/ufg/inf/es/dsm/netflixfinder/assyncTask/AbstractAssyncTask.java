package br.ufg.inf.es.dsm.netflixfinder.assyncTask;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.github.kevinsawicki.http.HttpRequest;

import br.ufg.inf.es.dsm.netflixfinder.R;
import br.ufg.inf.es.dsm.netflixfinder.interfaces.WebserviceConsumer;
import br.ufg.inf.es.dsm.netflixfinder.model.WebserviceResponse;

/**
 * Created by bruno on 6/3/15.
 */
public abstract class AbstractAssyncTask<T> extends AsyncTask<T, Void, WebserviceResponse> {
    protected Context context;
    private WebserviceConsumer handler;
    private WebserviceResponse response = new WebserviceResponse();
    protected Uri.Builder uriBuilder;

    public AbstractAssyncTask(WebserviceConsumer handler, Context context) {
        this.handler = handler;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        uriBuilder = new Uri.Builder();
        uriBuilder.scheme("http")
                .authority("api.themoviedb.org")
                .appendPath("3");

        appendUriBuilder(uriBuilder);

        String apiKey = getContext().getResources().getString(R.string.api_key);
        uriBuilder.appendQueryParameter("api_key", apiKey);
    }

    @Override
    protected WebserviceResponse doInBackground(T... params) {
        HttpRequest response  = HttpRequest.get( uriBuilder.build().toString() );
        this.response.setResponseCode(response.code());
        this.response.setBody(response.body());

        return this.response;
    }

    @Override
    protected void onPostExecute(WebserviceResponse response) {
        handler.receiveResponse(response);
    }

    abstract protected void appendUriBuilder( Uri.Builder uriBuilder );

    public Context getContext() {
        return context;
    }
}
