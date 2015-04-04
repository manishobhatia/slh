/**
 * @copyright 2007 IEX, A NICE Company
 * @author cgulledge
 * @version "$Id: ISessionProxy.java 90493 2012-11-16 22:12:58Z mahmad $"
 */
package com.mob.rs.server.session;

import java.io.Serializable;
import java.util.Properties;


/**
 * Class containing client-side user session state information.
 * 
 * @author cgulledge
 * @since Jun 7, 2007
 */
public interface ISessionProxy extends Serializable
{
    /**
     * Enumeration of session states stored in a session proxy.
     * 
     * @author cgulledge
     * @since Jun 11, 2007
     */
    public enum State
    {
        /** Session was established and is available for services. */
        ESTABLISHED,

        /** Authentication failed, session is a null proxy that can not be used for services. */
        AUTHENTICATION_FAILED,

        /** Authentication succeeded, but a password change was required and no new password supplied. */
        PASSWORD_CHANGE_REQUIRED,

        /** Authentication succeeded, but a password change was required and no new password supplied. */
        ALREADY_AUTHENTICATED,

        /** A session could not be established for some other reason than authentication. */
        SESSION_UNAVAILABLE;
    }

    /**
     * Get the state of the session that this is a proxy for.
     * 
     * @return session state
     */
    public State getSessionState();

    /**
     * Get the unique session identifier.
     * 
     * @return session identifier
     */
    public String getSessionId();

 
    /**
     * Get the user's preferences as a property list. Presently, this is only a copy of the preferences as they existed
     * at the time the user logged in.
     * 
     * @return user's preferences.
     */
    public Properties getPreferences();


}
