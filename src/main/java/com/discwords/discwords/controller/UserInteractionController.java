package com.discwords.discwords.controller;

import com.discwords.discwords.DTOs.FriendRequestDTO;
import com.discwords.discwords.service.UserInteractionService;
import com.discwords.discwords.service.UserInteractionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
