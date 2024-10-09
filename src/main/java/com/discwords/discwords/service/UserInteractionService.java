package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.FetchFriendRequestDTO;
import com.discwords.discwords.DTOs.FriendRequestDTO;

import java.util.List;


public interface UserInteractionService {

    public String handleFriendRequest(FriendRequestDTO friendRequestDTO);
    public List<FetchFriendRequestDTO> handleFetchFriendRequests(long profile_id);
}
