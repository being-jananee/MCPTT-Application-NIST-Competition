package com.example.application;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    ArrayList<ActionItem> allItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private ActionItemAdapter actionItemAdapter;
    private FloatingActionButton floatingButton;
    private DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference().child("events");
    private ChildEventListener listener = getChildListener();
    private FusedLocationProviderClient fusedClient;
    private String username = "user_"+Build.MODEL.replace(" ", "_");
    private LinearLayoutManager manager;
    private ActionItem selectedItem;
    private DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.sss", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fusedClient = new FusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
        }
        eventsRef.addChildEventListener(listener);
        floatingButton = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recycle);
        actionItemAdapter = new ActionItemAdapter(this, allItems);
        recyclerView.setAdapter(actionItemAdapter);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ActionItem newItem = ActionItem.newActionItem();
                final AlertDialog ad = new AlertDialog.Builder(MainActivity.this).create();
                View mView = getLayoutInflater().inflate(R.layout.dialog_layout, null);
                final Spinner sp = mView.findViewById(R.id.spinner);
                final EditText et = mView.findViewById(R.id.editText);
                ArrayList<String> tags = new ArrayList<>();
                for(ActionTag i : ActionTag.values()) {
                    tags.add(i.getName());
                }
                String[] a = tags.toArray(new String[tags.size()]);
                ArrayAdapter spinnerArrayAdapter = new ArrayAdapter
                        (MainActivity.this, android.R.layout.simple_spinner_item,
                                a); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item);
                sp.setAdapter(spinnerArrayAdapter);
                Button b = mView.findViewById(R.id.button);
                final Switch s = mView.findViewById(R.id.switch1);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        newItem.setUser(username).setContent(et.getText().toString()).setTimestamp(formatter.format(new Date()))
                                .setTag(ActionTag.get((sp.getSelectedItem().toString())));
                        if(s.isChecked() && hasPermission()) {
                            addLocationToActionItemAndSend(newItem);
                        } else {
                            updateEvent(newItem);
                        }
                        ad.dismiss();
                    }
                });
                ad.setView(mView);
                ad.show();
            }
        });
    }


//    public void getEvents() {
//        eventsRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot event: dataSnapshot.getChildren()) {
//                    ActionItem item = ActionItem.fromSnapshot(event);
//                    allItems.add(item);
//                }
//                actionItemAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                //handle error
//            }
//        });
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.maps:
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                i.putExtra("username", username);
               startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public ChildEventListener getChildListener() {
        return new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot addedEvent, @Nullable String s) {
                Log.d("MainActivity", "onChildAdded: ");
                ActionItem item = ActionItem.fromSnapshot(addedEvent);
                allItems.add(item);
                Collections.sort(allItems, ActionItem.Comparators.TIME);
                actionItemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //No Editing Of Events
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                UUID id = UUID.fromString(dataSnapshot.getKey());
                for(ActionItem item: allItems) {
                    if(item.getId().toString().equals(id.toString())) {
                        allItems.remove(item);
                        actionItemAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //won't happen
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Figure out what to do in error?
            }
        };
    }

    public void updateEvent(ActionItem item) {
        eventsRef.child(item.getId().toString()).setValue(ActionItem.ActionItemDTO.fromItem(item));
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (listener != null) {
            eventsRef.removeEventListener(listener);
            listener = null;
            eventsRef = null;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        final int position = recyclerView.getChildAdapterPosition(v);
        Log.d("TAG", "onLongClick: "+position);
        final AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        ad.setTitle("Confirm");
        ad.setMessage("Are you sure you would like to delete this item?");
        ad.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActionItem item = allItems.get(position);
                if(item.getUser().equals(username)) {
                    eventsRef.child(item.getId().toString()).removeValue();
                } else {
                    Toast.makeText(MainActivity.this, "Can't delete an event for a user that is not yourself.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        ad.create().show();
        return true;
    }

    public boolean hasPermission() {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }

    public void addLocationToActionItemAndSend(final ActionItem newItem) throws SecurityException {
        fusedClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
            if(location != null) {
                newItem.setLatitude(location.getLatitude());
                newItem.setLongitude(location.getLongitude());
                updateEvent(newItem);
            } else {
                //make dialog to show error;
            }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        if((selectedItem = allItems.get(position)) != null) {
            //View mView = manager.findViewByPosition(position);
            //MapView mapView = mView.findViewById(R.id.mapView);
            //mapView.getMapAsync(this);
            if(selectedItem.getLatitude() != null) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                i.putExtra("item", ActionItem.ActionItemDTO.fromItem(selectedItem));
                i.putExtra("username", username);
                startActivity(i);
            }
        }
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
//        } else {
//            LatLng currLoc = new LatLng(selectedItem.getLatitude(), selectedItem.getLongitude());
//            googleMap.addMarker(new MarkerOptions().position(currLoc));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currLoc, 15));
//        }
//    }
}
