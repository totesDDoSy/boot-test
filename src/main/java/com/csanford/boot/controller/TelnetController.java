package com.csanford.boot.controller;

import com.csanford.boot.utils.SocketHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Cody Sanford <cody.b.sanford@gmail.com>
 */
@Controller
@RequestMapping( "/telnet" )
public class TelnetController
{
    private static final Logger LOG = Logger.getLogger( TelnetController.class.
	    getName() );
    private SocketHelper telnetHelper;

    @Value( value = "${telnet.server}" )
    private final String server = "127.0.0.1";

	@RequestMapping( "" )
    public String display()
    {
		return "telnet";
    }

	@RequestMapping( "/{port}/{server}" )
	public String displayPing( Integer port, String server, Model model )
	{
		String output = "Error";
		try {
			output = doTelnetStuff( port, "" );
		}
		catch ( IOException ex )
		{
			LOG.log( Logger.Level.ERROR, ex);
		}
		model.addAttribute( "output", output );
		return "ping";
	}

    private String doTelnetStuff(Integer port, String... commands ) throws IOException
    {
		if ( telnetHelper == null )
		{
			telnetHelper = new SocketHelper( port, server );
		}
		PrintWriter telnetWriter = telnetHelper.getWriter();
		BufferedReader reader = telnetHelper.getReader();

		if( telnetWriter != null && reader != null )
		{
			telnetWriter.println( "ping" );
		} 
		else
		{
			return "ERROR";
		}
		return reader.readLine();
    }
}
