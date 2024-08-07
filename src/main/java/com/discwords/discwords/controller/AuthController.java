package com.discwords.discwords.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class AuthController {

   @Value("${google_client_id}")
   private String CLIENT_ID;


   @CrossOrigin(origins = "http://localhost:3000")
   @PostMapping("/api/auth/google")
   public String googleLogin(@RequestBody TokenRequest tokenRequest) throws Exception{

      GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance())
              .setAudience(Collections.singletonList(CLIENT_ID)).build();
      GoogleIdToken idToken = verifier.verify(tokenRequest.getTokenId());


      if (idToken != null) {
         GoogleIdToken.Payload payload = idToken.getPayload();

         String userId = payload.getSubject();
         String email = payload.getEmail();
         boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
         String name = (String) payload.get("name");
         String pictureUrl = (String) payload.get("picture");
         String locale = (String) payload.get("locale");
         String familyName = (String) payload.get("family_name");
         String givenName = (String) payload.get("given_name");

         // Handle user authentication and session creation here
         return "User authenticated: " + userId;
      } else {
         return "Invalid ID token.";
      }


   }


   public static class TokenRequest {
      private String tokenId;

      public String getTokenId() {
         return tokenId;
      }

      public void setTokenId(String tokenId) {
         this.tokenId = tokenId;
      }
   }
}
