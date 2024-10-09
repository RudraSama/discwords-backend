package com.discwords.discwords.controller;

import com.discwords.discwords.DTOs.FetchFriendRequestDTO;
import com.discwords.discwords.DTOs.FriendRequestDTO;
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
        System.out.println("friend request frontend request");
        return new ResponseEntity<>(userInteractionService.handleFriendRequest(friendRequestDTO), HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/fetchFriendRequests/{profile_id}")
    public ResponseEntity<List<FetchFriendRequestDTO>> handleFetchFriendRequests(@PathVariable String profile_id){
        return new ResponseEntity<List<FetchFriendRequestDTO>>(userInteractionService.handleFetchFriendRequests(Long.parseLong(profile_id)), HttpStatus.ACCEPTED);
    }
}
