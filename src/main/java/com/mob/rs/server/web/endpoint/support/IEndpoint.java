/**
 * 
 */
package com.mob.rs.server.web.endpoint.support;


import com.mob.rs.server.core.context.api.IUserContextAware;
import com.mob.rs.server.core.context.api.IWebContextAware;
import com.mob.rs.server.web.bind.IJsonContext;


/**
 * The Interface IEndpoint. Represents common access methods for all REST endpoints.
 * 
 * @author cupton
 */
public interface IEndpoint extends IWebContextAware, IUserContextAware
{

    /**
     * Gets the json context.
     *
     * @return the json context
     */
    public IJsonContext getJsonContext();

    /**
     * Sets the json context.
     *
     * @param jsonContextParm the new json context
     */
    public void setJsonContext(IJsonContext jsonContextParm);

}
