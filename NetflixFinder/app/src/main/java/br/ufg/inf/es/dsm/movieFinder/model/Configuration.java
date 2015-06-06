package br.ufg.inf.es.dsm.movieFinder.model;

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

    public static final Integer VERY_SMALL = 0;
    public static final Integer SMALL = 1;
    public static final Integer MEDIUM = 2;
    public static final Integer LARGE = 3;
    public static final Integer ORIGINAL = -1;

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

    public String getSizePath( String type, Integer size ) {
        ArrayList<String> pathSearch;
        switch( type ) {
            case "logo":
                pathSearch = this.logoSizes;
                break;
            case "profile":
                pathSearch = this.profileSizes;
                break;
            case "still":
                pathSearch = this.stillSizes;
                break;
            case "poster":
            default:
                pathSearch = this.posterSizes;
                break;
        }

        if( size == -1 ) {
            Integer last = (pathSearch.size() - 1 );
            return pathSearch.get( last );
        }

        return pathSearch.get( size );
    }
}
