package br.ufg.inf.es.dsm.movieFinder.interfaces;

import br.ufg.inf.es.dsm.movieFinder.model.WebServiceResponse;

/**
 * Created by Bruno on 03/06/2015.
 * Edited by Cleber on 03/06/2015.
 */
public interface WebServiceConsumer {
    public void receiveResponse(WebServiceResponse response);
}
