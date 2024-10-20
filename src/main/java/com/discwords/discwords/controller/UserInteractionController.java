package com.discwords.discwords.controller;

import com.discwords.discwords.DTOs.FetchFriendRequestDTO;
import com.discwords.discwords.model.Profile;
import com.discwords.discwords.service.UserInteractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserInteractionController {

    public final UserInteractionService userInteractionService;

    public UserInteractionController(UserInteractionService userInteractionService){
        this.userInteractionService = userInteractionService;
    }

    @PostMapping("/api/addFriend/{friendUsername}")
    public ResponseEntity<String> handleFriendRequest(@RequestHeader("profileId") long profileId, @PathVariable("friendUsername") String friendUsername){
        return new ResponseEntity<>(userInteractionService.handleSendFriendRequest(profileId, friendUsername), HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/fetchFriendRequests")
    public ResponseEntity<List<FetchFriendRequestDTO>> handleFetchFriendRequests(@RequestHeader("profileId") long profileId){
        return new ResponseEntity<>(userInteractionService.handleFetchFriendRequests(profileId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/fetchFriends")
    public ResponseEntity<List<Profile>> handleFetchFriends(@RequestHeader("profileId") long profileId){
        return new ResponseEntity<>(userInteractionService.handleFetchFriends(profileId), HttpStatus.ACCEPTED);
    }


    //we are getting id of row from FriendRequest table
    @PostMapping("/api/acceptFriendRequest/{id}")
    public ResponseEntity<String> handleFriendRequestAccept(@RequestHeader("profileId") long profileId, @PathVariable long id){
        return new ResponseEntity<>(userInteractionService.handleFriendRequestAccept(profileId, id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/api/rejectFriendRequest/{id}")
    public ResponseEntity<String> handleFriendRequestReject(@RequestHeader("profileId") long profileId, @PathVariable long id){
        return new ResponseEntity<>(userInteractionService.handleFriendRequestReject(profileId, id), HttpStatus.ACCEPTED);
    }

}
