/**
 * 
 */
package com.mob.rs.server.core.common;
import java.util.Locale;

import javax.ws.rs.core.Response.Status;

import com.mob.rs.server.core.util.resources.IMessageSource;


/**
 * The Enum EResponseMessage.
 * 
 * @author cupton
 */
public enum EResponseMessage
{
 
    /** The authentication failed error. */
    AUTHENTICATION_FAILED(Status.UNAUTHORIZED, "com.nice.iex.wfmws.authentication.failed"),

    /** The invalid authentication state error. */
    INVALID_AUTHENTICATION_STATE(Status.UNAUTHORIZED, "com.nice.iex.wfmws.invalid.authentication.state"),
    
    /** The not authenticated error. */
    NOT_AUTHENTICATED(Status.UNAUTHORIZED, "com.nice.iex.wfmws.not.authenticated"),

    /** The user not found error. */
    USER_NOT_FOUND(Status.UNAUTHORIZED, "com.nice.iex.wfmws.user.not.found"),
    
    /** The not modified. */
    NOT_MODIFIED(Status.NOT_MODIFIED, "com.nice.iex.wfmws.not.modified"),
    
    /** The resource not found error. */
    RESOURCE_NOT_FOUND(Status.NOT_FOUND, "com.nice.iex.wfmws.not.found"),
    
    /** The internal server error. */
    INTERNAL_SERVER_ERROR(Status.INTERNAL_SERVER_ERROR, "com.nice.iex.wfmws.internal.server.error");
    
    /** The status. */
    private Status status;

    /** The message key. */
    private String messageKey;

    /**
     * Instantiates a new e message.
     * 
     * @param messageKeyParm the message key parm
     */
    private EResponseMessage(Status statusParm, String messageKeyParm)
    {
        this.status = statusParm;
        this.messageKey = messageKeyParm;
    }

    /**
     * Gets the message.
     * 
     * @param messageSource the message source
     * @param localeParm the locale parm
     * @return the message
     */
    public String getMessage(IMessageSource messageSource, Locale localeParm)
    {
        return (messageSource == null) ? "{" + messageKey + "}" : messageSource.getMessage(messageKey,
            localeParm, "{" + messageKey + "}");
    }

    /**
     * Gets the status.
     * 
     * @return the status
     */
    public Status getStatus()
    {
        return status;
    }

    /**
     * Gets the message key.
     *
     * @return the message key
     */
    public String getMessageKey()
    {
        return messageKey;
    }

}
