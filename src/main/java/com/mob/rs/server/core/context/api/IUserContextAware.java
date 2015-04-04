/**
 * 
 */
package com.mob.rs.server.core.context.api;

import java.util.Locale;
import java.util.Properties;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import com.mob.rs.server.session.ISessionProxy;


/**
 * The Interface IUserContextAware.
 *
 * @author cupton
 */
public interface IUserContextAware extends IAppContextAware
{

    
    /**
     * Gets the logged in user preferences.
     *
     * @return the preferences
     */
    public Properties getPreferences();

    /**
     * Gets the locale. This is the best known locale. The user locale is retrieved and if it is null (the user is not
     * authenticated) then we fall back to the client locale. If the client locale is not specified then the default
     * locale is sent.
     * 
     * @return the locale
     */
    public Locale getlocale();

    /**
     * This could return null for methods that are not authenticated.
     * 
     * @return the date time zone
     */
    public DateTimeZone getDateTimeZone();
    
    /**
     * Gets the local date for the current user. This could return null for methods that are not authenticated.
     *
     * @return the today
     */
    public LocalDate getToday();
    
    /**
     * Gets the session proxy. The session proxy should always be non-null except for when the controller method is
     * marked as unauthenticated. The session proxy is attached to the request when the request is created. This is done
     * in the request context listener. Controllers are advised through APO to authenticate if the session proxy is not
     * found.
     * 
     * @return the session
     */
    public ISessionProxy getSessionProxy();
    
}
