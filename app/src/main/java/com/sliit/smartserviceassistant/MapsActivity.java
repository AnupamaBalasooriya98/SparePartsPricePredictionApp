package com.sliit.smartserviceassistant;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.sliit.smartserviceassistant.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Spinner spin_type;
    private Button btn_find;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat = 0, currentLong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);


        // Assign variables
        spin_type = findViewById(R.id.spin_type);
        btn_find = findViewById(R.id.btn_find);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        // Initialize array of place type
        String[] placeTypeList = {"atm", "bank", "hospital", "movie_theater", "restaurant"};

        // Initialize array of place name
        String[] placeNameList = {"ATM", "Bank", "Hospital", "Movie Theater", "Restaurant"};

        // Set adapter on spinner
        spin_type.setAdapter(new ArrayAdapter<>(MapsActivity.this,
                android.R.layout.simple_spinner_dropdown_item, placeNameList));

        // Initialize fused location provider client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Check permission
        if (ActivityCompat.checkSelfPermission(MapsActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // When permission granted
            // Call method
            getCurrentLocation();
        } else {
            // When permission denied
            // Request permission
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected position of spinner
                int i = spin_type.getSelectedItemPosition();
                // Initialize URL
                String url = "https://maps.googleapis.com/maps/api/places/nearbysearch/json" + // url
                        "?location=" + currentLat + "," + currentLong + // Location latitude and longitude
                        "&radius=5000" + // Nearby radius
                        "&types=" + placeTypeList[i] + // Place type
                        "&sensor=true" + // Sensor
                        "&key=" + getResources().getString(R.string.google_map_key); // Google map key

                // Execute place task method to download json data
                new MapsActivity.PlaceTask().execute(url);
            }
        });
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // Initialize task location
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // When success
                if (location != null) {
                    // When location in not null
                    // get current latitude
                    currentLat = location.getLatitude();
                    // Get current longitude
                    currentLong = location.getLongitude();

                    // Sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            // When map is ready
                            map = googleMap;
                            // Zoom current location on map
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(currentLat, currentLong), 10));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // When permission granted
                // Call method
                getCurrentLocation();
            }
        }
    }

    private class PlaceTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            String data = null;
            try {
                // Initialize data
                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            // Execute parser task
            new ParserTask().execute(s);
        }
    }

    private String downloadUrl(String string) throws IOException {
        // Initialize url
        URL url = new URL(string);
        // Initialize connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Connect connection
        connection.connect();
        // Initialize input stream
        InputStream stream = connection.getInputStream();
        // Initialize buffer reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        // Initialize string builder
        StringBuilder builder = new StringBuilder();
        // Initialize string variable
        String line = "";
        // use while loop
        while ((line = reader.readLine()) != null) {
            // Append line
            builder.append(line);
        }
        // Get append data
        String data = builder.toString();
        // Close reader
        reader.close();
        // return data
        return data;
    }

    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {
        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            // Create Json parser class
            JsonParser jsonParser = new JsonParser();
            // Initialize hash map list
            List<HashMap<String, String>> mapList = null;
            JSONObject object = null;
            try {
                // Initialize Json object
                object = new JSONObject(strings[0]);
                // Parse json object
                mapList = jsonParser.parseResult(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Return map list
            return mapList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            // Clear map
            map.clear();
            // Use for loop
            for (int i = 0; i < hashMaps.size(); i++) {
                // Initialize hash map
                HashMap<String,String> hashMapList = hashMaps.get(i);
                // Get latitude
                double lat = Double.parseDouble(hashMapList.get("lat"));
                // Get longitude
                double lng = Double.parseDouble(hashMapList.get("lng"));
                // Get name
                String name = hashMapList.get("name");

                // Concat latitude and longitude
                LatLng latLng = new LatLng(lat, lng);
                // Initialize marker options
                MarkerOptions options = new MarkerOptions();
                // Set position
                options.position(latLng);
                // Set title
                options.title(name);
                // Add marker on map
                map.addMarker(options);
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}