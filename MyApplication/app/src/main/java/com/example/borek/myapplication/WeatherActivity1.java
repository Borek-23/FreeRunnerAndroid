package com.example.borek.myapplication;

import android.graphics.Typeface;
import android.location.LocationProvider;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.borek.myapplication.Function.Function;

import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


public class WeatherActivity1 extends AppCompatActivity {

    Button navButtonWeather;
    TextView runCondition, runningCondition, weatherIcon, updatedField, humidity, windSpeed, currentTemperature, selectCity, currentCity, detailsField, visibility;
    static TextView locationText;
    ProgressBar loader;
    Typeface weatherFont;
    String city = "Birmingham, UK";
    String openWeatherApi = "4cf171611b393ec1f32e753bb67b2a87";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather1);

        // Initiate elements
        loader = (ProgressBar) findViewById(R.id.loader);
        navButtonWeather = (Button) findViewById(R.id.navButtonWeather);
        runCondition = (TextView) findViewById(R.id.runCondition);
        runningCondition = (TextView) findViewById(R.id.runningCondition);
        weatherIcon = (TextView) findViewById(R.id.weatherIcon);
        updatedField = (TextView) findViewById(R.id.updatedField);
        humidity = (TextView) findViewById(R.id.humidity);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        currentTemperature = (TextView) findViewById(R.id.currentTemperature);
        selectCity = (TextView) findViewById(R.id.selectCity);
        currentCity = (TextView) findViewById(R.id.currentCity);
        detailsField = (TextView) findViewById(R.id.detailsField);
        visibility = (TextView) findViewById(R.id.visibility);

        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weathericons-regular-webfont.ttf");
        weatherIcon.setTypeface(weatherFont);

        locationText = (TextView) findViewById(R.id.locationText);

        taskLoadUp(city);

        // Request location permission from user
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }

//        new LocationProvider().locationStringFromLocation(new LocationProvider().getLocation());


        selectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(WeatherActivity1.this);
                alertDialog.setTitle("ChangeCity");
                final EditText input = new EditText(WeatherActivity1.this);
                input.setText(city);
                ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setPositiveButton("Change",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                city =  input.getText().toString();
                                taskLoadUp(city);
                            }
                        });
                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.cancel();
                            }
                        });
                alertDialog.show();

                locationText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new LocationProvider().getLocation();
                    }
                });
            }
        });
    }

    public void taskLoadUp(String query) {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            DownloadWeather task = new DownloadWeather();
            task.execute(query);
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    class DownloadWeather extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String xml = Function.executeGet("http://api.openweathermap.org/data/2.5/weather?q=" + strings[0]
                    + "&units=metric&appid=" + openWeatherApi);
            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {
            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    JSONObject wind = json.getJSONObject("wind");
                    DateFormat df = DateFormat.getDateInstance();

                    currentCity.setText(json.getString("name").toUpperCase(Locale.UK) + ", " + json.getJSONObject("sys").getString("country"));
                    detailsField.setText(details.getString("description").toUpperCase(Locale.UK));
                    currentTemperature.setText(String.format("%.1f", main.getDouble("temp")) + "°C");
                    windSpeed.setText("Wind Speed: " + wind.getString("speed") + "m/s");
                    humidity.setText("Humidity: " + main.getString("humidity") + "%");
                    updatedField.setText(df.format(new Date(json.getLong("dt") * 1000)));
                    weatherIcon.setText(Html.fromHtml(Function.setWeatherIcons(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));
                    loader.setVisibility(View.GONE);

                    if (main.getDouble("temp") >= 5 && json.getInt("visibility") >= 5000) {
                        runningCondition.setText("Awesome!");
                    } else if ((main.getDouble("temp") >= 0 && main.getDouble("temp") <= 4) || (json.getInt("visibility") >= 0 && json.getInt("visibility") <= 2500)) {
                        runningCondition.setText("Good");
                    } else {
                        runningCondition.setText("Bad -> proceed with caution");
                    }

                    if (json.getInt("visibility") >= 5000) {
                        visibility.setText("Visibility: " + "Great");
                    } else if ((json.getInt("visibility") >= 0) && (json.getInt("visibility") <= 1500)) {
                        visibility.setText("Visibility: " + "Decent");
                    } else {
                        visibility.setText("Visibility: " + "Bad");
                    }
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Error, Check City Please", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class LocationProvider extends AppCompatActivity implements LocationListener {
        public LocationManager locationManager;

        public void getLocation() {
            try {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
            }
            catch(SecurityException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onLocationChanged(Location location) {
            WeatherActivity1.locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                WeatherActivity1.locationText.setText(WeatherActivity1.locationText.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
                        addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
            }catch(Exception e)
            {

            }
        }

        public String locationStringFromLocation(final Location location) {
            return Location.convert(location.getLatitude(), Location.FORMAT_DEGREES) + " " + Location.convert(location.getLongitude(), Location.FORMAT_DEGREES);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {
            Toast.makeText(WeatherActivity1.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
        }
    }
}












