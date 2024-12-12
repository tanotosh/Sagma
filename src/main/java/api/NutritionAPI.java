package api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class NutritionAPI {
    private static final String API_URL = "https://api.api-ninjas.com/v1/nutrition";
    private static final String API_KEY = "cHp4ZabWUnYryfx7A5ErwQ==IjIskOoZyBoVQWpt"; // Replace with your actual API key

    public static String getNutritionInfo(String query) {
        try {
            String encodedQuery = java.net.URLEncoder.encode(query, "UTF-8");
            URL url = new URL(API_URL + "?query=" + encodedQuery);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", API_KEY);
            connection.setRequestProperty("accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String response = new Scanner(connection.getInputStream()).useDelimiter("\\A").next();
                return parseNutritionData(response);
            } else {
                return "Error: Unable to fetch nutrition data";
            }
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }

    private static String parseNutritionData(String jsonResponse) {
        System.out.println(jsonResponse);
        JSONArray jsonArray = new JSONArray(jsonResponse);
        if (jsonArray.length() > 0) {
            JSONObject nutritionData = jsonArray.getJSONObject(0);
            double fat = nutritionData.getDouble("fat_total_g");
            double cards = nutritionData.getDouble("carbohydrates_total_g");
            int fiber = nutritionData.getInt("fiber_g");

            return String.format("Total fat: %.1fg,\nCarbs: %.1fg,\nFiber: %dg", fat, cards, fiber);
        } else {
            return "No nutrition data found";
        }
    }
}
