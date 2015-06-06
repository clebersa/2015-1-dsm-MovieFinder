package br.ufg.inf.es.dsm.movieFinder.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import br.ufg.inf.es.dsm.movieFinder.FinderApplication;
import br.ufg.inf.es.dsm.movieFinder.R;
import br.ufg.inf.es.dsm.movieFinder.assyncTask.ConfigurationAssyncTask;
import br.ufg.inf.es.dsm.movieFinder.interfaces.WebServiceConsumer;
import br.ufg.inf.es.dsm.movieFinder.model.Configuration;
import br.ufg.inf.es.dsm.movieFinder.model.WebServiceResponse;

public class SplashActivity extends ActionBarActivity implements WebServiceConsumer {

    @Override
    protected void onStart() {
        super.onStart();
        ConfigurationAssyncTask service = new ConfigurationAssyncTask(this, this);
        service.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void receiveResponse(WebServiceResponse response) {
        if( response.isSuccess() ) {
            Configuration configuration = new Configuration( response.getBody() );
            ((FinderApplication) this.getBaseContext().getApplicationContext()).setConfiguration(configuration);

            Intent intent = new Intent(this, HomeActivity.class);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Log.e("SPLASH", "Error waiting to start home: " + e.getMessage());
            }

            startActivity(intent);
            this.finish();
        }
    }
}
