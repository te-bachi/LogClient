package com.oscilloquartz.logclient.fsm;

/**
 *
 */
public class EstablishedState extends AbstractState {
    public EstablishedState(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public String getStateName() {
        return "Established";
    }

    @Override
    public void timeout() {
        stateMachine.setNextState(stateMachine.idleState);
    }
}
