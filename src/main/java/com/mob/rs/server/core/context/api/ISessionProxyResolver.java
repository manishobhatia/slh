/**
 * @copyright 2012 IEX, A NICE Company
 * @author cupton
 * @version "$Id: ISessionProxyResolver.java 90493 2012-11-16 22:12:58Z mahmad $"
 */
package com.mob.rs.server.core.context.api;

import com.mob.rs.server.session.ISessionProxy;

/**
 * The Interface ISessionProxyResolver.
 *
 * @author cupton
 * @since Oct 8, 2012
 */
public interface ISessionProxyResolver
{

    /**
     * Gets the session proxy.
     *
     * @return the session proxy
     */
    public ISessionProxy getSessionProxy();
    
}

