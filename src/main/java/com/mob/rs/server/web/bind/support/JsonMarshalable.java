/**
 * 
 */
package com.mob.rs.server.web.bind.support;

import com.mob.rs.server.web.bind.IJsonContext;
import com.mob.rs.server.web.bind.IJsonMarshalable;
import com.mob.rs.server.web.bind.IJsonNode;
import com.mob.rs.server.web.bind.JsonMarshallingException;



/**
 * The Class JsonMarshalable.  Base class with starter implementations of fromJson and toJson.
 *
 * @param <T> the generic type
 * @author cupton
 */
public class JsonMarshalable implements IJsonMarshalable
{

    /* (non-Javadoc)
     * @see com.nice.iex.wfmws.web.bind.json.IJsonMarshalable#fromJson(com.nice.iex.wfmws.web.bind.json.IJsonNode, com.nice.iex.wfmws.web.bind.json.IJsonContext)
     */
    @Override
    public void fromJson(IJsonNode jsonNodeParm, IJsonContext jsonContextParm) throws JsonMarshallingException
    {
        throw new JsonMarshallingException("JSON deserialization not supported");
    }

    /* (non-Javadoc)
     * @see com.nice.iex.wfmws.web.bind.json.IJsonMarshalable#toJson(com.nice.iex.wfmws.web.bind.json.IJsonContext)
     */
    @Override
    public IJsonNode toJson(IJsonContext jsonContextParm) throws JsonMarshallingException
    {
        throw new JsonMarshallingException("JSON serialization not supported");
    }

}
