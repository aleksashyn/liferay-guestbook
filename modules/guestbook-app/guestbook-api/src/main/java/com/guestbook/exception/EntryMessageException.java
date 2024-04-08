package com.guestbook.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class EntryMessageException extends PortalException {

    public EntryMessageException() {
    }

    public EntryMessageException(String msg) {
        super(msg);
    }

    public EntryMessageException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public EntryMessageException(Throwable throwable) {
        super(throwable);
    }

}
