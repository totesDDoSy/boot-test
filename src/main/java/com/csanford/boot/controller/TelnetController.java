package com.csanford.boot.controller;

import com.csanford.boot.database.Outlet;
import com.csanford.boot.database.OutletRepository;
import com.csanford.boot.database.RPC;
import com.csanford.boot.database.RPCRepository;
import com.csanford.boot.utils.SocketHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    RPCRepository rpcRepository;

    @Autowired
    OutletRepository outletRepository;

    @Value( value = "${telnet.server}" )
    private final String server = "127.0.0.1";

    @RequestMapping( "" )
    public String display( Model model )
    {
	// Display the list of RPCs
	rpcRepository.save( new RPC( 1L, "rpc1", "127.0.0.1", "user1",
		"password" ) );
	rpcRepository.save( new RPC( 2L, "rpc2", "192.168.1.0", "user",
		"pass" ) );

	ArrayList<RPC> rpcs = new ArrayList<>();
	rpcRepository.findAll().forEach( rpcs::add );
	model.addAttribute( "rpclist", rpcs );
	return "telnet";
    }

    @RequestMapping( "/{rpcid}/sockets" )
    public String displaySockets( Long rpcId, Model model )
    {
	outletRepository.save( new Outlet( 1L, 1L, "outlet 1", Boolean.FALSE ) );
	outletRepository.save( new Outlet( 2L, 1L, "outlet 2", Boolean.FALSE ) );

	ArrayList<Outlet> outlets = new ArrayList<>();
	outletRepository.findByRpcId( rpcId ).forEach(
		outlets::add );
	model.addAttribute( "outletlist", outlets );
	return "sockets";
    }

    @RequestMapping( "/{port}/{server}" )
    public String displayPing( Integer port, String server, Model model )
    {
	String output = "Error";
	try
	{
	    output = doTelnetStuff( 23, "192.168.99.24", "" );
	}
	catch ( IOException ex )
	{
	    LOG.log( Logger.Level.ERROR, ex );
	}
	model.addAttribute( "output", output );
	return "ping";
    }

    private String doTelnetStuff( Integer port, String rpc_server,
	    String... commands ) throws IOException
    {
	if ( telnetHelper == null )
	{
	    telnetHelper = new SocketHelper( port, rpc_server );
	}
	BufferedWriter writer = telnetHelper.getWriter();
	BufferedReader reader = telnetHelper.getReader();

	if ( writer != null && reader != null )
	{
	    //	telnetWriter.println( "ping" );
	}
	else
	{
	    return "ERROR";
	}

	StringJoiner joiner = new StringJoiner( " " );
	String line;
	do
	{
	    line = reader.readLine();
	    joiner.add( line );
	    System.out.println( line );
	} while ( !line.contains( "6)...Logout" ) );
	return joiner.toString();
    }
}
