package com.eure.traveling;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShotsParser {

    public static final String TAG = ShotsParser.class.getSimpleName();

    public static final String KEY_SHOTS                    = "shots";
    public static final String KEY_SHOTS_ID                 = "id";
    public static final String KEY_SHOTS_TITLE              = "title";
    public static final String KEY_SHOTS_IMAGE_URL          = "image_url";
    public static final String KEY_SHOTS_IMAGE_TEASER_URL   = "image_teaser_url";
    public static final String KEY_SHOTS_LIKES_COUNT        = "likes_count";

    public static final String KEY_PLAYER       = "player";
    public static final String KEY_PLAYER_NAME  = "name";

    public static List<Shots> parseShotsList(JSONObject jsonObject, String category) {
        return parseShotsList(jsonObject.optJSONArray(KEY_SHOTS), category);
    }

    public static List<Shots> parseShotsList(JSONArray jsonArray, String category) {
        List<Shots> shotsList = new ArrayList<Shots>();

        for (int i = 0; i < jsonArray.length(); i++) {
            Shots shots = ShotsParser.parseShots(jsonArray.optJSONObject(i), category);
            shotsList.add(shots);
        }

        return shotsList;
    }

    public static Shots parseShots(JSONObject jsonObject, String category) {
        Shots shots = null;

        for (int i = 0; i < jsonObject.length(); i++) {
            shots = Shots.getShots(jsonObject.optInt(KEY_SHOTS_ID));

            if (shots == null) {
                shots = new Shots();
            }

            shots.setCategory(category);
            shots.setShotsId(jsonObject.optInt(KEY_SHOTS_ID));
            shots.setTitle(jsonObject.optString(KEY_SHOTS_TITLE));
            shots.setImageUrl(jsonObject.optString(KEY_SHOTS_IMAGE_URL));
            shots.setImageTeaserUrl(jsonObject.optString(KEY_SHOTS_IMAGE_TEASER_URL));
            shots.setLikesCount(jsonObject.optInt(KEY_SHOTS_LIKES_COUNT));

            shots.setPlayerName(parsePlayer(jsonObject).optString(KEY_PLAYER_NAME));
        }

        shots.save();

        return shots;
    }

    public static JSONObject parsePlayer(JSONObject jsonObject) {
        return jsonObject.optJSONObject(KEY_PLAYER);
    }

}
