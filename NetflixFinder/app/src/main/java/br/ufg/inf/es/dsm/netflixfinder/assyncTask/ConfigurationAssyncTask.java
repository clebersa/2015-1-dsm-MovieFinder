package br.ufg.inf.es.dsm.netflixfinder.assyncTask;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import br.ufg.inf.es.dsm.netflixfinder.R;
import br.ufg.inf.es.dsm.netflixfinder.interfaces.WebserviceConsumer;
import br.ufg.inf.es.dsm.netflixfinder.model.WebserviceResponse;

/**
 * Created by Bruno on 03/06/2015.
 */
public class ConfigurationAssyncTask extends AbstractAssyncTask<Void> {
    public ConfigurationAssyncTask(WebserviceConsumer handler, Context context) {
        super(handler, context);
    }

    @Override
    protected void appendUriBuilder(Uri.Builder uriBuilder) {
        uriBuilder.appendPath("configuration");
    }
}
