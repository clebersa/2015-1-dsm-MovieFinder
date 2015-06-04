package br.ufg.inf.es.dsm.netflixfinder.activity;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.es.dsm.netflixfinder.FinderApplication;
import br.ufg.inf.es.dsm.netflixfinder.adapter.MovieAdapter;
import br.ufg.inf.es.dsm.netflixfinder.fragment.FilmsFragment;
import br.ufg.inf.es.dsm.netflixfinder.R;
import br.ufg.inf.es.dsm.netflixfinder.model.Configuration;
import br.ufg.inf.es.dsm.netflixfinder.assyncTask.MoviesAsyncTask;
import br.ufg.inf.es.dsm.netflixfinder.interfaces.WebServiceConsumer;
import br.ufg.inf.es.dsm.netflixfinder.model.Movie;
import br.ufg.inf.es.dsm.netflixfinder.model.WebServiceResponse;


/**
 * Created by Cleber on 02/06/2015.
 * Edited by Bruno on 03/06/2015.
 */
public class HomeActivity extends ActionBarActivity implements SearchView.OnQueryTextListener, WebServiceConsumer {

    private final static String LOG_TAG = HomeActivity.class.getSimpleName();
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);

        RecyclerView recList = (RecyclerView) findViewById( R.id.cardList );
        GridLayoutManager layout = new GridLayoutManager( this, 3 );
        recList.setLayoutManager( layout );
        recList.setHasFixedSize(true);

        Configuration configuration = ((FinderApplication) this.getBaseContext().getApplicationContext()).getConfiguration();
        movieAdapter = new MovieAdapter(new ArrayList<Movie>(), configuration);
        recList.setAdapter(movieAdapter);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.RESULT_FRAGMENT, new FilmsFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener( this );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_search){
            Log.d("SEARCH", "Search touched.");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if( isNetworkAvailable() ){
            MoviesAsyncTask service = new MoviesAsyncTask(this, this, query);
            service.execute();
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void receiveResponse(WebServiceResponse response) {
        if( response.isSuccess() ) {
            List<Movie> result = new ArrayList<Movie>();
            try {
                JSONArray reader = new JSONObject(response.getBody()).getJSONArray("results");
                Movie tmpMovie;
                for( int i = 0; i < reader.length(); i++ ) {
                    tmpMovie = new Movie( reader.getJSONObject(i).toString() );
                    result.add(tmpMovie);
                }
            } catch (JSONException e) {}

            movieAdapter.changeData( result );
        }
    }
}
