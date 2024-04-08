package com.guestbook.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class GuestbookNameException extends PortalException {

    public GuestbookNameException() {
    }

    public GuestbookNameException(String msg) {
        super(msg);
    }

    public GuestbookNameException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public GuestbookNameException(Throwable throwable) {
        super(throwable);
    }

}
