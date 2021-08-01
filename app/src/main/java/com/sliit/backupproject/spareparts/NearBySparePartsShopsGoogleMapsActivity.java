package com.sliit.backupproject.spareparts;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sliit.backupproject.R;

import java.util.ArrayList;

public class NearBySparePartsShopsGoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    ArrayList<LatLng> latLngs = new ArrayList<LatLng>();

    LatLng dakshinaMotors = new LatLng(6.185639, 80.105074);
    LatLng chahurangaMotors = new LatLng(6.186211, 80.104618);
    LatLng dileepaHardware = new LatLng(6.1897859942116735, 80.0984899699444);
    LatLng sushanMotors = new LatLng(6.148218882191242, 80.13912346921445);
    LatLng suyumiLanka = new LatLng(6.255964395543169, 80.06585197979837);
    LatLng dnEnterprice = new LatLng(6.249309427114979, 80.06619530255035);
    LatLng mallikaMotors = new LatLng(6.2726867628748835, 80.03718453000494);
    LatLng mmwMotors = new LatLng(6.1455469752984, 80.10035586213846);
    LatLng thilakMotors = new LatLng(6.149643156228272, 80.15271258181758);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_spare_parts_shops_google_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        latLngs.add(dakshinaMotors);
        latLngs.add(chahurangaMotors);
        latLngs.add(dileepaHardware);
        latLngs.add(sushanMotors);
        latLngs.add(suyumiLanka);
        latLngs.add(dnEnterprice);
        latLngs.add(mallikaMotors);
        latLngs.add(mmwMotors);
        latLngs.add(thilakMotors);

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

        Toast toast = Toast.makeText(getApplicationContext(),
                "Launching GoogleMaps",
                Toast.LENGTH_LONG);
        toast.show();

        // Add a marker into locations and move the camera
        for (int i = 0; i < latLngs.size(); i++) {
            mMap.addMarker(new MarkerOptions().position(latLngs.get(i)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngs.get(i), 11.0f));
        }

    }
}