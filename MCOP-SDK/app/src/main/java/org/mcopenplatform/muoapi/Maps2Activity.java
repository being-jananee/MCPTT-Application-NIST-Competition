package org.mcopenplatform.muoapi;

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

import android.widget.CompoundButton;

import android.widget.RadioGroup;

import android.widget.Switch;



import androidx.annotation.NonNull;

import androidx.core.app.ActivityCompat;

import androidx.core.content.ContextCompat;

import androidx.fragment.app.FragmentActivity;



import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.LocationSource;

import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptor;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.Marker;

import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.tasks.OnSuccessListener;


import org.mcopenplatform.muoapi.mcopsdk.ActionItem;
import org.mcopenplatform.muoapi.mcopsdk.ActionTag;
import org.mcopenplatform.muoapi.mcopsdk.LocationItem;
import org.mcopenplatform.muoapi.mcopsdk.LocationService;

import java.util.HashMap;



public class Maps2Activity extends FragmentActivity implements OnMapReadyCallback, Switch.OnCheckedChangeListener {



    private GoogleMap mMap;

    private FusedLocationProviderClient fusedClient;

    private String username;

    private LocationReceiver lReceiver;

    private Switch locationSwitch;

    private ActionItem.ActionItemDTO itemToDisplay;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        locationSwitch = findViewById(R.id.locationSwitch);

        locationSwitch.setOnCheckedChangeListener(this);

        username = getIntent().getStringExtra("username");

        if(getIntent().hasExtra("item"))
            itemToDisplay = (ActionItem.ActionItemDTO) getIntent().getSerializableExtra("item");

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

            if(username.equals(s)) {

                mMap.addMarker(new MarkerOptions().position(currLoc).icon(bitmapDescriptorFromVector(Maps2Activity.this, R.drawable.ic_person_pin_circle_black_24dp)).title("You"));

            } else {

                mMap.addMarker(new MarkerOptions().position(currLoc).icon(bitmapDescriptorFromVector(Maps2Activity.this, R.drawable.ic_person_pin_circle_black_24dp)).title(username));

            }

        }

        if(itemToDisplay != null) {

            LatLng currLoc = new LatLng(itemToDisplay.latitude, itemToDisplay.longitude);

            mMap.addMarker(new MarkerOptions().position(currLoc).title(itemToDisplay.tag.getName()));

        }

    }



    @Override

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked) {

            sendBroadcast(new Intent("START"));

        } else {

            sendBroadcast(new Intent("STOP"));

        }

    }



    public class LocationReceiver extends BroadcastReceiver {



        @Override

        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals("locations")) {

                HashMap<String, LocationItem> userLocations = (HashMap) intent.getSerializableExtra("items");

                updateLocations(userLocations);

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

            Intent i = new Intent(Maps2Activity.this, LocationService.class);

            i.putExtra("username", username);

            i.putExtra("checked", locationSwitch.isChecked());

            startService(i);

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

}