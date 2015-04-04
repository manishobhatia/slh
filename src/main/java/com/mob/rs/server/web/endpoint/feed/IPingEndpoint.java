package com.mob.rs.server.web.endpoint.feed;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.mob.rs.server.core.MediaTypeExtended;
import com.mob.rs.server.web.common.cache.NoCache;
import com.mob.rs.server.web.endpoint.support.IEndpoint;
import com.mob.rs.server.web.support.metadata.Resource;

/**
 * The Interface IPingEndpoint.
 */
@Produces( { MediaTypeExtended.APPLICATION_JSON_UTF_8, MediaTypeExtended.TEXT_PLAIN_UTF_8 })
@Consumes( { MediaTypeExtended.APPLICATION_JSON_UTF_8, MediaTypeExtended.TEXT_PLAIN_UTF_8 })
@Path(Resource.PING)
public interface IPingEndpoint extends IEndpoint
{

    /**
     * Ping.
     *
     * @return the response
     */
    @GET
    @Path(Resource.ROOT)
    @NoCache
    public abstract Response ping();

}
