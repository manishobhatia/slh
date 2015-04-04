/**
 * 
 */
package com.mob.rs.server.web.endpoint.support;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;


import com.mob.rs.server.core.common.EResponseMessage;
import com.mob.rs.server.core.context.impl.UserContextAware;
import com.mob.rs.server.core.util.web.JaxrsUtil;
import com.mob.rs.server.web.bind.IJsonContext;
import com.mob.rs.server.web.support.metadata.Param;


/**
 * The Class Endpoint. This is the core REST endpoint that should be extended by all other REST endpoints. By default it
 * gives access to some to some of the @Context injected resources. CFX injects ThreadLocal wrapped versions of UriInfo
 * and HttpHeaders.
 * 
 * @author cupton
 */
public abstract class Endpoint extends UserContextAware implements IEndpoint
{

    /** The json context. */
    @Autowired
    private IJsonContext jsonContext;

    /**
     * Gets the logger.
     * 
     * @return logger
     */
    //@@protected abstract TvLogger getLogger();

    /**
     * Builds the error response.
     * 
     * @param errorMessageParm the error message parm
     * @param throwable the throwable
     * @return the response
     */
    public Response buildErrorResponse(EResponseMessage errorMessageParm, Throwable throwable)
    {
        return JaxrsUtil.buildErrorResponse(errorMessageParm, throwable, getMessageSource(), getlocale());
    }

    /**
     * Builds the error response.
     * 
     * @param errorMessageParm the error message parm
     * @return the response
     */
    public Response buildErrorResponse(EResponseMessage errorMessageParm)
    {
        return JaxrsUtil.buildErrorResponse(errorMessageParm, getMessageSource(), getlocale());
    }

    /**
     * Resource not found.
     * 
     * @return the response
     */
    public Response resourceNotFound()
    {
        return JaxrsUtil.buildErrorResponse(EResponseMessage.RESOURCE_NOT_FOUND, getMessageSource(), getlocale());
    }

    /*
     * (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.web.endpoint.support.IEndpoint#getJsonContext()
     */
    @Override
    public IJsonContext getJsonContext()
    {
        return jsonContext;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.iex.tv.smartsync.rs.server.web.endpoint.support.IEndpoint#setJsonContext(com.iex.tv.smartsync.rs.server.web
     * .bind.IJsonContext)
     */
    @Override
    public void setJsonContext(IJsonContext jsonContextParm)
    {
        jsonContext = jsonContextParm;
    }

    
    /**
     * Not null.
     *
     * @param objectParm the object parm
     * @param messageParm the message parm
     */
    public void ensureNotNull(Object objectParm, String messageParm) {
        Param.ensureNotNull(objectParm, messageParm);
    }
    
    /*
     * We can use the javax annotation lifecycle methods
     */
    /**
     * Inits the.
     */
    @PostConstruct
    protected void init()
    {
        //@@getLogger().debug(this.getClass().getSimpleName(), ".init()");
    }

    /**
     * Destroy.
     */
    @PreDestroy
    protected void destroy()
    {
        ///@@getLogger().debug(this.getClass().getSimpleName(), ".destroy()");
    }

}
