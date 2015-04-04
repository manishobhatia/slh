/**
 * 
 */
package com.mob.rs.server.web.bind.adapter.json.support;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mob.rs.server.web.bind.IJsonContext;
import com.mob.rs.server.web.bind.IJsonNode;
import com.mob.rs.server.web.bind.builder.json.GsonNode;


/**
 * The Class BaseGsonJson.
 *
 * @author cupton
 */
public class BaseGsonJson <T extends Object> implements JsonSerializer<T>, InstanceCreator<T>, JsonDeserializer<T> 
{

    /** The JSON_CONTEXT. */
    private static IJsonContext JSON_CONTEXT = new GsonContext();
    
    /**
     * Gets the json context.
     *
     * @return the json context
     */
    public IJsonContext getJsonContext() {
        return JSON_CONTEXT;
    }
    
    /*
     * (non-Javadoc)
     * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
     * com.google.gson.JsonSerializationContext)
     */
    @Override
    public JsonElement serialize(T valueParm, Type typeParm, JsonSerializationContext jsonContextParm) throws JsonParseException
    {
        throw new JsonParseException("JSON serialization not supported");
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
        throw new JsonParseException("JSON deserialization not supported");
    }

    /*
     * (non-Javadoc)
     * @see com.google.gson.InstanceCreator#createInstance(java.lang.reflect.Type)
     */
    @SuppressWarnings("unchecked")
    @Override
    public T createInstance(Type typeParm)
    {
        try
        {
            return ((T) typeParm.getClass().newInstance());
        }
        catch (InstantiationException e)
        {
            throw new JsonParseException("Default create instance not supported for " + typeParm.toString()
                    + ".  You need to add a default constructor or implement InstanceCreator<T> for this type.");
        }
        catch (IllegalAccessException e)
        {
            throw new JsonParseException("Default create instance not supported for " + typeParm.toString()
                    + ".  You need to add a default constructor or implement InstanceCreator<T> for this type.");
        }
    }

    
    /**
     * Gets the json node.
     *
     * @param jsonElementParm the json element parm
     * @return the json node
     */
    public IJsonNode toJsonNode(JsonElement jsonElementParm) {
        return new GsonNode(jsonElementParm);
    }
    

    /**
     * Gets the json element.
     *
     * @param jsonNodeParm the json node parm
     * @return the json element
     */
    public JsonElement fromJsonNode(IJsonNode jsonNodeParm) {
        return ((GsonNode)jsonNodeParm).getJsonElement();
    }

    
}
