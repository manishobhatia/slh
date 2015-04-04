/**
 * 
 */
package com.mob.rs.server.web.endpoint.feed;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.mob.rs.server.web.endpoint.support.Endpoint;

/**
 * The Class PingEndpoint.
 * 
 * @author cupton
 */
@Component("PingEndpoint")
public class PingEndpoint extends Endpoint implements IPingEndpoint
{

    /** The logger to use. */
    //private static final TvLogger LOGGER = new TvLogger();

    /* (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.web.endpoint.ping.IPingEndpoint#ping()
     */
    public Response ping()
    {
        return Response.ok("pong").build();
    }


}
