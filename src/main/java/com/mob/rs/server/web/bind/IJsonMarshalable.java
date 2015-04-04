/**
 * 
 */
package com.mob.rs.server.web.bind;

/**
 * The Interface IJsonMarshalable is an interface that an object can implement to provide the ability to serialize /
 * deserialize to / from JSON. This interface is independent of JSON provider. This is a convenience interface that
 * should be used on objects that are temporary like DTOs or anonymous classes. For domain objects that might be nested
 * inside other domain objects you should use IJsonAdapter.
 * 
 * @param <T> the generic type
 * @author cupton
 */
public interface IJsonMarshalable
{

    /**
     * Deserialize from a JSON node into the current object.
     * 
     * @param jsonNodeParm the json node parm
     * @param typeParm the type parm
     * @return the t
     */
    public void fromJson(IJsonNode jsonNodeParm, IJsonContext jsonContextParm) throws JsonMarshallingException;

    /**
     * Serialize the current object into a JSON node.
     * 
     * @param jsonNodeParm the json node parm
     * @param valueParm the value parm
     * @return the i json node
     * @throws JsonMarshallingException the json marshalling exception
     */
    public IJsonNode toJson(IJsonContext jsonContextParm) throws JsonMarshallingException;

}
