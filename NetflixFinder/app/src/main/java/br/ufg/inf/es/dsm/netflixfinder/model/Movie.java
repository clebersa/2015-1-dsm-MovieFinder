package br.ufg.inf.es.dsm.netflixfinder.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Bruno on 03/06/2015.
 * Edited by Cleber on 03/06/2015.
 */
public class Movie {
    private Integer id;
    private Boolean isAdult;
    private String backDropPath;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private String posterPath;
    private String title;
    private Double voteAverrage;
    private Integer voteCount;
    private Integer runtime;
    private ArrayList<Genre> genres = new ArrayList<Genre>();;
    private ArrayList<Video> videos = new ArrayList<Video>();;
    private ArrayList<CastPeople> showCast = new ArrayList<CastPeople>();;
    private ArrayList<String> alternativeTitles = new ArrayList<String>();;

    public Movie() {

    }

    public Movie(String jsonIn) {
        try {
            JSONObject reader = new JSONObject(jsonIn);
            this.id = reader.getInt("id");
            this.isAdult = reader.getBoolean("adult");
            this.backDropPath = reader.getString("backdrop_path");
            this.originalTitle = reader.getString("original_title");
            this.overview = reader.getString("overview");
            this.releaseDate = reader.getString("release_date");
            this.posterPath = reader.getString("poster_path");
            this.title = reader.getString("title");
            this.voteAverrage = reader.getDouble("vote_average");
            this.voteCount = reader.getInt("vote_count");
            this.runtime = reader.getInt("runtime");

            JSONArray genres = reader.getJSONArray("genres");
            Genre tmpGenre;
            for( int i = 0; i < genres.length(); i++ ) {
                tmpGenre = new Genre( genres.getJSONObject(i).toString() );
                this.genres.add( tmpGenre );
            }

            JSONArray videos = reader.getJSONObject("videos").getJSONArray("results");
            Video tmpVideo;
            for( int i = 0; i < videos.length(); i++ ) {
                tmpVideo = new Video( videos.getJSONObject(i).toString() );
                this.videos.add( tmpVideo );
            }

            JSONArray castPeoples = reader.getJSONObject("credits").getJSONArray("cast");
            CastPeople tmpCastPeople;
            for( int i = 0; i < castPeoples.length(); i++ ) {
                tmpCastPeople = new CastPeople( castPeoples.getJSONObject(i).toString() );
                this.showCast.add( tmpCastPeople );
            }

            JSONArray alternativeTitles = reader.getJSONObject("alternative_titles").
                    getJSONArray("titles");
            String tmpTitle;
            for( int i = 0; i < alternativeTitles.length(); i++ ) {
                tmpTitle = alternativeTitles.getJSONObject(i).getString("title");
                this.alternativeTitles.add( tmpTitle );
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
        return backDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
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
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
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

    public String getShowCastString() {
        StringBuilder string = new StringBuilder();
        for( int i = 0; i < this.showCast.size(); i++ ) {
            if( i > 0 ) {
                string.append( ", ");
            }
            string.append( this.showCast.get(i).toString() );
        }

        return string.toString();
    }

    public String getGenresString() {
        StringBuilder string = new StringBuilder();
        for( int i = 0; i < this.genres.size(); i++ ) {
            if( i > 0 ) {
                string.append( ", ");
            }
            string.append( this.genres.get(i).toString() );
        }

        return string.toString();
    }
}
