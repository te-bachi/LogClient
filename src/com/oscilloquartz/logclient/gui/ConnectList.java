package com.oscilloquartz.logclient.gui;

import java.util.*;
import java.util.function.Consumer;
import java.util.prefs.Preferences;

/**
 *
 */
public class ConnectList extends Observable implements Iterable<String> {


    public static final String PREFERENCE_IPV4_ADDRESS[] = new String[] {
            "ipv4address1",
            "ipv4address2",
            "ipv4address3",
            "ipv4address4",
            "ipv4address5"
    };

    private ArrayList<String> connectList;
    private Preferences         preferences;

    public ConnectList() {
        this.preferences    = Preferences.userNodeForPackage(ConnectList.class);
        this.connectList    = new ArrayList<String>();

        open();
    }

    public void open() {
        String value;
        for (String key : PREFERENCE_IPV4_ADDRESS) {
            value = preferences.get(key, null);
            if (value != null) {
                connectList.add(value);
            }
        }
    }

    public void save() {
        String  value;
        int     i;

        for (i = 0; i < PREFERENCE_IPV4_ADDRESS.length && i < connectList.size(); i++) {
            value = connectList.get(i);
            preferences.put(PREFERENCE_IPV4_ADDRESS[i], value);
        }
    }

    public void push(String ipAddress) {
        if (connectList.contains(ipAddress)) {
            connectList.remove(ipAddress);
        }
        connectList.add(0, ipAddress);
        if (connectList.size() > PREFERENCE_IPV4_ADDRESS.length) {
            connectList.remove(connectList.size() - 1);
        }
        setChanged();
        notifyObservers();
    }

    @Override
    public Iterator<String> iterator() {
        return connectList.iterator();
    }

    @Override
    public void forEach(Consumer<? super String> action) {
        connectList.forEach(action);
    }

    @Override
    public Spliterator<String> spliterator() {
        return connectList.spliterator();
    }

    public int size() {
        return connectList.size();
    }
}
