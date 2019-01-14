package com.example.borek.myapplication.Function;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Created by borek on 05/01/19.
 */
// This is a helper class to aid with handling URL and icon type to appear correctly
public class Function {

    // This method checks if the network connection is available using ConnectivityManager class
    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    // This method creates connection, buffered reader and input stream to handle API URL
    public static String executeGet(String targetURL) {
        URL url;
        HttpURLConnection connection = null;
        try {
            // Now create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("content-type", "application/json; charset=utf-8");
            connection.setRequestProperty("content-language", "en-UK");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(false);

            // Create an input stream to obtain data
            InputStream inputStream;
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                inputStream = connection.getErrorStream();
            } else {
                inputStream = connection.getInputStream();
            }
            // Create a buffered reader object to read the data from input stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            // When all reading is done, close the buffered reader to flush the data, then disconnect the connection
            bufferedReader.close();
            return response.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    // This method handles the icons and and calculates sunrise and sunset
    public static String setWeatherIcons(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon = "";
        if (actualId == 800) {
            long currentTime = new Date().getTime();
            if (currentTime >= sunrise && currentTime < sunset) {
                icon = "&#xf00d;";
            } else {
                icon = "&#xf02e;";
            }
        } else {
            switch (id) {
                case 2 : icon = "&#xf01e;";
                    break;
                case 3 : icon = "&#xf01c;";
                    break;
                case 7 : icon = "&#xf014;";
                    break;
                case 8 : icon = "&#xf013;";
                    break;
                case 6 : icon = "&#xf01b;";
                    break;
                case 5 : icon = "&#xf019;";
                    break;
            }
        }
        return icon;
    }
}
