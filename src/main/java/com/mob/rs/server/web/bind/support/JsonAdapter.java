/**
 * 
 */
package com.mob.rs.server.web.bind.support;

import com.mob.rs.server.web.bind.IJsonAdapter;
import com.mob.rs.server.web.bind.IJsonContext;
import com.mob.rs.server.web.bind.IJsonNode;
import com.mob.rs.server.web.bind.JsonMarshallingException;



/**
 * The Class JsonAdapter.
 * 
 * @param <T> the generic type
 * @author cupton
 */
public abstract class JsonAdapter<T extends Object> implements IJsonAdapter<T>
{

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.web.bind.json.IJsonAdapter#fromJson(com.nice.iex.wfmws.web.bind.json.IJsonNode,
     * com.nice.iex.wfmws.web.bind.json.IJsonContext)
     */
    @Override
    public T fromJson(IJsonNode jsonNodeParm, IJsonContext jsonContextParm) throws JsonMarshallingException
    {
        throw new JsonMarshallingException("JSON deserialization not supported");
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.web.bind.json.IJsonAdapter#toJson(java.lang.Object,
     * com.nice.iex.wfmws.web.bind.json.IJsonContext)
     */
    @Override
    public IJsonNode toJson(T valueParm, IJsonContext jsonContextParm) throws JsonMarshallingException
    {
        throw new JsonMarshallingException("JSON serialization not supported");
    }

}
