package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject object = new JSONObject(json);
            String mainName = getMainName(object);
            String description = getDescription(object);
            String placeOfOrigin = getPlaceOfOrigin(object);
            List<String> alsoKnownAs = getAlsoKnownAs(object);
            List<String> ingredients = getIngredients(object);
            String image = getImage(object);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getImage(JSONObject object) throws JSONException {
        return object.getString("image");
    }

    private static String getMainName(JSONObject object) throws JSONException {
        return object.getJSONObject("name").getString("mainName");
    }

    private static String getDescription(JSONObject object) throws JSONException {
        return object.getString("description");

    }

    private static String getPlaceOfOrigin(JSONObject object) throws JSONException {
        return object.getString("placeOfOrigin");
    }

    private static List<String> getAlsoKnownAs(JSONObject object) throws JSONException {
        List<String> alsoKnownAs = new ArrayList<>();
        JSONArray array = object.getJSONObject("name").getJSONArray("alsoKnownAs");
        for (int i = 0; i < array.length(); i++) {
            alsoKnownAs.add(array.getString(i));
        }
        return alsoKnownAs;
    }

    private static List<String> getIngredients(JSONObject object) throws JSONException {
        List<String> ingredients = new ArrayList<>();
        JSONArray array = object.getJSONArray("ingredients");
        for (int i = 0; i < array.length(); i++) {
            ingredients.add(array.getString(i));
        }
        return ingredients;
    }

}
