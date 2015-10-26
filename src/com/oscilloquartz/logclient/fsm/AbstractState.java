package com.oscilloquartz.logclient.fsm;

/**
 *
 */
public abstract class AbstractState implements State {

    protected StateMachine  stateMachine;

    public AbstractState(StateMachine stateMachine) {
        this.stateMachine   = stateMachine;
    }

    public abstract String getStateName();

    public void connect() {
        error("connect");
    }

    public void receiveReject() {
        error("receiveReject");
    }

    public void receiveGrant() {
        error("receiveGrant");
    }

    public void timeout() {
        error("timeout");
    }

    private void error(String action) {
        System.out.println("[" + getStateName() + "] " + action + ": no action");
    }
}
