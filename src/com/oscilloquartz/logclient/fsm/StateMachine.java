package com.oscilloquartz.logclient.fsm;

import com.oscilloquartz.logclient.gui.MainFrame;

/**
 *
 */
public class StateMachine implements State {

    protected final State   idleState           = new IdleState(this);
    protected final State   connectState        = new ConnectState(this);
    protected final State   establishedState    = new EstablishedState(this);

    private State           currentState        = idleState;

    protected MainFrame     mainFrame;

    public StateMachine(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public String getStateName() {
        return "StateMachine";
    }

    @Override
    public void connect() {
        currentState.connect();
        mainFrame.getStatusBar().setState(currentState.getStateName());
    }

    @Override
    public void receiveReject() {
        currentState.receiveReject();
        mainFrame.getStatusBar().setState(currentState.getStateName());
    }

    @Override
    public void receiveGrant() {
        currentState.receiveGrant();
        mainFrame.getStatusBar().setState(currentState.getStateName());
    }

    @Override
    public void timeout() {
        currentState.timeout();
        mainFrame.getStatusBar().setState(currentState.getStateName());
    }

    public State getCurrentState() {
        return currentState;
    }

    protected void setNextState(State nextState) {
        this.currentState = nextState;
    }
}
