package org.mcopenplatform.muoapi.mcopsdk;

import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Locale;
import java.util.UUID;

public class ActionItem {
    private static final String TAG = "ActionItem";
    private static DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.sss", Locale.US);
    private UUID id;
    private String userId;
    private String content;
    private String timestamp;
    private ActionTag tag;
    private Double latitude;
    private Double longitude;
    private Boolean completed;

    public ActionItem() {
        this.userId = null;
        this.content = null;
        this.timestamp = null;
        this.tag = null;
        this.latitude = null;
        this.longitude = null;
        this.completed = false;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public static ActionItem newActionItem() {
        ActionItem item = new ActionItem();
        item.setId(UUID.randomUUID());
        item.setCompleted(false);
        return item;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ActionItem(String userId, String content, String timestamp, ActionTag tag) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.content = content;
        this.timestamp = timestamp;
        this.tag = tag;
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.completed = false;
    }



    public String getUserId() {
        return userId;
    }

    public ActionTag getTag() {
        return tag;
    }

    public ActionItem setTag(ActionTag tag) {
        this.tag = tag;
        return this;
    }

    public ActionItem setUserId(String userId) {
        this.userId = userId;
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
                case "userId":
                    item.setUserId(data.getValue() != null ? data.getValue().toString() : null);
                    break;
                case "timestamp":
                    item.setTimestamp(data.getValue() != null ? data.getValue().toString() : null);
                    break;
                case "latitude":
                    item.setLatitude(data.getValue() != null ? data.getValue(Double.class) : null);
                    break;
                case "longitude":
                    item.setLongitude(data.getValue() != null ? data.getValue(Double.class) : null);
                    break;
                case "completed":
                    item.setCompleted(data.getValue() != null ? data.getValue(Boolean.class) : null);
                    break;
            }
        }
        return item;
    }


    public static class ActionItemDTO implements Serializable {
        public String userId;
        public String content;
        public String timestamp;
        public ActionTag tag;
        public Double latitude;
        public Double longitude;
        public Boolean completed;

        public static ActionItemDTO fromItem(ActionItem item) {
            ActionItemDTO newDTO = new ActionItemDTO();
            newDTO.content = item.content;
            if(item.getLatitude() != null) {
                newDTO.latitude = item.latitude;
                newDTO.longitude = item.longitude;
            }
            newDTO.tag = item.tag;
            newDTO.timestamp = item.timestamp;
            newDTO.userId = item.userId;
            newDTO.completed = item.completed;
            return newDTO;
        }

    }

    public static class Comparators {
        public static Comparator<ActionItem> TIME = new Comparator<ActionItem>() {
            @Override
            public int compare(ActionItem item1, ActionItem item2) {
                try {
                    if(formatter.parse(item1.getTimestamp()).before(formatter.parse(item2.getTimestamp()))) {
                        return 1;
                    } else {
                        return -1;
                    }
                } catch(Exception e) {
                    return 1;
                }
            }
        };
    }
//timestamp
//    public int getDate() {
//
//    }

}