package com.example.application;

import java.util.HashMap;
import java.util.Map;

enum ActionTag {
    TAG_SOS("SOS"), TAG_INCIDENT("Incident"), TAG_INTEREST_LOCATION("Location of Interest"), TAG_THREAT("Threat Found"), TAG_COMPLETE("Completed");

    private String tagName;

    ActionTag(String tagName) {
        this.tagName = tagName;
    }

    public String getName() {
        return tagName;
    }

    private static final Map<String, ActionTag> lookup = new HashMap<>();

    static {
        for(ActionTag tag : ActionTag.values()) {
            lookup.put(tag.getName(), tag);
        }
    }

    public static ActionTag get(String tagName) {
        return lookup.get(tagName);
    }
}
