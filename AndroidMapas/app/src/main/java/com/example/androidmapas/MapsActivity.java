package com.example.androidmapas;

import androidx.fragment.app.FragmentActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mapa;
    private LatLng myLatLng;
    private Button btnSatelite = null;
    private Button btncentrar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        btnSatelite = (Button)findViewById(R.id.BtnSatelite);
        btncentrar = (Button)findViewById(R.id.BtnCentrar);

        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.raw.point);

        LatLng southWest = new LatLng(37.38, -5.99);
        LatLng northEast = new LatLng(37.58, -5.79);
        LatLngBounds latLngBounds = new LatLngBounds(southWest, northEast);
        GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions();
        groundOverlayOptions.positionFromBounds(latLngBounds);
        groundOverlayOptions.image(bitmapDescriptor);
        groundOverlayOptions.transparency(0.5f);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnSatelite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(mMap.getMapType() == 1)
                    mMap.setMapType(2);
                else
                    mMap.setMapType(1);
            }
        });
        btncentrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                try {
                    LatLng zocalo = new LatLng(19.4326018, -99.1332049);
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(zocalo));
                    mMap.addGroundOverlay(groundOverlayOptions);
                }
                catch (Exception e)
                {
                    
                }

            }
        });

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng escom = new LatLng(19.50448, -99.14660);
        mMap.addMarker(new MarkerOptions().position(escom).title("Marker in ESCOM"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(escom));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                myLatLng = latLng;
                //String msg = "Hola Mundo";
                String msg = "Lat: " + myLatLng.latitude + "-" + "Lon: "+ myLatLng.longitude;
                Toast toast = Toast.makeText(MapsActivity.this, msg, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}