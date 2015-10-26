package com.oscilloquartz.logclient.fsm;

/**
 *
 */
public class ConnectState extends AbstractState {

    public ConnectState(StateMachine stateMachine) {
        super(stateMachine);

    }

    @Override
    public String getStateName() {
        return "Connect";
    }

    @Override
    public void receiveReject() {
        stateMachine.setNextState(stateMachine.idleState);
    }

    @Override
    public void receiveGrant() {
        stateMachine.setNextState(stateMachine.establishedState);
    }

    @Override
    public void timeout() {
    }
}
