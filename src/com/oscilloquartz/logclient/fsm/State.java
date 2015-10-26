package com.oscilloquartz.logclient.fsm;

/**
 *
 */
public interface State {
    public String getStateName();

    public void connect();
    public void receiveReject();
    public void receiveGrant();
    public void timeout();
}
