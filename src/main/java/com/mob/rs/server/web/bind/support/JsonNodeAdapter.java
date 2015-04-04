/**
 * 
 */
package com.mob.rs.server.web.bind.support;

import com.mob.rs.server.web.bind.IJsonContext;
import com.mob.rs.server.web.bind.IJsonNode;




/**
 * The Class JsonNodeAdapter.
 *
 * @author cupton
 */
public class JsonNodeAdapter extends JsonAdapter<IJsonNode>
{

    /* (non-Javadoc)
     * @see com.nice.iex.wfmws.web.bind.json.support.JsonAdapter#toJson(java.lang.Object, com.nice.iex.wfmws.web.bind.json.IJsonContext)
     */
    @Override
    public IJsonNode toJson(IJsonNode valueParm, IJsonContext jsonContextParm)
    {
        return valueParm;
    }

}
