package br.com.zup.xyinc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZupException extends Exception {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZupException.class);
    public ZupException() {
        super();
    }

    public ZupException(String message) {
        super(message);
        LOGGER.error(message);
    }

    public ZupException(String message, Object... args) {
        super(String.format(message,args));
        LOGGER.error(String.format(message,args));
    }
    
    public ZupException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error(message, cause);
    }

}
