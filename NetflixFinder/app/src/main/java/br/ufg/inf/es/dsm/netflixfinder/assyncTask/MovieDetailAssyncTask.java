package br.ufg.inf.es.dsm.netflixfinder.assyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;

import br.ufg.inf.es.dsm.netflixfinder.interfaces.WebserviceConsumer;
import br.ufg.inf.es.dsm.netflixfinder.model.WebserviceResponse;


/**
 * Created by Bruno on 03/06/2015.
 */
public class MovieDetailAssyncTask extends AsyncTask<Integer, Void, WebserviceResponse> {
    public static final int TIMEOUT = 10000;
    private WebserviceConsumer handler;
    private WebserviceResponse response = new WebserviceResponse();

    private Integer movieId;
    private final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private final String API_KEY = "b4bcadd043cecc365ee67a9aceb83937";

    public MovieDetailAssyncTask(WebserviceConsumer handler){
        this.handler = handler;
    }

    @Override
    protected WebserviceResponse doInBackground(Integer... params) {
        movieId = params[0];
        String getTitleUrl = BASE_URL + movieId + "?api_key=" + API_KEY;

        HttpRequest response  = HttpRequest.get( getTitleUrl );
        this.response.setResponseCode(response.code());
        this.response.setBody(response.body());

        return this.response;
    }

    @Override
    protected void onPostExecute(WebserviceResponse response) {
        handler.receiveResponse( response );
    }
}
