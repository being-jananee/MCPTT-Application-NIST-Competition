package com.example.application.Maps;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.application.DatabaseUtils;
import com.example.application.Domain.ActionItem.ActionItemDTO;
import com.example.application.Domain.UserData;
import com.example.application.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FloatingActionButton fab;
    private FusedLocationProviderClient fusedClient;
    private UserData user;
    private LocationReceiver lReceiver;
    private ActionItemDTO itemToDisplay;
    private boolean running = false;
    private boolean realtimeActive = false;
    private DatabaseUtils db = new DatabaseUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!realtimeActive) {
                    Toast.makeText(MapsActivity.this, "Real-time location services turned on", Toast.LENGTH_SHORT).show();
                    fab.setImageResource(R.drawable.real_time_on);
                    sendBroadcast(new Intent("START"));
                    realtimeActive = true;
                } else {
                    Toast.makeText(MapsActivity.this, "Real-time location services turned off", Toast.LENGTH_SHORT).show();
                    fab.setImageResource(R.drawable.real_time_off);
                    sendBroadcast(new Intent("STOP"));
                    realtimeActive = false;
                }
            }
        });
        user = getIntent().getParcelableExtra("currentUser");
        itemToDisplay = (ActionItemDTO) getIntent().getSerializableExtra("item");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedClient = new FusedLocationProviderClient(this);
    }

    @Override
    protected void onStart() {
        lReceiver = new LocationReceiver();
        IntentFilter filter = new IntentFilter("locations");
        filter.addAction("fromNotif");
        registerReceiver(lReceiver, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(lReceiver);
        itemToDisplay = null;
        super.onStop();
    }



    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10) {
            // Checking whether user granted the permission or not.
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    finish();
            } else {
                onMapReady(mMap);
            }
        }
    }


    public void updateLocations(HashMap<String, LocationItem> userLocations) {
        mMap.clear();
        for(String s : userLocations.keySet()) {
            LatLng currLoc = new LatLng(userLocations.get(s).latitude, userLocations.get(s).longitude);
            UserData user = db.getUserById(s);
            Log.d("HOBBY", "updateLocations: "+user.toString());
            if(this.user.getMcpttID().equals(user.getMcpttID())) {
                mMap.addMarker(new MarkerOptions().position(currLoc).icon(bitmapDescriptorFromVector(MapsActivity.this, R.drawable.ic_person_pin_circle_black_24dp)).title("You"));
            } else {
                mMap.addMarker(new MarkerOptions().position(currLoc).icon(bitmapDescriptorFromVector(MapsActivity.this, R.drawable.ic_person_pin_circle_black_24dp)).title(this.user.getDisplayName()));
            }
        }
        if(itemToDisplay != null) {
            LatLng currLoc = new LatLng(itemToDisplay.latitude, itemToDisplay.longitude);
            mMap.addMarker(new MarkerOptions().position(currLoc).title(itemToDisplay.tag.getName()));
        }
    }

    public class LocationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (("locations").equals(intent.getAction())) {
                HashMap<String, LocationItem> userLocations = (HashMap) intent.getSerializableExtra("items");
                updateLocations(userLocations);
            } else if (("fromNotif").equals(intent.getAction())) {
                if(realtimeActive) {
                    fab.performClick();
                }
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
        } else {

            if(itemToDisplay != null) {
                String message = "";
                message += itemToDisplay.tag.getName();
                mMap.addMarker(new MarkerOptions().position(new LatLng(itemToDisplay.latitude, itemToDisplay.longitude)).title(message));
                mMap.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(new LatLng(itemToDisplay.latitude, itemToDisplay.longitude), 14));
            } else {
                fusedClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 14));
                        }
                    }
                });
            }
            Intent i = new Intent(MapsActivity.this, LocationService.class);
            i.putExtra("currentUser", user);
            i.putExtra("checked", realtimeActive);
            startService(i);
            running = true;
        }
    }



    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(running) {
            stopService(new Intent(MapsActivity.this, LocationService.class));
        }
    }
}
