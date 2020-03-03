package com.pl.solarsystem.model;

import android.content.Context;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SolarObject implements Serializable {
    public SolarObject(String name) {
        this.name = name;
    }

    private String name;
    private String video;
    private String text;
    private String image;
    private List<SolarObject> moons;

    Context context;

    public SolarObject() {
    }


    public void setVideo(String video) {
        this.video = video;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setMoons(List<SolarObject> moons) {
        this.moons = moons;
    }

    public String getVideo() {
        return video;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public List<SolarObject> getMoons() {
        return moons;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public SolarObject(JSONObject jsonObject) throws JSONException {
        this.name = jsonObject.getString("name");
        this.image = String.format("images/%s.jpg", name.toLowerCase());
        this.text = String.format("texts/%s.txt", name.toLowerCase());
        this.video = jsonObject.optString("video");
        JSONArray moons = jsonObject.optJSONArray("moons");
        if (moons != null)
            this.moons = getSolarObjectsFromJsonArray(moons);
    }


    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public static List<SolarObject> loadArrayFromJSON(Context context, String type) {
        try {
            String json = loadStringFromAssets(context, "solar.json");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(type);


            return getSolarObjectsFromJsonArray(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList<SolarObject>();
    }

    private static List<SolarObject> getSolarObjectsFromJsonArray(JSONArray jsonArray) throws JSONException {
        List<SolarObject> solarObject = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            SolarObject so = new SolarObject(jsonArray.getJSONObject(i));
            solarObject.add(so);
        }
        return solarObject;
    }

    public static String loadStringFromAssets(Context context, String data) throws IOException {
        InputStream inputStream = context.getAssets().open(data);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, "UTF-8");
    }

    public boolean hasMoons() {
        return moons != null && moons.size() > 0;
    }

    public String getImagePath() {
        return String.format("file:///android_asset/%s" , getImage());
    }

}
