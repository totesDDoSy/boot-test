package com.csanford.boot.controller;

import com.csanford.boot.utils.SocketHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Cody Sanford <cody.b.sanford@gmail.com>
 */
@Controller
public class TelnetController
{
    private static final Logger LOG = Logger.getLogger( TelnetController.class.
	    getName() );
    private SocketHelper telnetHelper;

    @Value( value = "${telnet.server}" )
    private String server = "127.0.0.1";

    @RequestMapping( "/telnet" )
    public String display()
    {
	return "telnet";
    }

    private void doTelnetStuff() throws IOException
    {
	if ( telnetHelper == null )
	{
	    telnetHelper = new SocketHelper( 23, server );
	}
	PrintWriter telnetWriter = telnetHelper.getWriter();
	BufferedReader reader = telnetHelper.getReader();

	telnetWriter.println( "ping" );
	LOG.info( reader.readLine() );
    }
}
