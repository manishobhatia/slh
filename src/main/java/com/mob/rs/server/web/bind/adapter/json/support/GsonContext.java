/**
 * 
 */
package com.mob.rs.server.web.bind.adapter.json.support;

import org.springframework.stereotype.Component;

import com.mob.rs.server.web.bind.IJsonContext;
import com.mob.rs.server.web.bind.IJsonNode;
import com.mob.rs.server.web.bind.builder.json.GsonNode;



/**
 * The Class GsonContext.
 *
 * @author cupton
 */
@Component("GsonContext")
public class GsonContext implements IJsonContext
{

    /* (non-Javadoc)
     * @see com.nice.iex.wfmws.web.bind.json.IJsonContext#createJsonNode()
     */
    @Override
    public IJsonNode createJsonNode()
    {
        return new GsonNode();
    }

}
