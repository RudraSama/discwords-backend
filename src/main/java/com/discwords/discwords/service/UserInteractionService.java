package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.FetchFriendRequestDTO;
import com.discwords.discwords.model.Profile;

import java.util.List;


public interface UserInteractionService {

    public String handleSendFriendRequest(long profileId, String friendUsername);
    public List<Profile> handleFetchFriends(long profileId);
    public List<FetchFriendRequestDTO> handleFetchFriendRequests(long profile_id);
    public String handleFriendRequestAccept(long profileId, long friendRequestId);
    public String handleFriendRequestReject(long profileId, long friendRequestId);

}
