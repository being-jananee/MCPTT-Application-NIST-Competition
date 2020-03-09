/*
 *
 *   Copyright (C) 2018, University of the Basque Country (UPV/EHU)
 *
 *  Contact for licensing options: <licensing-mcpttclient(at)mcopenplatform(dot)com>
 *
 *  This file is part of MCOP MCPTT Client
 *
 *  This is free software: you can redistribute it and/or modify it under the terms of
 *  the GNU General Public License as published by the Free Software Foundation, either version 3
 *  of the License, or (at your option) any later version.
 *
 *  This is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package com.example.application.Domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class UserData implements Parcelable {
    private String mcpttID;
    private String displayName;
    private Map<String,Session> sessionIDs;
    private boolean isRegisted;
    private boolean checked;

    public UserData(String mcpttID, String displayName) {
        this.mcpttID = mcpttID;
        this.displayName = displayName;
        sessionIDs=new HashMap<String,Session>();
    }

    public UserData(String mcpttID, String displayName, boolean isRegisted) {
        this.mcpttID = mcpttID;
        this.displayName = displayName;
        this.isRegisted = isRegisted;
        sessionIDs=new HashMap<String,Session>();
    }

    private UserData(Parcel in) {
        this.mcpttID = in.readString();
        this.displayName = in.readString();
        in.readMap(this.sessionIDs, Session.class.getClassLoader());
        this.isRegisted = in.readInt() != 0;
        this.checked = in.readInt() != 0;

    }

    public UserData() {
        sessionIDs=new HashMap<String,Session>();
    }

    public String getMcpttID() {
        return mcpttID;
    }

    public void setMcpttID(String mcpttID) {
        this.mcpttID = mcpttID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isRegisted() {
        return isRegisted;
    }

    public void setRegisted(boolean registed) {
        isRegisted = registed;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public List<String> getSessionIDs() {
        if(sessionIDs==null || sessionIDs.keySet()==null)return null;
        Iterator<String> interator=sessionIDs.keySet().iterator();
        ArrayList<String> ids=new ArrayList<>();
        while (interator.hasNext())
            ids.add(interator.next());
        return ids;
    }

    public Session getSession(String sessionID) {
        if(sessionIDs==null || sessionIDs.isEmpty())return null;
        return sessionIDs.get(sessionID);
    }

    public void removeSessionID(String sessionIDs) {
        this.sessionIDs.remove(sessionIDs);
    }

    public void addSessionID(String sessionIDs) {
        this.sessionIDs.put(sessionIDs,new Session(sessionIDs));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mcpttID);
        dest.writeString(this.displayName);
        dest.writeMap(this.sessionIDs);
        dest.writeInt(this.isRegisted ? 1 : 0);
        dest.writeInt(this.checked ? 1 : 0);
    }

    public static final Parcelable.Creator<UserData> CREATOR
            = new Parcelable.Creator<UserData>() {
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    public static class Comparators {
        public static Comparator<UserData> ID = new Comparator<UserData>() {
            @Override
            public int compare(UserData user1, UserData user2) {
                try {
                    if(user1.getMcpttID().compareTo(user2.getMcpttID()) > 0) {
                        return 1;
                    } else {
                        return -1;
                    }
                } catch(Exception e) {
                    return 0;
                }
            }
        };
    }}
