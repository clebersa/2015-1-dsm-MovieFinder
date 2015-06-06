package br.ufg.inf.es.dsm.netflixfinder;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.ufg.inf.es.dsm.netflixfinder.activity.SortMethod;
import br.ufg.inf.es.dsm.netflixfinder.adapter.MovieAdapter;
import br.ufg.inf.es.dsm.netflixfinder.assyncTask.MoviesAsyncTask;
import br.ufg.inf.es.dsm.netflixfinder.interfaces.WebServiceConsumer;
import br.ufg.inf.es.dsm.netflixfinder.model.Movie;
import br.ufg.inf.es.dsm.netflixfinder.model.WebServiceResponse;

/**
 * Created by Bruno on 04/06/2015.
 */
public class MovieListLoader implements OnMoreListener, WebServiceConsumer {
    private Context context;
    private SuperRecyclerView recList;
    private String queryTitle;
    private Integer nextLoadPage = 1;
    private Boolean isAllLoaded = false;

    public MovieListLoader( Context context, SuperRecyclerView recList, String queryTitle ) {
        this.context = context;
        this.recList = recList;
        this.queryTitle = queryTitle;

        ((MovieAdapter)recList.getAdapter()).getMovieList().clear();
        this.recList.setupMoreListener(this, 6);
        onMoreAsked( 0, 0, 0 );
    }

    @Override
    public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {
        if( isAllLoaded ) {
            recList.removeMoreListener();
        }

        if( isNetworkAvailable() ) {
            MoviesAsyncTask service = new MoviesAsyncTask(this, context, queryTitle, nextLoadPage);
            service.execute();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public void receiveResponse(WebServiceResponse response) {
        if( response.isSuccess() ) {
            MovieAdapter adapter = (MovieAdapter) recList.getAdapter();
            List<Movie> adapterMovies = adapter.getMovieList();
            try {
                JSONObject reader = new JSONObject(response.getBody());

                JSONArray moviesResult = reader.getJSONArray("results");
                Movie tmpMovie;
                for( int i = 0; i < moviesResult.length(); i++ ) {
                    tmpMovie = new Movie( moviesResult.getJSONObject(i).toString() );
                    adapterMovies.add( tmpMovie );
                }

                Integer totalPages = reader.getInt("total_pages");
                nextLoadPage++;

                if( totalPages.equals(nextLoadPage) ) {
                    isAllLoaded = true;
                }
            } catch (JSONException e) {}

            recList.getAdapter().notifyDataSetChanged();
        }

        recList.hideMoreProgress();
        recList.setLoadingMore(false);
    }

    public void sortList(){
        Log.d("SORT", "Sorting...");

        SharedPreferences preferences = context.getSharedPreferences(
                context.getString(R.string.sortMode), 0);
        SortMethod sortMode = SortMethod.valueOf(preferences.getString(
                context.getString(R.string.sortMode), ""));

        switch (sortMode){
            case NAME:
                Log.d("SORT", "The list will be sorted by name.");

                break;
            case NAME_INVERSE:
                Log.d("SORT", "The list will be sorted by name (inverse)");
                break;
            case YEAR:
                Log.d("SORT", "The list will be sorted by release year (newest to oldest).");
                break;
            case YEAR_INVERSE:
                Log.d("SORT", "The list will be sorted by release year (oldest to newest).");
                break;
            case RATING:
                Log.d("SORT", "The list will be sorted by rating.");
                break;
            default:
                Log.d("SORT", "The list will not be sorted.");
        }

    }

}
