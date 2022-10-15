package org.microq.client.exception;

import java.io.IOException;

public class MicroQueueGeneralException extends RuntimeException {
    private String exception;

    public MicroQueueGeneralException(String exception) {
        this.exception = exception;
    }

}
