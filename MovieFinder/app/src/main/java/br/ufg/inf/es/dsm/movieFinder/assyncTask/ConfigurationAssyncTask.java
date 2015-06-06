package br.ufg.inf.es.dsm.movieFinder.assyncTask;

import android.content.Context;
import android.net.Uri;
import br.ufg.inf.es.dsm.movieFinder.interfaces.WebServiceConsumer;

/**
 * Created by Bruno on 03/06/2015.
 */
public class ConfigurationAssyncTask extends AbstractAssyncTask<Void> {
    public ConfigurationAssyncTask(WebServiceConsumer handler, Context context) {
        super(handler, context);
    }

    @Override
    protected void appendUriBuilder(Uri.Builder uriBuilder) {
        uriBuilder.appendPath("configuration");
    }
}
