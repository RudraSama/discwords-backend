package com.discwords.discwords.controller;

import com.discwords.discwords.DTOs.ConversationDTO;
import com.discwords.discwords.DTOs.FetchFriendRequestDTO;
import com.discwords.discwords.DTOs.FriendRequestDTO;
import com.discwords.discwords.model.Profile;
import com.discwords.discwords.service.UserInteractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserInteractionController {

    public final UserInteractionService userInteractionService;

    public UserInteractionController(UserInteractionService userInteractionService){
        this.userInteractionService = userInteractionService;
    }

    @PostMapping("/api/addFriend")
    public ResponseEntity<String> handleFriendRequest(@RequestBody FriendRequestDTO friendRequestDTO){
        return new ResponseEntity<>(userInteractionService.handleFriendRequest(friendRequestDTO), HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/fetchFriendRequests/{profile_id}")
    public ResponseEntity<List<FetchFriendRequestDTO>> handleFetchFriendRequests(@PathVariable String profile_id){
        return new ResponseEntity<>(userInteractionService.handleFetchFriendRequests(Long.parseLong(profile_id)), HttpStatus.ACCEPTED);
    }

    //we are getting id of row from FriendRequest table
    @PostMapping("/api/acceptFriendRequest/{id}")
    public ResponseEntity<String> handleFriendRequestAccept(@PathVariable long id){
        return new ResponseEntity<>(userInteractionService.handleFriendRequestAccept(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/api/rejectFriendRequest/{id}")
    public ResponseEntity<String> handleFriendRequestReject(@PathVariable long id){
        return new ResponseEntity<>(userInteractionService.handleFriendRequestReject(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/fetchFriends/{id}")
    public ResponseEntity<List<Profile>> handleFetchFriends(@PathVariable long id){
        return new ResponseEntity<>(userInteractionService.handleFetchFriends(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/fetchConversations/{id}")
    public ResponseEntity<List<ConversationDTO>> handleFetchConversations(@PathVariable long id){
        return new ResponseEntity<>(userInteractionService.handleFetchConversations(id), HttpStatus.ACCEPTED);
    }
}
