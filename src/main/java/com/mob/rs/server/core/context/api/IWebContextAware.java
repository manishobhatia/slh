/**
 * 
 */
package com.mob.rs.server.core.context.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The Interface IWebContextAware.
 *
 * @author cupton
 */
public interface IWebContextAware extends IAppContextAware
{
    /**
     * Gets the uri.
     *
     * @return the uri
     */
    public String getUri(boolean includeParms);
    
    /**
     * Gets the base uri.
     * 
     * @return the base uri
     */
    public String getBaseUri();

    /**
     * Gets the server uri.
     * 
     * @return the base uri
     */
    public String getServerUri();

    /**
     * Gets the header.
     * 
     * @param headerKeyParm the header key parm
     * @return the header
     */
    public String getHeader(String headerKeyParm);
    
    /**
     * Sets the request header.
     * 
     * @param headerKeyParm the header key parm
     * @param headerValueParm the header value parm
     */
    public void setHeader(String headerKeyParm, String headerValueParm);
    
    /**
     * Gets the headers.
     *
     * @return the headers
     */
    public String getHeaders();
    
    /**
     * Gets the session. This is the session for the current request. The session should not be used to store / retrieve
     * data across multiple request.
     * 
     * @return the session
     */
    public HttpSession getSession();

    /**
     * Gets the servlet response.
     * 
     * @return the servlet response
     */
    public HttpServletResponse getServletResponse();

    /**
     * Gets the servlet request.
     * 
     * @return the servlet request
     */
    public abstract HttpServletRequest getServletRequest();
    
}
