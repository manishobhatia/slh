/**
 * 
 */
package com.mob.rs.server.web.bind.builder.json;

import java.util.Collection;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mob.rs.server.core.util.Utils;
import com.mob.rs.server.web.bind.IJsonNode;

/**
 * The Class GsonElement.
 * 
 * @author cupton
 */
public class GsonNode implements IJsonNode
{

    /**
     * Instantiates a new gson node.
     * 
     * @param nodeParm the node parm
     */
    public GsonNode(JsonElement nodeParm)
    {
        super();
        node = nodeParm;
    }

    /**
     * Instantiates a new gson node.
     */
    public GsonNode()
    {
        super();
    }

    /** The json object. */
    private JsonElement node;


    /* (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.web.bind.IJsonNode#wrap(java.lang.String)
     */
    @Override
    public IJsonNode wrap(String nameParm)
    {
        JsonObject parentNode = new JsonObject();
        parentNode.add(nameParm, getNode());
        return new GsonNode(parentNode);
    }


    /* (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.web.bind.IJsonNode#add(java.lang.String, java.lang.String)
     */
    @Override
    public IJsonNode add(String nameParm, String valueParm)
    {
        if (getNode().isJsonObject())
        {
            ((JsonObject) getNode()).addProperty(nameParm, valueParm);
        }
        return this;
    }

    /* (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.web.bind.IJsonNode#addNull(java.lang.String)
     */
    @Override
    public IJsonNode addNull(String nameParm)
    {
        return add(nameParm, (Object)null);
    }
    

    /* (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.web.bind.IJsonNode#add(java.lang.String, java.lang.Object)
     */
    @Override
    public IJsonNode add(String nameParm, Object valueParm)
    {
        if (getNode().isJsonObject())
        {
            ((JsonObject) getNode()).add(nameParm, GSON.getGsonInstance().toJsonTree(valueParm));
        }
        return this;
    }


    /* (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.web.bind.IJsonNode#add(java.lang.String, E[])
     */
    @Override
    public <E> IJsonNode add(String nameParm, E[] collectionParm)
    {
        if (getNode().isJsonObject() && !Utils.isEmpty(collectionParm))
        {
            ((JsonObject) getNode()).add(nameParm, GSON.toJsonArray(collectionParm));
        }
        return this;
    }


    /* (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.web.bind.IJsonNode#toArray(E[])
     */
    @Override
    public <E> IJsonNode toArray(E[] collectionParm)
    {
        this.node = GSON.toJsonArray(collectionParm);
        return this;
    }

    /* (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.web.bind.IJsonNode#toArray(java.util.Collection)
     */
    @Override
    public <E> IJsonNode toArray(Collection<E> collectionParm)
    {
        this.node = GSON.toJsonArray(collectionParm);
        return this;
    }


    /* (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.web.bind.IJsonNode#add(java.lang.String, java.util.Collection)
     */
    @Override
    public <E> IJsonNode add(String nameParm, Collection<E> collectionParm)
    {
        if (getNode().isJsonObject() && !Utils.isEmpty(collectionParm))
        {
            ((JsonObject) getNode()).add(nameParm, GSON.toJsonArray(collectionParm));
        }
        return this;
    }


    /* (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.web.bind.IJsonNode#add(java.lang.String, java.util.Map)
     */
    @Override
    public <K, V> IJsonNode add(String nameParm, Map<K, V> collectionParm)
    {
        if (getNode().isJsonObject() && !Utils.isEmpty(collectionParm))
        {
            ((JsonObject) getNode()).add(nameParm, GSON.toJsonArray(collectionParm));
        }
        return this;
    }

    /**
     * Gets the node.
     * 
     * @return the node
     */
    protected JsonElement getNode()
    {
        return (node == null) ? node = new JsonObject() : node;
    }


    /* (non-Javadoc)
     * @see com.iex.tv.smartsync.rs.server.web.bind.IJsonNode#toJson()
     */
    @Override
    public String toJson()
    {
        return GSON.getGsonInstance().toJson(getNode());
    }

    /**
     * Gets the json element.
     * 
     * @return the json element
     */
    public JsonElement getJsonElement()
    {
        return getNode();
    }



}
