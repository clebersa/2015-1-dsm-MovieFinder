package br.ufg.inf.es.dsm.netflixfinder.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;

import br.ufg.inf.es.dsm.netflixfinder.FinderApplication;
import br.ufg.inf.es.dsm.netflixfinder.assyncTask.FilmAssyncTask;
import br.ufg.inf.es.dsm.netflixfinder.fragment.FilmsFragment;
import br.ufg.inf.es.dsm.netflixfinder.R;
import br.ufg.inf.es.dsm.netflixfinder.interfaces.WebserviceConsumer;
import br.ufg.inf.es.dsm.netflixfinder.model.Configuration;
import br.ufg.inf.es.dsm.netflixfinder.model.Movie;
import br.ufg.inf.es.dsm.netflixfinder.model.WebserviceResponse;


public class HomeActivity extends ActionBarActivity implements SearchView.OnQueryTextListener, WebserviceConsumer {

    private final static String LOG_TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);

        Configuration configuration = ((FinderApplication) this.getBaseContext().getApplicationContext()).getConfiguration();

        Log.d(LOG_TAG, configuration.getImageBaseUrl());
        configuration.setImageBaseUrl("LALALALALALALALA");

        configuration = ((FinderApplication) this.getBaseContext().getApplicationContext()).getConfiguration();
        Log.d(LOG_TAG, configuration.getImageBaseUrl());

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.resultList, new FilmsFragment()).commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
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
            FilmAssyncTask service = new FilmAssyncTask(this, this, query);
            service.execute();
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void receiveResponse(WebserviceResponse response) {
        if( response.isSuccess() ) {
            JSONObject reader = null;
            Integer movieId = null;
            try {
                reader = new JSONObject(response.getBody()).getJSONArray("results").getJSONObject(0);
                movieId = reader.getInt("id");
            } catch (JSONException e) {}

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("movieId", movieId);
            startActivity(intent);
        }
    }
}
