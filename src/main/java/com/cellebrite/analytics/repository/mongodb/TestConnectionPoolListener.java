package com.cellebrite.analytics.repository.mongodb;

import com.mongodb.event.*;


public class TestConnectionPoolListener implements ConnectionPoolListener {

	@Override
    public void connectionPoolOpened(final ConnectionPoolOpenedEvent event) {
        System.out.println(event);
    }

    @Override
    public void connectionPoolClosed(final ConnectionPoolClosedEvent event) {
        System.out.println(event);
    }

    @Override
    public void connectionCheckedOut(final ConnectionCheckedOutEvent event) {
        System.out.println(event);
    }

    @Override
    public void connectionCheckedIn(final ConnectionCheckedInEvent event) {
        System.out.println(event);
    }

    @Override
    public void waitQueueEntered(final ConnectionPoolWaitQueueEnteredEvent event) {
        System.out.println(event);
    }

    @Override
    public void waitQueueExited(final ConnectionPoolWaitQueueExitedEvent event) {
        System.out.println(event);
    }

    @Override
    public void connectionAdded(final ConnectionAddedEvent event) {
        System.out.println(event);
    }

    @Override
    public void connectionRemoved(final ConnectionRemovedEvent event) {
        System.out.println(event);
    }
}