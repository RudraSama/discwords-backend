package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.DTOs.FetchFriendRequestDTO;
import com.discwords.discwords.DTOs.FriendRequestDTO;
import com.discwords.discwords.model.FriendList;
import com.discwords.discwords.model.FriendRequest;
import com.discwords.discwords.model.Profile;
import com.discwords.discwords.repository.ConversationRepo;
import com.discwords.discwords.repository.FriendListRepo;
import com.discwords.discwords.repository.FriendRequestRepo;
import com.discwords.discwords.repository.ProfileRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.List;

@Service
public class UserInteractionServiceImpl implements UserInteractionService{

    private final Logger LOGGER = LogManager.getLogger();
    private final ProfileRepo profileRepo;
    private final FriendListRepo friendListRepo;
    private final FriendRequestRepo friendRequestRepo;
    private final ConversationRepo conversationRepo;

    public UserInteractionServiceImpl(ProfileRepo profileRepo, FriendListRepo friendListRepo,  FriendRequestRepo friendRequestRepo, ConversationRepo conversationRepo){
        this.profileRepo = profileRepo;
        this.friendListRepo = friendListRepo;
        this.friendRequestRepo = friendRequestRepo;
        this.conversationRepo = conversationRepo;
    }


    @Override
    public String handleFriendRequest(FriendRequestDTO friendRequestDTO){

        Optional<Profile> profileRes = profileRepo.findByUsername(friendRequestDTO.getUsername());
        System.out.println(friendRequestDTO.getUsername());
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
    @Override
    public List<FetchFriendRequestDTO> handleFetchFriendRequests(long profile_id){
        List<FetchFriendRequestDTO> requestRes = friendRequestRepo.findFriendRequests(profile_id);
        return requestRes;
    }

    @Override
    public String handleFriendRequestAccept(long id){
        Optional<FriendRequest> friendRequestRes = friendRequestRepo.findById(id);

        if(friendRequestRes.isEmpty()){
            throw new RuntimeException();
        }
        FriendRequest friendRequest = friendRequestRes.get();

        FriendList friendList = new FriendList(friendRequest.getSender_id(), friendRequest.getReceiver_id());

        friendListRepo.save(friendList);
        friendRequestRepo.delete(friendRequest);
        return "success";
    }

    @Override
    public String handleFriendRequestReject(long id){
        Optional<FriendRequest> friendRequestRes = friendRequestRepo.findById(id);

        if(friendRequestRes.isEmpty()){
            throw new RuntimeException();
        }
        friendRequestRepo.delete(friendRequestRes.get());
        return "success";
    }

    @Override
    public List<Profile> handleFetchFriends(long id){
        return friendListRepo.findFriends(id);
    }

    @Override
    public List<ConversationDTO> handleFetchConversations(long id){
        return conversationRepo.findConversation(id);
    }

}
