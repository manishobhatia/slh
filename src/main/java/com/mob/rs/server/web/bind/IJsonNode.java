/**
 * 
 */
package com.mob.rs.server.web.bind;

import java.util.Collection;
import java.util.Map;

/**
 * The Interface IJsonNode.
 *
 * @author cupton
 */
public interface IJsonNode
{

    
    /**
     * Adds the node.
     *
     * @param nameParm the name parm
     * @return the json element
     */
    public IJsonNode wrap(String nameParm);

    /**
     * Adds the property.
     * 
     * @param nameParm the name parm
     * @param valueParm the value parm
     * @return the json node (self)
     */
    public IJsonNode add(String nameParm, String valueParm);

    /**
     * Adds the object as a property.
     *
     * @param nameParm the name parm
     * @param valueParm the value parm
     * @return the json node
     */
    public IJsonNode add(String nameParm, Object valueParm);

    /**
     * Adds the null property.
     *
     * @param nameParm the name parm
     * @return the json node
     */
    public IJsonNode addNull(String nameParm);
    
    /**
     * Adds the array.
     *
     * @param <E> the element type
     * @param nameParm the name parm
     * @param collectionParm the collection parm
     * @return the json node
     */
    public <E> IJsonNode add(String nameParm, Collection<E> collectionParm);
    
    /**
     * Adds the array.
     *
     * @param <E> the element type
     * @param nameParm the name parm
     * @param collectionParm the collection parm
     * @return the json node
     */
    public <E> IJsonNode add(String nameParm, E[] collectionParm);
    
    /**
     * Adds the array.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param nameParm the name parm
     * @param collectionParm the collection parm
     * @return the json node
     */
    public <K,V> IJsonNode add(String nameParm, Map<K,V> collectionParm);
    
    /**
     * Convert this node to a JSON array.
     *
     * @param <E> the element type
     * @param collectionParm the collection parm
     * @return the json node
     */
    public <E> IJsonNode toArray(E[] collectionParm);

    /**
     * Convert this node to a JSON array.
     *
     * @param <E> the element type
     * @param collectionParm the collection parm
     * @return the json node
     */
    public <E> IJsonNode toArray(Collection<E> collectionParm);
    
    /**
     * To json.
     * 
     * @return the string
     */
    public String toJson();
}
