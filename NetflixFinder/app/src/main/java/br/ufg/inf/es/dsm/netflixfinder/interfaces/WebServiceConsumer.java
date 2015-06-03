package br.ufg.inf.es.dsm.netflixfinder.interfaces;

import br.ufg.inf.es.dsm.netflixfinder.model.WebserviceResponse;

/**
 * Created by Bruno on 03/06/2015.
 */
public interface WebserviceConsumer {
    public void receiveResponse(WebserviceResponse response);
}
