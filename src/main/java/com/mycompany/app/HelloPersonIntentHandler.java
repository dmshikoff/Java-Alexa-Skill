package com.mycompany.app;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class HelloPersonIntentHandler implements RequestHandler {
    public static final String NAME_KEY = "NAME";
    public static final String NAME_SLOT = "name";

    String speechText;


    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("HelloPersonIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();


        Slot yourNameSlot = slots.get(NAME_SLOT);

        if (yourNameSlot != null) {
            // Store the user's name in the Session and create response.
            String yourName = yourNameSlot.getValue();
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(NAME_KEY, yourName));

            speechText = "Hello " + yourName;
        }


        ResponseBuilder responseBuilder = input.getResponseBuilder();

        responseBuilder.withSimpleCard("HelloWorld", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false);


        return responseBuilder.build();

    }

}
