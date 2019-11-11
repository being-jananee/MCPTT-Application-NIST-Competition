package org.mcopenplatform.muoapi.mcopsdk;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import org.mcopenplatform.muoapi.R;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
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
import android.widget.Spinner;
import android.widget.Switch;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener{
    Random rand = new Random(0);
    ArrayList<ActionItem> allItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private ActionItemAdapter actionItemAdapter;
    private FloatingActionButton floatingButton;
    private DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference().child("events");
    private ChildEventListener listener = getChildListener();
    private FusedLocationProviderClient fusedClient;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        user = getIntent().getExtras().getString("user");
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ActionItem newItem = ActionItem.newActionItem();
                final AlertDialog ad = new AlertDialog.Builder(Main2Activity.this).create();
                View mView = getLayoutInflater().inflate(R.layout.dialog_layout, null);
                final Spinner sp = mView.findViewById(R.id.spinner);
                final EditText et = mView.findViewById(R.id.editText);
                ArrayList<String> tags = new ArrayList<>();
                for(ActionTag i : ActionTag.values()) {
                    tags.add(i.getName());
                }
                String[] a = tags.toArray(new String[tags.size()]);
                ArrayAdapter spinnerArrayAdapter = new ArrayAdapter
                        (Main2Activity.this, android.R.layout.simple_spinner_item,
                                a); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item);
                sp.setAdapter(spinnerArrayAdapter);
                Button b = mView.findViewById(R.id.button);
                final Switch s = mView.findViewById(R.id.switch1);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        newItem.setUser(user).setContent(et.getText().toString()).setTimestamp(getTimestamp())
                                .setTag(ActionTag.get((sp.getSelectedItem().toString())));
                        if(s.isChecked() && hasPermission()) {
                            addLocationToActionItem(newItem);
                        } else {
                            eventsRef.child(newItem.getId().toString()).setValue(ActionItem.ActionItemDTO.fromItem(newItem));
                        }
                        ad.dismiss();
                    }
                });
                ad.setView(mView);
                ad.show();
            }
        });
    }

    public ChildEventListener getChildListener() {
        return new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot addedEvent, @Nullable String s) {
                Log.d("MainActivity", "onChildAdded: ");
                ActionItem item = new ActionItem();
                item.setId(UUID.fromString(addedEvent.getKey()));
                for(DataSnapshot eventDescription: addedEvent.getChildren()) {
                    switch(eventDescription.getKey()) {
                        case "content":
                            item.setContent(eventDescription.getValue() != null ? eventDescription.getValue().toString() : null);
                            break;
                        case "tag":
                            item.setTag(eventDescription.getValue() != null ? ActionTag.valueOf(eventDescription.getValue().toString()) : null);
                            break;
                        case "user":
                            item.setUser(eventDescription.getValue() != null ? eventDescription.getValue().toString() : null);
                            break;
                        case "timestamp":
                            item.setTimestamp(eventDescription.getValue() != null ? eventDescription.getValue().toString() : null);
                            break;
                        case "location":
                            String loc = eventDescription.getValue() != null ? eventDescription.getValue().toString() : null;
                            if(loc != null) {
                                item.setLocation(new LatLng(Double.parseDouble(loc.split(",")[0]), Double.parseDouble(loc.split(",")[1])));
                            }
                            break;
                    }
                }
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

    public String getTimestamp() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        String timestamp = (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) +"-"+c.get(Calendar.YEAR) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + "." + c.get(Calendar.MILLISECOND);
        return timestamp;
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
        final AlertDialog.Builder ad = new AlertDialog.Builder(Main2Activity.this);
        ad.setTitle("Confirm");
        ad.setMessage("Are you sure you would like to delete this item?");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                allItems.remove(position);
            }
        });
        ad.create().show();
        return true;
    }

    public boolean hasPermission() {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }

    public void addLocationToActionItem(final ActionItem newItem) throws SecurityException {
        fusedClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null) {
                    newItem.setLocation(new LatLng(location.getLatitude(), location.getLongitude()));
                } else {
                    newItem.setLocation(new LatLng(41.8781, 87.6298));
                }
                eventsRef.child(newItem.getId().toString()).setValue(ActionItem.ActionItemDTO.fromItem(newItem));
            }
        });
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        if(allItems.get(position).getLocation() != null) {
            Intent i = new Intent(Main2Activity.this, MapsActivity.class);
            i.putExtra("item_lat", allItems.get(position).getLocation().latitude);
            i.putExtra("item_long", allItems.get(position).getLocation().longitude);
            startActivity(i);
        }
    }


}
