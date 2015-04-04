/**
 * @copyright 2012 IEX, A NICE Company
 * @author cupton
 * @version "$Id: Resource.java 90843 2012-12-07 17:14:28Z mbhatia $"
 */
package com.mob.rs.server.web.support.metadata;

/**
 * The Class Resource.
 * 
 * @author cupton
 * @since Sep 13, 2012
 */
 
public final class Resource
{

    /**
     * Block the constructor.
     */
    private Resource()
    {
        super();
    }
    
    /*
     * Icon resource
     */
    public static final String SCHEDULES = "/schedules";
    public static final String ACTIVITIES = "/activities";
    public static final String ACTIVITIES_ICON = "/{" + Param.ACTIVITY_CODE_OID + "}/icon";
    
    /*
     * Root resource
     */
    public static final String ROOT = "/";

    /*
     * Ping
     */
    public static final String PING = "/ping";
    
    public static final String FEED = "/feed";
    
    public static final String FEED_URL = "/feedUrl";
    
    public static final String PAGE = "/page";
    
    public static final String CATEGORY = "/category";
    
    public static final String SITE = "/site";
    
    public static final String SITE_FOR_LOCATION = "/siteForLocation";
    
    public static final String ALL_STATES = "/allStates";
    
    /*
     * Auth resources
     */
    public static final String AUTH = "/auth";
    public static final String AUTH_LOGIN = "/login/{" + Param.CUSTOMER + "}/{" + Param.USER_NAME + "}";
    public static final String AUTH_LOGOUT = "/logout";
    public static final String AUTH_USER = "/user";
    
    /*
     * System resources
     */
    public static final String SYSTEM = "/system";
    public static final String SYSTEM_BUILD_INFO = "/build/info";
    public static final String SYSTEM_STATS_SERVICES = "/services/stats";
    public static final String SYSTEM_PROPERTIES = "/properties";
    public static final String SYSTEM_CUSTOMERS = "/customers";
    public static final String SYSTEM_CONFIG = "/config";
    
    /*
     * Entity resources
     */
    public static final String ENTITIES = "/";
    
    
    /** The Constant ENTITIES_SELECTOR. This would list out the MUs or MUSets that the agent has permissions. */
    public static final String ENTITIES_SELECTOR = "/{" + Param.ENTITY_TYPE + "}/selector";

    /** The Constant ENTITIES_ADG. THis would list out the agent data groups for the enitiy. */
    public static final String ENTITIES_ADGS = "/{" + Param.ENTITY_TYPE + "}/{" + Param.ENTITY_OID + "}/adgs";

    /** The Constant ENTITIES_ADG. This would list out the agent data values for the selected agent data group. */
    public static final String ENTITIES_ADVS = "/{" + Param.ENTITY_TYPE + "}/{" + Param.ENTITY_OID + "}/adgs/{"
            + Param.AGENT_DATA_GROUP_OID + "}/advs";
    
    
    /*
     * RTA resources
     */
    public static final String RTA = "/rta";
    public static final String RTA_AGENT_STATE = "/agentstate";

}
