package com.mob.rs.server.core.util;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;



public class Utils {
	

    /**
     * Returns true if the string is null OR every character in the string is a "whitespace" character.
     * 
     * @param string
     * @return true if the string is null OR every character in the string is a "whitespace" character.
     */
    public static final boolean isBlank(String string)
    {
        return StringUtils.isBlank(string);
    }	
    
    /**
     * Return true if any of the parms are null.
     * 
     * @param objectParm the object parm
     * @return true, if is null
     */
    public static boolean isAnyNull(Object... objectParms)
    {
        for (Object object : objectParms)
        {
            if (object == null)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Creates a set from the given collection. Uses LinkedHashSet to keep the order of the collection.
     * 
     * @param <E>
     * @param elements
     * @return list of elements
     */
    public static final <E> Set<E> collectionToSet(Collection<E> collection)
    {
        if (!isEmpty(collection))
        {
            return new LinkedHashSet<E>(collection);
        }
        return new LinkedHashSet<E>(0);
    }
    
    /**
     * Returns true if the collection is null or empty.
     * 
     * @param collection
     * @return true if the collection is null or empty.
     */
    public static final boolean isEmpty(Collection<?> collection)
    {
        return ((collection == null) || (collection.isEmpty()));
    }
    
    
    /**
     * Returns true if the collection is null or empty.
     * 
     * @param array
     * @return true if the collection is null or empty.
     */
    public static final boolean isEmpty(Object[] array)
    {
        return ((array == null) || (array.length == 0));
    }    
    
    /**
     * Returns true if the map is null or empty.
     * 
     * @param map
     * @return true if the map is null or empty.
     */
    public static final boolean isEmpty(Map<?, ?> map)
    {
        return ((map == null) || (map.isEmpty()));
    }    
    
    /**
     * Create a StringBuilder containing each object's toString equivalent delimited by the given delimiter.
     * 
     * @param map
     * @param objects 0 or more Objects
     * @param delimiter The delimiter that seperates each map entry.
     * @param buf Pre-allocated StringBuilder. If null a new StringBuilder will be created/returned.
     * @return a StringBuilder containing each object's toString equivalent delimited by the given delimiter.
     */
    public static final StringBuilder buildDelimitedString(Map<String, ?> map, String delimiter, StringBuilder buf)
    {
        if (buf == null)
        {
            buf = new StringBuilder();
        }

        if (!Utils.isEmpty(map) && (delimiter != null))
        {
            for (Map.Entry<String, ?> entry : map.entrySet())
            {
                buf.append(entry.getKey());

                Object value = entry.getValue();
                if (value != null)
                {
                    buf.append('=');

                    // Make sure we get "readable" array entries
                    if (value.getClass().isArray())
                    {
                        buildDelimitedString((Object[]) value, ",", buf);
                    }
                    else
                    {
                        buf.append(value);
                    }
                }

                buf.append(delimiter);
            }

            // Remove trailing delimiter
            if (buf.length() > 0)
            {
                buf.setLength(buf.length() - delimiter.length());
            }
        }

        return buf;
    }

    /**
     * Create a StringBuilder containing each object's toString equivalent delimited by the given delimiter.
     * 
     * @param objects 0 or more Objects
     * @param delimiter The delimiter that seperates the objects.
     * @param buf Pre-allocated StringBuilder. If null a new StringBuilder will be created/returned.
     * @return a StringBuilder containing each object's toString equivalent delimited by the given delimiter.
     */
    public static final StringBuilder buildDelimitedString(Object[] objects, String delimiter, StringBuilder buf)
    {
        if (buf == null)
        {
            buf = new StringBuilder();
        }

        if (!isEmpty(objects) && (delimiter != null))
        {
            for (Object obj : objects)
            {
                // Make sure we get "readable" array entries
                if ((obj != null) && obj.getClass().isArray())
                {
                    buf.append('[');
                    buildDelimitedString((Object[]) obj, ",", buf);
                    buf.append(']');
                }
                else
                {
                    buf.append(obj);
                }
                buf.append(delimiter);
            }

            // Remove trailing delimiter
            if (buf.length() > 0)
            {
                buf.setLength(buf.length() - delimiter.length());
            }
        }

        return buf;
    }

    

    /**
     * Create a StringBuilder containing each object's toString equivalent, comma-delimited.
     * 
     * @param objects 0 or more Objects
     * @param buf Pre-allocated StringBuilder. If null a new StringBuilder will be created/returned.
     * @return a StringBuilder containing each object's toString equivalent, comma-delimited.
     */
    public static final StringBuilder toStringHelper(Collection<?> objects, StringBuilder buf)
    {
        return buildDelimitedString(objects, ",", buf);
    }
    
    /**
     * Convenience method for overriding toString() representation in a consistent manner.
     * 
     * @param obj The object doing the toString()
     * @param attributes Name-value pairs representing attributes of the object that should be shown.
     * @return Format: <class-name>[attr1-name=attr1-value,...]
     */
    public static final StringBuilder toStringHelper(Object obj, Map<String, ?> attributes)
    {
        StringBuilder buf = new StringBuilder();

        if (obj != null)
        {
            // Discard the package portion of the class name.
            buf.append(obj.getClass().getName());
            buf.delete(0, buf.lastIndexOf(".") + 1);
        }

        if (!Utils.isEmpty(attributes))
        {
            buf.append('[');
            buildDelimitedString(attributes, ", ", buf);
            buf.append(']');
        }

        return buf;
    }

    
    
    /**
     * Create a StringBuilder containing each object's toString equivalent delimited by the given delimiter.
     * 
     * @param objects 0 or more Objects
     * @param delimiter The delimiter that seperates the objects.
     * @param buf Pre-allocated StringBuilder. If null a new StringBuilder will be created/returned.
     * @return a StringBuilder containing each object's toString equivalent delimited by the given delimiter.
     */
    public static final StringBuilder buildDelimitedString(Collection<?> objects, String delimiter, StringBuilder buf)
    {
        if (buf == null)
        {
            buf = new StringBuilder();
        }

        if ((objects != null) && (delimiter != null))
        {
            for (Object obj : objects)
            {
                // Make sure we get "readable" array entries
                if ((obj != null) && obj.getClass().isArray())
                {
                    buildDelimitedString((Object[]) obj, ",", buf);
                }
                else
                {
                    buf.append(obj);
                }
                buf.append(delimiter);
            }

            // Remove trailing delimiter
            if (buf.length() > 0)
            {
                buf.setLength(buf.length() - delimiter.length());
            }
        }

        return buf;
    }
    
    
    
}
