package com.discwords.discwords.service;


import com.discwords.discwords.model.*;
import com.discwords.discwords.repository.ConversationRepo;
import com.discwords.discwords.repository.DirectMessageRepo;
import com.discwords.discwords.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class DirectMessageServiceServiceImpl implements DirectMessageService {

    @Autowired
    ConversationRepo conversationRepo;

    @Autowired
    ProfileRepo profileRepo;

    @Autowired
    DirectMessageRepo directMessageRepo;

    @Override
    public ConversationDTO createConversation(long profile_id_one, long profile_id_two){

        Optional<Profile> profile = profileRepo.findById(profile_id_one);
        Optional<Profile> profile2 = profileRepo.findById(profile_id_two);

        if(profile.isEmpty()){
            throw new RuntimeException("Profile Id One is invalid");
        }
        if(profile2.isEmpty()){
            throw new RuntimeException("Profile Id Two is invalid");
        }


        Conversation newConversation = new Conversation();
        newConversation.setProfile(profile.get());
        newConversation.setProfile2(profile2.get());


        Conversation conversation = conversationRepo.save(newConversation);

        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.setConversation_id(conversation.getConversation_id());
        conversationDTO.setProfile(conversation.getProfile());
        conversationDTO.setProfile2(conversation.getProfile2());

        return conversationDTO;

    }

    @Override
    public ConversationDTO getConversation(long conversation_id){
        Optional<Conversation> conversation = conversationRepo.findById(conversation_id);

        if(conversation.isEmpty()){
            throw new RuntimeException("Conversation Id is invalid");
        }

        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.setConversation_id(conversation.get().getConversation_id());
        conversationDTO.setProfile(conversation.get().getProfile());
        conversationDTO.setProfile2(conversation.get().getProfile2());
        conversationDTO.setDirectMessages(conversation.get().getDirectMessages());

        return conversationDTO;
    }

    @Override
    public void sendMessage(long conversation_id, long profile_id, String message){
        Optional<Conversation> conversation = conversationRepo.findById(conversation_id);
        Optional<Profile> profile = profileRepo.findById(profile_id);

        if(conversation.isEmpty()){
            throw new RuntimeException("Conversation Id is invalid");
        }

        if(profile.isEmpty()){
            throw new RuntimeException("Profile Id is invalid");
        }

        DirectMessage newDirectMessage = new DirectMessage();
        newDirectMessage.setConversation(conversation.get());
        newDirectMessage.setProfile(profile.get());
        newDirectMessage.setMessage(message);
        newDirectMessage.setTimestamp(new Date());

        directMessageRepo.save(newDirectMessage);


    }

    @Override
    public List<DirectMessageDTO> getMessages(long conversation_id){
       List<DirectMessageDTO> directMessageDTOList = new ArrayList<DirectMessageDTO>();

       Optional<Conversation> conversation = conversationRepo.findById(conversation_id);

       if(conversation.isEmpty()){
           throw new RuntimeException("Conversation ID is invalid");
       }

       Optional<List<DirectMessage>> directMessageList = directMessageRepo.findByConversationId(conversation.get().getConversation_id());

       if(directMessageList.isEmpty()){
          throw new RuntimeException("No Direct Message with this conversation Id");
       }

       if (directMessageList.get().isEmpty()){
           return directMessageDTOList;
       }

       directMessageList.get().forEach(directMessage->{
           DirectMessageDTO tempDTO = new DirectMessageDTO();
           tempDTO.setDirectMessage_id(directMessage.getDirectMessage_id());
           tempDTO.setMessage(directMessage.getMessage());
           tempDTO.setTimestamp(directMessage.getTimestamp());
           tempDTO.setProfile(directMessage.getProfile());
           tempDTO.setConversation(directMessage.getConversation());


           //setting profile_id and conversation_id
           tempDTO.setConversation_id(directMessage.getConversation().getConversation_id());
           tempDTO.setProfile_id(directMessage.getProfile().getProfile_id());

           directMessageDTOList.add(tempDTO);
       });


       return directMessageDTOList;
    }

}
