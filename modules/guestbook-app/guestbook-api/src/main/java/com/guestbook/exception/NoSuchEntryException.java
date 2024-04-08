package com.guestbook.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchEntryException extends NoSuchModelException {

    public NoSuchEntryException() {
    }

    public NoSuchEntryException(String msg) {
        super(msg);
    }

    public NoSuchEntryException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public NoSuchEntryException(Throwable throwable) {
        super(throwable);
    }

}
