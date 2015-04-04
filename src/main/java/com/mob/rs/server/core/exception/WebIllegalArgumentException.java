package com.mob.rs.server.core.exception;
 
@SuppressWarnings("serial")
public class WebIllegalArgumentException extends IllegalArgumentException
{

    public WebIllegalArgumentException()
    {
        super();
    }

    public WebIllegalArgumentException(String messageParm, Throwable causeParm)
    {
        super(messageParm, causeParm);
    }

    public WebIllegalArgumentException(String sParm)
    {
        super(sParm);
    }

    public WebIllegalArgumentException(Throwable causeParm)
    {
        super(causeParm);
    }

}
