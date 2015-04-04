/**
 * @copyright 2012 IEX, A NICE Company
 * @author cupton
 * @version "$Id: Param.java 90493 2012-11-16 22:12:58Z mahmad $"
 */
package com.mob.rs.server.web.support.metadata;

import java.util.Collection;
import java.util.Map;

import com.mob.rs.server.core.exception.WebIllegalArgumentException;

/**
 * The Class Param. These are JAX-RS parameter names. The parameter names can be regular expressions that match url path
 * or query prameters.
 * 
 * @author cupton
 * @since Sep 13, 2012
 */
 
public final class Param
{
    //@@private static final TvLogger logger = new TvLogger(Param.class);

    /**
     * Block the constructor.
     */
    private Param()
    {
        super();
    }

    public static final String ADHERENCE = "adherence";
    public static final String CHECKPOINT = "checkpoint";
    public static final String CUSTOMER = "customer";
    public static final String LIMIT = "limit";
    public static final String OFFSET = "offset";
    public static final String ENTITY_TYPE = "entityType";
    public static final String ENTITY_OID = "entityOid";
    public static final String THRESHOLD = "threshold";
    public static final String THRESHOLD_OPERATION = "thresholdOperation";  

    public static final String PREFERRED = "preferred ";
    public static final String USER_NAME = "userName";
    public static final String AGENT_DATA_GROUP_OID = "agentDataGroupOid";
    public static final String AGENT_DATA_VALUE_OID = "agentDataValueOid";
    public static final String ACTIVITY_CODE_OID = "activityCodeOid";

    public static final String APPLICATION_CONTEXT = "applicationContext";
    public static final String SESSION_PROXY = "sessionProxy";
    public static final String USER = "user";
    public static final String CONFIGURATION = "configuration";
    public static final String CUSTOMER_NAME = "customerName";
    public static final String PREFERENCES = "preferences";
    public static final String SERVLET_REQUEST = "servletRequest";
    public static final String SERVLET_RESPONSE = "servletResponse";
    public static final String SESSION = "session";
    public static final String SORT_BY = "sortBy";
    public static final String MESSAGE_SOURCE = "messageSource";

    public static final String INPUT_STRING = "inputString";
    
    /**
     * Convenience method for ensuring a parameter is not null
     * 
     * @param arg the parameter to check
     * @param argName a name representing the parameter for logging
     * @throws WebIllegalArgumentException
     */
    public static void ensureNotNull(Object arg, String argName) throws WebIllegalArgumentException
    {
        if (arg == null)
        {
            WebIllegalArgumentException exception = new WebIllegalArgumentException(argName + " should not be null."); //$NON-NLS-1$
            //logger.error(exception.getMessage());
            throw exception;
        }
    }

    /**
     * Convenience method for ensuring a parameter is not null or empty
     * 
     * @param arg the parameter to check
     * @param argName a name representing the parameter for logging
     * @throws WebIllegalArgumentException
     */
    public static <E extends Object> void ensureNotNullOrEmpty(Collection<E> arg, String argName)
            throws WebIllegalArgumentException
    {
        if (arg == null || arg.isEmpty())
        {
            WebIllegalArgumentException exception = new WebIllegalArgumentException(argName
                    + " should not be null or empty."); //$NON-NLS-1$
            //logger.error(exception.getMessage());
            throw exception;
        }
    }

    /**
     * Convenience method for ensuring a parameter is not null or empty
     * 
     * @param arg the parameter to check
     * @param argName a name representing the parameter for logging
     * @throws WebIllegalArgumentException
     */
    public static <K extends Object, V extends Object> void ensureNotNullOrEmpty(Map<K, V> arg, String argName)
            throws WebIllegalArgumentException
    {
        if (arg == null || arg.isEmpty())
        {
            WebIllegalArgumentException exception = new WebIllegalArgumentException(argName
                    + " should not be null or empty."); //$NON-NLS-1$
            //logger.error(exception.getMessage());
            throw exception;
        }
    }

    /**
     * Convenience method for ensuring a parameter is not null or empty
     * 
     * @param arg the parameter to check
     * @param argName a name representing the parameter for logging
     * @throws WebIllegalArgumentException
     */
    public static void ensureNotNullOrEmpty(String arg, String argName) throws WebIllegalArgumentException
    {
        if (arg == null || arg.isEmpty())
        {
            WebIllegalArgumentException exception = new WebIllegalArgumentException(argName
                    + " should not be null or empty."); //$NON-NLS-1$
            //logger.error(exception.getMessage());
            throw exception;
        }
    }
}
