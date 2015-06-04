package br.ufg.inf.es.dsm.netflixfinder.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bruno on 6/3/15.
 */
public class Configuration {
    private String imageBaseUrl;
    private ArrayList<String> backdropImageSizes = new ArrayList<String>();
    private ArrayList<String> logoSizes = new ArrayList<String>();
    private ArrayList<String> posterSizes = new ArrayList<String>();
    private ArrayList<String> profileSizes = new ArrayList<String>();
    private ArrayList<String> stillSizes = new ArrayList<String>();

    public Configuration( String jsonIn ) {
        try {
            JSONObject reader = new JSONObject(jsonIn).getJSONObject("images");
            this.imageBaseUrl = reader.getString("base_url");

            String tmpValue;
            JSONArray backdropImageSizes = reader.getJSONArray("backdrop_sizes");
            for( int i = 0; i < backdropImageSizes.length(); i++ ) {
                tmpValue = backdropImageSizes.getString(i);
                this.backdropImageSizes.add( tmpValue );
            }

            JSONArray logoSizes = reader.getJSONArray("logo_sizes");
            for( int i = 0; i < logoSizes.length(); i++ ) {
                tmpValue = logoSizes.getString(i);
                this.logoSizes.add( tmpValue );
            }

            JSONArray posterSizes = reader.getJSONArray("poster_sizes");
            for( int i = 0; i < posterSizes.length(); i++ ) {
                tmpValue = posterSizes.getString(i);
                this.posterSizes.add( tmpValue );
            }

            JSONArray profileSizes = reader.getJSONArray("profile_sizes");
            for( int i = 0; i < profileSizes.length(); i++ ) {
                tmpValue = profileSizes.getString(i);
                this.profileSizes.add( tmpValue );
            }

            JSONArray stillSizes = reader.getJSONArray("still_sizes");
            for( int i = 0; i < stillSizes.length(); i++ ) {
                tmpValue = stillSizes.getString(i);
                this.stillSizes.add( tmpValue );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getImageBaseUrl() {
        return imageBaseUrl;
    }

    public void setImageBaseUrl(String imageBaseUrl) {
        this.imageBaseUrl = imageBaseUrl;
    }

    public ArrayList<String> getBackdropImageSizes() {
        return backdropImageSizes;
    }

    public void setBackdropImageSizes(ArrayList<String> backdropImageSizes) {
        this.backdropImageSizes = backdropImageSizes;
    }

    public ArrayList<String> getLogoSizes() {
        return logoSizes;
    }

    public void setLogoSizes(ArrayList<String> logoSizes) {
        this.logoSizes = logoSizes;
    }

    public ArrayList<String> getPosterSizes() {
        return posterSizes;
    }

    public void setPosterSizes(ArrayList<String> posterSizes) {
        this.posterSizes = posterSizes;
    }

    public ArrayList<String> getProfileSizes() {
        return profileSizes;
    }

    public void setProfileSizes(ArrayList<String> profileSizes) {
        this.profileSizes = profileSizes;
    }

    public ArrayList<String> getStillSizes() {
        return stillSizes;
    }

    public void setStillSizes(ArrayList<String> stillSizes) {
        this.stillSizes = stillSizes;
    }
}
