/**
 * 
 */
package com.mob.rs.server.core.context.api;

import com.mob.rs.server.core.util.resources.IMessageSource;


/**
 * The Interface IAppContextAware.
 *
 * @author cupton
 */
public interface IAppContextAware
{

    /**
     * Sets the application context.
     *
     * @param applicationContext the application context
     */
    public void setApplicationContext(IApplicationContext applicationContext);
    
    /**
     * Gets the application context.
     * 
     * @return the application context
     */
    public IApplicationContext getApplicationContext();
    
    /**
     * Gets the message source. The message source should always be available.
     * 
     * @return the message source
     */
    public IMessageSource getMessageSource();
    
}
