/**
 * 
 */
package com.mob.rs.server.web.bind.builder.json;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mob.rs.server.web.bind.IJsonMarshalable;
import com.mob.rs.server.web.bind.IJsonNode;
import com.mob.rs.server.web.bind.support.JsonMarshalable;
import com.mob.rs.server.web.bind.support.JsonNodeAdapter;
import com.mob.rs.server.web.support.metadata.CommonNames;
import com.mob.rs.server.web.bind.adapter.json.support.GsonJsonAdapter;
import com.mob.rs.server.web.bind.adapter.json.support.GsonJsonMarshalable;


/**
 * The Class GSON.
 *
 * @author cupton
 */
public final class GSON
{

    /** The Constant GSON. */
    public static final Gson GSON;
    static
    {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        //builder.registerTypeHierarchyAdapter(JsonMarshalable.class, new GsonJsonMarshalable<JsonMarshalable>());
        builder.registerTypeAdapter(IJsonMarshalable.class,  new GsonJsonMarshalable<JsonMarshalable>());
        
        // If I send a raw IJsonNode it will know how to convert it to JSON using this
        builder.registerTypeAdapter(GsonNode.class, new GsonJsonAdapter<IJsonNode>(new JsonNodeAdapter()));
        // All types that extend JsonAdapter will need to get registered.  
        //builder.registerTypeAdapter(UserPreferences.class, new GsonJsonAdapter<UserPreferences>(new JsonUserPreferences()));
                
        GSON = builder.create();
    }

    /**
     * Gets the single instance of GSON.
     * 
     * @return single instance of GSON
     */
    public static final Gson getGsonInstance()
    {
        return GSON;
    }

    
    
    /**
     * To json array.
     * 
     * @param <E> the element type
     * @param collectionParm the collection parm
     * @return the json array
     */
    public static <E> JsonArray toJsonArray(E[] collectionParm)
    {
        JsonArray array = new JsonArray();
        for (E object : collectionParm)
        {
            array.add(getGsonInstance().toJsonTree(object));
        }
        return array;
    }

    /**
     * To json array.
     * 
     * @param <E> the element type
     * @param collectionParm the collection parm
     * @return the json array
     */
    public static <E> JsonArray toJsonArray(Collection<E> collectionParm)
    {
        JsonArray array = new JsonArray();
        for (E object : collectionParm)
        {
            array.add(getGsonInstance().toJsonTree(object));
        }
        return array;
    }

    /**
     * To json array.
     * 
     * @param <E> the element type
     * @param collectionParm the collection parm
     * @return the json array
     */
    public static <K, V> JsonArray toJsonArray(Map<K, V> mapParm)
    {
        return toJsonArray(mapParm, CommonNames.KEY, CommonNames.VALUE);
    }

    /**
     * To json array.
     * 
     * @param <E> the element type
     * @param collectionParm the collection parm
     * @return the json array
     */
    public static <K, V> JsonArray toJsonArray(Map<K, V> mapParm, String keyNameParm, String valueNameParm)
    {
        JsonArray array = new JsonArray();
        for (Entry<K, V> entry : mapParm.entrySet())
        {
            JsonObject json = new JsonObject();
            json.add(keyNameParm, getGsonInstance().toJsonTree(entry.getKey()));
            json.add(valueNameParm, getGsonInstance().toJsonTree(entry.getValue()));
            array.add(json);
        }
        return array;
    }
    
    
}
