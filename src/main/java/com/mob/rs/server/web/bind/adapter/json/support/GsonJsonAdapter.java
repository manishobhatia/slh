/**
 * 
 */
package com.mob.rs.server.web.bind.adapter.json.support;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.mob.rs.server.web.bind.IJsonAdapter;
import com.mob.rs.server.web.bind.IJsonNode;
import com.mob.rs.server.web.bind.JsonMarshallingException;


/**
 * @author cupton
 */
public class GsonJsonAdapter<T extends Object> extends BaseGsonJson<T> 
{


    /** The json adapter. */
    private IJsonAdapter<T> jsonAdapter;
    
    /**
     * Instantiates a new gson json adapter.
     *
     * @param jsonAdapterParm the json adapter parm
     */
    public GsonJsonAdapter(IJsonAdapter<T> jsonAdapterParm)
    {
        super();
        jsonAdapter = jsonAdapterParm;
    }

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
            return fromJsonNode(jsonAdapter.toJson(valueParm, getJsonContext()));
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
            T createInstance = null;
            if (jsonNode != null)
            {
                createInstance = jsonAdapter.fromJson(jsonNode, getJsonContext());
            }
            return createInstance;
        }
        catch (JsonMarshallingException e)
        {
            throw new JsonParseException(e);
        }
    }




}
