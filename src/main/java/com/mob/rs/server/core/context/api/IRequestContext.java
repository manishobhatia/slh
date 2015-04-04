/**
 * 
 */
package com.mob.rs.server.core.context.api;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.mob.rs.server.session.ISessionProxy;



/**
 * The Interface IRequestContext.
 * 
 * @author cupton
 */
public interface IRequestContext
{

    /**
     * Gets the servlet context.
     * 
     * @return the servlet context
     */
    public ServletContext getServletContext();

    /**
     * Sets the servlet context.
     * 
     * @param servletContextParm the new servlet context
     */
    public void setServletContext(ServletContext servletContextParm);

    /**
     * Gets the servlet request.
     * 
     * @return the servlet request
     */
    public ServletRequest getServletRequest();

    /**
     * Sets the servlet request.
     * 
     * @param servletRequestParm the new servlet request
     */
    public void setServletRequest(ServletRequest servletRequestParm);

    /**
     * Gets the servlet response.
     * 
     * @return the servlet response
     */
    public ServletResponse getServletResponse();

    /**
     * Sets the servlet response.
     * 
     * @param servletResponseParm the new servlet response
     */
    public void setServletResponse(ServletResponse servletResponseParm);

    /**
     * Gets the sessionproxy.
     * 
     * @return the sessionproxy
     */
    public ISessionProxy getSessionProxy();

    /**
     * Sets the sessionproxy.
     * 
     * @param sessionproxyParm the new sessionproxy
     */
    public void setSessionProxy(ISessionProxy sessionproxyParm);

    /**
     * Gets the client locale.  This will be the locale send by the client.  I.e. Accept-Language.
     *
     * @return the client locale
     */
    public Locale getClientLocale();
    
    /**
     * Gets the user locale. This will be the user's locale from the session proxy preferences. This is set when the
     * user logs in and will not be refreshed unless the user logs in again.
     * 
     * @return the user locale
     */
    public Locale getUserLocale();
    
    /**
     * Gets the locale. This is the best known locale. The user locale is retrieved and if it is null (the user is not
     * authenticated) then we fall back to the client locale. If the client locale is not specified then the default
     * locale is sent.
     * 
     * @return the locale
     */
    public Locale getLocale();
}
