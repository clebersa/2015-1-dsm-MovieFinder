package br.ufg.inf.es.dsm.netflixfinder.assyncTask;

import android.os.AsyncTask;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import br.ufg.inf.es.dsm.netflixfinder.interfaces.WebServiceConsumer;
import br.ufg.inf.es.dsm.netflixfinder.model.WebServiceResponse;

/**
 * Created by Bruno on 03/06/2015.
 * Edited by Cleber on 03/06/2015.
 */
public class MoviesAsyncTask extends AsyncTask<String, Void, WebServiceResponse> {
    public static final int TIMEOUT = 10000;
    private final String BASE_URL = "http://api.themoviedb.org/3/search/movie?api_key=b4bcadd043cecc365ee67a9aceb83937&include_adult=false&query=";
    private WebServiceConsumer handler;
    private WebServiceResponse response;
    private String title;

    public MoviesAsyncTask(WebServiceConsumer handler){
        this.handler = handler;
        response = new WebServiceResponse();
    }

    @Override
    protected WebServiceResponse doInBackground(String... params) {
        try {
            title = URLEncoder.encode(params[0], "UTF-8");
        } catch (UnsupportedEncodingException e) {}
        String getTitleUrl = BASE_URL + title;

        HttpRequest response  = HttpRequest.get( getTitleUrl );
        this.response.setResponseCode(response.code());
        this.response.setBody(response.body());

        return this.response;
    }

    @Override
    protected void onPostExecute(WebServiceResponse response) {
        handler.receiveResponse( response );
    }
}
