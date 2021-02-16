package com.example.geolocalizacion;



import android.Manifest;
import android.content.*;
import android.content.pm.PackageManager;
import android.location.*;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.*;
import androidx.core.app.ActivityCompat;


public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    double lonBest, latBest, lonGPS, latGPS, lonNetwork, latNetwork;
    TextView lonValueBest, latValueBest, lonValueGPS, latValueGPS, lonValueNetwork,
            latValueNetwork, jtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lonValueBest = (TextView) findViewById(R.id.xlonValueBest);
        latValueBest = (TextView) findViewById(R.id.xlatValueBest);
        lonValueGPS = (TextView) findViewById(R.id.xlonValueGPS);
        latValueGPS = (TextView) findViewById(R.id.xlatValueGPS);
        lonValueNetwork = (TextView) findViewById(R.id.xlonValueNetwork);
        latValueNetwork = (TextView) findViewById(R.id.xlatValueNetwork);
        jtv = (TextView) findViewById(R.id.xtv);
    }
    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }
    private void showAlert() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        try {
            dialog.setTitle("Habilitar Localizacion")
                    .setMessage("Ubicación desactivada. Activar la ubicación en esta app")
                    .setPositiveButton("Configurar ubicación", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt)
                                {
                                    Intent myIntent = new
                                            Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivity(myIntent);
                                }
                            })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt)
                        { }
                    });
            dialog.show();
        }
        catch (Exception e)
        {
            jtv.setText("Error: " + String.valueOf(e));
        }

    }
    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
    public void toggleGPSUpdates(View view) {

        try {
            if (!checkLocation())
                return;
            Button button = (Button) view;
            if (button.getText().equals(getResources().getString(R.string.pause))) {
                locationManager.removeUpdates(locationListenerGPS);
                button.setText(R.string.resume);
            } else {
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED) {
                }
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 2 * 20 * 1000, 10, locationListenerGPS);
                button.setText(R.string.pause);
            }
        }
        catch (Exception e)
        {
            jtv.append("\nError: " + String.valueOf(e));
        }
    }
    public void toggleBestUpdates(View view) {
        if (!checkLocation())
            return;
        Button button = (Button) view;
        if (button.getText().equals(getResources().getString(R.string.pause))) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
            }
            locationManager.removeUpdates(locationListenerBest);
            button.setText(R.string.resume);
        } else {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            String provider = locationManager.getBestProvider(criteria, true);
            if (provider != null) {
                locationManager.requestLocationUpdates(provider, 2 * 20 * 1000, 10,
                        locationListenerBest);
                button.setText(R.string.pause);
                Toast.makeText(this, "Mejor Proveedor: " + provider,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    public void toggleNetworkUpdates(View view) {

        try {
            if (!checkLocation())
                return;
            Button button = (Button) view;
            if (button.getText().equals(getResources().getString(R.string.pause))) {
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED) {
                }
                locationManager.removeUpdates(locationListenerNetwork);
                button.setText(R.string.resume);
            }
            else {
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, 20 * 1000, 10,
                        locationListenerNetwork);
                Toast.makeText(this, "Proveedor de Red inicio ejecucion",
                        Toast.LENGTH_LONG).show();
                button.setText(R.string.pause);
            }
        }
        catch (Exception e)
        {
            jtv.append("\nError: " + String.valueOf(e));
        }
    }
    private final LocationListener locationListenerBest = new LocationListener() {
        public void onLocationChanged(Location location) {
            lonBest = location.getLongitude();
            latBest = location.getLatitude();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    lonValueBest.setText(lonBest + "");
                    latValueBest.setText(latBest + "");
                    Toast.makeText(MainActivity.this, "Actualiza Mejor Proveedor",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) { }
        @Override
        public void onProviderEnabled(String s) { }
        @Override
        public void onProviderDisabled(String s) { }
    };
    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            lonNetwork = location.getLongitude();
            latNetwork = location.getLatitude();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    lonValueNetwork.setText(lonNetwork + "");
                    latValueNetwork.setText(latNetwork + "");
                    Toast.makeText(MainActivity.this, "Actualiza Proveedor de Red",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) { }
        @Override
        public void onProviderEnabled(String s) { }
        @Override
        public void onProviderDisabled(String s) { }
    };
    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(Location location) {
            lonGPS = location.getLongitude();
            latGPS = location.getLatitude();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    lonValueGPS.setText(lonGPS + "");
                    latValueGPS.setText(latGPS + "");
                    Toast.makeText(MainActivity.this, "Actualiza Proveedor de GPS",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) { }
        @Override
        public void onProviderEnabled(String s) { }
        @Override
        public void onProviderDisabled(String s) { }
    };

}