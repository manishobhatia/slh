/**
 * @copyright 2012 IEX, A NICE Company
 * @author cupton
 * @version "$Id: JsonUtil.java 90493 2012-11-16 22:12:58Z mahmad $"
 */
package com.mob.rs.server.web.bind;

/**
 * The Class JsonUtil.
 *
 * @author cupton
 * @since Oct 5, 2012
 */
public final class JsonUtil
{
    /**
     * Block the constructor.
     */
    private JsonUtil()
    {
        super();
    }
    
    /**
     * Append null properties.
     *
     * @param jsonNode the json node
     * @param propertyNames the property names
     */
    public static void addNullProperties(IJsonNode jsonNode, String...propertyNames) {
        for (String propertyName : propertyNames)
        {
            jsonNode.add(propertyName, (Object)null);
        }
    }
    
}

