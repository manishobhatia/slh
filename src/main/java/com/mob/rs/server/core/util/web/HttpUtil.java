/**
 * 
 */
package com.mob.rs.server.core.util.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mob.rs.server.core.util.Utils;
import com.mob.rs.server.core.util.string.Strings;




/**
 * The Class HttpUtil.
 * 
 * @author cupton
 */
public final class HttpUtil
{

    /**
     * Gets the server uri.
     * 
     * @param servletRequest the servlet request
     * @return the server uri
     */
    public static final String getServerUri(HttpServletRequest servletRequest)
    {
        if (servletRequest != null)
        {
            try
            {
                URL reconstructedURL = new URL(servletRequest.getScheme(), servletRequest.getServerName(),
                        servletRequest.getServerPort(), Strings.EMPTY);
                return reconstructedURL.toString();
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static final String getHeader(String headerName, HttpServletRequest servletRequest)
    {
        String headerValue = Utils.isAnyNull(headerName, servletRequest) ? null : servletRequest.getHeader(headerName);
        return !Utils.isBlank(headerValue) ? null : headerValue;
    }    
    
    /**
     * Gets the content type header. Null safe.
     * 
     * @param servletRequest the servlet request
     * @return the content type header
     */
    public static final String getHeadersAsString(HttpServletRequest servletRequest)
    {
        StringBuilder headers = new StringBuilder();
        Map<String, Set<String>> headerMap = getHeaderMap(servletRequest);
        Utils.buildDelimitedString(headerMap, Strings.COMMA, headers);
        return headers.toString();
    }    

    /**
     * Gets the header map.
     * 
     * @param servletRequest the servlet request
     * @return the header map
     */

    @SuppressWarnings("unchecked")
    public static final Map<String, Set<String>> getHeaderMap(HttpServletRequest servletRequest)
    {
        Map<String, Set<String>> headerMap = new LinkedHashMap<String, Set<String>>();
        if (servletRequest != null)
        {
            List<String> headerNames = Collections.list(servletRequest.getHeaderNames());
            for (String headerName : headerNames)
            {
                List<String> headerValues = Collections.list(servletRequest.getHeaders(headerName));
                headerMap.put(headerName, Utils.collectionToSet(headerValues));
            }
        }
        return headerMap;
    }    
 
    /**
     * Sets the header. Null safe.
     * 
     * @param headerName the header name
     * @param headerValue the header value
     * @param servletResponse the servlet response
     */
    public static final void setHeader(String headerName, String headerValue, HttpServletResponse servletResponse)
    {
        if (!Utils.isBlank(headerName) && !Utils.isBlank(headerValue) && servletResponse != null)
        {
            servletResponse.setHeader(headerName, headerValue);
        }
    }


    /**
     * Gets the base uri.
     * 
     * @param servletRequest the servlet request
     * @return the base uri
     */
    public static final String getBaseUri(HttpServletRequest servletRequest)
    {
        return (servletRequest == null) ? null : (getServerUri(servletRequest) + servletRequest.getContextPath());
    }    
    
    /**
     * Gets the uri. Null safe and returns null instead of blank for consistency.
     * 
     * @param servletRequest the servlet request
     * @return the uri
     */
    public static final String getUri(HttpServletRequest servletRequest, boolean includeParms)
    {
        String requestURI = null;
        if (servletRequest != null)
        {
            requestURI = servletRequest.getRequestURI();
            if (!Utils.isBlank(requestURI) && requestURI.endsWith("/"))
            {
                requestURI = requestURI.substring(0, requestURI.length() - 1);
            }
        }
        return requestURI;
    }
    
}
