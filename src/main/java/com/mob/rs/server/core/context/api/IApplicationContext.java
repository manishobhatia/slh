/**
 * 
 */
package com.mob.rs.server.core.context.api;

import org.springframework.context.ApplicationContext;

import com.mob.rs.server.core.util.resources.IMessageSource;



/**
 * The Interface IApplicationContext.
 * 
 * @author cupton
 */
public interface IApplicationContext
{

    /**
     * Gets the request context.
     * 
     * @return the request context
     */
    public IRequestContext getRequestContext();

    /**
     * Invalidate request context.
     */
    public void invalidateRequestContext();

    /**
     * Gets the message source.
     * 
     * @return the message source
     */
    public IMessageSource getMessageSource();

    /**
     * Gets the bean.
     * 
     * @param <E> the element type
     * @param clazzParm the clazz parm
     * @return the bean
     */
    public <E> E getBean(Class<E> clazzParm);

    /**
     * Gets the bean.
     * 
     * @param <E> the element type
     * @param beanNameParm the bean name parm
     * @param clazzParm the clazz parm
     * @return the bean
     */
    public <E> E getBean(String beanNameParm, Class<E> clazzParm);

    /**
     * Gets the spring context.
     * 
     * @return the spring context
     */
    public ApplicationContext getSpringContext();

}
