package com.test.nafl.test;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class  MainActivity extends AppCompatActivity implements LocationListener {
    final private int REQUEST_CODE_ASK_SINGLE_PERMISSION = 123; //permission id
    String[] permission = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};   //single permission required
    private LocationManager locationManager;
    private String provider = LocationManager.GPS_PROVIDER; //Default provider
    private TextView locationTextView;
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 1 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 600;


    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationTextView = (TextView) findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button2) ;
        //get the location from the all the available providers in the device.
        Location location = getLastKnownLocation();
        if (location != null) {
            Log.v("usinglocationmanager", provider + " has been selected.");
            onLocationChanged(location);
        } else {
            locationTextView.setText("No provider found, please enable either GPS or Network");
            Log.v("usinglocationmanager", provider + " not available");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent video = new Intent(MainActivity.this, VideoViewNow.class);
                startActivity(video);
            }
        });

    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();

        if (PermissionUtils.checkAndRequestPermission(MainActivity.this, REQUEST_CODE_ASK_SINGLE_PERMISSION, "You need to grant access to Fine Location", permission[0]))
            locationManager.requestLocationUpdates(provider, MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        if (PermissionUtils.checkAndRequestPermission(MainActivity.this, REQUEST_CODE_ASK_SINGLE_PERMISSION, "You need to grant access to Fine Location", permission[0]))
            locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {

        String msg = "New Latitude: " + location.getLatitude()
                + "New Longitude: " + location.getLongitude();
        Log.v("usinglocationmanager", msg);
        locationTextView.setText(msg);
    }

    @Override
    public void onProviderDisabled(String provider) {
        showAlert();
        Log.v("usinglocationmanager", "No location provider found");
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    private Location getLastKnownLocation() {
        // Get the location manager
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        //Get the list of the names of location providers which are currently enabled.
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String mProvider : providers) {
            if (PermissionUtils.checkAndRequestPermission(MainActivity.this, REQUEST_CODE_ASK_SINGLE_PERMISSION, "You need to grant access to Fine Location", permission[0])) {
                //Returns a Location indicating the data from the last known location fix obtained from the given provider.
                Location l = locationManager.getLastKnownLocation(mProvider);
                if (l == null) {
                    continue;
                }

                //Check for better accuracy compared with all the available providers.
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location
                    provider = mProvider;
                    bestLocation = l;
                }
            }
        }
        //returning the best matched location
        return bestLocation;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_SINGLE_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }
}