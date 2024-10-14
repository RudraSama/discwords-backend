package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.DTOs.FetchFriendRequestDTO;
import com.discwords.discwords.DTOs.FriendRequestDTO;
import com.discwords.discwords.model.Profile;

import java.util.List;


public interface UserInteractionService {

    public String handleFriendRequest(FriendRequestDTO friendRequestDTO);
    public List<FetchFriendRequestDTO> handleFetchFriendRequests(long profile_id);
    public String handleFriendRequestAccept(long id);
    public String handleFriendRequestReject(long id);
    public List<Profile> handleFetchFriends(long id);

}
