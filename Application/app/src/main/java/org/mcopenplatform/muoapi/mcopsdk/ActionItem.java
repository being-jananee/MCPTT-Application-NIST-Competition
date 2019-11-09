package org.mcopenplatform.muoapi.mcopsdk;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;

public class ActionItem {

    private UUID id;
    private String user;
    private String content;
    private String timestamp;
    private ActionTag tag;
    private LatLng location;

    public ActionItem() {
        this.user = null;
        this.content = null;
        this.timestamp = null;
        this.tag = null;
        this.location = null;
    }

    public static ActionItem newActionItem() {
        ActionItem item = new ActionItem();
        item.setId(UUID.randomUUID());
        return item;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ActionItem(String user, String content, String timestamp, ActionTag tag) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.content = content;
        this.timestamp = timestamp;
        this.tag = tag;
        this.location = null;
    }



    public String getUser() {
        return user;
    }

    public ActionTag getTag() {
        return tag;
    }

    public ActionItem setTag(ActionTag tag) {
        this.tag = tag;
        return this;
    }

    public LatLng getLocation() {
        return location;
    }

    public ActionItem setLocation(LatLng location) {
        this.location = location;
        return this;
    }

    public ActionItem setUser(String user) {
        this.user = user;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ActionItem setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public ActionItem setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public static ActionItem fromSnapshot(DataSnapshot event) {
        ActionItem item = new ActionItem();
        item.setId(UUID.fromString(event.getKey()));
        for(DataSnapshot data: event.getChildren()) {
            switch (data.getKey()) {
                case "content":
                    item.setContent(data.getValue() != null ? data.getValue().toString() : null);
                    break;
                case "tag":
                    item.setTag(data.getValue() != null ? ActionTag.valueOf(data.getValue().toString()) : null);
                    break;
                case "user":
                    item.setUser(data.getValue() != null ? data.getValue().toString() : null);
                    break;
                case "timestamp":
                    item.setTimestamp(data.getValue() != null ? data.getValue().toString() : null);
                    break;
                case "location":
                    String loc = data.getValue() != null ? data.getValue().toString() : null;
                    if (loc != null) {
                        item.setLocation(new LatLng(Double.parseDouble(loc.split(",")[0]), Double.parseDouble(loc.split(",")[1])));
                    }
                    break;
            }
        }
        return item;
    }


    public static class ActionItemDTO implements Serializable {
        public String user;
        public String content;
        public String timestamp;
        public ActionTag tag;
        public String location;

        public static ActionItemDTO fromItem(ActionItem item) {
            ActionItemDTO newDTO = new ActionItemDTO();
            newDTO.content = item.content;
            if(item.location != null) {
                newDTO.location = item.location.latitude + "," + item.location.longitude;
            }
            newDTO.tag = item.tag;
            newDTO.timestamp = item.timestamp;
            newDTO.user = item.user;
            return newDTO;
        }

    }

    public static class Comparators {
        public static Comparator<ActionItem> TIME = new Comparator<ActionItem>() {
            @Override
            public int compare(ActionItem item1, ActionItem item2) {
                return -1 * item1.timestamp.compareTo(item2.timestamp);
            }
        };
    }
}
