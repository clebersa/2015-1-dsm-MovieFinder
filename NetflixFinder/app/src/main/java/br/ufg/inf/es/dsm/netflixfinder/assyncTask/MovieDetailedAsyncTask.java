package br.ufg.inf.es.dsm.netflixfinder.assyncTask;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.github.kevinsawicki.http.HttpRequest;

import br.ufg.inf.es.dsm.netflixfinder.interfaces.WebServiceConsumer;
import br.ufg.inf.es.dsm.netflixfinder.model.WebServiceResponse;


/**
 * Created by Bruno on 03/06/2015.
 * Edited by Cleber on 03/06/2015.
 */
<<<<<<< HEAD:NetflixFinder/app/src/main/java/br/ufg/inf/es/dsm/netflixfinder/assyncTask/MovieDetailAssyncTask.java
public class MovieDetailAssyncTask extends AbstractAssyncTask<Integer> {
    private Integer movieId;
=======
public class MovieDetailedAsyncTask extends AsyncTask<Integer, Void, WebServiceResponse> {
    public static final int TIMEOUT = 10000;
    private final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private final String API_KEY = "b4bcadd043cecc365ee67a9aceb83937";
    private WebServiceConsumer handler;
    private WebServiceResponse response = new WebServiceResponse();
    private Integer movieId;

    public MovieDetailedAsyncTask(WebServiceConsumer handler){
        this.handler = handler;
    }

    @Override
    protected WebServiceResponse doInBackground(Integer... params) {
        movieId = params[0];
        String getTitleUrl = BASE_URL + movieId + "?api_key=" + API_KEY;

        HttpRequest response  = HttpRequest.get( getTitleUrl );
        this.response.setResponseCode(response.code());
        this.response.setBody(response.body());
>>>>>>> 5cc90274e9b0b0583e9880b15ea44788d64ea61b:NetflixFinder/app/src/main/java/br/ufg/inf/es/dsm/netflixfinder/assyncTask/MovieDetailedAsyncTask.java

    public MovieDetailAssyncTask(WebserviceConsumer handler, Context context, Integer movieId) {
        super(handler, context);
        this.movieId = movieId;
    }

    @Override
<<<<<<< HEAD:NetflixFinder/app/src/main/java/br/ufg/inf/es/dsm/netflixfinder/assyncTask/MovieDetailAssyncTask.java
    protected void appendUriBuilder(Uri.Builder uriBuilder) {
        uriBuilder.appendPath("movie")
                .appendPath(movieId.toString())
                .appendQueryParameter("append_to_response",
                        "similar,videos,credits,alternative_titles");
=======
    protected void onPostExecute(WebServiceResponse response) {
        handler.receiveResponse( response );
>>>>>>> 5cc90274e9b0b0583e9880b15ea44788d64ea61b:NetflixFinder/app/src/main/java/br/ufg/inf/es/dsm/netflixfinder/assyncTask/MovieDetailedAsyncTask.java
    }
}
