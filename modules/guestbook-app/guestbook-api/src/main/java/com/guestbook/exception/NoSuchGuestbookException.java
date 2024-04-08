package com.guestbook.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchGuestbookException extends NoSuchModelException {

    public NoSuchGuestbookException() {
    }

    public NoSuchGuestbookException(String msg) {
        super(msg);
    }

    public NoSuchGuestbookException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public NoSuchGuestbookException(Throwable throwable) {
        super(throwable);
    }

}
