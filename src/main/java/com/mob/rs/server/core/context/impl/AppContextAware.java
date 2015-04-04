/**
 * 
 */
package com.mob.rs.server.core.context.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mob.rs.server.core.context.api.IAppContextAware;
import com.mob.rs.server.core.context.api.IApplicationContext;
import com.mob.rs.server.core.util.resources.IMessageSource;
import com.mob.rs.server.web.support.metadata.Param;




/**
 * The Class AppContextAware.
 * 
 * @author cupton
 */
public abstract class AppContextAware implements IAppContextAware
{

    /** The application context. */
    @Autowired
    private IApplicationContext applicationContext;

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IAppContextAware#getApplicationContext()
     */
    @Override
    public IApplicationContext getApplicationContext()
    {
        Param.ensureNotNull(applicationContext, Param.APPLICATION_CONTEXT);
        return applicationContext;
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IAppContextAware#getMessageSource()
     */
    @Override
    public IMessageSource getMessageSource()
    {
        return (applicationContext == null) ? null : applicationContext.getMessageSource();
    }

    /* (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.core.context.api.IAppContextAware#setApplicationContext(com.iex.tv.smartsync.rs.server.core.context.api.IApplicationContext)
     */
    @Override
    public void setApplicationContext(IApplicationContext applicationContextParm)
    {
        applicationContext = applicationContextParm;
    }

}
