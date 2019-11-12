package org.mcopenplatform.muoapi.mcopsdk;

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

    private Double latitude;

    private Double longitude;

    private Boolean completed;



    public ActionItem() {

        this.user = "";

        this.content = "";

        this.timestamp = "";

        this.tag = null;

        this.latitude = 0.0;

        this.longitude = 0.0;

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



    public ActionItem(String user, String content, String timestamp, ActionTag tag) {

        this.id = UUID.randomUUID();

        this.user = user;

        this.content = content;

        this.timestamp = timestamp;

        this.tag = tag;

        this.latitude = null;

        this.longitude = null;

        this.completed = false;

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

        public String user;

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

            newDTO.user = item.user;

            newDTO.completed = item.completed;

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