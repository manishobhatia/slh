/**
 * 
 */
package com.mob.rs.server.web.bind;


/**
 * The Interface IJsonAdapter.
 *
 * @param <T> the generic type
 * @author cupton
 */
public interface IJsonAdapter<T extends Object>
{
    
    /**
     * Deserialize from a JSON node into the current object.
     * 
     * @param jsonNodeParm the json node parm
     * @param typeParm the type parm
     * @return the t
     */
    public T fromJson(IJsonNode jsonNodeParm, IJsonContext jsonContextParm) throws JsonMarshallingException;

    /**
     * Serialize the current object into a JSON node.
     * 
     * @param jsonNodeParm the json node parm
     * @param valueParm the value parm
     * @return the i json node
     * @throws JsonMarshallingException the json marshalling exception
     */
    public IJsonNode toJson(T valueParm, IJsonContext jsonContextParm) throws JsonMarshallingException;
    
}
