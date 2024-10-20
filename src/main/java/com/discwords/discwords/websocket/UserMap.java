package com.discwords.discwords.websocket;

import com.discwords.discwords.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class UserMap {
    public final static Map<String, Profile> users = new HashMap<>();

    public static void addToUserList(String sessionId, Profile profile){
        users.put(sessionId, profile);
    }

    public static Profile getProfile(String sessionId){
        return users.get(sessionId);
    }
}
