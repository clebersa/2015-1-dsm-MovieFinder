package br.ufg.inf.es.dsm.netflixfinder.model;

/**
 * Created by Bruno on 03/06/2015.
 * Edited by Cleber on 03/06/2015.
 */
public class WebServiceResponse {
    private final int SUCCESS = 200;
    private Integer responseCode;
    private String body;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isSuccess() {
        return this.responseCode == this.SUCCESS;
    }
}
