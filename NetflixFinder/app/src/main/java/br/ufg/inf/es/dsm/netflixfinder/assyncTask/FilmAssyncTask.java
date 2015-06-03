package br.ufg.inf.es.dsm.netflixfinder.assyncTask;

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
public class FilmAssyncTask extends AsyncTask<String, Void, WebserviceResponse> {
    public static final int TIMEOUT = 10000;
    private WebserviceConsumer handler;
    private WebserviceResponse response = new WebserviceResponse();

    private String title;
    private final String BASE_URL = "http://api.themoviedb.org/3/search/movie?api_key=b4bcadd043cecc365ee67a9aceb83937&include_adult=false&query=";

    public FilmAssyncTask(WebserviceConsumer handler){
        this.handler = handler;
    }

    @Override
    protected WebserviceResponse doInBackground(String... params) {
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
    protected void onPostExecute(WebserviceResponse response) {
        handler.receiveResponse( response );
    }
}
