package br.ufg.inf.es.dsm.movieFinder.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bruno on 6/3/15.
 */
public class CastPeople {
    private Integer id;
    private Integer castId;
    private String character;
    private String name;
    private String profileImagePath;

    public CastPeople( String jsonIn ) {
        try {
            JSONObject reader = new JSONObject(jsonIn);
            this.id = reader.getInt("id");
            this.castId = reader.getInt("cast_id");
            this.character = reader.getString("character");
            this.name = reader.getString("name");
            this.profileImagePath = reader.getString("profile_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCastId() {
        return castId;
    }

    public void setCastId(Integer castId) {
        this.castId = castId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }
}
