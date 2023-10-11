package tn.esprit.usecase.RestController;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.admin.users.AdminUsersInviteRequest;
import com.slack.api.methods.request.conversations.ConversationsCreateRequest;
import com.slack.api.methods.request.conversations.ConversationsInviteRequest;
import com.slack.api.methods.request.users.UsersLookupByEmailRequest;
import com.slack.api.methods.response.admin.users.AdminUsersInviteResponse;
import com.slack.api.methods.response.conversations.ConversationsCreateResponse;
import com.slack.api.methods.response.conversations.ConversationsInviteResponse;
import com.slack.api.methods.response.users.UsersLookupByEmailResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.slack.api.Slack;
import com.slack.api.methods.request.users.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@RestController

public class SlackRestController {

    @PostMapping("/create-conversation")
    public String createConversation(@RequestParam String accessToken,
//                                     @RequestParam String userId1,
                                     @RequestParam String chanelName,
                                     @RequestParam String email,
                                     @RequestParam String userId2) {

        Slack slack = Slack.getInstance();

        try {
            ConversationsCreateResponse response = slack.methods().conversationsCreate(
                    ConversationsCreateRequest.builder()
                            .token(accessToken)
                            .name(chanelName).build()

            );

            if (!response.isOk()) {
                return "Error creating conversation: " + response.getError();
            }

            String channelId = response.getChannel().getId();

//            AdminUsersInviteRequest inviteRequest = AdminUsersInviteRequest.builder().email(email).channelIds(Arrays.asList(channelId))
//                    .customMessage("Welcome to our Slack workspace!").build();
//
//
//            // Call the users.admin.invite method
//            AdminUsersInviteResponse inviteResponse = slack.methods(accessToken).adminUsersInvite(inviteRequest);
//
//            // Print the result of the invite request
//            System.out.println(inviteResponse.toString());



            UsersLookupByEmailRequest lookupRequest = UsersLookupByEmailRequest.builder()
                    .token(accessToken)
                    .email(email)
                    .build();

            UsersLookupByEmailResponse lookupResponse = slack.methods().usersLookupByEmail(lookupRequest);
            System.out.println(email);
            System.out.println(lookupResponse);
            String guestUserId = lookupResponse.getUser().getId();




            ConversationsInviteResponse inviteResponse = slack.methods().conversationsInvite(
                    ConversationsInviteRequest.builder()
                            .token(accessToken)
                            .channel(channelId)
                            .users(Arrays.asList(guestUserId))
                            .build()
            );

            if (!inviteResponse.isOk()) {
                return "Error inviting users to conversation: " + inviteResponse.getError();
            }

            return "Conversation created with ID " + channelId;

        } catch (IOException | SlackApiException e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/invite-user")
    public ResponseEntity<String> inviteUserToWorkspace(@RequestParam String accessToken,@RequestParam String email) {

        Slack slack = Slack.getInstance();
        AdminUsersInviteRequest inviteRequest = AdminUsersInviteRequest.builder()
                .token(accessToken)
                .email(email)
                .channelIds(Arrays.asList("C053LLEA02X"))
                .teamId("T053GJSBHAR")
                .build();
        System.out.println(inviteRequest);
        try {
            AdminUsersInviteResponse inviteResponse = slack.methods().adminUsersInvite(inviteRequest);

            if (inviteResponse.isOk()) {
                return new ResponseEntity<>("User invited to workspace", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to invite user: " + inviteResponse.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Failed to invite user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
