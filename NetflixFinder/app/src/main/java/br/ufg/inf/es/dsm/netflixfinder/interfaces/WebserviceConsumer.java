package br.ufg.inf.es.dsm.netflixfinder.interfaces;

import br.ufg.inf.es.dsm.netflixfinder.model.WebServiceResponse;

/**
 * Created by Bruno on 03/06/2015.
 * Edited by Cleber on 03/06/2015.
 */
public interface WebServiceConsumer {
    public void receiveResponse(WebServiceResponse response);
}
