package br.ufg.inf.es.dsm.netflixfinder.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Bruno on 03/06/2015.
 * Edited by Cleber on 03/06/2015.
 */
public class Movie {
    private final String imageBaseUrl = "https://image.tmdb.org/t/p/w342/";
    private Integer id;
    private Boolean isAdult;
    private String backDropPath;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private String posterPath;
    private Double popularity;
    private String title;
    private Double voteAverrage;
    private Integer voteCount;
    private Integer runtime;
    private Integer revenue;
    private String status;
    private String tagline;
    private ArrayList<String> genres;

    public Movie() {

    }

    public Movie(String jsonIn) {
        this.genres = new ArrayList<>();
        try {
            JSONObject reader = new JSONObject(jsonIn);
            this.id = reader.getInt("id");
            this.isAdult = reader.getBoolean("adult");
            this.backDropPath = reader.getString("backdrop_path");
            this.originalLanguage = reader.getString("original_language");
            this.originalTitle = reader.getString("original_title");
            this.overview = reader.getString("overview");
            this.releaseDate = reader.getString("release_date");
            this.posterPath = reader.getString("poster_path");
            this.popularity = reader.getDouble("popularity");
            this.title = reader.getString("title");
            this.voteAverrage = reader.getDouble("vote_average");
            this.voteCount = reader.getInt("vote_count");
            this.revenue = reader.getInt("revenue");
            this.runtime = reader.getInt("runtime");
            this.status = reader.getString("status");
            this.tagline = reader.getString("tagline");

            JSONArray genres = reader.getJSONArray("genres");
            for( int i = 0; i < genres.length(); i++ ) {
                this.genres.add( genres.getJSONObject(i).getString("name") );
            }
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

    public Boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(Boolean isAdult) {
        this.isAdult = isAdult;
    }

    public String getBackDropPath() {
        return imageBaseUrl + backDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return imageBaseUrl + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverrage() {
        return voteAverrage;
    }

    public void setVoteAverrage(Double voteAverrage) {
        this.voteAverrage = voteAverrage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }
}
