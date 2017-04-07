package com.csanford.boot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

/**
 *
 * @author Cody Sanford <cody.b.sanford@gmail.com>
 */
public class SocketHelper
{
    private static final Logger LOG = Logger.getLogger( SocketHelper.class.
	    getName() );
    private Socket socket;

    public SocketHelper( int port, String serverName )
    {
	try
	{
	    socket = new Socket( serverName, port );
	}
	catch ( IOException ex )
	{
	    LOG.severe( ex.getLocalizedMessage() );
	}
    }

    public PrintWriter getWriter()
    {
	PrintWriter writer = null;
	try
	{
	    writer = new PrintWriter( socket.getOutputStream(), true );
	}
	catch ( IOException ex )
	{
	    LOG.severe( ex.getLocalizedMessage() );
	}

	return writer;
    }

    public BufferedReader getReader()
    {
	BufferedReader reader = null;

	try
	{
	    reader = new BufferedReader( new InputStreamReader( socket.
		    getInputStream() ) );
	}
	catch ( IOException ex )
	{
	    LOG.severe( ex.getLocalizedMessage() );
	}

	return reader;
    }
}
