package br.ufg.inf.es.dsm.movieFinder.activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;

import br.ufg.inf.es.dsm.movieFinder.FinderApplication;
import br.ufg.inf.es.dsm.movieFinder.MovieListLoader;
import br.ufg.inf.es.dsm.movieFinder.adapter.MovieAdapter;
import br.ufg.inf.es.dsm.movieFinder.R;
import br.ufg.inf.es.dsm.movieFinder.fragment.ListSortFragment;
import br.ufg.inf.es.dsm.movieFinder.model.Configuration;
import br.ufg.inf.es.dsm.movieFinder.model.Movie;
import br.ufg.inf.es.dsm.movieFinder.model.SortMethod;


/**
 * Created by Cleber on 02/06/2015.
 * Edited by Bruno on 03/06/2015.
 */
public class HomeActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {

    private final static String LOG_TAG = HomeActivity.class.getSimpleName();
    SuperRecyclerView recList;
    MovieListLoader loader;
    private ProgressDialog ringProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);

        recList = (SuperRecyclerView) findViewById( R.id.cardList );
        GridLayoutManager layout = new GridLayoutManager( this, 3 );
        recList.setLayoutManager(layout);
        recList.getRecyclerView().setHasFixedSize(true);

        Configuration configuration = ((FinderApplication) this.getBaseContext().getApplicationContext()).getConfiguration();
        MovieAdapter movieAdapter = new MovieAdapter(new ArrayList<Movie>(), configuration);
        recList.setAdapter(movieAdapter);

        SharedPreferences preferences = getSharedPreferences(
                getString(R.string.sortMode), 0);
        if("".equals(preferences.getString(getString(R.string.sortMode), ""))){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(getString(R.string.sortMode), SortMethod.NAME.toString());
            editor.commit();
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
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_order_list:
                ListSortFragment lsf = new ListSortFragment();
                lsf.show(getFragmentManager(), "ListSortFragment");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectOrderList() {
        Log.d("ORDER", "let's order!");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        loader = new MovieListLoader( this, recList, query );

        getCurrentFocus().getRootView().clearFocus();

        ringProgressDialog = ProgressDialog.show(this, getString(R.string.loading_detail),
                getString(R.string.searching_movies), true);
        ringProgressDialog.show();

        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    public void updateMovieListOrder(){
        Log.d("SORT", "Update list sort");
        if(loader != null) {
            loader.sortList();
        }
    }

    public ProgressDialog getProgressDialog(){
        return ringProgressDialog;
    }
}
