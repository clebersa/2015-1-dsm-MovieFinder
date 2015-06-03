package br.ufg.inf.es.dsm.netflixfinder.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.ufg.inf.es.dsm.netflixfinder.fragment.FilmsFragment;
import br.ufg.inf.es.dsm.netflixfinder.R;
import br.ufg.inf.es.dsm.netflixfinder.model.Film;


public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.resultList, new FilmsFragment()).commit();
        }

        String jsonIn = "{\"unit\":6226,\"show_id\":70299043,\"show_title\":\"Attack on Titan\"," +
                "\"release_year\":\"2013\",\"rating\":\"4.6\",\"category\":\"Anime\",\"show_cast\"" +
                ":\"Yuki Kaji, Yui Ishikawa, Marina Inoue, Daisuke Ono, Hiro Shimono, Hiroshi " +
                "Kamiya, Keiji Fujiwara, Kish\\u00f4 Taniyama, Romi Park, Ryota Ohsaka\",\"" +
                "director\":\"\",\"summary\":\"For over a century, people have been living behind" +
                " barricades to block out the giant Titans that threaten to destroy the human race." +
                " When a Titan destroys his hometown, young Eren Yeager becomes determined to fight" +
                " back.\",\"poster\":\"http:\\/\\/cdn-2.nflximg.com\\/en_us\\/boxshots\\/ghd\\" +
                "/70299043.jpg\",\"mediatype\":1}";

        Film film = new Film( jsonIn );
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("film", film);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

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
}
