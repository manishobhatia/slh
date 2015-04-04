/**
 * 
 */
package com.mob.rs.server.core.util.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import com.mob.rs.server.core.MediaTypeExtended;
import com.mob.rs.server.core.common.EResponseMessage;
import com.mob.rs.server.core.util.Utils;
import com.mob.rs.server.core.util.resources.IMessageSource;
import com.mob.rs.server.web.bind.IJsonContext;
import com.mob.rs.server.web.bind.IJsonNode;
import com.mob.rs.server.web.bind.adapter.json.error.JsonError;
import com.mob.rs.server.web.support.metadata.CommonNames;


/**
 * The Class JaxrsUtil.
 * 
 * @author cupton
 */
public final class JaxrsUtil
{

    /**
     * Block the constructor.
     */
    private JaxrsUtil()
    {
        super();
    }

    /**
     * Builds the error response.
     * 
     * @param errorMessageParm the error message parm
     * @param exceptionParm the exception parm
     * @param messageSourceParm the message source parm
     * @param localeParm the locale parm
     * @return the response
     */
    public static Response buildErrorResponse(final EResponseMessage errorMessageParm, final Throwable exceptionParm,
            final IMessageSource messageSourceParm, final Locale localeParm)
    {
        Response response = Response.serverError().build();
        if (!Utils.isAnyNull(errorMessageParm, localeParm))
        {
            final Status status = errorMessageParm.getStatus();
            final String message = errorMessageParm.getMessage(messageSourceParm, localeParm);
            JsonError error = new JsonError(message);
            if (exceptionParm != null)
            {
                error = new JsonError(message, exceptionParm);
            }
            response = Response.status(status).type(MediaTypeExtended.APPLICATION_JSON_UTF_8).entity(error).build();
        }
        return response;
    }

    /**
     * Builds the error response.
     * 
     * @param errorMessageParm the error message parm
     * @param messageSourceParm the message source parm
     * @param localeParm the locale parm
     * @return the response
     */
    public static Response buildErrorResponse(final EResponseMessage errorMessageParm,
            final IMessageSource messageSourceParm, final Locale localeParm)
    {
        return buildErrorResponse(errorMessageParm, null, messageSourceParm, localeParm);
    }

    /**
     * Apply limit and offset.
     * 
     * @param <T> the generic type
     * @param listOfElements the list of elements
     * @param offsetParm the offset parm
     * @param limitParm the limit parm
     * @return the list
     */
    public static <T> List<T> applyLimitAndOffset(final List<T> listOfElements, final int offsetParm,
            final int limitParm)
    {
        List<T> subList = new ArrayList<T>();
        if (!Utils.isEmpty(listOfElements))
        {
            final int size = listOfElements.size();
            if (offsetParm >= 0 && limitParm >= 0)
            {
                if (offsetParm <= size)
                {
                    final int limit = ((offsetParm + limitParm) > size) ? size : (offsetParm + limitParm);
                    final int offset = (offsetParm > size) ? size : offsetParm;
                    subList = listOfElements.subList(offset, limit);
                }
                else
                {
                    subList = new ArrayList<T>();
                }
            }
            else
            {
                subList = listOfElements;
            }
        }
        return subList;
    }

    /**
     * Builds the json link.
     * 
     * @param jsonContextParm the json context parm
     * @param relParm the rel parm
     * @param nameParm the name parm
     * @param uriParm the uri parm
     * @return the i json node
     */
    // This code should go away
    public static IJsonNode buildJsonLink(final IJsonContext jsonContextParm, final String relParm,
            final String nameParm, final String uriParm)
    {
        IJsonNode jsonLink = jsonContextParm.createJsonNode();
        jsonLink.add(CommonNames.REL, relParm);
        jsonLink.add(CommonNames.NAME, nameParm);
        jsonLink.add(CommonNames.URI, uriParm);
        return jsonLink;
    }

}
