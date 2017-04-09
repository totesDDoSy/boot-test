package com.csanford.boot.controller;

import com.csanford.boot.utils.SocketHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
			output = doTelnetStuff( 23, "192.168.99.24", "" );
		}
		catch ( IOException ex )
		{
			LOG.log( Logger.Level.ERROR, ex);
		}
		model.addAttribute( "output", output );
		return "ping";
	}

    private String doTelnetStuff(Integer port, String rpc_server, String... commands ) throws IOException
    {
		if ( telnetHelper == null )
		{
			telnetHelper = new SocketHelper( port, rpc_server );
		}
		BufferedWriter writer = telnetHelper.getWriter();
		BufferedReader reader = telnetHelper.getReader();

		if( writer != null && reader != null )
		{
		//	telnetWriter.println( "ping" );
		} 
		else
		{
			return "ERROR";
		}
                
                StringBuilder builder = new StringBuilder();
                for( int i = 0; i < 3; i++ )
                {
                    builder.append( reader.readLine() );
                }
		return builder.toString();
    }
}
