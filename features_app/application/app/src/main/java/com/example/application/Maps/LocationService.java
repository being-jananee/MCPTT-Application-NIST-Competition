package com.example.application.Maps;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.application.Domain.ActionItem;
import com.example.application.Domain.UserData;
import com.example.application.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LocationService extends Service {

    private static final String TAG = LocationService.class.getSimpleName();
    private UserData username;
    private static boolean running;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("locations");
    private HashMap<String, LocationItem> allItems = new HashMap<>();
    private LocationServiceReceiver lServiceReciever = new LocationServiceReceiver();
    private int permission;
    private NotificationManager notificationManager;
    private ChildEventListener listener;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId) {
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        username = intent.getParcelableExtra("username");
        running = intent.getBooleanExtra("checked", false);
        permission = ContextCompat.checkSelfPermission(LocationService.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        IntentFilter filter = new IntentFilter("STOP");
        filter.addAction("START");
        filter.addAction("displayItem");
        registerReceiver(lServiceReciever, filter);
        Log.d(TAG, "onStartCommand: " + username);
        return flags;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        listener = getOtherUserLocations();
        ref.addChildEventListener(listener);
        requestLocationUpdates(permission);
    }

//Create the persistent notification//

    private void buildNotification(int permission) {
//If the app currently has access to the location permission...//
        if (permission == PackageManager.PERMISSION_GRANTED) {
            String stop = "stop";
            registerReceiver(stopReceiver, new IntentFilter(stop));
            PendingIntent broadcastIntent = PendingIntent.getBroadcast(
                    this, 0, new Intent(stop), PendingIntent.FLAG_UPDATE_CURRENT);

// Create the persistent notification//
            String channelId = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?
                    createNotificationChannel("my_service", "My Background Service") : "";
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                    .setContentTitle(getString(R.string.app_name))
                    .setSubText("Currently Tracking Location")
                    .setContentText("Tap here to turn off Location Tracking")

//Make this notification ongoing so it can’t be dismissed by the user//

                    .setContentIntent(broadcastIntent)
                    .setSmallIcon(R.drawable.ic_location_searching_black_24dp);
            notificationManager.notify(1, builder.build());
        }
    }

    protected BroadcastReceiver stopReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

//Unregister the BroadcastReceiver when the notification is tapped//

            unregisterReceiver(stopReceiver);

//Stop the Service//
            running = false;
            sendBroadcast(new Intent("fromNotif"));
            notificationManager.cancel(1);
        }
    };

//Initiate the request to track the device's location//

    private void requestLocationUpdates(int permission) throws SecurityException {
        LocationRequest request = new LocationRequest();
        final LocationItem lastSentLoc = new LocationItem();
//Specify how often your app should request the device’s location//
        request.setInterval(1000);
//Get the most accurate location data available//
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        final FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
//If the app currently has access to the location permission...//
        if (permission == PackageManager.PERMISSION_GRANTED) {
//...then request location updates//
            client.requestLocationUpdates(request, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if(!running) {
                        client.removeLocationUpdates(this);
                        ref.child(username.getMcpttID()).removeValue();
                        return;
                    }
//Get a reference to the database, so your app can perform read and write operations//
                    Location location = locationResult.getLastLocation();
                    if (location != null) {
//Save the location data to the database//
                        LocationItem currentLocation = new LocationItem(username.getMcpttID(), location.getLatitude(), location.getLongitude());
                        if(lastSentLoc.latitude == null || distanceChangedEnough(lastSentLoc, currentLocation)) {
                            Log.d(TAG, "onLocationResult: "+ref.child(username.getMcpttID()).toString());
                            Log.d(TAG, "onLocationResult: "+currentLocation.toString());
                            ref.child(username.getMcpttID()).setValue(new LatLng(currentLocation.latitude, currentLocation.longitude));
                            lastSentLoc.latitude = currentLocation.latitude;
                            lastSentLoc.longitude = currentLocation.longitude;
                        }
                    }
                }
            }, null);
        } else {
            running = false;
        }
    }

    public boolean distanceChangedEnough(LocationItem loc1, LocationItem loc2) {
        //distance changed is less than 1.1 meters, don't update.
        return !(Math.abs(loc1.latitude - loc2.latitude) < 0.00001) && !(Math.abs(loc1.latitude - loc2.latitude) < 0.00001);
    }

    private ChildEventListener getOtherUserLocations() {
        return new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot userLocation, @Nullable String s) {
                Log.d("TAG", "onChildChanged: CHILD HAS BEEN ADDED");
                LocationItem item = new LocationItem(userLocation.getKey(), userLocation.child("latitude").getValue(Double.class), userLocation.child("longitude").getValue(Double.class));
                allItems.put(item.getUser(), item);
                sendBroadcast(new Intent("locations").putExtra("items", allItems));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot userLocation, @Nullable String s) {
                LocationItem item = new LocationItem(userLocation.getKey(), (Double) userLocation.child("latitude").getValue(), (Double) userLocation.child("longitude").getValue());
                Log.d("TAG", "onChildChanged: CHILD HAS CHANGED\n" + item.toString());
                allItems.put(item.getUser(), item);
                sendBroadcast(new Intent("locations").putExtra("items", allItems));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot userLocation) {
                Log.d(TAG, "onChildRemoved: CHILD HAS BEEN REMOVED");
                allItems.remove(userLocation.getKey());
                sendBroadcast(new Intent("locations").putExtra("items", allItems));
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "onChildMoved: CHILD HAS BEEN MOVED");
                //nothing needed here
                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: CHILD HAS AN ERROR");
                //handle errors;
                return;
            }
        };
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel(String channelId, String channelName) {
        NotificationChannel chan = new NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        NotificationManager service = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        service.createNotificationChannel(chan);
        return channelId;
    }

    public class LocationServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("STOP".equals(intent.getAction())) {
                running = false;
                sendBroadcast(new Intent("stop"));
                Log.d("HUBBA", "onReceive: STOP");
            } else if ("START".equals(intent.getAction())) {
                running = true;
                buildNotification(permission);
                requestLocationUpdates(permission);
                Log.d("HUBBA", "onReceive: START");
            } else if("displayItem".equals(intent.getAction())) {
                running = false;
                ActionItem.ActionItemDTO dto = (ActionItem.ActionItemDTO) intent.getSerializableExtra("displayItem");
                LocationItem item = new LocationItem(dto.user, dto.latitude, dto.longitude);
                allItems.put("selectedItem", item);
            }
        }
    }

    @Override
    public void onDestroy() {
        sendBroadcast(new Intent("stop"));
        unregisterReceiver(lServiceReciever);
        ref.removeEventListener(listener);
        super.onDestroy();


    }
}

