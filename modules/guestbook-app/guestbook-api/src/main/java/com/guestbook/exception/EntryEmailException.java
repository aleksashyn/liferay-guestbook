package com.guestbook.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class EntryEmailException extends PortalException {

    public EntryEmailException() {
    }

    public EntryEmailException(String msg) {
        super(msg);
    }

    public EntryEmailException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public EntryEmailException(Throwable throwable) {
        super(throwable);
    }

}
