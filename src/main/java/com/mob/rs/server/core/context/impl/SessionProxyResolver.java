package com.mob.rs.server.core.context.impl;

import org.springframework.stereotype.Component;

import com.mob.rs.server.core.context.api.ISessionProxyResolver;
import com.mob.rs.server.session.ISessionProxy;

/**
 * The Class SessionProxyResolver.
 * 
 * @author cupton
 * @since Oct 8, 2012
 */
@Component("ISessionProxyResolver")
public class SessionProxyResolver implements ISessionProxyResolver
{

    /** The user service. */
    //@@@Autowired
    //@@private IUserService userService;
    
    
    /* (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.core.context.api.ISessionProxyResolver#getSessionProxy()
     */
    @Override
    public ISessionProxy getSessionProxy()
    {
    	/*
        try
        {
            return userService.getSessionProxy();
        }
        catch (TvServiceException e)
        {
            // Ignore and return null indicating that we are not logged in
        }
        */
        return null;
    }


    /**
     * Sets the user service.
     *
     * @param userServiceParm the new user service
     */
    /*
    public void setUserService(IUserService userServiceParm)
    {
        //@@userService = userServiceParm;
    }
    */

}

