/**
 * 
 */
package com.mob.rs.server.web.bind.adapter.json.support;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.mob.rs.server.web.bind.IJsonMarshalable;
import com.mob.rs.server.web.bind.IJsonNode;
import com.mob.rs.server.web.bind.JsonMarshallingException;


/**
 * This class adapts an instance of IJsonMarshalable to the GSON marshaling framework.
 * 
 * @param <T> the generic type
 * @author cupton
 */
public class GsonJsonMarshalable<T extends IJsonMarshalable> extends BaseGsonJson<T> 
{

    /*
     * (non-Javadoc)
     * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
     * com.google.gson.JsonSerializationContext)
     */
    @Override
    public JsonElement serialize(T valueParm, Type typeParm, JsonSerializationContext jsonContextParm)
    {
        try
        {
            return fromJsonNode(valueParm.toJson(getJsonContext()));
        }
        catch (JsonMarshallingException e)
        {
            throw new JsonParseException(e);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
     * com.google.gson.JsonDeserializationContext)
     */
    @Override
    public T deserialize(JsonElement jsonElementParm, Type typeParm, JsonDeserializationContext jsonContextParm)
            throws JsonParseException
    {
        try
        {
            IJsonNode jsonNode = toJsonNode(jsonElementParm);
            T createInstance = createInstance(typeParm);
            if (jsonNode != null)
            {
                createInstance.fromJson(jsonNode, getJsonContext());
            }
            return createInstance;
        }
        catch (JsonMarshallingException e)
        {
            throw new JsonParseException(e);
        }
    }


    
}
