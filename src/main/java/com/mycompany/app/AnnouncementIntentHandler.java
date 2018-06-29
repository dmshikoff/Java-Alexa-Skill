package com.mycompany.app;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class AnnouncementIntentHandler implements RequestHandler {
    public static final String ANNOUNCEMENT_KEY = "ANNOUNCEMENT";
    public static final String ANNOUNCEMENT_SLOT = "announcementContent";

    String speechText;


    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("AnnouncementIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();


        Slot announcementSlot = slots.get(ANNOUNCEMENT_SLOT);

        if (announcementSlot != null) {
            // Store the user's name in the Session and create response.
            String yourAnnouncement = announcementSlot.getValue();
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(ANNOUNCEMENT_KEY, yourAnnouncement));

            speechText = yourAnnouncement;
        }


        ResponseBuilder responseBuilder = input.getResponseBuilder();

        responseBuilder.withSimpleCard("HelloWorld", speechText)
                .withSpeech("<audio src='https://s3.amazonaws.com/daniel-alexa-announcement/rogerAnnouncement.mp3' />" + " " + speechText)
                .withShouldEndSession(false);


        return responseBuilder.build();

    }

}