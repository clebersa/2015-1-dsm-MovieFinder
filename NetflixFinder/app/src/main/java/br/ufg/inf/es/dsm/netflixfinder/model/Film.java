package br.ufg.inf.es.dsm.netflixfinder.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Film implements Serializable {
    private Integer unit;
    private Integer showId;
    private String showTitle;
    private String releaseYear;
    private String rating;
    private String categoty;
    private String showCast;
    private String director;
    private String summary;
    private String poster;
    private Integer mediaType;
    private String runtime;

    public Film( Integer unit, Integer showId, String showTitle, String releaseYear, String rating,
                  String category, String showCast, String director, String summary, String poster,
                  Integer mediaType, String runtime ) {
        this.unit = unit;
        this.showId = showId;
        this.showTitle = showTitle;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.categoty = category;
        this.showCast = showCast;
        this.director = director;
        this.summary = summary;
        this.poster = poster;
        this.mediaType = mediaType;
        this.runtime = runtime;
    }

    public Film() {

    }

    public Film( String jsonIn ) {
        try {
            JSONObject reader = new JSONObject(jsonIn);
            this.unit = reader.getInt("unit");
            this.showId = reader.getInt("show_id");
            this.showTitle = reader.getString("show_title");
            this.releaseYear = reader.getString("release_year");
            this.rating = reader.getString("rating");
            this.categoty = reader.getString("category");
            this.showCast = reader.getString("show_cast");
            this.director = reader.getString("director");
            this.summary = reader.getString("summary");
            this.poster = reader.getString("poster");
            this.mediaType = reader.getInt("mediatype");
            this.runtime = reader.getString("runtime");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategoty() {
        return categoty;
    }

    public void setCategoty(String categoty) {
        this.categoty = categoty;
    }

    public String getShowCast() {
        return showCast;
    }

    public void setShowCast(String showCast) {
        this.showCast = showCast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getMediaType() {
        return mediaType;
    }

    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
}
