/**
 * 
 */
package com.mob.rs.server.core.context.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mob.rs.server.core.context.api.IWebContextAware;
import com.mob.rs.server.core.util.web.HttpUtil;
import com.mob.rs.server.web.support.metadata.Param;


/**
 * The Class WebContextAware.
 * 
 * @author cupton
 */
public abstract class WebContextAware extends AppContextAware implements IWebContextAware
{

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IWebContextAware#getServletRequest()
     */
    @Override
    public HttpServletRequest getServletRequest()
    {
        return (HttpServletRequest) getApplicationContext().getRequestContext()
                .getServletRequest();
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IWebContextAware#getServletResponse()
     */
    @Override
    public HttpServletResponse getServletResponse()
    {
        return (HttpServletResponse) getApplicationContext().getRequestContext()
                .getServletResponse();
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IWebContextAware#getSession()
     */
    @Override
    public HttpSession getSession()
    {
        HttpSession session = getServletRequest().getSession();
        Param.ensureNotNull(session, Param.SESSION);
        return session;
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IWebContextAware#setHeader(java.lang.String, java.lang.String)
     */
    @Override
    public void setHeader(String headerKeyParm, String headerValueParm)
    {
        HttpUtil.setHeader(headerKeyParm, headerValueParm, getServletResponse());
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IWebContextAware#getHeader(java.lang.String)
     */
    @Override
    public String getHeader(String headerKeyParm)
    {
        return HttpUtil.getHeader(headerKeyParm, getServletRequest());
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IWebContextAware#getHeader(java.lang.String)
     */
    @Override
    public String getHeaders()
    {
        return HttpUtil.getHeadersAsString(getServletRequest());
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IWebContextAware#getServerUri()
     */
    @Override
    public String getServerUri()
    {
        return HttpUtil.getServerUri(getServletRequest());
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IWebContextAware#getBaseUri()
     */
    @Override
    public String getBaseUri()
    {
        // TODO The service path should not be hard coded
        return HttpUtil.getBaseUri(getServletRequest()) + "/services/rs";
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IWebContextAware#getUri()
     */
    @Override
    public String getUri(boolean includeParms)
    {
        return HttpUtil.getUri(getServletRequest(), includeParms);
    }

    
    
}
