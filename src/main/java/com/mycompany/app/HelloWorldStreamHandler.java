package com.mycompany.app;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;

import com.mycompany.app.CancelandStopIntentHandler;
import com.mycompany.app.HelloWorldIntentHandler;
import com.mycompany.app.HelpIntentHandler;
import com.mycompany.app.SessionEndedRequestHandler;
import com.mycompany.app.LaunchRequestHandler;

public class HelloWorldStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(new CancelandStopIntentHandler(), new HelloWorldIntentHandler(), new HelpIntentHandler(), new LaunchRequestHandler(), new SessionEndedRequestHandler(), new HelloPersonIntentHandler(), new AnnouncementIntentHandler())
                .build();
    }

    public HelloWorldStreamHandler() {
        super(getSkill());
    }

}
