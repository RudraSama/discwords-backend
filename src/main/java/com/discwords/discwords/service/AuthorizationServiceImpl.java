package com.discwords.discwords.service;


import com.discwords.discwords.model.*;
import com.discwords.discwords.repository.ProfileRepo;
import com.discwords.discwords.repository.SessionRepo;
import com.discwords.discwords.repository.UserRepo;
import com.discwords.discwords.repository.UserSecretRepo;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserSecretRepo userSecretRepo;

    @Autowired
    private SessionRepo sessionRepo;


    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    JWTService jwtService;

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

        Optional<UserSession> userSessionRes = sessionRepo.findByUserId(existingUser.getUserId());
        if (userSessionRes.isPresent()) {
            UserSession userSession = userSessionRes.get();
            if (userSession.getSessionEndTime().before(new Date())) {
                System.out.println("Deleting User session for new");
                sessionRepo.delete(userSession);
            } else {
                System.out.println("User already logged in");
                return new UserSessionDTO(profile, userSession.getToken());
            }
        }

        Optional<UserSecret> userSecretRes = userSecretRepo.findById(existingUser.getUserId());

        if (userSecretRes.isEmpty()) {
            System.out.println("User secret is empty");
            return null;
        }

        UserSecret userSecret = userSecretRes.get();

        if (BCrypt.checkpw(user.getPassword(), userSecret.getPassword())) {
            String jwtToken = jwtService.generateToken(existingUser.getEmail(), Long.toString(existingUser.getUserId()));
            int newSessionId = (int) (System.currentTimeMillis() / 100000);
            UserSession newUserSession = new UserSession(
                    existingUser.getUserId(),
                    jwtToken,
                    new Date(),
                    (Date)jwtService.extractExpiryDate(jwtToken),
                    newSessionId
            );

            UserSession userSession = sessionRepo.save(newUserSession);
            UserSessionDTO userSessionDTO = new UserSessionDTO(profile, userSession.getToken());

            return userSessionDTO;

        }
        return null;
    }


    @Override
    public UserSessionDTO signupUser(UserDTO user) throws Exception {

//        checking if email already exists
        Optional<User> userRes = userRepo.findByEmail(user.getEmail());
        if (!userRes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exist");
        }

//       checking if username already exists
        Optional<User> tempUserRes = userRepo.findByUsername(user.getUsername());
        if (!tempUserRes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username exist");
        }


        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        Long userId = generateId();

        User newUser = new User(userId, user.getEmail(), user.getUsername());
        userRepo.save(newUser);

        UserSecret newUserSecret = new UserSecret(userId, password);
        userSecretRepo.save(newUserSecret);


        //creating profile
        Profile profile = new Profile();
        profile.setUserId(newUser.getUserId());
        profile.setEmail(newUser.getEmail());
        System.out.println(newUser.getUsername());
        profile.setUsername(newUser.getUsername());
        profileRepo.save(profile);



        //token generation

        String jwtToken = jwtService.generateToken(newUser.getEmail(), Long.toString(newUser.getUserId()));

        int newSessionId = (int) (System.currentTimeMillis() / 100000);
        UserSession newUserSession = new UserSession(
                newUser.getUserId(),
                jwtToken,
                new Date(),
                (Date)jwtService.extractExpiryDate(jwtToken),
                newSessionId
        );
        UserSession userSession = sessionRepo.save(newUserSession);


        UserSessionDTO userSessionDTO = new UserSessionDTO(profile, userSession.getToken());

        return userSessionDTO;
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

            long userId = generateId();
            String username = userEmail.split("@")[0];

            User newUser = new User(userId, userEmail, username);
            userRepo.save(newUser);

            Profile profile = new Profile();
            profile.setUserId(newUser.getUserId());
            profile.setEmail(newUser.getEmail());
            System.out.println(newUser.getUsername());
            profile.setUsername(newUser.getUsername());
            profileRepo.save(profile);


            String jwtToken = jwtService.generateToken(newUser.getEmail(), Long.toString(newUser.getUserId()));
            int newSessionId = (int) (System.currentTimeMillis() / 100000);
            UserSession newUserSession = new UserSession(
                    newUser.getUserId(),
                    jwtToken,
                    new Date(),
                    (Date)jwtService.extractExpiryDate(jwtToken),
                    newSessionId
            );

            UserSession userSession = sessionRepo.save(newUserSession);

            UserSessionDTO userSessionDTO = new UserSessionDTO(profile, userSession.getToken());

            return userSessionDTO;
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


            Optional<UserSession> userSessionRes = sessionRepo.findByUserId(existingUser.getUserId());
            if (userSessionRes.isPresent()) {
                UserSession userSession = userSessionRes.get();
                if (userSession.getSessionEndTime().before(new Date())) {
                    sessionRepo.delete(userSession);
                } else {
                    System.out.println("User already logged in");
                    return new UserSessionDTO(profile, userSession.getToken());
                }
            } else {
                String jwtToken = jwtService.generateToken(existingUser.getEmail(), Long.toString(existingUser.getUserId()));
                int newSessionId = (int) (System.currentTimeMillis() / 100000);
                UserSession newUserSession = new UserSession(
                        existingUser.getUserId(),
                        jwtToken,
                        new Date(),
                        (Date)jwtService.extractExpiryDate(jwtToken),
                        newSessionId
                );
                UserSession userSession = sessionRepo.save(newUserSession);

                UserSessionDTO userSessionDTO = new UserSessionDTO(profile, userSession.getToken());

                return userSessionDTO;

            }
        }

        return null;
    }

    //using token
    @Override
    public boolean authorizeUser(String jwtToken) {

        long userId = Long.valueOf((String)jwtService.extractUserId(jwtToken));
        Date tokenExpiryDate = (Date) jwtService.extractExpiryDate(jwtToken);
        Optional<UserSession> userSessionRes = sessionRepo.findByUserId(userId);

        if(userSessionRes.isEmpty()){
            return false;
        }

        UserSession userSession = userSessionRes.get();

        if(userSession.getSessionEndTime().before(new Date()) || tokenExpiryDate.before(new Date())){
            sessionRepo.delete(userSession);
            return false;
        }

        return true;
    }


    //TODO - Making Util package for all these stuff
    private Long generateId() {
        Long time = System.currentTimeMillis();
        time = time % 1000000000;
        time = time * 1000;
        time = time + (long) (Math.random() * 1000);

        return time;
    }

}

