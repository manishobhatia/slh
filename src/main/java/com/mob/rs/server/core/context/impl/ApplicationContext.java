/**
 * 
 */
package com.mob.rs.server.core.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.mob.rs.server.core.context.api.IApplicationContext;
import com.mob.rs.server.core.context.api.IRequestContext;
import com.mob.rs.server.core.context.api.ISessionProxyResolver;
import com.mob.rs.server.core.util.resources.IMessageSource;
import com.mob.rs.server.web.support.metadata.Param;


/**
 * The Class ApplicationContext wrapper.
 * 
 * @author cupton
 */
@Component("ApplicationContext")
public final class ApplicationContext implements IApplicationContext, ApplicationContextAware
{

    /** The session proxy resolver. */
    @Autowired
    private ISessionProxyResolver sessionProxyResolver;

    /**
     * Instantiates a new application context. This should only be called by Spring during the component scan.
     */
    protected ApplicationContext()
    {
        super();
        INSTANCE = this;
    }

    /** The INSTANCE. */
    private static IApplicationContext INSTANCE;

    /** The Spring Application Context that is injected into this class on creation. */
    private static org.springframework.context.ApplicationContext APPLICATION_CONTEXT;

    /**
     * The request context thread local map is managed by a Servlet request listener that will construct the
     * IRequestContext on entrance of the request and destroy it on exit.
     */
    private static ThreadLocal<IRequestContext> REQUEST_CONTEXT = new ThreadLocal<IRequestContext>();

    /**
     * Gets the instance to the singleton application context. This should only be used in cases that
     * IApplicationContext cannot be injected into the dependent class using Spring.
     * 
     * @return the iinstance
     */
    public static final IApplicationContext getInstance()
    {
        return INSTANCE;
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IApplicationContext#getRequestContext()
     */
    @Override
    public IRequestContext getRequestContext()
    {
        IRequestContext localRequestContext = REQUEST_CONTEXT.get();
        if (localRequestContext == null)
        {
            //@@localRequestContext = new RequestContext(sessionProxyResolver);
            REQUEST_CONTEXT.set(localRequestContext);
        }
        return localRequestContext;
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IApplicationContext#invalidateRequestContext()
     */
    @Override
    public void invalidateRequestContext()
    {
        REQUEST_CONTEXT.set(null);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.
     * ApplicationContext)
     */
    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContextParm)
    {
        APPLICATION_CONTEXT = applicationContextParm;
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IApplicationContext#getMessageSource()
     */
    @Override
    public IMessageSource getMessageSource()
    {
        IMessageSource messageSource = getBean("RestMessageSource", IMessageSource.class);
        Param.ensureNotNull(messageSource, Param.MESSAGE_SOURCE);
        return messageSource;
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IApplicationContext#getBean(java.lang.Class)
     */
    @Override
    public <E> E getBean(Class<E> clazzParm)
    {
        return getBean(clazzParm.getSimpleName(), clazzParm);
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IApplicationContext#getBean(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E> E getBean(String beanNameParm, Class<E> clazzParm)
    {
        if (APPLICATION_CONTEXT == null)
        {
            return null;
        }
        return (E) APPLICATION_CONTEXT.getBean(beanNameParm, clazzParm);
    }

    /*
     * (non-Javadoc)
     * @see com.nice.iex.wfmws.core.context.api.IApplicationContext#getSpringContext()
     */
    @Override
    public org.springframework.context.ApplicationContext getSpringContext()
    {
        return APPLICATION_CONTEXT;
    }

    /**
     * Gets the session proxy resolver.
     * 
     * @return the session proxy resolver
     */
    public ISessionProxyResolver getSessionProxyResolver()
    {
        return sessionProxyResolver;
    }

    /**
     * Sets the session proxy resolver.
     * 
     * @param sessionProxyResolverParm the new session proxy resolver
     */
    public void setSessionProxyResolver(ISessionProxyResolver sessionProxyResolverParm)
    {
        sessionProxyResolver = sessionProxyResolverParm;
    }

}
