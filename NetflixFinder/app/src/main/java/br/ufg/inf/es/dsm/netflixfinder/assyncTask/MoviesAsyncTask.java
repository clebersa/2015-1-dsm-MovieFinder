package br.ufg.inf.es.dsm.netflixfinder.assyncTask;

import android.content.Context;
import android.net.Uri;
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
<<<<<<< HEAD:NetflixFinder/app/src/main/java/br/ufg/inf/es/dsm/netflixfinder/assyncTask/FilmAssyncTask.java
public class FilmAssyncTask extends AbstractAssyncTask<Void> {
    private String title;

    public FilmAssyncTask(WebserviceConsumer handler, Context context, String title) {
        super(handler, context);

=======
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
>>>>>>> 5cc90274e9b0b0583e9880b15ea44788d64ea61b:NetflixFinder/app/src/main/java/br/ufg/inf/es/dsm/netflixfinder/assyncTask/MoviesAsyncTask.java
        try {
            this.title = URLEncoder.encode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {}
    }

    @Override
<<<<<<< HEAD:NetflixFinder/app/src/main/java/br/ufg/inf/es/dsm/netflixfinder/assyncTask/FilmAssyncTask.java
    protected void appendUriBuilder(Uri.Builder uriBuilder) {
        uriBuilder.appendPath("search")
                .appendPath("movie")
                .appendQueryParameter("include_adult", "false")
                .appendQueryParameter("query", title);
=======
    protected void onPostExecute(WebServiceResponse response) {
        handler.receiveResponse( response );
>>>>>>> 5cc90274e9b0b0583e9880b15ea44788d64ea61b:NetflixFinder/app/src/main/java/br/ufg/inf/es/dsm/netflixfinder/assyncTask/MoviesAsyncTask.java
    }
}
