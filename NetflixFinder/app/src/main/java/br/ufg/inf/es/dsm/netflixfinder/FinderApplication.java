package br.ufg.inf.es.dsm.netflixfinder;

import android.app.Application;
import android.util.Log;

import br.ufg.inf.es.dsm.netflixfinder.model.Configuration;

/**
 * Created by bruno on 6/3/15.
 */
public class FinderApplication extends Application {
    private Configuration configuration;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
         this.configuration = configuration;
    }
}
