/**
 * 
 */
package com.mob.rs.server.core.context.impl;

import java.util.Locale;
import java.util.Properties;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import com.mob.rs.server.core.context.api.IUserContextAware;
import com.mob.rs.server.session.ISessionProxy;
import com.mob.rs.server.web.support.metadata.Param;


/**
 * The Class UserContextAware.
 * 
 * @author cupton
 */
public abstract class UserContextAware extends WebContextAware implements IUserContextAware
{

    @Override
    public Locale getlocale()
    {
        return getApplicationContext().getRequestContext().getLocale();
    }
    
    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.web.controller.support.ITvController#getToday()
     */
    @Override
    public LocalDate getToday()
    {
        //return DateUtils.getToday(getDateTimeZone());
    	return new LocalDate();
    }    

    @Override
    public DateTimeZone getDateTimeZone()
    {
        return DateTimeZone.getDefault();
    }
    
    @Override
    public Properties getPreferences()
    {
        Properties preferences = getSessionProxy().getPreferences();
        Param.ensureNotNull(preferences, Param.PREFERENCES);
        return preferences;
    }    
    
    @Override
    public ISessionProxy getSessionProxy()
    {
        ISessionProxy sessionProxy = getApplicationContext().getRequestContext().getSessionProxy();
        Param.ensureNotNull(sessionProxy, Param.SESSION_PROXY);
        return sessionProxy;
    }    
    
}
