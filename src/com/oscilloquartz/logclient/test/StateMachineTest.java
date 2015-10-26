package com.oscilloquartz.logclient.test;

import com.oscilloquartz.logclient.fsm.StateMachine;

/**
 *
 */
public class StateMachineTest {

    public static void main(String[] args) {
        StateMachine sm = new StateMachine(null);

        sm.connect();
        sm.receiveGrant();
        sm.timeout();

    }
}
