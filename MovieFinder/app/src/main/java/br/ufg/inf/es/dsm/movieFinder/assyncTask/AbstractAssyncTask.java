package br.ufg.inf.es.dsm.movieFinder.assyncTask;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;

import br.ufg.inf.es.dsm.movieFinder.R;
import br.ufg.inf.es.dsm.movieFinder.interfaces.WebServiceConsumer;
import br.ufg.inf.es.dsm.movieFinder.model.WebServiceResponse;

/**
 * Created by bruno on 6/3/15.
 */
public abstract class AbstractAssyncTask<T> extends AsyncTask<T, Void, WebServiceResponse> {
    protected Context context;
    private WebServiceConsumer handler;
    private WebServiceResponse response = new WebServiceResponse();
    protected Uri.Builder uriBuilder;

    public AbstractAssyncTask(WebServiceConsumer handler, Context context) {
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
    protected WebServiceResponse doInBackground(T... params) {
        String getUrl = uriBuilder.build().toString();
        Log.d("APIEXECUTE", getUrl);
        HttpRequest response  = HttpRequest.get( getUrl );
        this.response.setResponseCode(response.code());
        this.response.setBody(response.body());

        return this.response;
    }

    @Override
    protected void onPostExecute(WebServiceResponse response) {
        handler.receiveResponse(response);
    }

    abstract protected void appendUriBuilder( Uri.Builder uriBuilder );

    public Context getContext() {
        return context;
    }
}
