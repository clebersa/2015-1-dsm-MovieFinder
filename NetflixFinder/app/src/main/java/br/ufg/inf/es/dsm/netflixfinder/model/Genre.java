package br.ufg.inf.es.dsm.netflixfinder.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bruno on 6/3/15.
 */
public class Genre {
    private Integer id;
    private String name;

    public Genre( String jsonIn ) {
        try {
            JSONObject reader = new JSONObject(jsonIn);
            this.id = reader.getInt("id");
            this.name = reader.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
