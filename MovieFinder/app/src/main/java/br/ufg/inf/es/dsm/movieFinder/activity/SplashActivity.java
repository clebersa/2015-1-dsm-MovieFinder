package br.ufg.inf.es.dsm.movieFinder.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

        if(isNetworkAvailable()) {
            ConfigurationAssyncTask service = new ConfigurationAssyncTask(this, this);
            service.execute();
        } else {
            this.finish();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }

        Toast toast = Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG);
        toast.show();
        return false;
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
