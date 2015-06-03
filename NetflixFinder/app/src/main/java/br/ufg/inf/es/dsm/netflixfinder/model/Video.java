package br.ufg.inf.es.dsm.netflixfinder.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bruno on 6/3/15.
 */
public class Video {
    private String id;
    private String key;
    private String name;
    private String site;
    private String type;
    private Integer size;

    private final String YOUTUBE = "https://www.youtube.com/watch?v=";

    public Video(String jsonIn) {
        try {
            JSONObject reader = new JSONObject(jsonIn);
            this.id = reader.getString("id");
            this.key = reader.getString("key");
            this.name = reader.getString("name");
            this.site = reader.getString("site");
            this.type = reader.getString("type");
            this.size = reader.getInt("size");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        if( this.site.equals("YouTube") ) {
            return this.YOUTUBE + this.key;
        }
        return "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
