package com.guestbook.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class EntryNameException extends PortalException {

    public EntryNameException() {
    }

    public EntryNameException(String msg) {
        super(msg);
    }

    public EntryNameException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public EntryNameException(Throwable throwable) {
        super(throwable);
    }

}
