package com.discwords.discwords.service;


import com.discwords.discwords.DTOs.TokenRequestDTO;
import com.discwords.discwords.DTOs.UserDTO;
import com.discwords.discwords.DTOs.UserSessionDTO;
import com.discwords.discwords.common.Utils;
import com.discwords.discwords.model.*;
import com.discwords.discwords.repository.ProfileRepo;
import com.discwords.discwords.repository.SessionRepo;
import com.discwords.discwords.repository.UserRepo;
import com.discwords.discwords.repository.UserSecretRepo;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final UserRepo userRepo;
    private final UserSecretRepo userSecretRepo;
    private final SessionRepo sessionRepo;
    private final ProfileRepo profileRepo;
    private final JWTService jwtService;

    public AuthorizationServiceImpl(UserRepo userRepo, UserSecretRepo userSecretRepo, SessionRepo sessionRepo, ProfileRepo profileRepo, JWTService jwtService) {
        this.userRepo = userRepo;
        this.userSecretRepo = userSecretRepo;
        this.sessionRepo = sessionRepo;
        this.profileRepo = profileRepo;
        this.jwtService = jwtService;
    }


    @Value("${google_client_id}")
    private String CLIENT_ID;


    @Override
    public UserSessionDTO loginUser(UserDTO user) throws Exception {
        Optional<User> userRes = userRepo.findByEmail(user.getEmail());
        if (userRes.isEmpty()) {
            System.out.println("User not found");
            return null;
        }

        User existingUser = userRes.get();

        Optional<Profile> profileRes = profileRepo.findByUserId(existingUser.getUserId());
        if(profileRes.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Profile with this user id");
        }
        Profile profile = profileRes.get();

        UserSessionDTO userSessionDTO = handleUserSession(existingUser, profile);

        if(userSessionDTO != null){
            return userSessionDTO;
        }

        Optional<UserSecret> userSecretRes = userSecretRepo.findById(existingUser.getUserId());
        if (userSecretRes.isEmpty()) {
            return null;
        }

        UserSecret userSecret = userSecretRes.get();
        if (BCrypt.checkpw(user.getPassword(), userSecret.getPassword())) {
            UserSession userSession = createUserSession(existingUser);
            return new UserSessionDTO(profile, userSession.getToken());
        }
        return null;
    }


    @Override
    public UserSessionDTO signupUser(UserDTO user) throws Exception {

//        checking if email already exists
        Optional<User> userRes = userRepo.findByEmail(user.getEmail());
        if (userRes.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exist");
        }

//       checking if username already exists
        Optional<User> tempUserRes = userRepo.findByUsername(user.getUsername());
        if (tempUserRes.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exist");
        }

        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        long userId = Utils.generateId();
        User newUser = new User(userId, user.getEmail(), user.getUsername());
        userRepo.save(newUser);

        UserSecret newUserSecret = new UserSecret(userId, password);
        userSecretRepo.save(newUserSecret);

        Profile profile = new Profile(Utils.generateId(), newUser.getUserId(), newUser.getUsername(), newUser.getEmail(), "");
        profileRepo.save(profile);

        UserSession userSession = createUserSession(newUser);
        return new  UserSessionDTO(profile, userSession.getToken());
    }

    @Override
    public UserSessionDTO signupUserWithGoogle(TokenRequestDTO tokenRequestDTO) throws Exception {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(CLIENT_ID)).build();
        GoogleIdToken idToken = verifier.verify(tokenRequestDTO.getTokenId());

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            String userEmail = payload.getEmail();

            Optional<User> userRes = userRepo.findByEmail(userEmail);

            if (userRes.isPresent()) {
                return loginUserWithGoogle(tokenRequestDTO);
            }

            long userId = Utils.generateId();
            String username = userEmail.split("@")[0];

            User newUser = new User(userId, userEmail, username);
            userRepo.save(newUser);

            Profile profile = new Profile(Utils.generateId(), newUser.getUserId(), newUser.getUsername(), newUser.getEmail(), "");
            profileRepo.save(profile);

            UserSession userSession = createUserSession(newUser);
            return new UserSessionDTO(profile, userSession.getToken());
        }
        return null;
    }

    @Override
    public UserSessionDTO loginUserWithGoogle(TokenRequestDTO tokenRequestDTO) throws Exception {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(CLIENT_ID)).build();
        GoogleIdToken idToken = verifier.verify(tokenRequestDTO.getTokenId());

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            String userEmail = payload.getEmail();

            Optional<User> userRes = userRepo.findByEmail(userEmail);

            if (userRes.isEmpty()) {
                return signupUserWithGoogle(tokenRequestDTO);
            }

            User existingUser = userRes.get();

            Optional<Profile> profileRes = profileRepo.findByUserId(existingUser.getUserId());
            if(profileRes.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Profile with this user id");
            }
            Profile profile = profileRes.get();

            UserSessionDTO userSessionDTO = handleUserSession(existingUser, profile);

            if(userSessionDTO != null){
                return userSessionDTO;
            }

            UserSession userSession = createUserSession(existingUser);
            return new UserSessionDTO(profile, userSession.getToken());
        }
        return null;
    }

    //using token
    @Override
    public Profile authorizeUser(String jwtToken) {


        long userId = Long.parseLong((String)jwtService.extractUserId(jwtToken));
        Optional<UserSession> userSessionRes = sessionRepo.findByUserId(userId);

        if(userSessionRes.isEmpty()){
            //need to return Exception instead of null
            return null;
        }

        UserSession userSession = userSessionRes.get();

        if(userSession.getSessionEndTime().before(new Date()) || !jwtService.isTokenExpired(jwtToken)){
            sessionRepo.delete(userSession);
            //need to return Exception instead of null
            return null;
        }
        Optional<Profile> profileRes = profileRepo.findByUserId(userId);

        //need to return Exception instead of null
        return profileRes.orElse(null);
    }


    private UserSessionDTO handleUserSession(User existingUser, Profile profile){
        if(existingUser == null){
            return null;
        }

        Optional<UserSession> userSessionRes = sessionRepo.findByUserId(existingUser.getUserId());

        if(userSessionRes.isPresent()){
            UserSession userSession = userSessionRes.get();

            if(userSession.getSessionEndTime().before(new Date())){
                sessionRepo.delete(userSession);
            }
            else{
                return new UserSessionDTO(profile, userSession.getToken());
            }
        }
        return null;
    }

    private UserSession createUserSession(User user){
        //generating jwt token
        String jwtToken = jwtService.generateToken(user.getEmail(), Long.toString(user.getUserId()));
        int newSessionId = (int) (System.currentTimeMillis() / 100000);
        UserSession newUserSession = new UserSession(
                user.getUserId(),
                jwtToken,
                new Date(),
                (Date)jwtService.extractExpiryDate(jwtToken),
                newSessionId
        );

        return sessionRepo.save(newUserSession);
    }
}

