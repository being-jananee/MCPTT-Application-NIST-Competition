package com.example.application.Messaging;

import android.util.Log;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Locale;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class InstantMessage {

    public String sender;
    public String content;
    public String timeStamp;
    public String clientTimeStamp;
    private static SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.sss", Locale.US);

    public static class Comparators {
        public static Comparator<InstantMessage> TIME = new Comparator<InstantMessage>() {
            @Override
            public int compare(InstantMessage item1, InstantMessage item2) {
                try {
                    if(formatter.parse(item1.timeStamp).before(formatter.parse(item2.timeStamp))) {
                        return -1;
                    } else if(formatter.parse(item1.timeStamp).after(formatter.parse(item2.timeStamp))) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch(Exception e) {
                    Log.d(TAG, "compare: EXCEPTION!!! " + e.getMessage());
                    return 1;
                }
            }
        };
    }

    @NonNull
    @Override
    public String toString() {
        return sender+", "+content+", "+timeStamp;
    }
}
