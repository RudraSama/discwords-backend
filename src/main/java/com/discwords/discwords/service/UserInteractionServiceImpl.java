package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.FriendRequestDTO;
import com.discwords.discwords.model.FriendList;
import com.discwords.discwords.model.FriendRequest;
import com.discwords.discwords.model.Profile;
import com.discwords.discwords.repository.FriendListRepo;
import com.discwords.discwords.repository.FriendRequestRepo;
import com.discwords.discwords.repository.ProfileRepo;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserInteractionServiceImpl implements UserInteractionService{

    private final ProfileRepo profileRepo;
    private final FriendListRepo friendListRepo;
    private final FriendRequestRepo friendRequestRepo;


    public UserInteractionServiceImpl(ProfileRepo profileRepo, FriendListRepo friendListRepo,  FriendRequestRepo friendRequestRepo){
        this.profileRepo = profileRepo;
        this.friendListRepo = friendListRepo;
        this.friendRequestRepo = friendRequestRepo;
    }


    @Override
    public String handleFriendRequest(FriendRequestDTO friendRequestDTO){
        Optional<Profile> profileRes = profileRepo.findByUsername(friendRequestDTO.getUsername());
        if(profileRes.isEmpty())
        {
            return "Username doesn't exists";
        }
        Profile profile = profileRes.get();
        Optional<FriendList> friendListRes = friendListRepo.findIfFriend(friendRequestDTO.getProfile_id(), profile.getProfileId());
        if(friendListRes.isPresent()){
            return "Friend Already Exists";
        }
        FriendRequest friendRequest = new FriendRequest(friendRequestDTO.getProfile_id(), profile.getProfileId());
        friendRequestRepo.save(friendRequest);
        return "Friend request sent!!!";
    }
}
