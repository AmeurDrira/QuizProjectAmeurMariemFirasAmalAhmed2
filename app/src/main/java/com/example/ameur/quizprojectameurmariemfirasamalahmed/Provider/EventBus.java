package com.example.ameur.quizprojectameurmariemfirasamalahmed.EventProvider;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by !L-PAzZ0 on 18/06/2016.
 */
public class EventBus {
    private static final Bus uniqueInstance=new Bus(ThreadEnforcer.ANY);
    public static Bus getInstance()
    {
        return uniqueInstance;
    }

}