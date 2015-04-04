/**
 * @copyright 2006 IEX, A Tekelec Company
 * @author kchennu2
 * @version "%I%, %G%"
 */
package com.mob.rs.server.core.util.resources;

import java.util.Locale;

/**
 * Interface to be implemented for resolving messages. Also provides parameterization and internationalization of
 * messages
 * 
 * @author kchennu2
 * @since Mar 22, 2006
 */
public interface IMessageSource
{
    /**
     * Returns the message found in the bundle in the requested Locale or defaultMessage if not found. If no bundle in
     * requested locale then bundle for English is used.
     * 
     * @param key key to look up (e.g. "invalid.range")
     * @param args array of arguments that will be filled in for params within the message, null if none
     * @param defaultMessage String to return if lookup fails
     * @param locale Locale in which message is requested
     * @return the message found in the bundle in the requested Locale or defaultMessage if not found. If no bundle in
     *         requested locale then bundle for English is used.
     */
    public String getMessage(String key, Object[] args, String defaultMessage, Locale locale);

    /**
     * Returns the message string for the specified key and locale. If none found, the defaultMessage will be returned.
     * If no bundle in requested locale then bundle for English is used.
     * 
     * @param key key to look up (e.g. "invalid.range")
     * @param locale Locale in which message is requested
     * @param args 0 or more arguments that represent tokenized parameters in the message (e.g. Minimum {0} must be be
     *            less than or equal to maximum {1})
     * @return the message found in the bundle in the requested Locale or defaultMessage if not found. If no bundle in
     *         requested locale then bundle for English is used.
     */
    public String getMessage(String key, Locale locale, String defaultMessage, Object... args);

    /**
     * Convenience method for getting localized versions of TotalView-common strings. These are strings in the bundle
     * files that are prefixed the same, e.g. "common.mu". If none found, the defaultMessage will be returned.
     * <p>
     * For example, to retreive the localized version of the common label for MU (stored in the bundle file as
     * "common.mu") the key would be "mu".
     * 
     * @param key key to look up (e.g. "mu")
     * @param locale Locale in which message is requested
     * @param args 0 or more arguments that represent tokenized parameters in the message (e.g. Minimum {0} must be be
     *            less than or equal to maximum {1})
     * @return the message found in the bundle in the requested Locale or defaultMessage if not found. If no bundle in
     *         requested locale then bundle for English is used.
     */
    public String getCommonMessage(String key, Locale locale, String defaultMessage, Object... args);
}
