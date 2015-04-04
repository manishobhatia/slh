/**
 * 
 */
package com.mob.rs.server.web.bind.adapter.json.error;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.mob.rs.server.core.util.Utils;
import com.mob.rs.server.web.bind.IJsonContext;
import com.mob.rs.server.web.bind.IJsonNode;
import com.mob.rs.server.web.bind.JsonMarshallingException;
import com.mob.rs.server.web.bind.support.JsonMarshalable;
import com.mob.rs.server.web.support.metadata.CommonNames;



/**
 * The Class ErrorAdapter.
 * 
 * @author cupton
 */
public class JsonError extends JsonMarshalable
{

    /** The message. */
    private String message;

    /** The exception. */
    private Throwable exception;

    /**
     * Instantiates a new error wrapper.
     * 
     * @param messageParm the message parm
     */
    public JsonError(String messageParm)
    {
        super();
        message = messageParm;
    }

    /**
     * Instantiates a new json error.
     * 
     * @param messageParm the message parm
     * @param exceptionParm the exception parm
     */
    public JsonError(String messageParm, Throwable exceptionParm)
    {
        super();
        message = messageParm;
        exception = exceptionParm;
    }

    /**
     * Instantiates a new error adapter.
     */
    public JsonError()
    {
        super();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.iex.tv.smartsync.rs.server.web.bind.support.JsonMarshalable#toJson(com.iex.tv.smartsync.rs.server.web.bind
     * .IJsonContext)
     */
    @Override
    public IJsonNode toJson(IJsonContext jsonContextParm) throws JsonMarshallingException
    {
        IJsonNode jsonNode = jsonContextParm.createJsonNode().add(CommonNames.MESSAGE, message);
        if (exception != null)
        {
            jsonNode.add(CommonNames.EXCEPTION, exception.getLocalizedMessage());
        }
        return jsonContextParm.createJsonNode().add(CommonNames.MESSAGE, message).wrap(CommonNames.ERROR);
    }

    /**
     * Gets the message.
     * 
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Sets the message.
     * 
     * @param messageParm the new message
     */
    public void setMessage(String messageParm)
    {
        message = messageParm;
    }

    /**
     * Gets the exception.
     *
     * @return the exception
     */
    public Throwable getException()
    {
        return exception;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        Map<String, Object> attributes = new LinkedHashMap<String, Object>(2);
        attributes.put("message", getMessage());
        attributes.put("exception", getException());
        return Utils.toStringHelper(this, attributes).toString();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(9, 43).append(getMessage()).append(getException()).toHashCode();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object objParm)
    {
        if (objParm instanceof JsonError == false)
        {
            return false;
        }
        if (this == objParm)
        {
            return true;
        }
        JsonError rhs = (JsonError) objParm;
        return new EqualsBuilder().append(getMessage(), rhs.getMessage()).append(getException(), rhs.getException()).isEquals();
    }

}
